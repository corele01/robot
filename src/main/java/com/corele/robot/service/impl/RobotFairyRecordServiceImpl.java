package com.corele.robot.service.impl;


import com.corele.robot.mapper.RobotFairyRecordMapper;
import com.corele.robot.model.RobotFairyRecord;
import com.corele.robot.service.RobotFairyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;


/**
 * @author liujun 
 */
@Service
public class RobotFairyRecordServiceImpl implements RobotFairyRecordService {

    @Autowired
    private RobotFairyRecordMapper robotFairyRecordMapper;

    /**
     * 添加一个精灵
     *
     * @param robotFairyRecord
     * @return
     */
    @Override
    public boolean addFairyRecord(RobotFairyRecord robotFairyRecord) {
        int row = this.robotFairyRecordMapper.insertSelective(robotFairyRecord);
        if (row > 0){
            return true;
        }
        return false;
    }

    /**
     * 查询某个用户精灵列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<RobotFairyRecord> queryFairyByUserId(Integer userId) {
        Example example = getExample();
        example.createCriteria().andEqualTo("userId",userId);
        return this.robotFairyRecordMapper.selectByExample(example);
    }

    /**
     * 删除一个精灵
     *
     * @param fairyId
     * @param userId
     * @return
     */
    @Override
    public boolean delFairyForId(Integer fairyId, Integer userId) {
        Example example = getExample();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("id",fairyId);
        int row = this.robotFairyRecordMapper.deleteByExample(example);
        if (row > 0){
            return true;
        }
        return false;
    }

    private Example getExample() {
        return new Example(RobotFairyRecord.class);
    }

    /**
     * 查询精灵数量
     *
     * @param userId
     * @return
     */
    @Override
    public int getCountForUserId(Integer userId) {
        Example example = getExample();
        example.createCriteria().andEqualTo("userId",userId);
        return this.robotFairyRecordMapper.selectCountByExample(example);
    }
}
