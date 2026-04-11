package strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.Appointment;

public class ParticipantRuleTest {

    private final ParticipantRule rule = new ParticipantRule();

    @Test
    void testIsValid_WhenParticipantsLessThanOrEqual5_ReturnsTrue() {
        Appointment appointment = new Appointment(
                1,
                "Group Session",
                LocalDateTime.now(),
                20,
                5,
                "FOLLOW_UP"
        );

        assertTrue(rule.isValid(appointment));
    }

    @Test
    void testIsValid_WhenParticipantsGreaterThan5_ReturnsFalse() {
        Appointment appointment = new Appointment(
                2,
                "Large Group Session",
                LocalDateTime.now(),
                20,
                7,
                "FOLLOW_UP"
        );

        assertFalse(rule.isValid(appointment));
    }
}