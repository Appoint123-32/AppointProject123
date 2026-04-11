
package strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.Appointment;
import model.AppointmentCategory;
import model.AppointmentFormat;
import model.AppointmentMode;

class UrgentRuleTest {

    @Test
    void testUrgentRule_WhenUrgentAndIndividual_ReturnsTrue() {
        UrgentRule rule = new UrgentRule();

        Appointment appointment = new Appointment(
                1,
                "Urgent Appointment",
                LocalDateTime.now(),
                20,
                AppointmentCategory.URGENT,
                AppointmentMode.IN_PERSON,
                AppointmentFormat.INDIVIDUAL,
                1,
                "Clinic",
                ""
        );

        assertTrue(rule.isValid(appointment));
    }

    @Test
    void testUrgentRule_WhenUrgentAndGroup_ReturnsFalse() {
        UrgentRule rule = new UrgentRule();

        Appointment appointment = new Appointment(
                2,
                "Urgent Appointment",
                LocalDateTime.now(),
                20,
                AppointmentCategory.URGENT,
                AppointmentMode.IN_PERSON,
                AppointmentFormat.GROUP,
                3,
                "Clinic",
                ""
        );

        assertFalse(rule.isValid(appointment));
    }
}