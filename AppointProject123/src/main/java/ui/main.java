package ui;

import service.BookingService;
import repository.AppointmentRepository;
import model.Appointment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        AppointmentRepository repository = new AppointmentRepository();
        BookingService service = new BookingService(repository);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Appointment System =====");
            System.out.println("1. Book Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();

                    service.bookAppointment(id, LocalDateTime.now(), 30, 1, "virtual");
                    break;

                case 2:

                    List<Appointment> list = service.getAllAppointments();

                    for (Appointment a : list) {
                        System.out.println("ID: " + a.getId() + " | Type: " + a.getType() + " | Status: " + a.getStatus());
                    }

                    break;

                case 3:

                    System.out.print("Enter appointment ID to cancel: ");
                    int cancelId = scanner.nextInt();

                    service.cancelAppointment(cancelId);
                    break;

                case 4:

                    System.out.println("Exiting system...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}