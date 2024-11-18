package top.shy.springboot.task.timer;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class CountdownTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerTask task = new TimerTask(){
            int countdowm = 10;
            @Override
            public void run() {
                if (countdowm > 0){
                    log.info("倒计时：{}秒",countdowm);
                    countdowm --;
                }else {
                    log.info("倒计时结束");
                    timer.cancel();
                }
            }
        };
        timer.schedule(task,0,1000);
    }
}
