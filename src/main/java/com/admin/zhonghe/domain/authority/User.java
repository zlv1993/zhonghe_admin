package com.admin.zhonghe.domain.authority;


import com.admin.zhonghe.validations.Phone;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "用户信息")
@TableName("zh_user")
public class User  implements Serializable {
    @ApiModelProperty("用户id")

    @TableId(type = IdType.AUTO)
    private int id;
    @NotNull(message = "用户名不能为空")
    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户密码")
    @NotEmpty(message = "密码不能为空")
    private String password;
    @ApiModelProperty("用户邮箱")
    @Email
    private String email;
    @ApiModelProperty("用户手机")
    @Phone
    private String phone;
    @ApiModelProperty("用户匿名")
    private String nickName;
    @ApiModelProperty("用户头像")
    @NotEmpty(message = "用户头像不能为空")
    private String avator;
    @ApiModelProperty("用户地址")
    private String address;
    @ApiModelProperty("创建时间")
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;
}
