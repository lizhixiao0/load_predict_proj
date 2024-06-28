package com.li.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName lp_cleaning_methods
 */
@TableName(value ="lp_cleaning_methods")
@Data
public class LpCleaningMethods implements Serializable {
    /**
     * 清洗方法ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 清洗方法名称
     */
    private String method_name;

    /**
     * 清洗方法描述
     */
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}