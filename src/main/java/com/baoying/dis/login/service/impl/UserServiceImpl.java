package com.baoying.dis.login.service.impl;

import com.baoying.dis.entity.SysRole;
import com.baoying.dis.entity.SysUser;
import com.baoying.dis.login.repository.RoleRepository;
import com.baoying.dis.login.repository.UserDetailsRepository;
import com.baoying.dis.login.repository.UserRepository;
import com.baoying.dis.login.service.UserService;
import com.baoying.dis.vo.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/18 16:02
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public LoginUser loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userDetailsRepository.findUserByUsername(username);
        if(StringUtils.isEmpty(sysUser)){
            throw new UsernameNotFoundException("用户名不存在");
        }
        Set<SysRole> sysRoles = roleRepository.findRoleByUserId(sysUser.getUserId());
        //TODO 业务异常待处理
        LoginUser loginUser = new LoginUser();
        loginUser.setRoles(sysRoles);
        BeanUtils.copyProperties(sysUser,loginUser);
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        for(SysRole sysRole : sysRoles){
            simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(sysRole.getRoleName()));
        }
        return loginUser;
    }
}
