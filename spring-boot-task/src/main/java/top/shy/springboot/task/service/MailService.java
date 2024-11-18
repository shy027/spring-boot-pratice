package top.shy.springboot.task.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import top.shy.springboot.task.entity.EmailLog;
import top.shy.springboot.task.mapper.EmailLogMapper;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;
    private final EmailLogMapper emailLogMapper;

    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1533103845@qq.com");
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailSender.send(mailMessage);
        EmailLog emailLog = new EmailLog();
        emailLog.setRecipient(to);
        emailLog.setContent(content);
        emailLog.setSubject(subject);
        emailLog.setSentAt(LocalDateTime.now());
        emailLogMapper.insert(emailLog);
        log.info("邮件已发送：{}",to);
    }
}
