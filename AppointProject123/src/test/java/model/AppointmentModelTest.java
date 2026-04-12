
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
        LocalDateTime now = LocalDateTime.now();
        
        Appointment app1 = new Appointment(1, now, 30, 1, "TYPE");
        app1.setLocation(""); // empty
        app1.setMeetingLink(null); // null
        
        String output1 = app1.toString();
        assertTrue(output1.contains("Location: -"));
        assertTrue(output1.contains("Meeting Link: -"));

        app1.setLocation("Room 101");
        app1.setMeetingLink("https://zoom.us/j/123");
        
        String output2 = app1.toString();
     
        assertFalse(output2.contains("Location: -"));
        assertFalse(output2.contains("Meeting Link: -"));
        
        
        assertTrue(output2.contains("Location: Room 101"));
        assertTrue(output2.contains("Meeting Link: https://zoom.us/j/123"));
    }

    @Test
    void testConstructors_Branches() {
        Appointment app = new Appointment(101, LocalDateTime.now(), 60, 1, "TYPE");
        assertEquals("Appointment 101", app.getTitle());
    }
    @Test
    void testConstructorsAndSetters() {
        Appointment app1 = new Appointment(100, java.time.LocalDateTime.now(), 30, 1, "TYPE");
        assertEquals("Appointment 100", app1.getTitle());

        Appointment app2 = new Appointment(101, "Title", java.time.LocalDateTime.now(), 45, 2, "TYPE");
        assertEquals(AppointmentFormat.GROUP, app2.getFormat());

        app1.setParticipants(1);
        assertEquals(AppointmentFormat.INDIVIDUAL, app1.getFormat());
        app1.setParticipants(5);
        assertEquals(AppointmentFormat.GROUP, app1.getFormat());

        Appointment appFull = new Appointment(200, java.time.LocalDateTime.now(), 30, 1, "TYPE");
        appFull.addBooking("User1"); 
        assertEquals("FULL", appFull.getStatus());
        assertTrue(appFull.isFull());
    }
}