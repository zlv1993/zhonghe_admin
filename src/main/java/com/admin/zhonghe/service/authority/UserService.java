package com.admin.zhonghe.service.authority;



import com.admin.zhonghe.domain.authority.User;
import com.admin.zhonghe.domain.authority.UserBo;
import com.admin.zhonghe.domain.authority.UserRole;
import com.admin.zhonghe.domain.authority.UserRoleBo;
import com.admin.zhonghe.mapper.authority.UserMapper;
import com.admin.zhonghe.mapper.authority.UserRoleMapper;
import com.admin.zhonghe.utils.JwtTokenUtil;
import com.admin.zhonghe.utils.PageUtil;
import com.admin.zhonghe.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    public User getById(int id){

        return userMapper.selectById(id);
    }
    public User getByToken(String token){
      String userName= jwtTokenUtil.getUserNameFromToken(token);
        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        lambda.eq(User::getUsername,userName);
        return userMapper.selectOne(lambda);
    }
    public List<User> all(){

        return userMapper.selectList(null);
    }
    public User getByName(String username){
        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
         lambda.eq(User::getUsername,username);
        return userMapper.selectOne(lambda);
    }
    public PageUtil list(int pageNum, int pageSize, String username, Integer roleId){
        PageHelper.startPage(pageNum, pageSize);
        List<UserBo> allUser= userMapper.list(username,roleId);
        PageInfo<UserBo> pageInfo =new PageInfo<>(allUser);
        PageUtil<UserBo> pageUtil=new PageUtil<>(pageInfo);
        return pageUtil;
    }
    @Transactional(rollbackFor = {RuntimeException.class})
    public  R add(UserRoleBo userRoleBo){
        User user= userRoleBo.getUser();
        UserRole userRole= userRoleBo.getUserRole();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
        int userId= user.getId();
        userRole.setUserId(userId);
        userRoleMapper.insert(userRole);
        return   R.ok();

    }
    public int del(int id){
        LambdaQueryWrapper<UserRole> lambda = new QueryWrapper<UserRole>().lambda();
        lambda.eq(UserRole::getUserId,id);
         userRoleMapper.delete(lambda);
        return  userMapper.deleteById(id);
    }
    @Transactional
    public R update(UserRoleBo userRoleBo){
        User user= userRoleBo.getUser();
        UserRole userRole= userRoleBo.getUserRole();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userMapper.updateById(user);
        LambdaQueryWrapper<UserRole> lambda = new QueryWrapper<UserRole>().lambda();
        lambda.eq(UserRole::getUserId,user.getId());
        userRoleMapper.update(userRole,lambda);
        return R.ok("更新成功");
    }
}
