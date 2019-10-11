package com.corele.robot.utils;

import com.corele.robot.enums.FaceEnum;

/**
 * @author liujun
 */
public class Face {
    /**
     * 获取表情
     * @param faceEnum
     * @return
     */
    public static String face(FaceEnum faceEnum){
        return "[CQ:face,id="+faceEnum.getFaceId()+"]";
    }
}
