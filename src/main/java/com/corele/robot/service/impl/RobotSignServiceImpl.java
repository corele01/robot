package com.corele.robot.service.impl;


import com.corele.robot.enums.EnableEnum;
import com.corele.robot.mapper.RobotSignMapper;
import com.corele.robot.model.RobotSign;
import com.corele.robot.service.RobotSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


/**
 * @author liujun 
 */
@Service
public class RobotSignServiceImpl implements RobotSignService {

    @Autowired
    private RobotSignMapper robotSignMapper;


    /**
     * 获取签到设置
     *
     * @param groupNo
     * @return
     */
    @Override
    public RobotSign getSignConfigByGroup(String groupNo) {
        Example example = new Example(RobotSign.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("channel",groupNo);
        criteria.andEqualTo("enable", EnableEnum.ENABLE.getEnable());

        return this.robotSignMapper.selectOneByExample(example);
    }
}
