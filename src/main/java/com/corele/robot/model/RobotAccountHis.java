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
public class RobotAccountHis {

    @Id
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 变换数量
     */
    private Integer modifyMoney;
    /**
     * 变换类型  1为扣减  2为增加
     */
    private Integer modifyType;
    /**
     * 来源 1为转账  2为活动
     */
    private Integer modifySource;
    /**
     * 添加时间
     */
    private LocalDateTime gmtCreate;
    /**
     * 修改时间
     */
    private LocalDateTime gmtModify;
    private Integer version;
    /**
     * 修改描述
     */
    private String modifyContent;

}
