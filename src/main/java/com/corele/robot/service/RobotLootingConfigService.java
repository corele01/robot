package com.corele.robot.service;


import com.corele.robot.model.RobotLootingConfig;

/**
 * @author liujun 
 */
public interface RobotLootingConfigService {

    /**
     * 获取抢劫配置
     * @param groupNo
     * @return
     */
    RobotLootingConfig getConfigByGroupNo(String groupNo);
}
