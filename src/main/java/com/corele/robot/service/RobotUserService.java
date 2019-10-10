package com.corele.robot.service;


import com.corele.robot.model.RobotUser;

/**
 * @author liujun 
 */
public interface RobotUserService {
    /**
     * 获取用户
     * @param groupNo
     * @param sendNo
     * @return
     */
    RobotUser getUserBySendNo(String groupNo,String sendNo);

    /**
     * 创建并初始化
     * @param groupNo
     * @param sendNo
     * @return
     */
    RobotUser createAndInit(String groupNo,String sendNo);
}
