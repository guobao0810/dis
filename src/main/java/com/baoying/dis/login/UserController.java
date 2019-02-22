package com.baoying.dis.login;

import com.baoying.dis.annotation.LogAnnotation;
import com.baoying.dis.constant.SecurityConstant;
import com.baoying.dis.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/18 15:46
 * @Version: 1.0
 */
@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @GetMapping(SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL)
    @LogAnnotation
    @ResponseBody
    public ResultBean authentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest saveRequest = requestCache.getRequest(request, response);
        if(saveRequest != null){
            String tragetUrl = saveRequest.getRedirectUrl();
            logger.info("引发跳转的请求是:"+tragetUrl);
            if(StringUtils.endsWithIgnoreCase(tragetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,SecurityConstant.DEFAULT_UNLOGIN_URL);
            }
        }
        return new ResultBean("请您自己到登录页");
    }

    @RequestMapping(value = {"/login","/login.html"})
    public String login(){
        return "login";
    }

}
