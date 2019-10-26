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
public class RobotFairySearchConfig {

    private Integer id;
    private String channel;
    private String fairyList;
    private LocalDateTime gmtCreate;

}
