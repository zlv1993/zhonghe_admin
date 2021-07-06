package com.admin.zhonghe.domain.authority;

import lombok.Data;


import java.io.Serializable;
import java.util.List;
@Data
public class SpPermission  extends Permission  implements Serializable {

    private List<SpRole> spRoles;


}
