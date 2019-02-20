package com.baoying.dis.entity;

import com.baoying.dis.assets.base.IDBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 申请批次
 */
@Entity
@Table(name = "t_apply_batch")
@Getter @Setter
public class ApplyBatch extends IDBaseEntity {

    // 批号
    @Column(name="batch_no", unique = true, nullable = false, length = 17)
    private String batchNo;

    // 申请人
    @Column(nullable = false, length = 32)
    private String applicant;

    // 存储目录的路径
    @Column(name="dir_path")
    private String dirPath;

    // 压缩包路径
    @Column(name="zip_path")
    private String zipPath;

    // 错误或失败文件的路径
    @Column(name="err_path")
    private String errPath;

    // 上传源文件
    @Lob
    @Column
    private byte[] excel;

    // 批次的状态
    @Column
    private int status;
}
