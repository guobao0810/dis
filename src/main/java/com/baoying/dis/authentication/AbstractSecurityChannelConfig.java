package com.baoying.dis.authentication;

import com.baoying.dis.authentication.hanlder.AuthenticationSuccessHandler;
import com.baoying.dis.constant.SecurityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/14 16:50
 * @Version: 1.0
 */
public class AbstractSecurityChannelConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 基础账号密码认证
     * @param http
     * @throws Exception
     */
    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.formLogin()
                .loginProcessingUrl(SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .loginPage(SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
    }
}
