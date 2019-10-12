package com.corele.robot.service.impl;


import com.corele.robot.mapper.RobotAccountMapper;
import com.corele.robot.model.RobotAccount;
import com.corele.robot.service.RobotAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDateTime;


/**
 * @author liujun 
 */
@Service
public class RobotAccountServiceImpl implements RobotAccountService {

    @Autowired
    private RobotAccountMapper robotAccountMapper;

    /**
     * 获取用户账户信息
     *
     * @param userId
     * @return
     */
    @Override
    public RobotAccount getByUserId(Integer userId) {
        Example example = new Example(RobotAccount.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        return this.robotAccountMapper.selectOneByExample(example);
    }

    /**
     * 创建并初始化账户
     *
     * @param userId
     * @return
     */
    @Override
    public RobotAccount createAndInit(Integer userId) {
        RobotAccount robotAccount = RobotAccount.builder()
                .accountMoney(0)
                .enable(1)
                .gmtCreate(LocalDateTime.now())
                .userId(userId)
                .build();
        this.robotAccountMapper.insertSelective(robotAccount);
        return robotAccount;
    }

    /**
     * 更新账户金额
     *
     * @param userId
     * @param oldMoney
     * @param newMoney
     * @return
     */
    @Override
    public boolean updateAccount(Integer userId, Integer oldMoney, Integer newMoney) {
        Example example = new Example(RobotAccount.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("accountMoney",oldMoney);
        RobotAccount account = RobotAccount.builder().userId(userId).accountMoney(newMoney).gmtModify(LocalDateTime.now()).build();
        int row = this.robotAccountMapper.updateByExampleSelective(account, example);
        if (row > 0){
            return true;
        }
        return false;
    }
}
