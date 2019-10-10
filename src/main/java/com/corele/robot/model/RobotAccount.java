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
public class RobotAccount {

    @Id
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 货币余额
     */
    private Integer accountMoney;
    /**
     * 记录插入时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 记录修改时间
     */
    private LocalDateTime gmtModify;
    /**
     * 数据版本
     */
    private Integer version;
    /**
     * 是否启用
     */
    private Integer enable;

}
