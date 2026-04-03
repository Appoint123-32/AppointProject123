package service;

import model.Appointment;
import model.AppointmentCategory;
import model.AppointmentFormat;
import model.AppointmentMode;

public class AppointmentRules {

    public static void validate(Appointment appointment) {
        validateBasicRules(appointment);
        validateFormatRules(appointment);
        validateModeRules(appointment);
        validateCategoryRules(appointment);
    }

    private static void validateBasicRules(Appointment appointment) {
        if (appointment.getTitle() == null || appointment.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }

        if (appointment.getDurationMinutes() <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0.");
        }

        if (appointment.getCapacity() <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0.");
        }
    }

    private static void validateFormatRules(Appointment appointment) {
        if (appointment.getFormat() == AppointmentFormat.INDIVIDUAL) {
            if (appointment.getCapacity() != 1) {
                throw new IllegalArgumentException("Individual appointment must have capacity = 1.");
            }
        }

        if (appointment.getFormat() == AppointmentFormat.GROUP) {
            if (appointment.getCapacity() < 2 || appointment.getCapacity() > 5) {
                throw new IllegalArgumentException("Group appointment capacity must be between 2 and 5.");
            }
        }
    }

    private static void validateModeRules(Appointment appointment) {
        if (appointment.getMode() == AppointmentMode.VIRTUAL) {
            if (appointment.getMeetingLink() == null || appointment.getMeetingLink().isBlank()) {
                throw new IllegalArgumentException("Virtual appointment must have a meeting link.");
            }
        }

        if (appointment.getMode() == AppointmentMode.IN_PERSON) {
            if (appointment.getLocation() == null || appointment.getLocation().isBlank()) {
                throw new IllegalArgumentException("In-person appointment must have a location.");
            }
        }
    }

    private static void validateCategoryRules(Appointment appointment) {
        if (appointment.getCategory() == AppointmentCategory.URGENT) {
            if (appointment.getFormat() == AppointmentFormat.GROUP) {
                throw new IllegalArgumentException("Urgent appointments cannot be group appointments.");
            }
        }

        if (appointment.getCategory() == AppointmentCategory.FOLLOW_UP) {
            if (appointment.getDurationMinutes() > 30) {
                throw new IllegalArgumentException("Follow-up appointment cannot exceed 30 minutes.");
            }
        }

        if (appointment.getCategory() == AppointmentCategory.ASSESSMENT) {
            if (appointment.getDurationMinutes() < 45) {
                throw new IllegalArgumentException("Assessment appointment must be at least 45 minutes.");
            }
        }
    }
}