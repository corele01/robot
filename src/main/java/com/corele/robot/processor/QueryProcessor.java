package com.corele.robot.processor;

import com.corele.robot.processor.dto.MessageContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author liujun
 */
@Slf4j
@Component("query")
public class QueryProcessor extends AbstractProcessor {


    /**
     * 处理消息
     *
     * @param messageContext
     * @return
     */
    @Override
    public String handle(MessageContext messageContext) {



        return null;
    }
}
