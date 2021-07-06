package com.admin.zhonghe.domain.configuration;

import lombok.Data;

import java.io.Serializable;

@Data
public class RabbitMsg implements Serializable  {
    private String to;
    private String from;
    private String msg;

}
