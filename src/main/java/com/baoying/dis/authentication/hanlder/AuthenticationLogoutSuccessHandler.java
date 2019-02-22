package com.baoying.dis.authentication.hanlder;

import com.baoying.dis.assets.utils.JsonUtil;
import com.baoying.dis.constant.SecurityConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/15 17:06
 * @Version: 1.0
 */
@Component("authenticationlogoutSuccessHandler")
public class AuthenticationLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(AuthenticationFailureHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("当前用户:"+authentication.getPrincipal()+"登出成功!当前时间:"+ LocalDateTime.now());
        response.sendRedirect(SecurityConstant.DEFAULT_UNLOGIN_URL);
    }
}
