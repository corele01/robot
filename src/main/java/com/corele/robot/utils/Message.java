package com.corele.robot.utils;

import lombok.Builder;

/**
 * @author liujun
 */
public class Message {

    private static StringBuffer message;

    private static void initMessage(){
        message = new StringBuffer();
    }

    public static void addString(String string){
        if (message == null){
            initMessage();
        }
        message.append(string);
    }

    public static void addEnter(){
        addString("\n");
    }

    public static String toStr(){
        return message.toString();
    }
}
