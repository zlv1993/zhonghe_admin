package com.admin.zhonghe.controller.authority;

import com.admin.zhonghe.domain.authority.User;
import com.admin.zhonghe.domain.authority.UserRoleBo;
import com.admin.zhonghe.service.authority.UserRoleService;
import com.admin.zhonghe.service.authority.UserService;
import com.admin.zhonghe.utils.PageUtil;
import com.admin.zhonghe.utils.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(tags="权限管理--用户模块")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/getById/{id}")
    @ApiOperation("查询id")
    @ApiImplicitParam(paramType ="path",name="id",value ="用户id")
    private R getById(@PathVariable Integer id){
        User user=  userService.getById(id);
        user.setPassword("");
        return   R.ok(user);
    }
    @GetMapping("/getByToken")
    @ApiOperation("查询token")
    @ApiImplicitParam(paramType ="path",name="token",value ="用户token")
    private R getByToken( String token){
        User user=  userService.getByToken(token);
        user.setPassword("");
        return   R.ok(user);
    }

    @GetMapping("/list")
    @ApiOperation("列表")
    @ApiImplicitParams({
       @ApiImplicitParam (paramType = "query",name = "pageNum",value ="页数",required = true,dataType = "Integer"),
       @ApiImplicitParam (paramType = "query",name = "pageSize",value ="条数",required = true,dataType = "Integer"),
       @ApiImplicitParam (paramType = "query",name = "username",value ="用户名",dataType = "String"),
       @ApiImplicitParam (paramType = "query",name = "roleId",value ="角色ID",dataType = "Integer")
    })
    public PageUtil list(int pageNum, int pageSize, String username, Integer roleId){
        PageUtil pageUtil = userService.list(pageNum,pageSize,username,roleId);
        return pageUtil;
    }
    @PostMapping("/add")
    @ApiOperation(value = "新增")
    public R insert(@RequestBody @Valid UserRoleBo userRoleBo){
          return userService.add(userRoleBo);
    }
    @PutMapping("/update")
    @ApiOperation("更新")
    public R update( @RequestBody @Valid UserRoleBo userRoleBo){
       return userService.update(userRoleBo);

    }
    @DeleteMapping("/del/{id}")
    @ApiOperation("删除")
    @ApiImplicitParam(paramType ="path",name="id",value ="用户id")
    public R del(@PathVariable int id){
        userService.del(id);
        return R.ok();
    }
}
