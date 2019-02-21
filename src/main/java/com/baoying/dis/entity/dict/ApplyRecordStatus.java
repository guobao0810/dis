package com.baoying.dis.entity.dict;

import lombok.Getter;

import java.text.MessageFormat;

public enum ApplyRecordStatus {
    OK(0, "成功"), FAIL(1, "失败");

    ApplyRecordStatus(int value, String description) {
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
    public static ApplyRecordStatus of(int value) {
        for (ApplyRecordStatus applyRecordStatus : ApplyRecordStatus.values()) {
            if (applyRecordStatus.is(value)) {
                return applyRecordStatus;
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
