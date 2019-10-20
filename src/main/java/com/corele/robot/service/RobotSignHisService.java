package com.corele.robot.service;


import com.corele.robot.model.RobotSignHis;

/**
 * @author liujun 
 */
public interface RobotSignHisService {
    /**
     * 查询签到记录
     * @param userId
     * @param date
     * @return
     */
    RobotSignHis getByDate(int userId,String date);

    /**
     * 插入签到记录
     * @param robotSignHis
     * @return
     */
    boolean addSignHis(RobotSignHis robotSignHis);

    /**
     * 取签到次数
     * @param userId
     * @return
     */
    int getCountForUser(Integer userId);
}
