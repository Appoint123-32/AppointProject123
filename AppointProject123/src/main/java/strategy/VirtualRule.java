package strategy;

import model.Appointment;
import model.AppointmentMode;

public class VirtualRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment appointment) {
        if (appointment.getMode() != AppointmentMode.VIRTUAL) {
            return true;
        }
        return appointment.getMeetingLink() != null &&
               !appointment.getMeetingLink().isBlank();
    }
}