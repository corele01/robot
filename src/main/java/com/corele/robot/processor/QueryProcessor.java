package com.corele.robot.processor;

import com.corele.robot.constants.FaceConstant;
import com.corele.robot.model.RobotAccount;
import com.corele.robot.model.RobotUser;
import com.corele.robot.processor.dto.MessageContext;
import com.corele.robot.utils.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

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
        RobotUser user = messageContext.getUser();

        RobotAccount account = getAccount(user.getId());
        Integer accountMoney = account.getAccountMoney();

        LocalDateTime gmtCreate = user.getGmtCreate();
        LocalDateTime now = LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(gmtCreate, now);
        return Message.builder()
                .addFace(FaceConstant.SE).addSpace().addString("金币：").addString(accountMoney).addString("个").addEnter()
                .addFace(FaceConstant.SE).addSpace().addString("时长：").addString(hours).addString("个小时")
                .toMsg();
    }
}
