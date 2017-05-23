/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luosoy.common.interceptor;

import com.luosoy.frame.thread.ThreadIdUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author luozp
 */
public class CommonParamInterceptor implements HandlerInterceptor {

    @Value("${site.title}")
    private String title;

    @Value("${site.name}")
    private String name;

    @Value("${site.keywords}")
    private String keywords;
    
    @Value("${site.description}")
    private String description;

    @Value("${site.url}")
    private String url;

    @Value("${site.copyright}")
    private String copyright;

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ThreadIdUtil.generateThreadUUId();
        request.setAttribute("title", title);
        request.setAttribute("name", name);
        request.setAttribute("keywords", keywords);
        request.setAttribute("description", description);
        request.setAttribute("url", url);
        request.setAttribute("copyright", copyright);
        request.setAttribute("search", "");
        request.setAttribute("ctx", request.getContextPath());
        request.setAttribute("book", request.getContextPath()+"/novel/book");
        request.setAttribute("stc", request.getContextPath() + "/assets");
        return true;
    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        ThreadIdUtil.removeThreadUUId();
    }
}
