package com.admin.zhonghe.service.configuration;


import com.admin.zhonghe.domain.authority.UserBo;
import com.admin.zhonghe.domain.configuration.Schedule;
import com.admin.zhonghe.mapper.configuration.ScheduleMapper;
import com.admin.zhonghe.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleMapper scheduleMapper;
    public Schedule getById(int id){
      return  scheduleMapper.selectById(id);
    }
    @Transactional
    public PageUtil  list(int pageNum,int pageSize){
       PageHelper.startPage(pageNum, pageSize);
        List<Schedule> schedules=scheduleMapper.selectList(null);;
        PageInfo<Schedule> pageInfo =new PageInfo<>(schedules);
        PageUtil<Schedule> pageUtil=new PageUtil<>(pageInfo);
        return pageUtil;
    }
    public int  update(Schedule schedule){
        return scheduleMapper.updateById(schedule);
    }
}
