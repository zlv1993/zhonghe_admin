package com.admin.zhonghe.domain.authority;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("zh_role_permission")
@Accessors(chain = false)
public class RolePermission  implements Serializable {
    private int id;
    private int roleId;
    private int permissionId;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
