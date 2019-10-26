package com.corele.robot.service.impl;

import com.corele.robot.dto.MessageInfo;
import com.corele.robot.model.RobotUser;
import com.corele.robot.processor.MessageProcessor;
import com.corele.robot.common.MessageContext;
import com.corele.robot.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liujun
 */
@Service
public class MessageServiceImpl implements MessageService {
    
    @Autowired
    private MessageProcessor messageProcessor;
    
    /**
     * 处理消息
     * @param messageInfo
     * @return
     */
    @Override
    public String handleMsg(MessageInfo messageInfo, RobotUser user) {
        MessageContext context = MessageContext.builder()
                .message(messageInfo.getMessage())
                .groupNo(messageInfo.getGroupNo())
                .sendNo(messageInfo.getSendNo())
                .user(user)
                .build();
        return messageProcessor.handle(context);
    }
}
