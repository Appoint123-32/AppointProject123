package service;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import model.*;

class AppointmentRulesExtendedTest {

    @Test
    void testValidationFailures() {
        //Negative Duration
        Appointment app1 = new Appointment(1, "Title", LocalDateTime.now(), -5, 1, "TYPE");
        assertThrows(IllegalArgumentException.class, () -> AppointmentRules.validate(app1));

        //Individual with Capacity > 1
        Appointment app2 = new Appointment(2, "Title", LocalDateTime.now(), 20, 
                                         AppointmentCategory.FOLLOW_UP, AppointmentMode.IN_PERSON, 
                                         AppointmentFormat.INDIVIDUAL, 5, "Loc", "");
        assertThrows(IllegalArgumentException.class, () -> AppointmentRules.validate(app2));

        // Virtual without Link
        Appointment app3 = new Appointment(3, "Title", LocalDateTime.now(), 20, 
                                         AppointmentCategory.FOLLOW_UP, AppointmentMode.VIRTUAL, 
                                         AppointmentFormat.INDIVIDUAL, 1, "", ""); // Blank link
        assertThrows(IllegalArgumentException.class, () -> AppointmentRules.validate(app3));

        //  Urgent as Group
        Appointment app4 = new Appointment(4, "Title", LocalDateTime.now(), 20, 
                                         AppointmentCategory.URGENT, AppointmentMode.IN_PERSON, 
                                         AppointmentFormat.GROUP, 3, "Loc", "");
        assertThrows(IllegalArgumentException.class, () -> AppointmentRules.validate(app4));
        
        //  Assessment duration too short
        Appointment app5 = new Appointment(5, "Title", LocalDateTime.now(), 30, 
                                         AppointmentCategory.ASSESSMENT, AppointmentMode.IN_PERSON, 
                                         AppointmentFormat.INDIVIDUAL, 1, "Loc", "");
        assertThrows(IllegalArgumentException.class, () -> AppointmentRules.validate(app5));
    }
    @Test
    void testAllValidationBranches() {
        // Group capacity < 2
        Appointment app1 = new Appointment(1, "Title", LocalDateTime.now(), 30, 
            AppointmentCategory.FOLLOW_UP, AppointmentMode.IN_PERSON, AppointmentFormat.GROUP, 1, "Loc", "");
        assertThrows(IllegalArgumentException.class, () -> AppointmentRules.validate(app1));

        //  Group capacity > 5
        Appointment app2 = new Appointment(1, "Title", LocalDateTime.now(), 30, 
            AppointmentCategory.FOLLOW_UP, AppointmentMode.IN_PERSON, AppointmentFormat.GROUP, 6, "Loc", "");
        assertThrows(IllegalArgumentException.class, () -> AppointmentRules.validate(app2));

        // In Person without Location
        Appointment app3 = new Appointment(1, "Title", LocalDateTime.now(), 30, 
            AppointmentCategory.FOLLOW_UP, AppointmentMode.IN_PERSON, AppointmentFormat.INDIVIDUAL, 1, "", "");
        assertThrows(IllegalArgumentException.class, () -> AppointmentRules.validate(app3));
        
        // Assessment too short
        Appointment app4 = new Appointment(1, "Title", LocalDateTime.now(), 10, 
            AppointmentCategory.ASSESSMENT, AppointmentMode.IN_PERSON, AppointmentFormat.INDIVIDUAL, 1, "Loc", "");
        assertThrows(IllegalArgumentException.class, () -> AppointmentRules.validate(app4));
    }
}