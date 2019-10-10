package com.corele.robot.mapper;


import com.corele.robot.model.RobotSign;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * @author liujun 
 */
@Mapper
@Component
public interface RobotSignMapper extends tk.mybatis.mapper.common.Mapper<RobotSign> {
}
