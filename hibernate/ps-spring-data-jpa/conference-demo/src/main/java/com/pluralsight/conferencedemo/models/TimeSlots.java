package com.pluralsight.conferencedemo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "time_slots")
public class TimeSlots {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_slot_date")
    private String timeSlotDate;

    @Column(name = "start_time")
    private LocalDate startTime;

    @Column(name = "end_time")
    private LocalDate endTime;

    @Column(name = "is_keynote_time")
    private Boolean isKeynoteTime;

    public TimeSlots(Long id, String timeSlotDate, LocalDate startTime, LocalDate endTime, boolean isKeynoteTime) {
        this.id = id;
        this.timeSlotDate = timeSlotDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isKeynoteTime = isKeynoteTime;
    }

    public TimeSlots() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeSlotDate() {
        return timeSlotDate;
    }

    public void setTimeSlotDate(String timeSlotDate) {
        this.timeSlotDate = timeSlotDate;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public boolean isKeynoteTime() {
        return isKeynoteTime;
    }

    public void setKeynoteTime(boolean keynoteTime) {
        isKeynoteTime = keynoteTime;
    }
}
