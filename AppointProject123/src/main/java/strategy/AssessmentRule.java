package strategy;

import model.Appointment;
import model.AppointmentCategory;

public class AssessmentRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment appointment) {
        if (appointment.getCategory() != AppointmentCategory.ASSESSMENT) {
            return true;
        }
        return appointment.getDurationMinutes() >= 45;
    }
}