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
        app.addBooking("user1"); // Full now
        
        assertThrows(IllegalStateException.class, () -> app.addBooking("user2"));
    }
}