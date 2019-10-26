package com.corele.robot.processor;

import com.corele.robot.common.AbstractProcessor;
import com.corele.robot.constants.FaceConstant;
import com.corele.robot.common.MessageContext;
import com.corele.robot.service.RobotFairyRecordService;
import com.corele.robot.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liujun
 */
@Service("delFairy")
public class DelFairyProcessor extends AbstractProcessor {

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
        List<String> params = regxParam(messageContext);
        String fairyId = params.get(1);
        boolean del = this.robotFairyRecordService.delFairyForId(Integer.parseInt(fairyId), messageContext.getUser().getId());
        if (!del){
            return Message.builder().at(messageContext.getSendNo()).enter()
                    .face(FaceConstant.SE).string("放生失败！！！").toMsg();
        }
        return Message.builder().at(messageContext.getSendNo()).enter()
                .face(FaceConstant.SE).string("放生成功！！！").toMsg();
    }
}
