package com.corele.robot.processor.dto;

import com.corele.robot.model.RobotUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liujun
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageContext {

    private String message;
    private String groupNo;
    private String sendNo;
    private RobotUser user;
}
