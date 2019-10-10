package com.corele.robot.enums;

import lombok.Getter;

/**
 * @author liujun
 */
@Getter
public enum EnableEnum {
    /**
     * 启用状态
     */
    ENABLE(1,"启用"),
    DISABLE(0,"未启用");


    private int enable;
    private String msg;

    EnableEnum(int enable , String msg){
        this.enable = enable;
        this.msg = msg;
    }
}
