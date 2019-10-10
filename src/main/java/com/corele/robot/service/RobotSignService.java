package com.corele.robot.service;


import com.corele.robot.model.RobotSign;

/**
 * @author liujun 
 */
public interface RobotSignService {
    /**
     * 获取签到设置
     * @param groupNo
     * @return
     */
    RobotSign getSignConfigByGroup(String groupNo);
}
