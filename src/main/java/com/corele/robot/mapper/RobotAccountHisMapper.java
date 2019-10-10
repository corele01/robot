package com.corele.robot.mapper;


import com.corele.robot.model.RobotAccountHis;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * @author liujun 
 */
@Mapper
@Component
public interface RobotAccountHisMapper extends tk.mybatis.mapper.common.Mapper<RobotAccountHis> {
}
