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
}