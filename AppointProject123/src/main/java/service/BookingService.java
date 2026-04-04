package service;

import model.Appointment;
import model.AppointmentCategory;
import model.AppointmentFormat;
import model.AppointmentMode;
import repository.AppointmentRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private AppointmentRepository repository;
    private AppointmentTypeRuleValidator ruleValidator;
    private TimeProvider timeProvider;

    public BookingService(AppointmentRepository repository) {
        this(repository, new SystemTimeProvider());
    }

    public BookingService(AppointmentRepository repository, TimeProvider timeProvider) {
        this.repository = repository;
        this.ruleValidator = new AppointmentTypeRuleValidator();
        this.timeProvider = timeProvider;
    }

    public void adminAddSlot(int id, String title, int duration,
                             AppointmentCategory category, AppointmentMode mode,
                             AppointmentFormat format, int capacity,
                             String location, String meetingLink) {

        Appointment appointment = new Appointment(
                id,
                title,
                timeProvider.now(),
                duration,
                category,
                mode,
                format,
                capacity,
                location,
                meetingLink
        );

        ruleValidator.validate(appointment);
        repository.addAppointment(appointment);

        System.out.println("✅ Appointment slot added successfully!");
    }

    public List<Appointment> getAllAppointments() {
        return repository.getAllAppointments();
    }

    public List<Appointment> viewAvailableSlots() {
        List<Appointment> available = new ArrayList<>();

        for (Appointment a : repository.getAllAppointments()) {
            if (a.getBookedUsers().size() < a.getParticipants()) {
                available.add(a);
            }
        }

        return available;
    }

    public void bookSlot(int id, String username) {
        Appointment appointment = repository.findById(id);

        if (appointment == null) {
            System.out.println("❌ Appointment not found.");
            return;
        }

        if (appointment.getBookedUsers().size() >= appointment.getParticipants()) {
            System.out.println("❌ No available slots (appointment is full).");
            return;
        }

        if (appointment.getBookedUsers().contains(username)) {
            System.out.println("❌ You already booked this appointment.");
            return;
        }

        appointment.getBookedUsers().add(username);

        if (appointment.getBookedUsers().size() == appointment.getParticipants()) {
            appointment.setStatus("FULL");
        }

        System.out.println("✅ Appointment booked successfully!");
    }

    public void cancelAppointment(int id) {
        Appointment appointment = repository.findById(id);

        if (appointment == null) {
            System.out.println("❌ Appointment not found.");
            return;
        }

        if (appointment.getBookedUsers().isEmpty()) {
            System.out.println("❌ No bookings to cancel.");
            return;
        }

        appointment.getBookedUsers().remove(appointment.getBookedUsers().size() - 1);
        appointment.setStatus("AVAILABLE");

        System.out.println("✅ Appointment cancelled.");
    }

    public void adminModifyAppointment(int id, int newDuration, int newParticipants, String newType) {
        Appointment appointment = repository.findById(id);

        if (appointment == null) {
            System.out.println("❌ Appointment not found.");
            return;
        }

        appointment.setDuration(newDuration);
        appointment.setParticipants(newParticipants);
        appointment.setType(newType);

        System.out.println("✅ Appointment updated successfully!");
    }

    public void adminCancelAppointment(int id) {
        Appointment appointment = repository.findById(id);

        if (appointment == null) {
            System.out.println("❌ Appointment not found.");
            return;
        }

        repository.removeAppointment(appointment);

        System.out.println("✅ Appointment deleted by admin.");
    }
}