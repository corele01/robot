package com.corele.robot.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
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
public class RobotFairyTemp {

    @Id
    @GeneratedValue(generator="JDBC")
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 贴图地址
     */
    private String imgUrl;
    /**
     * 攻击最小
     */
    private Integer attackMin;
    /**
     * 攻击最大
     */
    private Integer attackMax;
    /**
     * 防御最小
     */
    private Integer defenseMin;
    /**
     * 防御最大
     */
    private Integer defenseMax;
    private LocalDateTime gmtCreate;
    private Integer enable;
    /**
     * 等级信息
     */
    private String levelInfo;
    /**
     * 资质信息
     */
    private String wisdom;

    private Integer fee;

}
