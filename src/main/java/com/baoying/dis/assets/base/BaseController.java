package com.baoying.dis.assets.base;

import com.baoying.dis.assets.utils.WebUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Stream;

public class BaseController {
    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

    @Resource
    protected WebApplicationContext wac;

    /**
     * 查找指定的 Cookie
     *
     * @param name
     * @return
     */
    protected Optional<Cookie> findCookie(String name) {
        return Optional.ofNullable(WebUtils.getCookie(request, name));
    }

    /**
     * 设置浏览器端不缓存此次响应结果
     */
    protected void nocache() {
        WebUtil.nocache(response);
    }

    /**
     * request.getParameter()
     *
     * @param name
     * @return
     */
    protected String getParam(String name) {
        return request.getParameter(name);
    }

    /**
     * trim(request.getParameter())
     *
     * @param name
     * @return
     */
    protected String getTrimParam(String name) {
        return StringUtils.trim(getParam(name));
    }

    /**
     * request.getParameterNames()
     *
     * @return
     */
    protected List<String> getParamNames() {
        Enumeration<String> names = request.getParameterNames();
        List<String> list = new ArrayList<>();
        while (names.hasMoreElements()) {
            list.add(names.nextElement());
        }
        return Collections.unmodifiableList(list);
    }

    /**
     * request.getParameterValues()
     *
     * @param name
     * @return
     */
    protected String[] getParamArray(String name) {
        return request.getParameterValues(name);
    }

    /**
     * trim(request.getParameterValues())
     *
     * @param name
     * @return
     */
    protected String[] getTrimParamArray(String name) {
        String[] values = getParamArray(name);
        if (ArrayUtils.isEmpty(values)) {
            return values;
        }
        return Stream.of(values).map(StringUtils::trim).toArray(String[]::new);
    }

    /**
     * request.getAttribute()
     *
     * @param name
     * @param <T>
     * @return
     */
    protected <T> Optional<T> getAttr(String name) {
        return Optional.ofNullable(((T) request.getAttribute(name)));
    }

    /**
     * request.getAttributeNames()
     *
     * @return
     */
    protected List<String> getAttrNames() {
        Enumeration<String> names = request.getAttributeNames();
        List<String> list = new ArrayList<>();
        while (names.hasMoreElements()) {
            list.add(names.nextElement());
        }
        return Collections.unmodifiableList(list);
    }
}
