package service;

import java.util.ArrayList;
import java.util.List;

import model.Appointment;
import strategy.AssessmentRule;
import strategy.BookingRuleStrategy;
import strategy.DurationRule;
import strategy.FollowUpRule;
import strategy.GroupRule;
import strategy.InPersonRule;
import strategy.IndividualRule;
import strategy.ParticipantRule;
import strategy.UrgentRule;
import strategy.VirtualRule;

public class AppointmentTypeRuleValidator {

    private final List<BookingRuleStrategy> rules;

    public AppointmentTypeRuleValidator() {
        rules = new ArrayList<>();
        rules.add(new DurationRule());
        rules.add(new ParticipantRule());
        rules.add(new UrgentRule());
        rules.add(new FollowUpRule());
        rules.add(new AssessmentRule());
        rules.add(new VirtualRule());
        rules.add(new InPersonRule());
        rules.add(new IndividualRule());
        rules.add(new GroupRule());
    }

    public void validate(Appointment appointment) {
        for (BookingRuleStrategy rule : rules) {
            if (!rule.isValid(appointment)) {
                throw new IllegalArgumentException("Appointment rules are not valid for this type.");
            }
        }
    }
}
