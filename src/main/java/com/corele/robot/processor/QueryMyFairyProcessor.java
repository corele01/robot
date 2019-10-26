package com.corele.robot.processor;

import com.corele.robot.common.AbstractProcessor;
import com.corele.robot.constants.FaceConstant;
import com.corele.robot.model.RobotFairyRecord;
import com.corele.robot.common.MessageContext;
import com.corele.robot.service.RobotFairyRecordService;
import com.corele.robot.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujun
 */
@Service("queryMyFairy")
public class QueryMyFairyProcessor extends AbstractProcessor {

    @Autowired
    private RobotFairyRecordService robotFairyRecordService;

    /**
     * 处理消息
     *
     * @param messageContext 消息上下文
     * @return 返回消息
     */
    @Override
    public String handle(MessageContext messageContext) {
        Message.MessageBuilder enter = Message.builder().at(messageContext.getSendNo());
        List<RobotFairyRecord> robotFairyRecords = this.robotFairyRecordService.queryFairyByUserId(messageContext.getUser().getId());
        if (robotFairyRecords == null || robotFairyRecords.isEmpty()){
            return Message.builder().at(messageContext.getSendNo()).enter()
                    .face(FaceConstant.SHUAI).string("无购买精灵  发送 【精灵商城】购买吧！！").toMsg();
        }
        for (RobotFairyRecord robotFairyRecord : robotFairyRecords) {
            enter.enter().face(FaceConstant.SE).space().string(robotFairyRecord.getId())
                    .string(" - ").string(robotFairyRecord.getFairyName()).string(" ")
                    .string(robotFairyRecord.getLevel()).string("级");
        }
        return enter.toMsg();
    }
}
