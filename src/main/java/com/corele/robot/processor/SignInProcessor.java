package com.corele.robot.processor;

import com.corele.robot.model.RobotAccount;
import com.corele.robot.model.RobotSign;
import com.corele.robot.model.RobotSignHis;
import com.corele.robot.model.RobotUser;
import com.corele.robot.processor.dto.MessageContext;
import com.corele.robot.service.*;
import com.corele.robot.utils.LocalDateTimeUtil;
import com.corele.robot.utils.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author liujun
 */
@Slf4j
@Component("signIn")
public class SignInProcessor extends AbstractProcessor{

    @Autowired
    private RobotSignService robotSignService;
    @Autowired
    private RobotSignHisService robotSignHisService;
    @Autowired
    private RobotUserService robotUserService;
    @Autowired
    private RobotAccountService robotAccountService;
    @Autowired
    private RobotAccountHisService robotAccountHisService;

    /**
     * 处理消息
     *
     * @param messageContext
     * @return
     */
    @Override
    public String handle(MessageContext messageContext) {
        String groupNo = messageContext.getGroupNo();
        if (StringUtils.isEmpty(groupNo)){
            return "null";
        }
        String sendNo = messageContext.getSendNo();
        if (StringUtils.isEmpty(sendNo)) {
            return "null";
        }
        RobotUser user = this.robotUserService.getUserBySendNo(groupNo, sendNo);
        RobotAccount robotAccount = null;
        if (user == null){
            user = this.robotUserService.createAndInit(groupNo,sendNo);
            robotAccount = this.robotAccountService.createAndInit(user.getId());
        }

        String today = LocalDateTimeUtil.format(LocalDateTime.now(), "yyyy-MM-dd");
        RobotSignHis signHis = this.robotSignHisService.getByDate(user.getId(), today);
        if (signHis != null){
            return "您今天已签过到了，改天再来。";
        }

        RobotSign signConfig = this.robotSignService.getSignConfigByGroup(groupNo);
        if (signConfig == null){
            log.info("The sign config is not find .");
            return null;
        }
        if (robotAccount == null){
            robotAccount  = this.robotAccountService.getByUserId(user.getId());
        }
        int oldMoney = robotAccount.getAccountMoney();
        int signSize = MathUtil.random(signConfig.getMin(),signConfig.getMax());

        int newMoney = oldMoney + signSize;
        boolean flag = this.robotAccountService.updateAccount(user.getId(), oldMoney, newMoney);
        if (!flag){

        }
        boolean addAccountHis = this.robotAccountHisService.addAccountHisForNow(user.getId(), signSize, "签到所得");
        if (!addAccountHis){
            
        }
        return "[CQ:face,id=2] 签到成功 \n";
    }
}
