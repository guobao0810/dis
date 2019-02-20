package com.baoying.dis.assets.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 约定实体类
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public abstract class BaseEntity implements Serializable {
    @CreatedDate
    @Column
    private Date createTime;

    @LastModifiedDate
    @Column
    private Date updateTime;
}
