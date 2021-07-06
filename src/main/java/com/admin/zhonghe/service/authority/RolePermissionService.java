package com.admin.zhonghe.service.authority;


import com.admin.zhonghe.domain.authority.RolePermission;
import com.admin.zhonghe.mapper.authority.RolePermissionMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;


@Service
public class RolePermissionService {
    @Autowired(required = false)
    private RolePermissionMapper rolePermissionMapper;
    public List<RolePermission> list(){
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(null);
       return rolePermissions;
    }
    public  List<RolePermission> getByRoleId(int roleId){
        LambdaQueryWrapper<RolePermission> lambda = new QueryWrapper<RolePermission>().lambda();
        lambda.eq(RolePermission::getRoleId,roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(lambda);
        return rolePermissions;
    }
    public  int del(int roleId){
        LambdaQueryWrapper<RolePermission> lambda = new QueryWrapper<RolePermission>().lambda();
        lambda.eq(RolePermission::getRoleId,roleId);
        int delNum = rolePermissionMapper.delete(lambda);
        return delNum;
    }
    public  int add(List<RolePermission> rolePermissions){
        if(rolePermissions!=null){
            Iterator<RolePermission> iterator = rolePermissions.iterator();
            while (iterator.hasNext()){
                RolePermission rolePermission = iterator.next();
                rolePermissionMapper.insert(rolePermission);
            }
        }
       return 1;
    }
}
