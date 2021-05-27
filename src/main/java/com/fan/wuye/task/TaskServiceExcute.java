package com.fan.wuye.task;

import com.fan.wuye.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class TaskServiceExcute {
    @Autowired
    TaskService taskService;

    //更新车位状态和用户车位费用,定时规则：每分钟执行一次
    @Scheduled(cron = "0 0/1 * * * ?")
    //更新车位状态和用户车位费用,定时规则：每5s执行一次
    //@Scheduled(cron="0/5 * *  * * ? ")
    public void updateData() {
        taskService.updateData();
    }
}
