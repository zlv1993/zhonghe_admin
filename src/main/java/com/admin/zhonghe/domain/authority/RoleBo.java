package com.admin.zhonghe.domain.authority;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleBo extends Role  implements Serializable {
    private List<RolePermission> rolePermissions;

}
