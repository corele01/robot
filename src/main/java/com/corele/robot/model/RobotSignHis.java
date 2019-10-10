package com.corele.robot.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * @author liujun 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotSignHis {

    @Id
    private Integer id;
    private String channel;
    private Integer userId;
    private Integer size;
    private LocalDate gmtSign;

}
