package com.corele.robot.service.impl;


import com.corele.robot.common.AbstractService;
import com.corele.robot.mapper.RobotFairySearchConfigMapper;
import com.corele.robot.model.RobotFairySearchConfig;
import com.corele.robot.service.RobotFairySearchConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


/**
 * @author liujun 
 */
@Service
public class RobotFairySearchConfigServiceImpl extends AbstractService<RobotFairySearchConfig> implements RobotFairySearchConfigService {

    @Autowired
    private RobotFairySearchConfigMapper robotFairySearchConfigMapper;

    /**
     * 查询搜索配置
     *
     * @param channel
     * @return
     */
    @Override
    public RobotFairySearchConfig getChannel(String channel) {
        Example example = getExample();
        example.createCriteria().andEqualTo("channel",channel);
        RobotFairySearchConfig robotFairySearchConfig = this.robotFairySearchConfigMapper.selectOneByExample(example);
        return robotFairySearchConfig;
    }
}
