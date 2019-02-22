package com.baoying.dis.vo;

import com.baoying.dis.entity.SysRole;
import com.baoying.dis.entity.SysUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/15 13:30
 * @Version: 1.0
 */
@Entity
@Setter
@Getter
public class LoginUser extends SysUser implements UserDetails {

    @ManyToMany
    @JoinTable(name = "sys_user_role",joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<SysRole> roles;

    /**
     *  所有的角色放到集合中
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        Set<SysRole> roles = getRoles();
        for (SysRole role : roles) {
            grantedAuthorityList.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return grantedAuthorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }
}
