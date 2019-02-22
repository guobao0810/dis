package com.baoying.dis.assets.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Json 工具类
 *
 * @author maomh
 * @since 2019-01-03
 */
public abstract class JsonUtil {

    public static final String REPONSE_JSON_TYPE = "application/json;charset=utf-8";

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // ObjectMapper 配置项
    static {

    }

    /**
     * 将对象 object 转换为 json 字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return toJson(object, false);
    }

    /**
     * 将对象 object 转换为 json 字符串
     *
     * @param object
     * @param withPrettyFormat 是否启用美化
     * @return
     */
    public static String toJson(Object object, boolean withPrettyFormat) {
        try {
            return withPrettyFormat ? OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object)
                    : OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("对象转换为 JSON 字符串失败！");
        }
    }

    /**
     * 读取 json 字符串，转换为指定类型的 对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("读取 JSON 字符串转换为指定类型的对象失败！");
        }
    }

}
