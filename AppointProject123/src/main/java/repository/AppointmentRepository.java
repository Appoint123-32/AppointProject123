package repository;

import model.Appointment;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    private List<Appointment> appointments = new ArrayList<>();

    // Add appointment
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    // Find appointment by id
    public Appointment findById(int id) {
        for (Appointment a : appointments) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    // Remove appointment
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }
    
    public List<Appointment> getAvailableAppointments() {

        List<Appointment> available = new ArrayList<>();

        for (Appointment a : appointments) {
            if (a.getStatus().equals("AVAILABLE")) {
                available.add(a);
            }
        }

        return available;
    }
}