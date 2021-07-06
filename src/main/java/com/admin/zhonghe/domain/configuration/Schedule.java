package com.admin.zhonghe.domain.configuration;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@ApiModel("定时器")
public class Schedule {
    @ApiModelProperty(example = "ID")
    private int id;
    @ApiModelProperty(example ="定时任务")
    @NotEmpty(message = "定时任务不能为空")
    private String cron;
    @ApiModelProperty(example ="备注")
    private String remark;
    @TableField(fill= FieldFill.INSERT)
    @ApiModelProperty(example ="创建时间")
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(example ="更新时间")
    private LocalDateTime updateTime;
}
