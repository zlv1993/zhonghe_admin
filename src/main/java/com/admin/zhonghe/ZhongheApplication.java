package com.admin.zhonghe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class ZhongheApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZhongheApplication.class, args);
    }

}
