package com.baoying.dis.entity;

import com.baoying.dis.vo.LoginUser;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/15 10:47
 * @Version: 1.0
 */
@Entity
@Table(name = "sys_role")
public class SysRole {

    /**
     * 角色id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<LoginUser> sysUserList;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<LoginUser> getSysUserList() {
        return sysUserList;
    }

    public void setSysUserList(Set<LoginUser> sysUserList) {
        this.sysUserList = sysUserList;
    }
}
