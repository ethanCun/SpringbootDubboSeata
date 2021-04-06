package com.ethan.seata.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("storage_tbl")
public class Storage implements Serializable {

    private static final long serialVersionUID = -9033031802438641666L;
    private Integer id;

    @TableField("commodity_code")
    private String commodityCode;

    private Integer count;
}
