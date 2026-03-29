package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Appointment {

    private int id;
    private LocalDateTime dateTime;
    private int duration;
    private int participants;
    private String type;
    private String status;

    private List<String> bookedUsers;

    public Appointment(int id, LocalDateTime dateTime, int duration, int participants, String type) {
        this.id = id;
        this.dateTime = dateTime;
        this.duration = duration;
        this.participants = participants;
        this.type = type;
        this.status = "AVAILABLE";
        this.bookedUsers = new ArrayList<>();
    }

    public int getId() {
        return id;
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

    public List<String> getBookedUsers() {
        return bookedUsers;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 🔥 NEW (for admin modify)
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public void setType(String type) {
        this.type = type;
    }
}