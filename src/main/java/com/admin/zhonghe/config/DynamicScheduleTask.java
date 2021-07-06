package com.admin.zhonghe.config;


import com.admin.zhonghe.domain.configuration.Schedule;
import com.admin.zhonghe.domain.configuration.RabbitMsg;
import com.admin.zhonghe.service.configuration.ScheduleService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

//@Configuration
//@EnableScheduling
public class DynamicScheduleTask implements SchedulingConfigurer {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> {
                    RabbitMsg rabbitMsg =new RabbitMsg();
                    rabbitMsg.setTo("zhonglx");
                    rabbitMsg.setFrom("wulei");
                    rabbitMsg.setMsg(LocalDateTime.now().toString());
                    rabbitTemplate.convertAndSend("queue",rabbitMsg);
                },
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1从数据库获取执行周期
                    Schedule schedule = scheduleService.getById(1);
                    String cron=schedule.getCron();
                    //2.2 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
