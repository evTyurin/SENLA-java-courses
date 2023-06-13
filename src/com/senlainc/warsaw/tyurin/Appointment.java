package com.senlainc.warsaw.tyurin;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    private LocalDate startDate;
    private LocalTime startTime;
    private Duration duration;

    public Appointment() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }
}
