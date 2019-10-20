package com.corele.robot.service.impl;


import com.corele.robot.enums.EnableEnum;
import com.corele.robot.mapper.RobotLootingConfigMapper;
import com.corele.robot.model.RobotLootingConfig;
import com.corele.robot.service.RobotLootingConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


/**
 * @author liujun 
 */
@Service
public class RobotLootingConfigServiceImpl implements RobotLootingConfigService {

    @Autowired
    private RobotLootingConfigMapper robotLootingConfigMapper;

    /**
     * 获取抢劫配置
     *
     * @param groupNo
     * @return
     */
    @Override
    public RobotLootingConfig getConfigByGroupNo(String groupNo) {
        Example example = new Example(RobotLootingConfig.class);
        example.createCriteria().andEqualTo("channel",groupNo).andEqualTo("enable", EnableEnum.ENABLE.getEnable());
        return this.robotLootingConfigMapper.selectOneByExample(example);
    }
}
