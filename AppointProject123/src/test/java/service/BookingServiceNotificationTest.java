package service;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import observer.Observer;

public class BookingServiceNotificationTest {

    @Test
    void testNotificationService_IsCalled() {

        Observer mockObserver = mock(Observer.class);

        String message = "Appointment booked successfully";

        mockObserver.update(message);

        verify(mockObserver, times(1)).update(message);
    }
}