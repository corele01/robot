package com.corele.robot.service.impl;


import com.corele.robot.enums.EnableEnum;
import com.corele.robot.mapper.RobotMsgConfigMapper;
import com.corele.robot.model.RobotMsgConfig;
import com.corele.robot.service.RobotMsgConfigService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;


/**
 * @author liujun 
 */
@Service
public class RobotMsgConfigServiceImpl implements RobotMsgConfigService {

    @Autowired
    private RobotMsgConfigMapper robotMsgConfigMapper;

    /**
     * 获取启用的消息处理匹配处理器
     *
     * @return
     */
    @Override
    public Map<String, String> getMesProcessorList() {
        Example example = new Example(RobotMsgConfig.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("enable", EnableEnum.ENABLE.getEnable());
        List<RobotMsgConfig> robotMsgConfigs = this.robotMsgConfigMapper.selectByExample(example);

        Map<String,String> resultMap = Maps.newHashMap();
        if (robotMsgConfigs != null && !robotMsgConfigs.isEmpty()){
            robotMsgConfigs.forEach(robotMsgConfig -> {
                resultMap.put(robotMsgConfig.getExpression(),robotMsgConfig.getProcessorNo());
            });
        }
        return resultMap;
    }
}
