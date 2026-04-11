package strategy;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import model.Appointment;
import model.AppointmentCategory;
import model.AppointmentFormat;
import model.AppointmentMode;

class AssessmentRuleTest {

    @Test
    void testAssessmentRule_WhenIndividual_ReturnsTrue() {
        AssessmentRule rule = new AssessmentRule();

        Appointment appointment = new Appointment(
                1,
                "Assessment Appointment",
                LocalDateTime.now(),
                60,
                AppointmentCategory.ASSESSMENT,
                AppointmentMode.IN_PERSON,
                AppointmentFormat.INDIVIDUAL,
                1,
                "Clinic",
                ""
        );

        assertTrue(rule.isValid(appointment));
    }

    @Test
    void testAssessmentRule_WhenGroup_ReturnsTrue() {
        AssessmentRule rule = new AssessmentRule();

        Appointment appointment = new Appointment(
                2,
                "Assessment Appointment",
                LocalDateTime.now(),
                60,
                AppointmentCategory.ASSESSMENT,
                AppointmentMode.IN_PERSON,
                AppointmentFormat.GROUP,
                3,
                "Clinic",
                ""
        );

        assertTrue(rule.isValid(appointment));
    }
}