package strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.Appointment;

public class DurationRuleTest {

    private final DurationRule rule = new DurationRule();

    @Test
    void testIsValid_WhenDurationLessThanOrEqual30_ReturnsTrue() {
        Appointment appointment = new Appointment(
                1,
                "Follow Up Appointment",
                LocalDateTime.now(),
                20,
                1,
                "FOLLOW_UP"
        );

        assertTrue(rule.isValid(appointment));
    }

    @Test
    void testIsValid_WhenDurationGreaterThan30_ReturnsFalse() {
        Appointment appointment = new Appointment(
                2,
                "Assessment Appointment",
                LocalDateTime.now(),
                45,
                1,
                "ASSESSMENT"
        );

        assertFalse(rule.isValid(appointment));
    }
}