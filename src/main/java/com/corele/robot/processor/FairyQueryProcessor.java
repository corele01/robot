package com.corele.robot.processor;

import com.corele.robot.common.AbstractProcessor;
import com.corele.robot.common.BaseException;
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
@Service("fairyQuery")
public class FairyQueryProcessor extends AbstractProcessor {

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
        List<String> params = regxParam(messageContext);
        String fairyId = params.get(1);
        RobotFairyTemp robotFairyTemp = this.robotFairyTempService.getById(Integer.parseInt(fairyId));
        if (robotFairyTemp == null){
            throw BaseException.exception();
        }
        return Message.builder().at(messageContext.getSendNo()).enter()
                .face(FaceConstant.SE).space().string("名称：").string(robotFairyTemp.getName()).enter()
                .face(FaceConstant.SE).space().string("攻击：").string(robotFairyTemp.getAttackMin()).string("-").string(robotFairyTemp.getAttackMax()).enter()
                .face(FaceConstant.SE).space().string("防御：").string(robotFairyTemp.getDefenseMin()).string("-").string(robotFairyTemp.getAttackMax()).enter()
                .face(FaceConstant.SE).space().string("费用：").string(robotFairyTemp.getFee())
                .toMsg();
    }
}
