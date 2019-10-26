package com.corele.robot.processor;

import com.corele.robot.common.AbstractProcessor;
import com.corele.robot.constants.FaceConstant;
import com.corele.robot.model.RobotFairyTemp;
import com.corele.robot.common.MessageContext;
import com.corele.robot.service.RobotFairyTempService;
import com.corele.robot.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujun
 */
@Service("fairySelect")
public class FairySelectProcessor extends AbstractProcessor {

    @Autowired
    private RobotFairyTempService robotFairyTempService;

    /**
     * 处理消息
     *
     * @param messageContext 消息上下文
     * @return 返回消息
     */
    @Override
    public String handle(MessageContext messageContext) {
        List<RobotFairyTemp> fairyTemps = this.robotFairyTempService.getAll();
        Message.MessageBuilder builder = Message.builder().at(messageContext.getSendNo());
        for (RobotFairyTemp fairyTemp : fairyTemps) {
            builder.enter().face(FaceConstant.SE).space(2).string(fairyTemp.getId()).string("-").space()
                    .string(fairyTemp.getName()).space().string("(").string(fairyTemp.getFee()).string("金币)");
        }
        return builder.toMsg();
    }
}
