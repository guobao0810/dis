package com.baoying.dis.assets.utils;

import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public abstract class WebUtil {
    /**
     * 将 {@link ServletRequest} 强转为 {@link HttpServletRequest}
     *
     * @param request
     * @return
     */
    public static HttpServletRequest toHttp(ServletRequest request) {
        return ((HttpServletRequest) request);
    }

    /**
     * 将 {@link ServletResponse} 强转为 {@link HttpServletResponse}
     *
     * @param response
     * @return
     */
    public static HttpServletResponse toHttp(ServletResponse response) {
        return ((HttpServletResponse) response);
    }

    /**
     * 获取 Cookie 对象
     *
     * @param request
     * @param name
     * @return
     */
    public static Optional<Cookie> findCookie(HttpServletRequest request, String name) {
        return Optional.ofNullable(WebUtils.getCookie(request, name));
    }

    /**
     * 通知浏览器 no-cache
     */
    public static void nocache(HttpServletResponse response) {
        // HTTP 1.1.
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        // HTTP 1.0.
        response.setHeader("Pragma", "no-cache");
        // Proxies.
        response.setDateHeader("Expires", 0);
    }
}
