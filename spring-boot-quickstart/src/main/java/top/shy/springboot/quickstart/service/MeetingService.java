package top.shy.springboot.quickstart.service;

import top.shy.springboot.quickstart.entity.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingService {
    private final List<Meeting> meetings = new ArrayList<>();
    public boolean issRoomAvailable(Meeting newMeeting){
        return meetings.stream().noneMatch(exitingMeeting -> exitingMeeting.isOverlapping(newMeeting));

    }
    public void addMeeting(Meeting meeting){
        meetings.add(meeting);
    }

}
