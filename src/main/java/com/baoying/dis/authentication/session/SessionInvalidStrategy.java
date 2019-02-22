package com.baoying.dis.authentication.session;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * session失效处理器
 * @author: zhangchenglong
 * @Date; 2019/2/18 11:09
 * @Version: 1.0
 */
@Component("sessionInvalidStrategy")
public class SessionInvalidStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    public SessionInvalidStrategy(){

    }

    public SessionInvalidStrategy(String sessionInvalidUrl){
        super(sessionInvalidUrl);
    }

    public void onInvalidSessionDetected(HttpServletRequest request,
                                         HttpServletResponse response) throws IOException {
        onSessionInvalid(request,response);
    }

}
