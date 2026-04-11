package observer;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

public class EmailNotificationServiceTest {

    @Test
    void testUpdate_CallsSendEmail() {
        EmailNotificationService service = spy(new EmailNotificationService());

        doNothing().when(service).sendEmail(anyString(), anyString(), anyString());

        service.update("Test reminder");

        verify(service).sendEmail(
                "s12323530@stu.najah.edu",
                "Appointment Notification",
                "Test reminder"
        );
    }
}
