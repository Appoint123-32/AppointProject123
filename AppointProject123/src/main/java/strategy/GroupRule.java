package strategy;

import model.Appointment;
import model.AppointmentFormat;

public class GroupRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment appointment) {
        if (appointment.getFormat() != AppointmentFormat.GROUP) {
            return true;
        }
        return appointment.getCapacity() >= 2 && appointment.getCapacity() <= 5;
    }
}