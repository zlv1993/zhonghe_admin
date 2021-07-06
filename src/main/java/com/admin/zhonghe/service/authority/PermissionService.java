package com.admin.zhonghe.service.authority;


import com.admin.zhonghe.domain.authority.Permission;
import com.admin.zhonghe.domain.authority.SpPermission;
import com.admin.zhonghe.mapper.authority.PermissionMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired(required = false)
    private PermissionMapper permissionMapper;
    public List<Permission> list(){
        return permissionMapper.selectList(null);
    }
    public List<Permission> getByName(String username){

        return permissionMapper.getByName(username);
    }
    public List<Permission> getById(int roleId){
        return permissionMapper.getById(roleId);
    }
    public int add(Permission permission){
        return  permissionMapper.insert(permission);
    }
    public int del(int id){
        return  permissionMapper.deleteById(id);
    }
    public int delBatch(List<Integer> ids){
        return permissionMapper.deleteBatchIds(ids);
    }
    public int update(Permission permission){
        return permissionMapper.updateById(permission);
    }
    public List<SpPermission> getPermissionRole(){
        return permissionMapper.getPermissionRole();
    }
}
