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
@TableName("account_tbl")
public class User implements Serializable {

    private static final long serialVersionUID = 3873271585434687068L;

    private Integer id;

    @TableField("user_id")
    private String userId;

    private Integer money;
}
