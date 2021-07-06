package com.admin.zhonghe.controller.configuration;

import com.admin.zhonghe.domain.configuration.Schedule;
import com.admin.zhonghe.service.configuration.ScheduleService;
import com.admin.zhonghe.utils.PageUtil;
import com.admin.zhonghe.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/config")
@Api(tags = "配置模块--定时器")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation("列表")
    @GetMapping("/list")
    @ApiImplicitParams({
         @ApiImplicitParam(name = "pageNUm",value = "页数",required = true,paramType = "query"),
         @ApiImplicitParam(name = "pageSize",value = "条数",required = true,paramType = "query"),
    })
    public PageUtil list(@NotEmpty(message = "页码不能为空") int pageNUm,@NotEmpty(message ="页数不能为空" ) int pageSize ){
        return scheduleService.list(pageNUm,pageSize);
    }

    @ApiOperation("更新")
    @PutMapping("/update")
    public R update(@RequestBody @Valid Schedule schedule){
        scheduleService.update(schedule);
        return R.ok();

    }

    @ApiOperation("根据id查找")
    @GetMapping("/getById")
    @ApiImplicitParam(name = "id",value = "ID",required = true,paramType = "query")
    public R getById(int id){
      Schedule schedule=   scheduleService.getById(id);
        return R.ok(schedule);

    }

}
