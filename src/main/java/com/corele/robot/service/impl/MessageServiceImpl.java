package com.corele.robot.service.impl;

import com.corele.robot.dto.MessageInfo;
import com.corele.robot.processor.MessageProcessor;
import com.corele.robot.processor.dto.MessageContext;
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
    public String handleMsg(MessageInfo messageInfo) {
        MessageContext context = MessageContext.builder()
                .message(messageInfo.getMessage())
                .groupNo(messageInfo.getGroupNo())
                .sendNo(messageInfo.getSendNo())
                .build();
        return messageProcessor.handle(context);
    }
}
