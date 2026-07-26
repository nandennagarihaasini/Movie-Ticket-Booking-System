import exception.InvalidSeatException;
import exception.SeatAlreadyBookedException;
import model.Customer;
import model.Movie;
import service.BookingService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BookingService bookingService = new BookingService();
        Scanner scanner = new Scanner(System.in);
        int customerCounter = 1;

        System.out.println("=================================================");
        System.out.println("   WELCOME TO THE MOVIE TICKET BOOKING SYSTEM    ");
        System.out.println("=================================================");

        while (true) {
            System.out.println("\n=============================");
            System.out.println(" MOVIE TICKET BOOKING SYSTEM ");
            System.out.println("=============================");
            System.out.println("1. View Movies");
            System.out.println("2. View Seats");
            System.out.println("3. Book Ticket");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Booking Summary");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            String input = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter a number between 1 and 6.");
                continue;
            }

            switch (choice) {
                case 1:
                    bookingService.displayMovies();
                    break;

                case 2:
                    bookingService.displayMovies();
                    System.out.print("Enter Movie ID to view seat availability: ");
                    String movieIdInput = scanner.nextLine().trim();
                    try {
                        int movieId = Integer.parseInt(movieIdInput);
                        Movie movie = bookingService.getMovieById(movieId);
                        if (movie != null) {
                            bookingService.displaySeats(movie);
                        } else {
                            System.out.println("❌ Error: Movie ID " + movieId + " not found!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid Movie ID format!");
                    }
                    break;

                case 3:
                    bookingService.displayMovies();
                    System.out.print("Enter Movie ID to book: ");
                    String bookMovieIdInput = scanner.nextLine().trim();
                    int bookMovieId;
                    try {
                        bookMovieId = Integer.parseInt(bookMovieIdInput);
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Invalid Movie ID format!");
                        break;
                    }

                    Movie selectedMovie = bookingService.getMovieById(bookMovieId);
                    if (selectedMovie == null) {
                        System.out.println("❌ Error: Movie ID " + bookMovieId + " does not exist!");
                        break;
                    }

                    System.out.print("Enter Customer Name: ");
                    String custName = scanner.nextLine().trim();
                    if (custName.isEmpty()) {
                        System.out.println("❌ Customer name cannot be empty!");
                        break;
                    }

                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine().trim();
                    if (!util.ValidationUtil.isValidPhoneNumber(phone)) {
                        System.out.println("❌ Invalid Phone Number! Phone number must either start with a '+' country code (e.g. +919876543210) or start with a digit between 2 and 9 (e.g. 9876543210).");
                        break;
                    }

                    Customer customer = new Customer("CUST-" + (customerCounter++), custName, phone);

                    bookingService.displaySeats(selectedMovie);
                    System.out.print("Enter Seat Code to book (e.g. A1, B3, E5): ");
                    String seatCode = scanner.nextLine().trim();

                    try {
                        bookingService.bookTicket(bookMovieId, customer, seatCode);
                    } catch (InvalidSeatException e) {
                        System.out.println("❌ Seat Error: " + e.getMessage());
                    } catch (SeatAlreadyBookedException e) {
                        System.out.println("❌ Booking Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("❌ An unexpected error occurred: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter Booking ID to cancel (e.g. BK-1001): ");
                    String bookingId = scanner.nextLine().trim();
                    boolean cancelled = bookingService.cancelTicket(bookingId);
                    if (!cancelled) {
                        System.out.println("❌ Error: Booking ID " + bookingId + " not found!");
                    }
                    break;

                case 5:
                    bookingService.displayBookingSummary();
                    break;

                case 6:
                    System.out.println("\nThank you for using Movie Ticket Booking System! Goodbye! 👋");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice! Please select an option between 1 and 6.");
                    break;
            }
        }
    }
}
