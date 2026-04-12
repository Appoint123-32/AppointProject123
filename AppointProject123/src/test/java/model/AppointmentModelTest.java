
package model;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class AppointmentModelTest {

    @Test
    void testAppointmentLogic() {
        Appointment app = new Appointment(1, LocalDateTime.now(), 30, 1, "FOLLOW_UP");
        
        app.setTitle("New Title");
        assertEquals("New Title", app.getTitle());
        
        app.setParticipants(3);
        assertEquals(3, app.getCapacity());
        assertEquals(AppointmentFormat.GROUP, app.getFormat());

        app.addBooking("userA");
        assertTrue(app.getBookedUsers().contains("userA"));
        assertEquals(1, app.getBookedCount());
        
        assertNotNull(app.toString());
    }

    @Test
    void testAddBookingWhenFullThrows() {
        Appointment app = new Appointment(1, LocalDateTime.now(), 30, 1, "TYPE");
        app.addBooking("user1"); 
        
        assertThrows(IllegalStateException.class, () -> app.addBooking("user2"));
    }
    @Test
    void testToString_Branches() {
        // We need a dummy date for the constructor
        LocalDateTime now = LocalDateTime.now();
        
        // Branch 1: Test when Location and Meeting Link are empty/null
        Appointment app1 = new Appointment(1, now, 30, 1, "TYPE");
        app1.setLocation(""); // empty
        app1.setMeetingLink(null); // null
        
        String output1 = app1.toString();
        // Check if the specific labels show the placeholder "-"
        assertTrue(output1.contains("Location: -"));
        assertTrue(output1.contains("Meeting Link: -"));

        // Branch 2: Test when Location and Meeting Link are actually provided
        app1.setLocation("Room 101");
        app1.setMeetingLink("https://zoom.us/j/123");
        
        String output2 = app1.toString();
        // Check that the specific labels do NOT show the "-" placeholder anymore
        assertFalse(output2.contains("Location: -"));
        assertFalse(output2.contains("Meeting Link: -"));
        
        // Verify the actual values are there
        assertTrue(output2.contains("Location: Room 101"));
        assertTrue(output2.contains("Meeting Link: https://zoom.us/j/123"));
    }

    @Test
    void testConstructors_Branches() {
        Appointment app = new Appointment(101, LocalDateTime.now(), 60, 1, "TYPE");
        assertEquals("Appointment 101", app.getTitle());
    }
}