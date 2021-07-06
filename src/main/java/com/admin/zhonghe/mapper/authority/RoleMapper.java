package com.admin.zhonghe.mapper.authority;

import com.admin.zhonghe.domain.authority.Role;

import com.admin.zhonghe.domain.authority.SpRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper  extends BaseMapper<Role> {
    List<SpRole> getRolesByUserId(int userId);
}
