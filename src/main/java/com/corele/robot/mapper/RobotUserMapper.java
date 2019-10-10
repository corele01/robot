package com.corele.robot.mapper;


import com.corele.robot.model.RobotUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * @author liujun 
 */
@Mapper
@Component
public interface RobotUserMapper extends tk.mybatis.mapper.common.Mapper<RobotUser> {
}
