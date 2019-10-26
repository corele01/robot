package com.corele.robot.service;


import com.corele.robot.model.RobotFairySearchConfig;

/**
 * @author liujun 
 */
public interface RobotFairySearchConfigService {
    /**
     * 查询搜索配置
     * @param channel
     * @return
     */
    RobotFairySearchConfig getChannel(String channel);
}
