package strategy;

import model.Appointment;
import model.AppointmentMode;

public class InPersonRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment appointment) {
        if (appointment.getMode() != AppointmentMode.IN_PERSON) {
            return true;
        }
        return appointment.getLocation() != null &&
               !appointment.getLocation().isBlank();
    }
}