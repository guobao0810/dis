package com.baoying.dis.assets.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;
import java.util.Objects;

public abstract class ObjectUtil {

    /**
     * 从提供方拷贝同名属性到目标方
     *
     * @param target 目标方
     * @param supply 提供方
     * @param nullable 是否拷贝值为 null 的属性
     * @param includes 需要拷贝的同名属性名称, 若为 null 或 空数组 则拷贝所有同名属性值
     */
    public static void copyProperties(Object target, Object supply, boolean nullable, String[] includes) {
        Objects.requireNonNull(target);
        Objects.requireNonNull(supply);
        if (ArrayUtils.isEmpty(includes)) {
            try {
                includes = PropertyUtils.describe(supply).keySet().toArray(new String[0]);
            } catch (Exception e) {
                throw new RuntimeException("获取提供方对象属性映射失败！", e);
            }
        }
        for (int i = 0; i < includes.length; ++i) {
            String name = includes[i];
            // 是否可读
            if (!PropertyUtils.isReadable(supply, name)) {
                throw new RuntimeException(MessageFormat.format("属性[{0}]在提供方对象中不可读！", name));
            }
            // 是否可写
            if (!PropertyUtils.isWriteable(target, name)) {
                throw new RuntimeException(MessageFormat.format("属性[{0}]在目标方不可写！", name));
            }
            // 拷贝
            try {
                Object value = PropertyUtils.getProperty(supply, name);
                if (Objects.isNull(value) && !nullable) {
                    continue;
                }
                BeanUtils.setProperty(target, name, value);
            } catch (Exception e) {
                throw new RuntimeException(MessageFormat.format("拷贝属性[{0}]失败！", name));
            }
        }
    }
}
