package com.baoying.dis.entity.dict;

import lombok.Getter;

import java.text.MessageFormat;

/**
 * 申请批次的状态枚举
 */
public enum ApplyBatchStatus {
    UPLOAD(0, "上传"),
    AUDIT(1, "法务审核中"),
    PASS_AUDIT(2, "法务通过审核"),
    EXECUTE(3, "执行签章中"),
    FINISH(4, "已完成"),
    BREAKOFF(5, "意外中断");

    private ApplyBatchStatus(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Getter
    private final int value;

    @Getter
    private final String description;

    /**
     * 根据 value 获取状态枚举
     *
     * @param value
     * @return
     */
    public static ApplyBatchStatus of(int value) {
        ApplyBatchStatus[] all = ApplyBatchStatus.values();
        for (int i=0; i<all.length; ++i) {
            if (all[i].value == value) {
                return all[i];
            }
        }
        throw new IllegalArgumentException(MessageFormat.format("没有值为 [{0}] 的状态！", value));
    }

    /**
     * 直接判断是否是该枚举值
     *
     * @param value
     * @return
     */
    public boolean is(int value) {
        return this.value == value;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0} - value:{1}, description:{2}", name(), value, description);
    }
}
