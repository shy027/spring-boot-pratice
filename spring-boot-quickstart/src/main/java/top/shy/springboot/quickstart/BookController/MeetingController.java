package top.shy.springboot.quickstart.BookController;

import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.shy.springboot.quickstart.entity.Meeting;
import top.shy.springboot.quickstart.service.MeetingService;

@RestController
@RequestMapping("/meeting")
public class MeetingController {
    @Resource
    private MeetingService meetingService;

    @PostMapping("/check")
    public ResponseEntity<String> check(@RequestBody Meeting meeting){
        if (meetingService.issRoomAvailable(meeting)){
            meetingService.addMeeting(meeting);
            return ResponseEntity.ok("可用");
        }else {
            return ResponseEntity.ok("不可用");
        }

    }

}
