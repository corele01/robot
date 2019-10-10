package com.corele.robot.service;

import com.corele.robot.dto.MessageInfo;

/**
 * @author liujun
 */
public interface MessageService {
    /**
     * 处理消息
     * @param messageInfo
     * @return
     */
    String handleMsg(MessageInfo messageInfo);
}
