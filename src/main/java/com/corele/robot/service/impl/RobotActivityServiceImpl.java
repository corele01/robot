package com.corele.robot.service.impl;


import com.corele.robot.mapper.RobotActivityMapper;
import com.corele.robot.service.RobotActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author liujun 
 */
@Service
public class RobotActivityServiceImpl implements RobotActivityService {

    @Autowired
    private RobotActivityMapper robotActivityMapper;
}
