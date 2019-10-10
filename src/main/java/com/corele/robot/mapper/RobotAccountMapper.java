package com.corele.robot.mapper;


import com.corele.robot.model.RobotAccount;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * @author liujun 
 */
@Mapper
@Component
public interface RobotAccountMapper extends tk.mybatis.mapper.common.Mapper<RobotAccount> {
}
