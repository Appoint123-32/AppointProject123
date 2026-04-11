package service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.Appointment;
import model.AppointmentCategory;
import model.AppointmentFormat;
import model.AppointmentMode;
import repository.AppointmentRepository;

public class BookingServiceTimeTest {

    @Test
    void testAdminAddSlot_UsesMockedTime() {

        AppointmentRepository repository = new AppointmentRepository();
        TimeProvider timeProvider = mock(TimeProvider.class);

        LocalDateTime fixedTime = LocalDateTime.of(2026, 4, 4, 10, 30);
        when(timeProvider.now()).thenReturn(fixedTime);

        BookingService bookingService = new BookingService(repository, timeProvider);

        bookingService.adminAddSlot(
                1,
                "Follow Up",
                30,
                AppointmentCategory.FOLLOW_UP,
                AppointmentMode.VIRTUAL,
                AppointmentFormat.INDIVIDUAL,
                1,
                "",
                "https://meet.google.com/test"
        );

        Appointment savedAppointment = repository.findById(1);

        assertNotNull(savedAppointment);
        assertEquals(fixedTime, savedAppointment.getDateTime());
    }
}