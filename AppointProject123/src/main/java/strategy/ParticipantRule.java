package strategy;

import model.Appointment;

public class ParticipantRule implements BookingRuleStrategy {

    private static final int MAX_PARTICIPANTS = 5;

    @Override
    public boolean isValid(Appointment appointment) {
        return appointment.getParticipants() <= MAX_PARTICIPANTS;
    }
}