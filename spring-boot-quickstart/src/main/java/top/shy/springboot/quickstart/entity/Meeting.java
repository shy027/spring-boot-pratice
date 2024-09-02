package top.shy.springboot.quickstart.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Meeting {
    private Long id;

    public Meeting(Long id, LocalDate date, LocalTime starttime, LocalTime endtime) {
        this.id = id;
        this.date = date;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    private LocalDate date;
    private LocalTime starttime;
    private LocalTime endtime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalTime starttime) {
        this.starttime = starttime;
    }

    public LocalTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalTime endtime) {
        this.endtime = endtime;
    }

    public boolean isOverlapping(Meeting other) {
        return this.date.equals(other.date) &&
                (this.starttime.isBefore(other.endtime) && this.endtime.isAfter(other.starttime));
    }
}
