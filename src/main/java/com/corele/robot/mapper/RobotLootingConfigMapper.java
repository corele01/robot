package com.corele.robot.mapper;


import com.corele.robot.model.RobotLootingConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


/**
 * @author liujun 
 */
@Mapper
@Component
public interface RobotLootingConfigMapper extends tk.mybatis.mapper.common.Mapper<RobotLootingConfig> {
}
