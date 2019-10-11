package com.corele.robot.enums;

import lombok.Getter;

/**
 * @author liujun
 */
@Getter
public enum FaceEnum {
    /**
     * 表情枚举
     */
    SE(1,"色"),;


    private int faceId;
    private String msg;

    FaceEnum(int faceId,String msg){
        this.faceId = faceId;
        this.msg = msg;
    }
}
