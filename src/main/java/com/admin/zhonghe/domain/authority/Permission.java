package com.admin.zhonghe.domain.authority;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("zh_permission")
public class Permission implements Serializable {
    private int id;
    private int pid;
    private String url;
    private String redirect;
    private String component;
    private String name;
    private String icon;
    private int type;
    private int jump;
    private int display;
    @TableField(fill= FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

}
