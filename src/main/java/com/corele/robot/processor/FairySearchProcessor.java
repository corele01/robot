package com.corele.robot.processor;

import com.corele.robot.common.BaseException;
import com.corele.robot.constants.FaceConstant;
import com.corele.robot.convert.FairyConvert;
import com.corele.robot.model.RobotFairyRecord;
import com.corele.robot.model.RobotFairySearchConfig;
import com.corele.robot.model.RobotFairyTemp;
import com.corele.robot.processor.dto.MessageContext;
import com.corele.robot.service.RobotFairySearchConfigService;
import com.corele.robot.service.RobotFairyTempService;
import com.corele.robot.utils.JsonUtil;
import com.corele.robot.utils.MathUtil;
import com.corele.robot.utils.Message;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujun
 */
@Service("fairySearch")
public class FairySearchProcessor extends AbstractProcessor {

    @Autowired
    private RobotFairySearchConfigService robotFairySearchConfigService;
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
        RobotFairySearchConfig config = this.robotFairySearchConfigService.getChannel(messageContext.getGroupNo());
        if (config == null){
            return Message.builder().at(messageContext.getSendNo()).enter()
                    .face(FaceConstant.SE).space().string("该群还没有配置搜索精灵，请联系管理员配置").toMsg();

        }
        String fairyList = config.getFairyList();
        if (StringUtils.isEmpty(fairyList)){
            return Message.builder().at(messageContext.getSendNo()).enter()
                    .face(FaceConstant.SE).space().string("无精灵数据").toMsg();
        }
        List<Integer> fairyTempList = JsonUtil.parseArray(fairyList, Integer.class);
        int random = MathUtil.random(0, fairyTempList.size() - 1);
        Integer integer = fairyTempList.get(random);
        RobotFairyTemp temp = this.robotFairyTempService.getById(integer);
        if (temp == null){
            throw BaseException.exception();
        }
        RobotFairyRecord robotFairyRecord = FairyConvert.convert(temp, messageContext.getUser().getId());
        return Message.builder().at(messageContext.getSendNo()).enter()
                .face(FaceConstant.SE).space().string("恭喜搜索到精灵！！！！").enter()
                .face(FaceConstant.SE).space().string("名称：").string(robotFairyRecord.getFairyName()).enter()
                .face(FaceConstant.SE).space().string("资质：").string(robotFairyRecord.getWisdomName()).string("(+").string(robotFairyRecord.getWisdomRatio()).string(")").enter()
                .face(FaceConstant.SE).space().string("攻击：").string(robotFairyRecord.getAttack()).enter()
                .face(FaceConstant.SE).space().string("防御：").string(robotFairyRecord.getDefense()).enter()
                .face(FaceConstant.SE).space().string("回复【捕捉】获得精灵！！！！")
                .toMsg();
    }
}
