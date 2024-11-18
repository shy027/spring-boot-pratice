package top.shy.springboot.task.jobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
@Slf4j
public class ScheduledJobs {
//    @Scheduled(fixedRate = 3000)
//    public void fixedRatedJob() throws InterruptedException {
//        log.info("fixedRatedJob start :{}", new Date());
//        Thread.sleep(5000);
//        log.info("fixedRatedJob end :{}", new Date());
//    }

//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayJob() throws InterruptedException {
//        log.info("fixedDelayJob start :{}", new Date());
//        Thread.sleep(10000);
//        log.info("fixedDelayJob end :{}", new Date());
//    }

    @Scheduled(cron = "0/3 * * * * ? ")
    public void fixedDelayJob() throws InterruptedException {
        log.info("----------------cron 执行：{}",new Date());
    }
}
