package com.admin.zhonghe.mapper.authority;

import com.admin.zhonghe.domain.authority.Permission;
import com.admin.zhonghe.domain.authority.SpPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import javax.xml.parsers.SAXParser;
import java.util.List;
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
     List<Permission> getByName(String name);
    List<Permission> getById(int id);
    List<SpPermission> getPermissionRole();
}
