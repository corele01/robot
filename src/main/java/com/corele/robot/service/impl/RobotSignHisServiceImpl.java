package com.corele.robot.service.impl;


import com.corele.robot.mapper.RobotSignHisMapper;
import com.corele.robot.model.RobotSignHis;
import com.corele.robot.service.RobotSignHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.script.ScriptEngine;


/**
 * @author liujun 
 */
@Service
public class RobotSignHisServiceImpl implements RobotSignHisService {

    @Autowired
    private RobotSignHisMapper robotSignHisMapper;

    /**
     * 查询签到记录
     *
     * @param userId
     * @param date
     * @return
     */
    @Override
    public RobotSignHis getByDate(int userId, String date) {
        Example example = new Example(RobotSignHis.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("gmtSign",date);
        criteria.andEqualTo("userId",userId);

        return this.robotSignHisMapper.selectOneByExample(example);
    }

    /**
     * 插入签到记录
     *
     * @param robotSignHis
     * @return
     */
    @Override
    public boolean addSignHis(RobotSignHis robotSignHis) {
        int row = this.robotSignHisMapper.insertSelective(robotSignHis);
        if (row > 0){
            return true;
        }
        return false;
    }

    /**
     * 取签到次数
     *
     * @param userId
     * @return
     */
    @Override
    public int getCountForUser(Integer userId) {
        Example example = new Example(RobotSignHis.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        return this.robotSignHisMapper.selectCountByExample(example);
    }
}
