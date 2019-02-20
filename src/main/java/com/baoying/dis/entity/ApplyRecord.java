package com.baoying.dis.entity;

import com.baoying.dis.assets.base.IDBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 申请明细记录
 */
@Entity
@Table(name = "t_apply_record")
@Setter @Getter
public class ApplyRecord extends IDBaseEntity {
    // 月份
    private String month;
    // ID （客户 ID）
    private String acountId;
    // 序号 （证明文件上后缀的序号）
    private String seq;
    // 日期 （此证明日期）
    private String applyDate;
    // 商号名称 （经销商 公司名称）
    private String compName;
    // 金额
    private String money;
    // 中文大写的金额
    private String zhcnMoney;
    // 邮箱
    private String email;
    // 名称 （此证明开具人名）
    private String realName;

    @ManyToOne
    @JoinColumn(name="batch_no", referencedColumnName = "batch_no")
    private ApplyBatch applyBatch;
}
