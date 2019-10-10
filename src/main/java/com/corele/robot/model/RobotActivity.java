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
public class RobotActivity {

    @Id
    private Integer id;
    /**
     * 活动名称
     */
    private String name;
    /**
     * 有效时间 1永久 2固定时间
     */
    private Integer validTimeType;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 状态 1启用 0未启用
     */
    private Integer status;
    /**
     * 插入状态
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改状态
     */
    private LocalDateTime gmtModify;
    /**
     * 数据版本
     */
    private Integer version;

}
