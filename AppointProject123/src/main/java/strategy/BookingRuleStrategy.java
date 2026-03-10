package strategy;
import model.Appointment;
public interface BookingRuleStrategy {
	 boolean isValid(Appointment appointment);
}
