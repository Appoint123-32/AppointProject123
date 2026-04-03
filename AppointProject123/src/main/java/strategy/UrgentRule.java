package strategy;

import model.Appointment;
import model.AppointmentCategory;
import model.AppointmentFormat;

public class UrgentRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment appointment) {
        if (appointment.getCategory() != AppointmentCategory.URGENT) {
            return true;
        }
        return appointment.getFormat() != AppointmentFormat.GROUP;
    }
}