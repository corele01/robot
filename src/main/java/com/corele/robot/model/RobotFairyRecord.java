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
public class RobotFairyRecord {

    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 精灵名称
     */
    private String fairyName;
    /**
     * 攻击
     */
    private Integer attack;
    /**
     * 防御
     */
    private Integer defense;
    /**
     * 等级
     */
    private Integer level;
    /**
     * 经验
     */
    private Integer experience;
    /**
     * 资质名称
     */
    private String wisdomName;
    /**
     * 资质ID
     */
    private Integer wisdom;
    /**
     * 资质属性
     */
    private Double wisdomRatio;
    private LocalDateTime gmtCreate;
    private String levelInfo;
    private Integer fairyTempId;

}
