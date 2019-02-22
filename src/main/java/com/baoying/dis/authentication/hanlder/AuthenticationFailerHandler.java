package com.baoying.dis.authentication.hanlder;

import com.baoying.dis.assets.utils.JsonUtil;
import com.baoying.dis.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/14 16:54
 * @Version: 1.0
 */
@Component("authenticationFailerHandler")
public class AuthenticationFailerHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
       logger.info("登录失败,提示信息:"+exception.getMessage());
       response.setContentType(JsonUtil.REPONSE_JSON_TYPE);
       ResultBean resultBean = new ResultBean();
       resultBean.setCode(500);
       resultBean.setMessage("登录失败，请您输入正确的账号和密码");
       response.getWriter().write(JsonUtil.toJson(resultBean));
    }
}
