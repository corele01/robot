package com.corele.robot.processor;

import com.corele.robot.common.BaseException;
import com.corele.robot.constants.FaceConstant;
import com.corele.robot.model.RobotAccount;
import com.corele.robot.model.RobotFairyRecord;
import com.corele.robot.model.RobotFairyTemp;
import com.corele.robot.model.dto.WisdomDTO;
import com.corele.robot.processor.dto.MessageContext;
import com.corele.robot.service.RobotFairyRecordService;
import com.corele.robot.service.RobotFairyTempService;
import com.corele.robot.utils.JsonUtil;
import com.corele.robot.utils.MathUtil;
import com.corele.robot.utils.Message;
import com.corele.robot.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liujun
 */
@Service("buyFairy")
public class BuyFairyProcessor extends AbstractProcessor{

    @Autowired
    private RobotFairyRecordService robotFairyRecordService;
    @Autowired
    private RobotFairyTempService robotFairyTempService;

    /**
     * 处理消息
     *
     * @param messageContext 消息上下文
     * @return 返回消息
     */
    @Transactional
    @Override
    public String handle(MessageContext messageContext) {
        List<String> params = regxParam(messageContext);
        String fairyId = params.get(1);
        if (StringUtils.isEmpty(fairyId)){
            throw BaseException.exception();
        }
        RobotFairyTemp robotFairyTemp = this.robotFairyTempService.getById(Integer.parseInt(fairyId));
        if (robotFairyTemp == null){
            return Message.builder().at(messageContext.getSendNo()).enter()
                    .face(FaceConstant.SE).space().string("请输入有限精灵编码").toMsg();
        }
        RobotAccount account = getAccount(messageContext.getUser().getId());
        int accountMoney = account.getAccountMoney();
        int fairyFee = robotFairyTemp.getFee();
        if (accountMoney < fairyFee){
            return Message.builder().at(messageContext.getSendNo()).enter()
                    .face(FaceConstant.SE).space().string("金币不够了！去挣金币吧！！！")
                    .toMsg();
        }
        int newAccountMoney = accountMoney - fairyFee;
        boolean subMoney = subMoney(account, fairyFee);
        if (!subMoney){
            throw BaseException.exception();
        }
        boolean addAccountHis = addAccountHis(messageContext.getUser().getId(), fairyFee, "购买精灵-" + robotFairyTemp.getName());
        if (!addAccountHis){
            throw BaseException.exception();
        }

        int attack = MathUtil.random(robotFairyTemp.getAttackMin(), robotFairyTemp.getAttackMax());
        int defense = MathUtil.random(robotFairyTemp.getDefenseMin(), robotFairyTemp.getDefenseMax());
        String wisdomJson = robotFairyTemp.getWisdom();
        if (StringUtils.isEmpty(wisdomJson)){
            return Message.builder().at(messageContext.getSendNo()).enter()
                    .face(FaceConstant.SE).space().string("该精灵没有配置资质信息，请联系管理员！").toMsg();
        }
        List<WisdomDTO> wisdoms = JsonUtil.parseArray(wisdomJson, WisdomDTO.class);
        int random = MathUtil.random(0, wisdoms.size()-1);
        WisdomDTO wisdom = wisdoms.get(random);

        RobotFairyRecord robotFairyRecord = RobotFairyRecord.builder()
                .attack(attack)
                .defense(defense)
                .experience(0)
                .fairyName(robotFairyTemp.getName())
                .gmtCreate(LocalDateTime.now())
                .level(0)
                .levelInfo(robotFairyTemp.getLevelInfo())
                .userId(messageContext.getUser().getId())
                .wisdom(wisdom.getWisdom())
                .wisdomName(wisdom.getWisdomName())
                .wisdomRatio(wisdom.getWisdomRatio())
                .fairyTempId(robotFairyTemp.getId())
                .build();
        boolean add = this.robotFairyRecordService.addFairyRecord(robotFairyRecord);
        if (!add){
            throw BaseException.exception();
        }
        return Message.builder().at(messageContext.getSendNo()).enter()
                .face(FaceConstant.SE).space().string("恭喜成功购买！").enter()
                .face(FaceConstant.SE).space().string("名称：").string(robotFairyRecord.getFairyName()).enter()
                .face(FaceConstant.SE).space().string("资质：").string(robotFairyRecord.getWisdomName()).string("(+").string(robotFairyRecord.getWisdomRatio()).string(")").enter()
                .face(FaceConstant.SE).space().string("攻击：").string(robotFairyRecord.getAttack()).enter()
                .face(FaceConstant.SE).space().string("防御：").string(robotFairyRecord.getDefense()).enter()
                .toMsg();
    }
}
