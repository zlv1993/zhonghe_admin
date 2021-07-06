package com.admin.zhonghe.service.authority;



import com.admin.zhonghe.domain.authority.Role;
import com.admin.zhonghe.domain.authority.RoleBo;
import com.admin.zhonghe.domain.authority.RolePermission;
import com.admin.zhonghe.mapper.authority.RoleMapper;
import com.admin.zhonghe.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;


@Service
public class RoleService {
    @Autowired(required = false)
    private RoleMapper roleMapper;
    @Autowired(required = false)
    private RolePermissionService rolePermissionService;
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public R add(RoleBo roleBo){
        roleMapper.insert(roleBo);
        int id= roleBo.getId();
        List<RolePermission> rolePermissions = roleBo.getRolePermissions();
        Iterator<RolePermission> iterator = rolePermissions.iterator();
        while (iterator.hasNext()){
            RolePermission rolePermission= iterator.next();
            rolePermission.setRoleId(id);
        }

        rolePermissionService.add(rolePermissions);
        return R.ok();
    }
    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    public R update(RoleBo roleBo){
        roleMapper.updateById(roleBo);
        rolePermissionService.del(roleBo.getId());
        rolePermissionService.add(roleBo.getRolePermissions());
        return R.ok();
    }
    public List<Role> list(){
        return roleMapper.selectList(null);
    }
    public  int del(int id){
        return roleMapper.deleteById(id);
    }
}
