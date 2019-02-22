package com.baoying.dis.authentication.session;

import com.baoying.dis.assets.utils.JsonUtil;
import com.baoying.dis.constant.SecurityConstant;
import com.baoying.dis.result.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 抽象session处理器
 * @author: zhangchenglong
 * @Date; 2019/2/18 10:32
 * @Version: 1.0
 */
public class AbstractSessionStrategy {

    private Logger logger = LoggerFactory.getLogger(AbstractSessionStrategy.class);

    /**
     * 跳转url
     */
    private String destinationUrl = SecurityConstant.DEFAULT_UNLOGIN_URL;

    /**
     * 重定向策略
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public AbstractSessionStrategy(){

    }

    public AbstractSessionStrategy(String invalidSessionUrl){
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl),"url 必须以 / 开头,或者 http(s)");
        this.destinationUrl = invalidSessionUrl;
    }

    protected void  onSessionInvalid(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String sourceUrl = request.getRequestURI();
        String tragetUrl;
        if (StringUtils.endsWithIgnoreCase(sourceUrl,".html")) {
            tragetUrl = destinationUrl + ".html";
            logger.info("session失效,跳转到" + tragetUrl);
            redirectStrategy.sendRedirect(request,response,tragetUrl);
        }else{
            ResultBean resultBean = new ResultBean();
            resultBean.setCode(500);
            resultBean.setMessage("session已经失效,请您重新登录");
            response.setContentType(JsonUtil.REPONSE_JSON_TYPE);
            response.getWriter().write(JsonUtil.toJson(resultBean));
        }
    }


}
