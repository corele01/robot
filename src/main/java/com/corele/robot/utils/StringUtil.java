package com.corele.robot.utils;

import com.corele.robot.enums.FaceEnum;

/**
 * @author liujun
 */
public class StringUtil {
    public static void main(String[] args) {

        System.out.println(Message.builder()
                .string(Face.face(FaceEnum.SE))
                .string(" 签到成功！")
                .enter().toMsg());

        String string = "Magege";
        String s = string.substring(0, 1).toLowerCase()+string.substring(1);
        System.out.println(s);
    }
}
