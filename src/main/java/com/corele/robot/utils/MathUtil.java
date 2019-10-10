package com.corele.robot.utils;

import java.util.Random;

/**
 * @author liujun
 */
public class MathUtil {

    /**
     * 取范围内数据数
     * @param begin
     * @param end
     * @return
     */
    public static int random(int begin,int end){
        Random random = new Random();
        return random.nextInt(end-begin+1)+begin;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(random(80,100));
        }
    }
}
