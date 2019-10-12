package com.corele.robot.processor;

import com.corele.robot.common.BaseException;
import com.corele.robot.constants.FaceConstant;
import com.corele.robot.model.RobotAccount;
import com.corele.robot.processor.dto.MessageContext;
import com.corele.robot.service.*;
import com.corele.robot.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liujun
 */
public abstract class AbstractProcessor {

    @Autowired
    private RobotAccountService robotAccountService;
    @Autowired
    private RobotAccountHisService robotAccountHisService;

    /**
     * 处理消息
     * @param messageContext 消息上下文
     * @return 返回消息
     */
    @Transactional(rollbackFor = Throwable.class)
    public abstract String handle(MessageContext messageContext);

    String handleMsg(MessageContext messageContext){
        String resultMsg = handle(messageContext);
        return resultMsg;
    }

    protected RobotAccount getAccount(int userId){
        return this.robotAccountService.getByUserId(userId);
    }

    protected boolean addMoney(RobotAccount account,int addSize){
        int oldSize = account.getAccountMoney();
        int newSize = oldSize+addSize;
        return this.robotAccountService.updateAccount(account.getUserId(), oldSize, newSize);
    }

    protected boolean subMoney(RobotAccount account,int subSize){
        int oldSize = account.getAccountMoney();
        int newSize = oldSize-subSize;
        if (newSize < 0){
            throw new BaseException(1,
                    Message.builder()
                            .addFace(FaceConstant.SHUAI)
                            .addSpace()
                            .addString("金币不够了，努力吧骚年！")
                            .toMsg()
            );
        }
        return this.robotAccountService.updateAccount(account.getUserId(), oldSize, newSize);
    }

    protected boolean addAccountHis(int userId,int modSize,String content){
        return this.robotAccountHisService.addAccountHisForNow(userId,modSize,content);
    }
}
