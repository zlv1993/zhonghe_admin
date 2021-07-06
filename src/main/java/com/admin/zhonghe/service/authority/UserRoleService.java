package com.admin.zhonghe.service.authority;



import com.admin.zhonghe.domain.authority.UserRole;
import com.admin.zhonghe.mapper.authority.UserRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;
    public  int insert(UserRole userRole){ return userRoleMapper.insert(userRole);}
    public int  update(UserRole userRole){return userRoleMapper.updateById(userRole);}

}
