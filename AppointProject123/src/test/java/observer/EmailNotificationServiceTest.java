package observer;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmailNotificationServiceTest {

    @Test
    void testUpdate_CallsSendEmail() {
        // 1. Create a regular mock instead of a spy
        EmailNotificationService serviceMock = mock(EmailNotificationService.class);

        // 2. Tell Mockito to execute the REAL 'update' method logic when called
        doCallRealMethod().when(serviceMock).update(anyString());

        // 3. Ensure 'sendEmail' does nothing (prevents real email sending)
        doNothing().when(serviceMock).sendEmail(anyString(), anyString(), anyString());

        // 4. Call the method
        serviceMock.update("Test reminder");

        // 5. Verify sendEmail was called with expected parameters
        verify(serviceMock).sendEmail(
                eq("s12323530@stu.najah.edu"),
                eq("Appointment Notification"),
                eq("Test reminder")
        );
    }
}