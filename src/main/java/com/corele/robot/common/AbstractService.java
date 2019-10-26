package com.corele.robot.common;

import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;

/**
 * @author liujun
 */
public abstract class AbstractService<T> {

    protected Example getExample(){
        return new Example(getClazz());
    }

    private Class<T> getClazz(){
        Class<T> clazz = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz;
    }
}
