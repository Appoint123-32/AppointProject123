package ui;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import model.Appointment;
import repository.AppointmentRepository;
import service.AuthenticationService;
import service.BookingService;

public class main {

    public static void main(String[] args) {

        AppointmentRepository repository = new AppointmentRepository();
        BookingService bookingService = new BookingService(repository);
        AuthenticationService authService = new AuthenticationService();

        Scanner scanner = new Scanner(System.in);
        String currentUsername = "";

        while (true) {

            if (!authService.isLoggedIn()) {
                System.out.println("\n===== Login =====");
                System.out.print("Enter username: ");
                String username = scanner.next();

                System.out.print("Enter password: ");
                String password = scanner.next();

                if (authService.login(username, password)) {
                    currentUsername = username;
                    System.out.println("Login successful!");
                } else {
                    System.out.println("Invalid username or password.");
                }

                continue;
            }

            if (currentUsername.equals("admin")) {

            	System.out.println("\n===== Admin Menu =====");
            	System.out.println("1. Add Appointment Slot");
            	System.out.println("2. View All Appointments");
            	System.out.println("3. Modify Appointment");
            	System.out.println("4. Delete Appointment");
            	System.out.println("5. Logout");
            	System.out.println("6. Exit");
            	System.out.print("Choose option: ");

                int choice = scanner.nextInt();

                switch (choice) {

                    case 1:
                        System.out.print("Enter slot ID: ");
                        int id = scanner.nextInt();

                        System.out.print("Enter duration in minutes: ");
                        int duration = scanner.nextInt();

                        System.out.print("Enter number of participants: ");
                        int participants = scanner.nextInt();

                        System.out.print("Enter appointment type: ");
                        String type = scanner.next();

                        bookingService.adminAddSlot(id, LocalDateTime.now(), duration, participants, type);
                        break;

                    case 2:
                        List<Appointment> allAppointments = bookingService.getAllAppointments();

                        if (allAppointments.isEmpty()) {
                            System.out.println("No appointments found.");
                        } else {
                            for (Appointment a : allAppointments) {
                                System.out.println("ID: " + a.getId()
                                        + " | Type: " + a.getType()
                                        + " | Status: " + a.getStatus()
                                        + " | Booked By: " + a.getBookedUsers());
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter appointment ID to modify: ");
                        int modId = scanner.nextInt();

                        System.out.print("Enter new duration: ");
                        int newDuration = scanner.nextInt();

                        System.out.print("Enter new participants: ");
                        int newParticipants = scanner.nextInt();

                        System.out.print("Enter new type: ");
                        String newType = scanner.next();

                        bookingService.adminModifyAppointment(modId, newDuration, newParticipants, newType);
                        break;

                    case 4:
                        System.out.print("Enter appointment ID to delete: ");
                        int delId = scanner.nextInt();

                        bookingService.adminCancelAppointment(delId);
                        break;

                    case 5:
                        authService.logout();
                        currentUsername = "";
                        break;

                    case 6:
                        System.out.println("Exiting system...");
                        scanner.close();
                        return;


                    default:
                        System.out.println("Invalid choice.");
                }

            } else {

                System.out.println("\n===== User Menu =====");
                System.out.println("1. View Available Slots");
                System.out.println("2. Book Appointment");
                System.out.println("3. Cancel Appointment");
                System.out.println("4. Logout");
                System.out.println("5. Exit");
                System.out.print("Choose option: ");

                int choice = scanner.nextInt();

                switch (choice) {

                    case 1:
                        List<Appointment> availableAppointments = bookingService.viewAvailableSlots();

                        if (availableAppointments.isEmpty()) {
                            System.out.println("No available slots.");
                        } else {
                            for (Appointment a : availableAppointments) {
                                System.out.println("ID: " + a.getId()
                                        + " | Type: " + a.getType()
                                        + " | Status: " + a.getStatus()
                                        + " | Duration: " + a.getDuration()
                                        + " | Participants: " + a.getParticipants());
                            }
                        }
                        break;

                    case 2:
                        System.out.print("Enter slot ID to book: ");
                        int bookId = scanner.nextInt();

                        bookingService.bookSlot(bookId, currentUsername);
                        break;

                    case 3:
                        System.out.print("Enter appointment ID to cancel: ");
                        int cancelId = scanner.nextInt();

                        bookingService.cancelAppointment(cancelId);
                        break;

                    case 4:
                        authService.logout();
                        currentUsername = "";
                        break;

                    case 5:
                        System.out.println("Exiting system...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
    }
}