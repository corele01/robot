package com.corele.robot.service.impl;


import com.corele.robot.mapper.RobotUserMapper;
import com.corele.robot.model.RobotUser;
import com.corele.robot.service.RobotUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;


/**
 * @author liujun 
 */
@Service
public class RobotUserServiceImpl implements RobotUserService {

    @Autowired
    private RobotUserMapper robotUserMapper;

    /**
     * 获取用户
     *
     * @param groupNo
     * @param sendNo
     * @return
     */
    @Override
    public RobotUser getUserBySendNo(String groupNo, String sendNo) {
        Example example = new Example(RobotUser.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userNo",sendNo);
        criteria.andEqualTo("channel",groupNo);

        return this.robotUserMapper.selectOneByExample(example);
    }

    /**
     * 创建并初始化
     *
     * @param groupNo
     * @param sendNo
     * @return
     */
    @Override
    public RobotUser createAndInit(String groupNo, String sendNo) {
        RobotUser user = RobotUser.builder()
                .channel(groupNo)
                .userNo(sendNo)
                .userType(1)
                .version(0)
                .gmtCreate(LocalDateTime.now())
                .build();

        int row = this.robotUserMapper.insertSelective(user);
        if (row > 0){
            return user;
        }
        return null;
    }
}
