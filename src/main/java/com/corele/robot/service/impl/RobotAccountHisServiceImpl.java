package com.corele.robot.service.impl;


import com.corele.robot.mapper.RobotAccountHisMapper;
import com.corele.robot.model.RobotAccountHis;
import com.corele.robot.service.RobotAccountHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * @author liujun 
 */
@Service
public class RobotAccountHisServiceImpl implements RobotAccountHisService {

    @Autowired
    private RobotAccountHisMapper robotAccountHisMapper;

    /**
     * 插入账户变更记录 增加记录
     *
     * @param userId
     * @param size
     * @param content
     * @return
     */
    @Override
    public boolean addAccountHisForNow(Integer userId, Integer size, String content) {
        RobotAccountHis robotAccountHis = RobotAccountHis.builder()
                .gmtCreate(LocalDateTime.now())
                .modifyContent(content)
                .modifyMoney(size)
                .modifySource(2)
                .modifyType(2)
                .userId(userId)
                .build();
        int row = this.robotAccountHisMapper.insertSelective(robotAccountHis);
        if (row > 0){
            return true;
        }
        return false;
    }
}
