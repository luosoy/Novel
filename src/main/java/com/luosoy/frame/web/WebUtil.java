package com.luosoy.frame.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class WebUtil {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebUtil.class);

    /**
     * The temp_dir.
     */
    private static String temp_dir;

    /**
     * Gets the thread request.
     *
     * @return the thread request
     */
    public HttpServletRequest getThreadRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * Gets the thread session.
     *
     * @return the thread session
     */
    public HttpSession getThreadSession() {
        return getThreadRequest().getSession(true);
    }

    /**
     * Checks if is ajax request.
     *
     * @return true, if is ajax request
     */
    public boolean isAjaxRequest() {
        return isAjaxRequest(getThreadRequest());
    }

    /**
     * Checks if is ajax request.
     *
     * @param request the request
     * @return true, if is ajax request
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }

    /**
     * Gets the context path.
     *
     * @param request the request
     * @return the context path
     */
    public String getContextPath(HttpServletRequest request) {
        return request.getContextPath();
    }

    /**
     * Gets the full url based on.
     *
     * @param path the path
     * @return the full url based on
     */
    public String getFullUrlBasedOn(String path) {
        StringBuilder targetUrl = new StringBuilder();
        if (path.startsWith("/")) {
            // Do not apply context path to relative URLs.
            targetUrl.append(getThreadRequest().getContextPath());
        }
        targetUrl.append(path);
        return targetUrl.toString();
    }

    /**
     * Re login.
     *
     * @param request the request
     * @param response the response
     * @param basePath the base path
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static void reLogin(HttpServletRequest request, HttpServletResponse response, String basePath)
            throws IOException {
        LOGGER.error("==============严重错误：SESSION中的登录信息已经过期=======================");
        String xmlHttpRequest = request.getHeader("x-requested-with");
        LOGGER.info("xmlHttpRequest=" + xmlHttpRequest);
        if (!StringUtils.isEmpty(xmlHttpRequest) && "XMLHttpRequest".equalsIgnoreCase(xmlHttpRequest)) {
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            out.print("sessionTimeOut");
            out.close();
        } else {
            response.sendRedirect(basePath);// NOSONAR
        }
    }

    /**
     * Gets the content path.
     *
     * @return the content path
     */
    public static String getContentPath() {
        return getRequest().getContextPath();
    }

    /**
     * Gets the server path.
     *
     * @return the server path
     */
    public static String getServerPath() {
        HttpServletRequest req = getRequest();
        StringBuilder sb = new StringBuilder();
        sb.append(req.getScheme());
        sb.append("://");
        try {
            sb.append(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            LOGGER.info(e.getMessage(), e);
            sb.append(req.getServerName());
        }
        sb.append(":");
        sb.append(req.getServerPort());
        sb.append(req.getContextPath());
        return sb.toString();
    }

    /**
     * Gets the doc base path.
     *
     * @return the doc base path
     */
    public static String getDocBasePath() {
        HttpServletRequest req = getRequest();
        HttpSession sess = req.getSession(false);
        if (null == sess) {
            return "";
        } else {
            return sess.getServletContext().getRealPath("/");
        }
    }

    /**
     * Gets the temp dir path.
     *
     * @return the temp dir path
     */
    public static String getTempDirPath() {
        if (StringUtils.isEmpty(temp_dir)) {
            temp_dir = System.getProperty("java.io.tmpdir") + File.separatorChar + getContentPath().substring(1)
                    + File.separatorChar;
        }
        return temp_dir;
    }

    /**
     * Gets the request.
     *
     * @return the request
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest();
    }

    /**
     * Gets the request path.
     *
     * @param req the req
     * @return the request path
     */
    public static String getRequestPath(HttpServletRequest req) {
        return req.getRequestURI().substring(req.getContextPath().length());
    }

    /**
     * Gets the request path.
     *
     * @return the request path
     */
    public static String getRequestPath() {
        HttpServletRequest req = getRequest();
        return req.getRequestURI().substring(req.getContextPath().length());
    }

    /**
     * A convenience method that merely casts the incoming <code>ServletRequest</code> to an
     * <code>HttpServletRequest</code>:
     * <p/>
     * <code>return (HttpServletRequest)request;</code>
     * <p/>
     * Logic could be changed in the future for logging or throwing an meaningful exception in non
     * HTTP request environments (e.g. Portlet API).
     *
     * @param request the incoming ServletRequest
     * @return the <code>request</code> argument casted to an <code>HttpServletRequest</code>.
     */
    public static HttpServletRequest toHttp(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    /**
     * A convenience method that merely casts the incoming <code>ServletResponse</code> to an
     * <code>HttpServletResponse</code>:
     * <p/>
     * <code>return (HttpServletResponse)response;</code>
     * <p/>
     * Logic could be changed in the future for logging or throwing an meaningful exception in non
     * HTTP request environments (e.g. Portlet API).
     *
     * @param response the outgoing ServletResponse
     * @return the <code>response</code> argument casted to an <code>HttpServletResponse</code>.
     */
    public static HttpServletResponse toHttp(ServletResponse response) {
        return (HttpServletResponse) response;
    }

    /**
     *
     * @Title: getIpAddr
     * @Description: 获取IP地址
     * @param request
     * @return String 返回类型
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割   
        if (ip != null && ip.length() > 15) { // "***.***.***.***".length() = 15
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
}
