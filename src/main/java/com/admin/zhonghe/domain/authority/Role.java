package com.admin.zhonghe.domain.authority;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("zh_role")
@ApiModel(description = "角色")
public class Role implements Serializable {
    @ApiModelProperty(example ="角色ID")
    private int id;
    @ApiModelProperty(example ="角色英文名称")
    private  String role;
    @ApiModelProperty(example ="角色中文名称")
    private  String name;
    @ApiModelProperty(example ="标记")
    private String remark;
    @TableField(fill= FieldFill.INSERT)
    @ApiModelProperty(example ="创建时间")
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(example ="更新时间")
    private LocalDateTime updateTime;

}
