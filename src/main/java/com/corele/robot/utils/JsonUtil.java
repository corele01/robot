package com.corele.robot.utils;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @author liujun
 */
public class JsonUtil {
    /**
     * 反序列化LIST
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String json,Class<T> clazz){
        return JSON.parseArray(json,clazz);
    }
}
