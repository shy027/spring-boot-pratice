package top.shy.springboot.task.jobs;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.shy.springboot.task.service.MailService;

import java.time.LocalDateTime;

//@Component
@AllArgsConstructor
@Slf4j
public class DailyReportTask {
    private final MailService mailService;
    @Scheduled(cron = "0 48 15 * * ?")
    public void sendReport(){
        String report = "每日报表的内容";
        mailService.sendEmail("1533103845@qq.com","每日数据报表",report);
        log.info("报表已生成，并发送邮件完成：{}", LocalDateTime.now());
    }
}
