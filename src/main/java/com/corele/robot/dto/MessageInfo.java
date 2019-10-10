package com.corele.robot.dto;

import lombok.Data;

/**
 * @author liujun
 */
@Data
public class MessageInfo {
    /**
     * 发送者
     */
    private String sendNo;
    /**
     * 群号
     */
    private String groupNo;
    /**
     * 消息
     */
    private String message;
}
