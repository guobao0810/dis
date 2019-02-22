package com.baoying.dis.login.repository;

import com.baoying.dis.assets.repository.BaseRepository;
import com.baoying.dis.entity.SysRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/19 11:21
 * @Version: 1.0
 */
@Repository
public interface RoleRepository extends BaseRepository<SysRole> {

    @Query(nativeQuery = true,value = "select DISTINCT r.* from sys_user_role ur left join sys_role r on ur.user_id=r.role_id where ur.user_id=?1")
    Set<SysRole> findRoleByUserId(Integer userId);
}
