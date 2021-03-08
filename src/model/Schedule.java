package model;

public class Schedule {
    private String day;
    private String startTime;
    private String endTime;
    private String duration;

    public Schedule(String day, String startTime, String endTime, String duration) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public Schedule(String day, String startTime, String endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
