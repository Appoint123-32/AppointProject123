package service;

import model.Appointment;
import model.AppointmentCategory;
import model.AppointmentFormat;
import model.AppointmentMode;
import repository.AppointmentRepository;

import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    private AppointmentRepository repository;
    private AppointmentTypeRuleValidator ruleValidator;
    private TimeProvider timeProvider;
    private static final String APPOINTMENT_NOT_FOUND =  "❌ Appointment not found." ; 
    private static final Logger LOGGER = Logger.getLogger(BookingService.class.getName());

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

        LOGGER.info("✅ Appointment slot added successfully!");
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
            LOGGER.info(APPOINTMENT_NOT_FOUND);
            return;
        }

        if (appointment.getBookedUsers().size() >= appointment.getParticipants()) {
            LOGGER.info("❌ No available slots (appointment is full).");
            return;
        }

        if (appointment.getBookedUsers().contains(username)) {
            LOGGER.info("❌ You already booked this appointment.");
            return;
        }

        appointment.getBookedUsers().add(username);

        if (appointment.getBookedUsers().size() == appointment.getParticipants()) {
            appointment.setStatus("FULL");
        }

       LOGGER.info("✅ Appointment booked successfully!");
    }

    public void cancelAppointment(int id) {
        Appointment appointment = repository.findById(id);

        if (appointment == null) {
           LOGGER.info(APPOINTMENT_NOT_FOUND);
            return;
        }

        if (appointment.getBookedUsers().isEmpty()) {
            LOGGER.info("❌ No bookings to cancel.");
            return;
        }

        appointment.getBookedUsers().remove(appointment.getBookedUsers().size() - 1);
        appointment.setStatus("AVAILABLE");

        LOGGER.info("✅ Appointment cancelled.");
    }

    public void adminModifyAppointment(int id, int newDuration, int newParticipants, String newType) {
        Appointment appointment = repository.findById(id);

        if (appointment == null) {
            LOGGER.info(APPOINTMENT_NOT_FOUND);
            return;
        }

        appointment.setDuration(newDuration);
        appointment.setParticipants(newParticipants);
        appointment.setType(newType);

        LOGGER.info("✅ Appointment updated successfully!");
    }

    public void adminCancelAppointment(int id) {
        Appointment appointment = repository.findById(id);

        if (appointment == null) {
           LOGGER.info(APPOINTMENT_NOT_FOUND);
            return;
        }

        repository.removeAppointment(appointment);

       LOGGER.info("✅ Appointment deleted by admin.");
    }
}
