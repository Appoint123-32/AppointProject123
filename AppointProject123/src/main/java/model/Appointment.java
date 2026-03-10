package model;

import java.time.LocalDateTime;

public class Appointment {

    private int id;
    private LocalDateTime dateTime;
    private int duration;
    private int participants;
    private String type;
    private String status;

    public Appointment(int id, LocalDateTime dateTime, int duration, int participants, String type) {
        this.id = id;
        this.dateTime = dateTime;
        this.duration = duration;
        this.participants = participants;
        this.type = type;
        this.status = "CONFIRMED";
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getDuration() {
        return duration;
    }

    public int getParticipants() {
        return participants;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void cancel() {
        status = "CANCELLED";
    }
}