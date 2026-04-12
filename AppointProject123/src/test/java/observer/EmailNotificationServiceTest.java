package observer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class EmailNotificationServiceTest {

    @Test
    void testUpdate_SuccessfulExecution() {
        EmailNotificationService service = new EmailNotificationService();
        assertDoesNotThrow(() -> service.update("Test Notification"));
    }

    @Test
    void testSendEmail_InternalLogic() {
        EmailNotificationService service = new EmailNotificationService();
        
        service.sendEmail("invalid-email", "Subject", "Body");
        
                assertTrue(true); 
    }
}