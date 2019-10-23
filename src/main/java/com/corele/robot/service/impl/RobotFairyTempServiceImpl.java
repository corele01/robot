package com.corele.robot.service.impl;


import com.corele.robot.enums.EnableEnum;
import com.corele.robot.mapper.RobotFairyTempMapper;
import com.corele.robot.model.RobotFairyTemp;
import com.corele.robot.service.RobotFairyTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @author liujun 
 */
@Service
public class RobotFairyTempServiceImpl implements RobotFairyTempService {

    @Autowired
    private RobotFairyTempMapper robotFairyTempMapper;

    /**
     * 获取精灵模板
     *
     * @return
     */
    @Override
    public List<RobotFairyTemp> getAll() {
        Example example = new Example(RobotFairyTemp.class);
        example.createCriteria().andEqualTo("enable", EnableEnum.ENABLE.getEnable());
        return this.robotFairyTempMapper.selectByExample(example);
    }

    /**
     * 查询某个精灵
     *
     * @param fairyId
     * @return
     */
    @Override
    public RobotFairyTemp getById(int fairyId) {
        return this.robotFairyTempMapper.selectByPrimaryKey(fairyId);
    }
}
