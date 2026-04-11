package strategy;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import model.*;

class AdditionalStrategyTests {

    @Test
    void testVirtualAndInPersonRules() {
        VirtualRule vRule = new VirtualRule();
        InPersonRule iRule = new InPersonRule();
        
        Appointment vApp = new Appointment(1, "T", LocalDateTime.now(), 20, 
                                         AppointmentCategory.FOLLOW_UP, AppointmentMode.VIRTUAL, 
                                         AppointmentFormat.INDIVIDUAL, 1, "", "http://zoom.us");
        
        Appointment iApp = new Appointment(2, "T", LocalDateTime.now(), 20, 
                                         AppointmentCategory.FOLLOW_UP, AppointmentMode.IN_PERSON, 
                                         AppointmentFormat.INDIVIDUAL, 1, "Room 101", "");

        assertTrue(vRule.isValid(vApp));
        assertTrue(iRule.isValid(iApp));
    }

    @Test
    void testIndividualAndGroupRules() {
        IndividualRule indRule = new IndividualRule();
        GroupRule grpRule = new GroupRule();
        
        Appointment app = new Appointment(1, "T", LocalDateTime.now(), 20, 
                                         AppointmentCategory.FOLLOW_UP, AppointmentMode.IN_PERSON, 
                                         AppointmentFormat.INDIVIDUAL, 1, "Loc", "");
        
        assertTrue(indRule.isValid(app));
        
        app.setParticipants(3);
        app.setFormat(AppointmentFormat.GROUP);
        assertTrue(grpRule.isValid(app));
        
        app.setParticipants(10); // Too many for a group (Limit is 5)
        assertFalse(grpRule.isValid(app));
    }
}