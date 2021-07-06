package com.admin.zhonghe.mapper.authority;


import com.admin.zhonghe.domain.authority.SpUser;
import com.admin.zhonghe.domain.authority.User;
import com.admin.zhonghe.domain.authority.UserBo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<UserBo> list(String username, Integer roleId);
    SpUser getSpUser(String username);
}
