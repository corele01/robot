package com.corele.robot.processor;

import com.corele.robot.processor.dto.MessageContext;
import com.corele.robot.service.RobotMsgConfigService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liujun
 */
@Component
public class MessageProcessor implements InitializingBean {

    private Map<String,String> expressionStrMap = Maps.newHashMap();

    @Autowired
    private RobotMsgConfigService robotMsgConfigService;
    @Autowired
    private Map<String,AbstractProcessor> processorMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        expressionStrMap = robotMsgConfigService.getMesProcessorList();
    }

    public String handle(MessageContext context){
        String message = context.getMessage();

        String componentNo = regxComponent(message);
        if (StringUtils.isEmpty(componentNo)){
            expressionStrMap.clear();
            expressionStrMap = robotMsgConfigService.getMesProcessorList();
            componentNo = regxComponent(message);
            if (org.apache.commons.lang3.StringUtils.isEmpty(componentNo)){
                return null;
            }
        }

        AbstractProcessor abstractProcessor = processorMap.get(componentNo);
        if (abstractProcessor != null){
            return abstractProcessor.handleMsg(context);
        }
        return null;
    }

    private String regxComponent(String message) {
        String componentNo = "";
        for (String expr : expressionStrMap.keySet()) {
            Pattern pattern = Pattern.compile(expr);
            Matcher matcher = pattern.matcher(message);
            boolean flag = matcher.matches();
            if (flag){
                componentNo = expressionStrMap.get(expr);
                break;
            }
        }
        return componentNo;
    }
}
