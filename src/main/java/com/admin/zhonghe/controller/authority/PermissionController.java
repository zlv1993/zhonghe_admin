package com.admin.zhonghe.controller.authority;


import com.admin.zhonghe.domain.authority.Permission;
import com.admin.zhonghe.service.authority.PermissionService;
import com.admin.zhonghe.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/permission")
@Api(tags = "权限管理---权限")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/list")
    @ApiOperation("列表")
    private R list(){
        List<Permission> permissions= permissionService.list();
        return  R.ok(permissions);
    }
    @GetMapping("/getByName")
    @ApiOperation("根据用户名获取权限")
    @ApiImplicitParam(name="username",value = "用户名" ,paramType = "query",required = true)
    private R get(String username) {
        List<Permission> permissionVos=  permissionService.getByName(username);
        return  R.ok(permissionVos);
    }

    @PostMapping("/add")
    @ApiOperation("新增权限")
    private   R add(@RequestBody @Validated Permission permission){
       permissionService.add(permission);
        return  R.ok();
    }
    @DeleteMapping("/del/{id}")
    @ApiOperation("删除")
    @ApiImplicitParam(name="id",value ="权限Id",paramType = "path",required = true)
    private   R del(@NotEmpty @PathVariable int id){
        permissionService.del(id);
        return  R.ok();
    }
    @DeleteMapping("/delBatch")
    @ApiOperation("批量删除")
    @ApiImplicitParam(name="ids",value ="权限id集合",paramType = "query",required = true)
    private   R delBatch( String ids){

        Integer[] idArray =stringConvertInt(ids);
       List<Integer> idlist= Arrays.asList(idArray);
        permissionService.delBatch(idlist);
            return  R.ok();

    }
    private Integer[] stringConvertInt(String value) {
        Integer[] intArr = new Integer[0];
        if(isNull(value)){
            intArr = new Integer[0];
        }else{
            String[] valueArr = value.split(",");
            intArr = new Integer[valueArr.length];
            for (int i = 0; i < valueArr.length; i++) {
                intArr[i] = Integer.parseInt(valueArr[i]);
            }
        }
        return intArr;
    }
    private static boolean isNull(String param){
        if(param==null||param.isEmpty()||param.trim().equals("")) return true;
        return false;
    }


    @GetMapping("/getById")
    @ApiOperation("根据用户Id获取权限")
    @ApiImplicitParam(name="roleId",value = "用户id" ,paramType = "query",required = true)
    private  R getByRoleId(int roleId){
        List<Permission> permissions=  permissionService.getById(roleId);
        return  R.ok(permissions);
    }
    @PutMapping("/update")
    @ApiOperation("更新")
    private  R update(@RequestBody Permission permission){
        permissionService.update(permission);
        return  R.ok("更新成功");
    }
}
