package strategy;

import model.Appointment;
import model.AppointmentCategory;

public class FollowUpRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment appointment) {
        if (appointment.getCategory() != AppointmentCategory.FOLLOW_UP) {
            return true;
        }
        return appointment.getDurationMinutes() <= 30;
    }
}
