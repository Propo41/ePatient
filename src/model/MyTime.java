package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class MyTime {
    LocalDate date;
    LocalTime startTime;
    LocalTime endTime;

    public MyTime(LocalDate date, LocalTime startTime, LocalTime endTime ){
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "Date :  "
                + date + "  |" +
                "   Starting Time : " + startTime + "  |" +
                "   Ending Time: " + endTime;
    }

}
