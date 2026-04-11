package strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.Appointment;
import model.AppointmentCategory;
import model.AppointmentFormat;
import model.AppointmentMode;

class FollowUpRuleTest {

    @Test
    void testFollowUpRule_WhenDurationLessThanOrEqual30_ReturnsTrue() {
        FollowUpRule rule = new FollowUpRule();

        Appointment appointment = new Appointment(
                1,
                "Follow Up Appointment",
                LocalDateTime.now(),
                30,
                AppointmentCategory.FOLLOW_UP,
                AppointmentMode.VIRTUAL,
                AppointmentFormat.INDIVIDUAL,
                1,
                "",
                "https://meet.com"
        );

        assertTrue(rule.isValid(appointment));
    }

    @Test
    void testFollowUpRule_WhenDurationMoreThan30_ReturnsFalse() {
        FollowUpRule rule = new FollowUpRule();

        Appointment appointment = new Appointment(
                2,
                "Follow Up Appointment",
                LocalDateTime.now(),
                45,
                AppointmentCategory.FOLLOW_UP,
                AppointmentMode.VIRTUAL,
                AppointmentFormat.INDIVIDUAL,
                1,
                "",
                "https://meet.com"
        );

        assertFalse(rule.isValid(appointment));
    }
}
