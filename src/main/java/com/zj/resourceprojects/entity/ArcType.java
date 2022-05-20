package com.zj.resourceprojects.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("arc_type")
@NoArgsConstructor
@AllArgsConstructor
public class ArcType {


    @TableId(value = "arcType_id",type = IdType.AUTO)
    private Integer arcTypeId;

    @TableField("arc_type_name")
    private String arcTypeName;

    @TableField("remark")
    private String remark;

    @TableField("sort")
    private Integer sort;
}
