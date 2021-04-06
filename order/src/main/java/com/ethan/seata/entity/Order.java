package com.ethan.seata.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("order_tbl")
public class Order {

    private Integer id;

    @TableField("user_id")
    private String userId;

    @TableField("commodity_code")
    private String commodityCode;

    private Integer count;

    private Integer money;
}
