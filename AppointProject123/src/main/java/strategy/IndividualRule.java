package strategy;

import model.Appointment;
import model.AppointmentFormat;

public class IndividualRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Appointment appointment) {
        if (appointment.getFormat() != AppointmentFormat.INDIVIDUAL) {
            return true;
        }
        return appointment.getCapacity() == 1;
    }
}