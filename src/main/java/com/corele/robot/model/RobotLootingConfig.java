package com.corele.robot.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


/**
 * @author liujun 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotLootingConfig {

    private Integer id;
    private String channel;
    private Integer max;
    private Integer min;
    private Double ratio;
    private Integer enable;
    private LocalDateTime gmtCreate;

}
