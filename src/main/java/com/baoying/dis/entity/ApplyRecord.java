package com.baoying.dis.entity;

import com.baoying.dis.assets.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;

/**
 * 申请明细记录
 */
@Entity
@Table(name = "t_apply_record")
@Setter @Getter
public class ApplyRecord extends BaseEntity {
    // 月份 1928-09
    @Column(length = 15)
    private String month;
    // ID （客户 ID）
    @Column(length = 20)
    private String acountId;
    // 序号 （证明文件上后缀的序号）
    @Column(length = 10)
    private String seq;
    // 日期 （此证明日期）
    @Column(length = 20)
    private String applyDate;
    // 商号名称 （经销商 公司名称）
    @Column(length = 50)
    private String compName;
    // 金额
    @Column(length = 20)
    private String money;
    // 中文大写的金额
    @Column(length = 50)
    private String zhcnMoney;
    // 邮箱
    @Column(length = 50)
    private String email;
    // 名称 （此证明开具人名）
    @Column(length = 30)
    private String realName;
    // 状态
    @Column
    private int status;

    @ManyToOne
    @JoinColumn(name="batch_no", referencedColumnName = "batch_no")
    private ApplyBatch applyBatch;
}
