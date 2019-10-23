package com.corele.robot.service;


import com.corele.robot.model.RobotFairyTemp;

import java.util.List;

/**
 * @author liujun 
 */
public interface RobotFairyTempService {
    /**
     * 获取精灵模板
     * @return
     */
    List<RobotFairyTemp> getAll();

    /**
     * 查询某个精灵
     * @param fairyId
     * @return
     */
    RobotFairyTemp getById(int fairyId);
}
