package com.corele.robot.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


/**
 * @author liujun 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RobotUser {

    @Id
    @GeneratedValue(generator="JDBC")
    private Integer id;
    /**
     * 用户标识
     */
    private String userNo;
    /**
     * 1 为QQ渠道
     */
    private Integer userType;
    /**
     * 添加时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 渠道标识
     */
    private String channel;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModify;
    /**
     * 数据版本
     */
    private Integer version;

}
