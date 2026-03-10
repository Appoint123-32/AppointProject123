package service;

import model.Appointment;
import repository.AppointmentRepository;

import java.time.LocalDateTime;
import java.util.List;

public class BookingService {

    private AppointmentRepository repository;

    public BookingService(AppointmentRepository repository) {
        this.repository = repository;
    }

    // book appointment
    public void bookAppointment(int id, LocalDateTime time, int duration, int participants, String type) {

        Appointment appointment = new Appointment(id, time, duration, participants, type);
        repository.addAppointment(appointment);

        System.out.println("Appointment booked successfully.");
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
        } else {
            System.out.println("Appointment not found.");
        }
    }
}