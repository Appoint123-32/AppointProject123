package service;
import observer.Observer;
import observer.EmailNotificationService;
import model.Appointment;
import repository.AppointmentRepository;

import java.time.LocalDateTime;
import java.util.List;

public class BookingService {
	private Observer notification = new EmailNotificationService();
    private AppointmentRepository repository;

    public BookingService(AppointmentRepository repository) {
        this.repository = repository;
    }

    // book appointment
    public void bookAppointment(int id, LocalDateTime time, int duration, int participants, String type) {

        Appointment appointment = new Appointment(id, time, duration, participants, type);
        repository.addAppointment(appointment);

        System.out.println("Appointment booked successfully.");
        notification.update("Your appointment has been booked successfully.");
    }

    // view appointments
    public List<Appointment> getAllAppointments() {
        return repository.getAllAppointments();
    }

    // cancel appointment
    public void cancelAppointment(int id) {

        Appointment appointment = repository.findById(id);

        if (appointment != null) {
            appointment.cancel();
            System.out.println("Appointment cancelled.");
            notification.update("Your appointment has been cancelled.");
        } else {
            System.out.println("Appointment not found.");
        }
    }
    
    public void adminAddSlot(int id, LocalDateTime time, int duration, int participants, String type) {

        Appointment appointment = new Appointment(id, time, duration, participants, type);
        repository.addAppointment(appointment);

        System.out.println("Slot added by admin.");
        notification.update("A new appointment slot has been added successfully.");
    }
    
    
    public List<Appointment> viewAvailableSlots() {
        return repository.getAvailableAppointments();
    }
    
    public void bookSlot(int id, String username) {

        Appointment appointment = repository.findById(id);

        if (appointment == null) {
            System.out.println("Slot not found.");
            return;
        }

        if (!appointment.getStatus().equals("AVAILABLE")) {
            System.out.println("Slot already booked.");
            return;
        }

        appointment.book(username);

        System.out.println("Appointment booked successfully.");
    }
}