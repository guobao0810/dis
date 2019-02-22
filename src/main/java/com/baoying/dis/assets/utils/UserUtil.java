package com.baoying.dis.assets.utils;

import com.baoying.dis.vo.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/15 14:35
 * @Version: 1.0
 */
public class UserUtil {

    /**
     * 得到当前用户
     * @return
     */
    public static LoginUser getCurrentUser(){
        return (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
