package com.baoying.dis.login.repository;

import com.baoying.dis.assets.repository.BaseRepository;
import com.baoying.dis.entity.SysUser;
import com.baoying.dis.vo.LoginUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/18 16:06
 * @Version: 1.0
 */
@Repository
public interface UserDetailsRepository extends BaseRepository<SysUser> {

    @Query(value = "SELECT u.user_id,u.username,u.password,u.status,u.create_time,u.update_time,u.dtype FROM sys_user u WHERE u.username= :username",nativeQuery = true)
    LoginUser findUserByUsername(@Param("username") String username);

}
