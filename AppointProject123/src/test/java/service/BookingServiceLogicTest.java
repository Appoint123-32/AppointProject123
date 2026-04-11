package service;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;
import repository.AppointmentRepository;

class BookingServiceLogicTest {
    private BookingService bookingService;
    private AppointmentRepository repository;

    @BeforeEach
    void setup() {
        repository = new AppointmentRepository();
        bookingService = new BookingService(repository);
        
        // Add a test appointment: ID 1, Capacity 1 (Individual)
        Appointment app = new Appointment(1, "Test", LocalDateTime.now(), 30, 
                                        AppointmentCategory.FOLLOW_UP, AppointmentMode.IN_PERSON, 
                                        AppointmentFormat.INDIVIDUAL, 1, "Office", "");
        repository.addAppointment(app);
    }

    @Test
    void testBookSlot_Success() {
        bookingService.bookSlot(1, "user1");
        Appointment app = repository.findById(1);
        assertEquals(1, app.getBookedUsers().size());
        assertEquals("FULL", app.getStatus());
    }

    @Test
    void testBookSlot_WhenFull_Fails() {
        bookingService.bookSlot(1, "user1"); // Now it's full
        bookingService.bookSlot(1, "user2"); // Should not add user2
        assertEquals(1, repository.findById(1).getBookedUsers().size());
    }

    @Test
    void testBookSlot_DuplicateUser_Fails() {
        bookingService.bookSlot(1, "user1");
        bookingService.bookSlot(1, "user1"); // Same user
        assertEquals(1, repository.findById(1).getBookedUsers().size());
    }

    @Test
    void testCancelAppointment_Success() {
        bookingService.bookSlot(1, "user1");
        bookingService.cancelAppointment(1);
        assertEquals(0, repository.findById(1).getBookedUsers().size());
        assertEquals("AVAILABLE", repository.findById(1).getStatus());
    }

    @Test
    void testAdminModifyAndCancel() {
        bookingService.adminModifyAppointment(1, 45, 3, "NEW_TYPE");
        Appointment app = repository.findById(1);
        assertEquals(45, app.getDuration());
        assertEquals(3, app.getParticipants());
        
        bookingService.adminCancelAppointment(1);
        assertNull(repository.findById(1));
    }
}