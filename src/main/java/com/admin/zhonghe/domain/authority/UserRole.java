package com.admin.zhonghe.domain.authority;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("zh_user_role")
@Accessors(chain = false)
@ApiModel(description = "用户角色")
public class UserRole  implements Serializable {
  @ApiModelProperty(example ="用户角色ID")
  private int id;
  @ApiModelProperty(example ="用户ID")
  private int userId;
  @ApiModelProperty(example ="角色ID")
  private int roleId;
  @ApiModelProperty(example ="插入时间")
  @TableField(fill= FieldFill.INSERT)
  private LocalDateTime createTime;
  @ApiModelProperty(example ="更新时间")
  @TableField(fill = FieldFill.UPDATE)
  private LocalDateTime updateTime;
 }
