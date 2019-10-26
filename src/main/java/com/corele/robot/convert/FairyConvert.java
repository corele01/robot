package com.corele.robot.convert;

import com.corele.robot.common.BaseException;
import com.corele.robot.constants.FaceConstant;
import com.corele.robot.model.RobotFairyRecord;
import com.corele.robot.model.RobotFairyTemp;
import com.corele.robot.model.dto.WisdomDTO;
import com.corele.robot.utils.JsonUtil;
import com.corele.robot.utils.MathUtil;
import com.corele.robot.utils.Message;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author liujun
 */
public class FairyConvert {


    public static RobotFairyRecord convert(RobotFairyTemp robotFairyTemp,Integer userId){
        int attack = MathUtil.random(robotFairyTemp.getAttackMin(), robotFairyTemp.getAttackMax());
        int defense = MathUtil.random(robotFairyTemp.getDefenseMin(), robotFairyTemp.getDefenseMax());
        String wisdomJson = robotFairyTemp.getWisdom();
        if (StringUtils.isEmpty(wisdomJson)){
            throw BaseException.exceptionForMsg(Message.builder().face(FaceConstant.SE).space().string("该精灵没有配置资质信息，请联系管理员！").toMsg());
        }
        List<WisdomDTO> wisdoms = JsonUtil.parseArray(wisdomJson, WisdomDTO.class);
        int random = MathUtil.random(0, wisdoms.size()-1);
        WisdomDTO wisdom = wisdoms.get(random);

        return RobotFairyRecord.builder()
                .attack(attack)
                .defense(defense)
                .experience(0)
                .fairyName(robotFairyTemp.getName())
                .gmtCreate(LocalDateTime.now())
                .level(0)
                .levelInfo(robotFairyTemp.getLevelInfo())
                .userId(userId)
                .wisdom(wisdom.getWisdom())
                .wisdomName(wisdom.getWisdomName())
                .wisdomRatio(wisdom.getWisdomRatio())
                .fairyTempId(robotFairyTemp.getId())
                .build();
    }
}
