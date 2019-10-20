package com.corele.robot.processor;

import com.corele.robot.common.BaseException;
import com.corele.robot.constants.FaceConstant;
import com.corele.robot.model.RobotAccount;
import com.corele.robot.model.RobotUser;
import com.corele.robot.processor.dto.MessageContext;
import com.corele.robot.service.*;
import com.corele.robot.utils.Message;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liujun
 */
public abstract class AbstractProcessor {

    @Autowired
    private RobotAccountService robotAccountService;
    @Autowired
    private RobotAccountHisService robotAccountHisService;
    @Autowired
    private RobotUserService robotUserService;

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

    /**
     * 获取一个账户
     * @param userId
     * @return
     */
    protected RobotAccount getAccount(int userId){
        return this.robotAccountService.getByUserId(userId);
    }

    /**
     * 获取一个账户信息
     * @param groupNo
     * @param userNo
     * @return
     */
    protected RobotAccount getAccount(String groupNo,String userNo){
        RobotUser user = this.robotUserService.getUserBySendNo(groupNo, userNo);
        if (user == null){
            return null;
        }
        return this.robotAccountService.getByUserId(user.getId());
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
                            .face(FaceConstant.SHUAI)
                            .space()
                            .string("金币不够了，努力吧骚年！")
                            .toMsg()
            );
        }
        return this.robotAccountService.updateAccount(account.getUserId(), oldSize, newSize);
    }

    protected boolean addAccountHis(int userId,int modSize,String content){
        return this.robotAccountHisService.addAccountHisForNow(userId,modSize,content);
    }

    /**
     * 取消息中参数
     * @param messageContext
     * @return
     */
    protected List<String> regxParam(MessageContext messageContext) {
        String pattern = messageContext.getPattern();
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(messageContext.getMessage());
        List<String> regxList = Lists.newArrayList();
        if (matcher.find()){
            int count = matcher.groupCount();
            for (int i = 0; i < count + 1; i++) {
                regxList.add(matcher.group(i));
            }
        }
        return regxList;
    }
}
