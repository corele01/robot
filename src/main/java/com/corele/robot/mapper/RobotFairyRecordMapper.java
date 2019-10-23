package com.corele.robot.mapper;


import com.corele.robot.model.RobotFairyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * @author liujun 
 */
@Mapper
@Component
public interface RobotFairyRecordMapper extends tk.mybatis.mapper.common.Mapper<RobotFairyRecord> {
}
