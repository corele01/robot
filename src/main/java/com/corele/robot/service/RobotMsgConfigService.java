package com.corele.robot.service;


import java.util.Map;

/**
 * @author liujun 
 */
public interface RobotMsgConfigService {
    /**
     * 获取启用的消息处理匹配处理器
     * @return
     */
    Map<String,String> getMesProcessorList();
}
