package com.corele.robot.mapper;


import com.corele.robot.model.RobotMsgConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * @author liujun 
 */
@Mapper
@Component
public interface RobotMsgConfigMapper extends tk.mybatis.mapper.common.Mapper<RobotMsgConfig> {
}
