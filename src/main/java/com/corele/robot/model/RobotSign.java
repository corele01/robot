package com.corele.robot.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDateTime;


/**
 * @author liujun 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotSign {

    @Id
    private Integer id;
    private LocalDateTime gmtCreate;
    private Integer version;
    private Integer min;
    private Integer max;
    private Integer enable;
    private String channel;

}
