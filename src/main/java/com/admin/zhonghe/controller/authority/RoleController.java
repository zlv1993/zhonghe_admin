package com.admin.zhonghe.controller.authority;


import com.admin.zhonghe.domain.authority.Role;
import com.admin.zhonghe.domain.authority.RoleBo;
import com.admin.zhonghe.service.authority.RolePermissionService;
import com.admin.zhonghe.service.authority.RoleService;
import com.admin.zhonghe.utils.PageUtil;
import com.admin.zhonghe.utils.R;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("/role")
@Api(tags = "权限管理--角色")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @GetMapping("/list")
    @ApiOperation("列表")
    @Cacheable(value = "role",key="123" )
     public PageUtil list(@NotEmpty(message = "页码不能为空") int  pageNum,@NotEmpty(message = "分页限制数不能为空")  int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roles=  roleService.list();
        PageInfo<Role> pageInfo =new PageInfo<>(roles);
        PageUtil<Role> pageUtil=new PageUtil<>(pageInfo);
        return pageUtil;
    }

    @GetMapping("/all")
    @ApiOperation("所有角色")
    public R all(){
        List<Role> roles=roleService.list();
        return R.ok(roles);
    }

    @PostMapping("/add")
    @ApiOperation("新增")
    public R add(@RequestBody @Validated RoleBo roleBo) {
        return roleService.add(roleBo);
    }
    @PutMapping("/update")
    @ApiOperation("更新")
    public R update(@RequestBody RoleBo roleBo){

        return roleService.update(roleBo);
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("删除")
    @ApiImplicitParam(name = "id",value = "角色id",required = true,paramType = "path")
    public R del(@PathVariable int id){
        roleService.del(id);
        return R.ok();
    }

}
