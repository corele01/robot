package com.corele.robot.service.impl;


import com.corele.robot.mapper.RobotSignHisMapper;
import com.corele.robot.model.RobotSignHis;
import com.corele.robot.service.RobotSignHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


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

        return this.robotSignHisMapper.selectOneByExample(example);
    }
}
