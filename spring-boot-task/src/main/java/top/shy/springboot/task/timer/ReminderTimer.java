package top.shy.springboot.task.timer;

import lombok.extern.slf4j.Slf4j;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class ReminderTimer {
    public static void main(String[] args) {
        Timer timer = new  Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                log.info("请休息一下，喝口水吧");
            }
        };
        timer.schedule(task,0,5000);
    }
}
