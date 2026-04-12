package observer;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmailNotificationServiceTest {

    @Test
    void testUpdate_CallsSendEmail() {

        EmailNotificationService serviceMock = mock(EmailNotificationService.class);

        doCallRealMethod().when(serviceMock).update(anyString());
        doNothing().when(serviceMock).sendEmail(anyString(), anyString(), anyString());

        serviceMock.update("Test reminder");

        verify(serviceMock).sendEmail(
                eq("s12323530@stu.najah.edu"),
                eq("Appointment Notification"),
                eq("Test reminder")
        );
    }

    @Test
    void testUpdate_CallsSendEmailTwice() {

        EmailNotificationService serviceMock = mock(EmailNotificationService.class);

        doCallRealMethod().when(serviceMock).update(anyString());
        doNothing().when(serviceMock).sendEmail(anyString(), anyString(), anyString());

        serviceMock.update("First reminder");
        serviceMock.update("Second reminder");

        verify(serviceMock, times(2)).sendEmail(
                eq("s12323530@stu.najah.edu"),
                eq("Appointment Notification"),
                anyString()
        );
    }

    @Test
    void testUpdate_SendsCorrectMessage() {

        EmailNotificationService serviceMock = mock(EmailNotificationService.class);

        doCallRealMethod().when(serviceMock).update(anyString());
        doNothing().when(serviceMock).sendEmail(anyString(), anyString(), anyString());

        String message = "Appointment booked successfully";

        serviceMock.update(message);

        verify(serviceMock).sendEmail(
                eq("s12323530@stu.najah.edu"),
                eq("Appointment Notification"),
                eq(message)
        );
    }
}