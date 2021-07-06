package com.admin.zhonghe.domain.authority;
;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SpRole  implements  Serializable , GrantedAuthority {
    private int id;
    private  String role;
    private  String name;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Override
    public String getAuthority() {
        return role;
    }


}