package com.corele.robot.service;


import com.corele.robot.model.RobotFairyRecord;

import java.util.List;

/**
 * @author liujun 
 */
public interface RobotFairyRecordService {

    /**
     * 添加一个精灵
     * @param robotFairyRecord
     * @return
     */
    boolean addFairyRecord(RobotFairyRecord robotFairyRecord);

    /**
     * 查询某个用户精灵列表
     * @param userId
     * @return
     */
    List<RobotFairyRecord> queryFairyByUserId(Integer userId);

    /**
     * 删除一个精灵
     * @param fairyId
     * @param userId
     * @return
     */
    boolean delFairyForId(Integer fairyId,Integer userId);

    /**
     * 查询精灵数量
     * @param userId
     * @return
     */
    int getCountForUserId(Integer userId);
}
