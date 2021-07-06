package com.admin.zhonghe.domain.authority;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserBo extends User implements Serializable {
    private  int roleId;
    private  int userId;
    private String roleName;
}
