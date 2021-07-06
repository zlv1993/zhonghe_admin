package com.admin.zhonghe.domain.authority;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Data
@ApiModel
@Accessors(chain = false)
public class UserRoleBo implements Serializable {
   @ApiModelProperty(example ="用户信息")
   private User user;
   @ApiModelProperty(example ="用户角色信息")
   private UserRole userRole;
}
