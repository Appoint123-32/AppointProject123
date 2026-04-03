package service;

import model.Appointment;
import repository.AppointmentRepository;

import java.util.List;

public class AppointmentService {

    private final AppointmentRepository repository;
    private final AppointmentTypeRuleValidator ruleValidator;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
        this.ruleValidator = new AppointmentTypeRuleValidator();
    }

    public void addAppointment(Appointment appointment) {
        if (repository.findById(appointment.getId()) != null) {
            throw new IllegalArgumentException("Appointment ID already exists.");
        }

        ruleValidator.validate(appointment);
        repository.addAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return repository.getAllAppointments();
    }

    public Appointment findAppointmentById(int id) {
        return repository.findById(id);
    }

    public void bookAppointment(int id) {
        Appointment appointment = repository.findById(id);

        if (appointment == null) {
            throw new IllegalArgumentException("Appointment not found.");
        }

        appointment.addBooking();
    }

    public boolean deleteAppointment(int id) {
        Appointment appointment = repository.findById(id);

        if (appointment == null) {
            return false;
        }

        repository.removeAppointment(appointment);
        return true;
    }

    public List<Appointment> getAvailableAppointments() {
        return repository.getAvailableAppointments();
    }
}