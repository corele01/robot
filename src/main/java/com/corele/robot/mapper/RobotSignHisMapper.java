package com.corele.robot.mapper;


import com.corele.robot.model.RobotSignHis;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * @author liujun 
 */
@Mapper
@Component
public interface RobotSignHisMapper extends tk.mybatis.mapper.common.Mapper<RobotSignHis> {
}
