package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private int id;
    private String title;
    private LocalDateTime dateTime;

    private int durationMinutes;
    private AppointmentCategory category;
    private AppointmentMode mode;
    private AppointmentFormat format;
    private int capacity;
    private String location;
    private String meetingLink;

    private String type;
    private String status;
    private List<String> bookedUsers;

    public Appointment(int id, String title, LocalDateTime dateTime, int durationMinutes,
                       AppointmentCategory category, AppointmentMode mode, AppointmentFormat format,
                       int capacity, String location, String meetingLink) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.durationMinutes = durationMinutes;
        this.category = category;
        this.mode = mode;
        this.format = format;
        this.capacity = capacity;
        this.location = location;
        this.meetingLink = meetingLink;

        this.type = category != null ? category.name() : "";
        this.status = "AVAILABLE";
        this.bookedUsers = new ArrayList<>();
    }

    public Appointment(int id, String title, LocalDateTime dateTime, int duration, int participants, String type) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.durationMinutes = duration;
        this.capacity = participants;
        this.type = type;
        this.status = "AVAILABLE";
        this.bookedUsers = new ArrayList<>();

        this.category = AppointmentCategory.FOLLOW_UP;
        this.mode = AppointmentMode.IN_PERSON;
        this.format = participants > 1 ? AppointmentFormat.GROUP : AppointmentFormat.INDIVIDUAL;
        this.location = "";
        this.meetingLink = "";
    }
    public Appointment(int id, LocalDateTime dateTime, int duration, int participants, String type) {
        this.id = id;
        this.title = "Appointment " + id;
        this.dateTime = dateTime;
        this.durationMinutes = duration;
        this.capacity = participants;
        this.type = type;
        this.status = "AVAILABLE";
        this.bookedUsers = new ArrayList<>();
        
        this.category = AppointmentCategory.FOLLOW_UP;
        this.mode = AppointmentMode.IN_PERSON;
        this.format = participants > 1 ? AppointmentFormat.GROUP : AppointmentFormat.INDIVIDUAL;
        this.location = "";
        this.meetingLink = "";
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    
    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getDuration() {
        return durationMinutes;
    }

    public void setDuration(int duration) {
        this.durationMinutes = duration;
    }

   
    public AppointmentCategory getCategory() {
        return category;
    }

    public void setCategory(AppointmentCategory category) {
        this.category = category;
    }

    public AppointmentMode getMode() {
        return mode;
    }

    public void setMode(AppointmentMode mode) {
        this.mode = mode;
    }

    public AppointmentFormat getFormat() {
        return format;
    }

    public void setFormat(AppointmentFormat format) {
        this.format = format;
    }

   
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    public int getParticipants() {
        return capacity;
    }

    public void setParticipants(int participants) {
        this.capacity = participants;
        this.format = participants > 1 ? AppointmentFormat.GROUP : AppointmentFormat.INDIVIDUAL;
    }

   
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    
    public List<String> getBookedUsers() {
        return bookedUsers;
    }

    public void setBookedUsers(List<String> bookedUsers) {
        this.bookedUsers = bookedUsers;
    }

    public int getBookedCount() {
        return bookedUsers.size();
    }

    public boolean isFull() {
        return bookedUsers.size() >= capacity;
    }

    public void addBooking() {
        if (isFull()) {
            throw new IllegalStateException("Appointment is full.");
        }
        bookedUsers.add("BOOKED");
        if (isFull()) {
            status = "FULL";
        }
    }

    public void addBooking(String username) {
        if (isFull()) {
            throw new IllegalStateException("Appointment is full.");
        }
        bookedUsers.add(username);
        if (isFull()) {
            status = "FULL";
        }
    }

    @Override
    public String toString() {
        return "\nAppointment ID: " + id +
                "\nTitle: " + title +
                "\nDate/Time: " + dateTime +
                "\nDuration: " + durationMinutes + " minutes" +
                "\nType: " + type +
                "\nStatus: " + status +
                "\nCategory: " + category +
                "\nMode: " + mode +
                "\nFormat: " + format +
                "\nParticipants/Capacity: " + capacity +
                "\nBooked: " + bookedUsers.size() +
                "\nLocation: " + (location == null || location.isBlank() ? "-" : location) +
                "\nMeeting Link: " + (meetingLink == null || meetingLink.isBlank() ? "-" : meetingLink) +
                "\n-----------------------------------";
    }
}