package service;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.Appointment;
import model.AppointmentCategory;
import model.AppointmentFormat;
import model.AppointmentMode;

public class AppointmentRulesTest {

    @Test
    void testValidate_WhenValidAppointment_DoesNotThrowException() {

        Appointment appointment = new Appointment(
                1,
                "Follow Up",
                LocalDateTime.now(),
                30,
                AppointmentCategory.FOLLOW_UP,
                AppointmentMode.VIRTUAL,
                AppointmentFormat.INDIVIDUAL,
                1,
                "",
                "https://meet.google.com/test"
        );

        assertDoesNotThrow(() -> AppointmentRules.validate(appointment));
    }

    @Test
    void testValidate_WhenTitleIsEmpty_ThrowsException() {

        Appointment appointment = new Appointment(
                2,
                "",
                LocalDateTime.now(),
                30,
                AppointmentCategory.FOLLOW_UP,
                AppointmentMode.VIRTUAL,
                AppointmentFormat.INDIVIDUAL,
                1,
                "",
                "https://meet.google.com/test"
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> AppointmentRules.validate(appointment)
        );

        assertEquals("Title cannot be empty.", exception.getMessage());
    }

    @Test
    void testValidate_WhenFollowUpDurationMoreThan30_ThrowsException() {

        Appointment appointment = new Appointment(
                3,
                "Follow Up",
                LocalDateTime.now(),
                40,
                AppointmentCategory.FOLLOW_UP,
                AppointmentMode.VIRTUAL,
                AppointmentFormat.INDIVIDUAL,
                1,
                "",
                "https://meet.google.com/test"
        );

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> AppointmentRules.validate(appointment)
        );

        assertEquals("Follow-up appointment cannot exceed 30 minutes.", exception.getMessage());
    }
}