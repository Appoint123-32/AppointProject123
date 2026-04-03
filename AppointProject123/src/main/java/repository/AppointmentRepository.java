package repository;

import model.Appointment;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    private List<Appointment> appointments = new ArrayList<>();

    //Add appoit
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // Get all appoit
    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    // Find appoit by id
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

            if (a.getBookedUsers().size() < a.getParticipants()) {
                available.add(a);
            }
        }

        return available;
    }
}