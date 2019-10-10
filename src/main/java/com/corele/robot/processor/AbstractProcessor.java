package com.corele.robot.processor;

import com.corele.robot.processor.dto.MessageContext;

/**
 * @author liujun
 */
public abstract class AbstractProcessor {

    /**
     * 处理消息
     * @param messageContext
     * @return
     */
    public abstract String handle(MessageContext messageContext);
}
