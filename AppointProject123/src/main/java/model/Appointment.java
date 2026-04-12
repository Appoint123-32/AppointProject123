package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an appointment within the scheduling system.
 * This class holds information about the appointment's timing, category, 
 * format, and participant management.
 * 
 * @author Tuqa Ghanem, Shireen Fathi, Marah Abdullah
 * @version 1.0
 */
public class Appointment {
    
    /** The unique identifier for the appointment */
    private int id;
    
    /** The title of the appointment */
    private String title;
    
    /** The date and time when the appointment is scheduled */
    private LocalDateTime dateTime;

    /** The duration of the appointment in minutes */
    private int durationMinutes;
    
    /** The category of the appointment (e.g., URGENT, FOLLOW_UP) */
    private AppointmentCategory category;
    
    /** The mode of the appointment (VIRTUAL or IN_PERSON) */
    private AppointmentMode mode;
    
    /** The format of the appointment (INDIVIDUAL or GROUP) */
    private AppointmentFormat format;
    
    /** The maximum number of participants allowed */
    private int capacity;
    
    /** The physical location for in-person appointments */
    private String location;
    
    /** The digital meeting link for virtual appointments */
    private String meetingLink;

    /** A string representation of the appointment type/category */
    private String type;
    
    /** The current status of the appointment (e.g., AVAILABLE, FULL) */
    private String status;
    
    /** A list of usernames who have booked this appointment */
    private List<String> bookedUsers;

    /**
     * Constructs a full Appointment with all specified details.
     * 
     * @param id The unique ID
     * @param title The appointment title
     * @param dateTime The scheduled date and time
     * @param durationMinutes Length in minutes
     * @param category The appointment category
     * @param mode The appointment mode
     * @param format The appointment format
     * @param capacity Maximum capacity
     * @param location Physical address
     * @param meetingLink Digital URL
     */
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

    /**
     * Constructs an Appointment with basic details, defaulting to FOLLOW_UP and IN_PERSON.
     * 
     * @param id The unique ID
     * @param title The appointment title
     * @param dateTime The scheduled date and time
     * @param duration Duration in minutes
     * @param participants Maximum capacity
     * @param type The type string
     */
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

    /**
     * Constructs an Appointment with an auto-generated title.
     * 
     * @param id The unique ID
     * @param dateTime The scheduled date and time
     * @param duration Duration in minutes
     * @param participants Maximum capacity
     * @param type The type string
     */
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

    /**
     * @return the unique ID of the appointment
     */
    public int getId() {
        return id;
    }

    /**
     * @return the title of the appointment
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the scheduled date and time
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param dateTime the date and time to set
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return duration in minutes
     */
    public int getDurationMinutes() {
        return durationMinutes;
    }

    /**
     * @param durationMinutes the duration in minutes to set
     */
    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    /**
     * Alias for getDurationMinutes.
     * @return duration in minutes
     */
    public int getDuration() {
        return durationMinutes;
    }

    /**
     * Alias for setDurationMinutes.
     * @param duration the duration in minutes to set
     */
    public void setDuration(int duration) {
        this.durationMinutes = duration;
    }

    /**
     * @return the appointment category
     */
    public AppointmentCategory getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(AppointmentCategory category) {
        this.category = category;
    }

    /**
     * @return the appointment mode
     */
    public AppointmentMode getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(AppointmentMode mode) {
        this.mode = mode;
    }

    /**
     * @return the appointment format
     */
    public AppointmentFormat getFormat() {
        return format;
    }

    /**
     * @param format the format to set
     */
    public void setFormat(AppointmentFormat format) {
        this.format = format;
    }

    /**
     * @return the maximum capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the maximum capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Alias for getCapacity.
     * @return the maximum number of participants
     */
    public int getParticipants() {
        return capacity;
    }

    /**
     * Sets the capacity and updates format based on participant count.
     * @param participants the number of participants to set
     */
    public void setParticipants(int participants) {
        this.capacity = participants;
        this.format = participants > 1 ? AppointmentFormat.GROUP : AppointmentFormat.INDIVIDUAL;
    }

    /**
     * @return the type string
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type string to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the current status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status string to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the physical location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the virtual meeting link
     */
    public String getMeetingLink() {
        return meetingLink;
    }

    /**
     * @param meetingLink the meeting link to set
     */
    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    /**
     * @return the list of usernames who booked this slot
     */
    public List<String> getBookedUsers() {
        return bookedUsers;
    }

    /**
     * @param bookedUsers the list of usernames to set
     */
    public void setBookedUsers(List<String> bookedUsers) {
        this.bookedUsers = bookedUsers;
    }

    /**
     * @return the number of current bookings
     */
    public int getBookedCount() {
        return bookedUsers.size();
    }

    /**
     * Checks if the appointment has reached its capacity.
     * @return true if full, false otherwise
     */
    public boolean isFull() {
        return bookedUsers.size() >= capacity;
    }

    /**
     * Adds a generic booking to the appointment.
     * @throws IllegalStateException if the appointment is already full
     */
    public void addBooking() {
        if (isFull()) {
            throw new IllegalStateException("Appointment is full.");
        }
        bookedUsers.add("BOOKED");
        if (isFull()) {
            status = "FULL";
        }
    }

    /**
     * Adds a specific user's booking to the appointment.
     * @param username the name of the user booking the slot
     * @throws IllegalStateException if the appointment is already full
     */
    public void addBooking(String username) {
        if (isFull()) {
            throw new IllegalStateException("Appointment is full.");
        }
        bookedUsers.add(username);
        if (isFull()) {
            status = "FULL";
        }
    }

    /**
     * Returns a string summary of the appointment details.
     * @return Formatted string of appointment attributes
     */
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