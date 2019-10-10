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
public class RobotMsgConfig {

    @Id
    private Integer id;
    /**
     * 消息匹配正则表达式
     */
    private String expression;
    /**
     * 处理器标识
     */
    private String processorNo;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModify;
    private Integer version;
    /**
     * 启用 1启用0未启用
     */
    private Integer enable;

}
