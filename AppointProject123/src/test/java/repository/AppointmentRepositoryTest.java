package repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.Appointment;

class AppointmentRepositoryTest {

    @Test
    void testAddAndFindAppointment() {
        AppointmentRepository repository = new AppointmentRepository();

        Appointment appointment = new Appointment(
                1,
                LocalDateTime.now(),
                30,
                2,
                "FOLLOW_UP"
        );

        repository.addAppointment(appointment);

        assertEquals(appointment, repository.findById(1));
    }

    @Test
    void testRemoveAppointment() {
        AppointmentRepository repository = new AppointmentRepository();

        Appointment appointment = new Appointment(
                2,
                LocalDateTime.now(),
                30,
                2,
                "FOLLOW_UP"
        );

        repository.addAppointment(appointment);
        repository.removeAppointment(appointment);

        assertNull(repository.findById(2));
    }

    @Test
    void testGetAvailableAppointments() {
        AppointmentRepository repository = new AppointmentRepository();

        Appointment appointment = new Appointment(
                3,
                LocalDateTime.now(),
                30,
                2,
                "FOLLOW_UP"
        );

        repository.addAppointment(appointment);

        assertEquals(1, repository.getAvailableAppointments().size());
    }
    @Test
    void testFindById_NotFoundBranch() {
        AppointmentRepository repo = new AppointmentRepository();
        assertNull(repo.findById(9999)); 
    }

    @Test
    void testGetAvailableAppointments_MixedBranch() {
        AppointmentRepository repo = new AppointmentRepository();
        
        Appointment full = new Appointment(1, java.time.LocalDateTime.now(), 30, 1, "TYPE");
        full.addBooking("User1");
        repo.addAppointment(full);
        
        Appointment avail = new Appointment(2, java.time.LocalDateTime.now(), 30, 2, "TYPE");
        repo.addAppointment(avail);
        
        assertEquals(1, repo.getAvailableAppointments().size());
        assertEquals(2, repo.getAvailableAppointments().get(0).getId());
    }
}