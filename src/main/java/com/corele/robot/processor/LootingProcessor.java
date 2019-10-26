package com.corele.robot.processor;

import com.corele.robot.common.AbstractProcessor;
import com.corele.robot.common.BaseException;
import com.corele.robot.constants.FaceConstant;
import com.corele.robot.model.RobotAccount;
import com.corele.robot.model.RobotLootingConfig;
import com.corele.robot.common.MessageContext;
import com.corele.robot.service.RobotLootingConfigService;
import com.corele.robot.utils.MathUtil;
import com.corele.robot.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liujun
 */
@Service("looting")
public class LootingProcessor extends AbstractProcessor {

    @Autowired
    private RobotLootingConfigService robotLootingConfigService;

    /**
     * 处理消息
     *
     * @param messageContext 消息上下文
     * @return 返回消息
     */
    @Override
    @Transactional
    public String handle(MessageContext messageContext) {
        List<String> regxList = regxParam(messageContext);
        if (regxList.isEmpty()){
            throw BaseException.exception();
        }
        RobotLootingConfig lootingConfig = this.robotLootingConfigService.getConfigByGroupNo(messageContext.getGroupNo());
        if (lootingConfig == null){
            throw BaseException.exception();
        }
        String lootNo = regxList.get(1);
        RobotAccount lootAccount = getAccount(messageContext.getGroupNo(), lootNo);
        if (lootAccount == null){
            return Message.builder()
                    .at(messageContext.getSendNo()).enter()
                    .face(FaceConstant.SHUAI).space().string("对方已经没有钱了").toMsg();
        }
        int lootAccountMoney = lootAccount.getAccountMoney();
        if (lootAccountMoney == 0){
            return Message.builder()
                    .at(messageContext.getSendNo()).enter()
                    .face(FaceConstant.SHUAI).space().string("对方已经没有钱了").toMsg();
        }
        boolean isLoot = MathUtil.random(lootingConfig.getRatio());
        if (!isLoot){
            return Message.builder()
                    .at(messageContext.getSendNo()).enter()
                    .face(FaceConstant.SHUAI).space().string("抢劫失败！！").toMsg();
        }
        int random = MathUtil.random(lootingConfig.getMin(), lootingConfig.getMax());
        RobotAccount userAccount = getAccount(messageContext.getUser().getId());
        if (lootAccountMoney < random){
            random = lootAccountMoney;
        }

        int userAccountMoney = userAccount.getAccountMoney();
        int newUserAccountMoney = userAccountMoney + random;

        int newLootAccountMoney = lootAccountMoney - random;

        RobotAccount newUserAccount = RobotAccount.builder()
                .accountMoney(userAccountMoney)
                .userId(messageContext.getUser().getId())
                .gmtCreate(LocalDateTime.now())
                .enable(1).build();
        boolean addUserAccount = addMoney(newUserAccount, random);
        if (!addUserAccount){
            throw BaseException.exception();
        }
        boolean addUserAccountHis = addAccountHis(messageContext.getUser().getId(), random, "抢劫所得");
        if (!addUserAccountHis){
            throw BaseException.exception();
        }
        RobotAccount newLootAccount = RobotAccount.builder()
                .gmtModify(LocalDateTime.now())
                .userId(lootAccount.getUserId())
                .accountMoney(lootAccountMoney)
                .build();
        boolean subMoney = subMoney(newLootAccount, random);
        if (!subMoney){
            throw BaseException.exception();
        }
        boolean addAccountHis = addAccountHis(lootAccount.getUserId(), random, "被抢劫所失");
        if (!addAccountHis){
            throw BaseException.exception();
        }
        return Message.builder()
                .at(messageContext.getSendNo()).enter()
                .face(FaceConstant.SE).space().string("恭喜抢劫成功！").enter()
                .face(FaceConstant.SE).space().string("抢得：").string(random).string("枚金币").enter()
                .face(FaceConstant.SE).space().string("最新金币：").string(newUserAccountMoney).string("个")
                .toMsg();
    }


}
