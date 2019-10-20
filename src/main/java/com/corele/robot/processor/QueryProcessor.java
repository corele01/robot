package com.corele.robot.processor;

import com.corele.robot.constants.FaceConstant;
import com.corele.robot.model.RobotAccount;
import com.corele.robot.model.RobotUser;
import com.corele.robot.processor.dto.MessageContext;
import com.corele.robot.service.RobotSignHisService;
import com.corele.robot.utils.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author liujun
 */
@Slf4j
@Component("query")
public class QueryProcessor extends AbstractProcessor {

    @Autowired
    private RobotSignHisService robotSignHisService;

    /**
     * 处理消息
     *
     * @param messageContext
     * @return
     */
    @Override
    public String handle(MessageContext messageContext) {
        RobotUser user = messageContext.getUser();
        RobotAccount account = getAccount(user.getId());
        Integer accountMoney = account.getAccountMoney();
        LocalDateTime gmtCreate = user.getGmtCreate();
        LocalDateTime now = LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(gmtCreate, now);
        int countForSign = this.robotSignHisService.getCountForUser(user.getId());
        return Message.builder()
                .face(FaceConstant.SE).space().string("金币：").string(accountMoney).string("个").enter()
                .face(FaceConstant.SE).space().string("时长：").string(hours).string("个小时").enter()
                .face(FaceConstant.SE).space().string("签到次数：").string(countForSign).string("次")
                .toMsg();
    }
}
