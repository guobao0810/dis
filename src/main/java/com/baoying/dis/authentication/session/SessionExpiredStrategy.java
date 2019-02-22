package com.baoying.dis.authentication.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 并发登录导致session失效
 * @author: zhangchenglong
 * @Date; 2019/2/18 13:39
 * @Version: 1.0
 */
@Component("expiredSessionStrategy")
public class SessionExpiredStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public SessionExpiredStrategy(){

    }

    public SessionExpiredStrategy(String invalidSessionUrl){
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(),event.getResponse());
    }
}
