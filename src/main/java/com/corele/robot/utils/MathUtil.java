package com.corele.robot.utils;

import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
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

    /**
     * 概率取真假
     * @param ratio
     * @return
     */
    public static boolean random(double ratio){
        if (ratio >= 1){
            return true;
        }
        double num = new BigDecimal(ratio).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        int mix = (int) (num * 100);
        int random = random(0, 100);
        if (random < mix){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(random(0.01));
        }
    }
}
