package com.baoying.dis.authentication.hanlder;

import com.baoying.dis.assets.utils.JsonUtil;
import com.baoying.dis.entity.SysRole;
import com.baoying.dis.result.ResultBean;
import com.baoying.dis.vo.LoginUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/14 16:52
 * @Version: 1.0
 */
@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        logger.info("登录成功,当前用户:" + authentication.getPrincipal() + ",当前时间:" + LocalDateTime.now());
        Set<SysRole> roles = ((LoginUser) authentication.getPrincipal()).getRoles();
        response.setContentType(JsonUtil.REPONSE_JSON_TYPE);
        ResultBean resultBean = new ResultBean();
        resultBean.setCode(HttpStatus.OK.value());
        resultBean.setMessage("登录成功");
        resultBean.setData(((LoginUser)authentication.getPrincipal()).getRoles());
        response.getWriter().write(objectMapper.writeValueAsString(resultBean));
    }
}
