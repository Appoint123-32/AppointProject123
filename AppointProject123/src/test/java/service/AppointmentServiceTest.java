package service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Appointment;
import model.AppointmentCategory;
import model.AppointmentFormat;
import model.AppointmentMode;
import repository.AppointmentRepository;

class AppointmentServiceTest {

    private AppointmentService service;

    @BeforeEach
    void setup() {
        service = new AppointmentService(new AppointmentRepository());
    }

    private Appointment createAppointment(int id) {
        Appointment appointment = new Appointment(
                id,
                "Follow Up Appointment",
                LocalDateTime.now(),
                30,
                AppointmentCategory.FOLLOW_UP,
                AppointmentMode.IN_PERSON,
                AppointmentFormat.GROUP,
                2,
                "Clinic Room 1",
                ""
        );

        appointment.setType("FOLLOW_UP");

        return appointment;
    }

    @Test
    void testAddAppointment() {
        Appointment appointment = createAppointment(1);

        service.addAppointment(appointment);

        assertEquals(1, service.getAllAppointments().size());
    }

    @Test
    void testFindAppointmentById() {
        Appointment appointment = createAppointment(2);

        service.addAppointment(appointment);

        Appointment found = service.findAppointmentById(2);

        assertNotNull(found);
        assertEquals(2, found.getId());
    }

    @Test
    void testDeleteAppointment() {
        Appointment appointment = createAppointment(3);

        service.addAppointment(appointment);

        assertTrue(service.deleteAppointment(3));
        assertNull(service.findAppointmentById(3));
    }
}