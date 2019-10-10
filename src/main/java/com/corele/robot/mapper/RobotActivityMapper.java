package com.corele.robot.mapper;


import com.corele.robot.model.RobotActivity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * @author liujun 
 */
@Mapper
@Component
public interface RobotActivityMapper extends tk.mybatis.mapper.common.Mapper<RobotActivity> {
}
