package com.corele.robot.utils;

import com.corele.robot.enums.FaceEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liujun
 */
public class StringUtil {
    public static void main(String[] args) {
        Pattern compile = Pattern.compile("抢劫 {0,}\\[CQ:at,qq=([0-9]{5,})\\]");
        Matcher matcher = compile.matcher("抢劫 [CQ:at,qq=676877529]");
        if (matcher.find()){
            int count = matcher.groupCount();
            for (int i = 0; i < count + 1; i++) {
                System.out.println(matcher.group(i));
            }
        }
    }
}
