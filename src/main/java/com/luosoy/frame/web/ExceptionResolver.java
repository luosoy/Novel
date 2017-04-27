package com.luosoy.frame.web;

import com.luosoy.frame.data.JsonUtils;
import com.luosoy.frame.exception.BusinessException;
import com.luosoy.frame.exception.ValidationException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

public class ExceptionResolver extends AbstractHandlerExceptionResolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);
    
    public static final String SYSTEM_ERROR = "sys.error";

   
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        ModelAndView model = new ModelAndView();
        if (!(ex instanceof ValidationException) && !(ex instanceof BusinessException)) {
            LOGGER.error("请求URL: {}  出现错误!", request.getRequestURI());
            LOGGER.error("请求输入Parameter参数为  {}", JsonUtils.toJson(request.getParameterMap()));
            LOGGER.error("异常为: ", ex);
        }
        
        if (WebUtil.isAjaxRequest(request)) {
            try {
                response.setContentType("application/json;charset=UTF-8");
                Response<?> resp = null;
                if (ex instanceof ValidationException) {
                    ValidationException validationException = (ValidationException) ex;
                    if (!validationException.getValidationResult().isValidationFail()) {
                        resp = Response.error(validationException.getValidationResult().getGlobalMessageList().get(0));
                    } else {
                        resp = Response.error(SYSTEM_ERROR);
                    }
                } else if (ex instanceof BusinessException) {
                    BusinessException bException = (BusinessException) ex;
                    // 业务异常返回
                    if (!StringUtils.isEmpty(bException.getMessage())) {
                        // 错误信息不为空，则返回错误信息
                        resp = Response.error(bException.getMessage());
                    } else {
                        // 否则返回错误代码
                        resp = Response.error(bException.getErrorCode());
                    }
                } else {
                    resp = Response.error(SYSTEM_ERROR);
                }
                response.setStatus(HttpStatus.OK.value());
                JsonUtils.writeValue(response.getWriter(), resp);
            } catch (IOException e) {
                LOGGER.error("Failed to serialize the object to json for exception handling.", e);
            }
        } else {
            response.setContentType("text/html;charset=UTF-8");
            model.addObject("exception", ex);
            if (ex instanceof BusinessException) {
                model.setViewName("error/500_biz");
            } else {
                model.setViewName("error/500_com");
            }
        }
        return model;
    }
}
