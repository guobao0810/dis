package com.baoying.dis.authentication.hanlder;

import com.baoying.dis.assets.utils.JsonUtil;
import com.baoying.dis.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/15 17:03
 * @Version: 1.0
 */
@Component("authenticationAccessDeniedHandler")
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {

    private Logger logger = LoggerFactory.getLogger(AuthenticationFailureHandler.class);
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(403);
        resultBean.setMessage("没有权限访问");
        response.setContentType(JsonUtil.REPONSE_JSON_TYPE);
        response.getWriter().write(JsonUtil.toJson(resultBean));
    }

}
