package com.corele.robot.controller;

import com.corele.robot.model.RobotUser;
import com.corele.robot.service.RobotAccountService;
import com.corele.robot.service.RobotUserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liujun
 */
public abstract class BaseController {

    @Autowired
    private RobotUserService robotUserService;
    @Autowired
    private RobotAccountService robotAccountService;

    protected RobotUser getUser(String groupNo,String sendNo){
        RobotUser user = this.robotUserService.getUserBySendNo(groupNo, sendNo);
        if (user == null){
            user = this.robotUserService.createAndInit(groupNo,sendNo);
            this.robotAccountService.createAndInit(user.getId());
        }
        return user;
    }
}
