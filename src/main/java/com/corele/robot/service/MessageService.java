package com.corele.robot.service;

import com.corele.robot.dto.MessageInfo;
import com.corele.robot.model.RobotUser;

/**
 * @author liujun
 */
public interface MessageService {
    /**
     * 处理消息
     * @param messageInfo
     * @return
     */
    String handleMsg(MessageInfo messageInfo, RobotUser user);
}
