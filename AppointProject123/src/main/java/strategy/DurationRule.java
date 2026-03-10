package strategy;

import model.Appointment;

public class DurationRule implements BookingRuleStrategy {

    private static final int MAX_DURATION = 30;

    @Override
    public boolean isValid(Appointment appointment) {
        return appointment.getDuration() <= MAX_DURATION;
    }
}