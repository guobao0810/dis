package com.baoying.dis.entity.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

/**
 * @author: zhangchenglong
 * @Date; 2019/2/15 13:24
 * @Version: 1.0
 */
@Setter
@Getter
@ToString
public class BaseEntity<T> {

    private Date createTime;

    private Date updateTime;

    private Integer status;

}
