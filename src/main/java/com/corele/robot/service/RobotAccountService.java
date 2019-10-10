package com.corele.robot.service;


import com.corele.robot.model.RobotAccount;

/**
 * @author liujun 
 */
public interface RobotAccountService {

    /**
     * 获取用户账户信息
     * @param userId
     * @return
     */
    RobotAccount getByUserId(Integer userId);

    /**
     * 创建并初始化账户
     * @param userId
     * @return
     */
    RobotAccount createAndInit(Integer userId);

    /**
     * 更新账户金额
     * @param userId
     * @param oldMoney
     * @param newMoney
     * @return
     */
    boolean updateAccount(Integer userId,Integer oldMoney,Integer newMoney);
}
