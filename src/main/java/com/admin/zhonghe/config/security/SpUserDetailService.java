package com.admin.zhonghe.config.security;


import com.admin.zhonghe.domain.authority.SpRole;
import com.admin.zhonghe.domain.authority.SpUser;
import com.admin.zhonghe.domain.authority.User;
import com.admin.zhonghe.mapper.authority.RoleMapper;
import com.admin.zhonghe.mapper.authority.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("userDetailService")
public class SpUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SpUser user=userMapper.getSpUser(username);
        if(user!=null){
            int userId=user.getId();
            List<SpRole> roles=roleMapper.getRolesByUserId(userId);
            System.out.println(roles);
            user.setAuthorities(roles);
        }else{
            throw  new UsernameNotFoundException("用户名或密码不正确");
        }

        return user;
    }
}
