package com.corele.robot.service;



/**
 * @author liujun 
 */
public interface RobotAccountHisService {
    /**
     * 插入账户变更记录 增加记录
     * @param userId
     * @param size
     * @param content
     * @return
     */
    boolean addAccountHisForNow(Integer userId,Integer size,String content);
}
