package service;

import exception.InvalidSeatException;
import exception.SeatAlreadyBookedException;
import model.Booking;
import model.Customer;
import model.Movie;
import util.TicketGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookingService {
    private ArrayList<Movie> movies;
    private HashMap<String, Booking> bookings;
    private int bookingCounter;

    public BookingService() {
        movies = new ArrayList<>();
        bookings = new HashMap<>();
        bookingCounter = 1001;
        preloadMovies();
    }

    private void preloadMovies() {
        movies.add(new Movie(1, "Pushpa 2", "06:30 PM", 12.50));
        movies.add(new Movie(2, "Kalki 2898 AD", "09:00 PM", 15.00));
        movies.add(new Movie(3, "Salaar", "02:00 PM", 10.00));
        movies.add(new Movie(4, "Leo", "04:30 PM", 11.00));
        movies.add(new Movie(5, "Devara", "10:00 AM", 13.50));
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public Movie getMovieById(int movieId) {
        for (Movie m : movies) {
            if (m.getMovieId() == movieId) {
                return m;
            }
        }
        return null;
    }

    public void displayMovies() {
        System.out.println("\n------------------------------------------------------------");
        System.out.println("                   AVAILABLE MOVIES                        ");
        System.out.println("------------------------------------------------------------");
        for (Movie m : movies) {
            System.out.println(m);
        }
        System.out.println("------------------------------------------------------------");
    }

    public void displaySeats(Movie movie) {
        System.out.println("\n=================================");
        System.out.println("   MOVIE: " + movie.getMovieName().toUpperCase() + " (" + movie.getShowTime() + ")");
        System.out.println("=================================");
        System.out.println("             SCREEN              \n");

        System.out.println("    1   2   3   4   5");
        System.out.println("  -----------------");
        char rowHeader = 'A';
        boolean[][] seats = movie.getSeats();

        for (int r = 0; r < 5; r++) {
            System.out.print((char)(rowHeader + r) + " | ");
            for (int c = 0; c < 5; c++) {
                char symbol = seats[r][c] ? 'X' : 'O';
                System.out.print(symbol + "   ");
            }
            System.out.println();
        }
        System.out.println("\nLegend: [O] Available   [X] Booked");
        System.out.println("=================================\n");
    }

    public Booking bookTicket(int movieId, Customer customer, String seatCode) 
            throws InvalidSeatException, SeatAlreadyBookedException {
        Movie movie = getMovieById(movieId);
        if (movie == null) {
            throw new IllegalArgumentException("Invalid Movie ID: " + movieId);
        }

        int[] coords = parseSeatCode(seatCode);
        int row = coords[0];
        int col = coords[1];

        if (movie.isSeatBooked(row, col)) {
            throw new SeatAlreadyBookedException("Seat " + seatCode.toUpperCase() + " is already booked for " + movie.getMovieName() + "!");
        }

        movie.bookSeat(row, col);
        String bookingId = "BK-" + (bookingCounter++);
        Booking booking = new Booking(bookingId, customer, movie, row, col, movie.getTicketPrice());

        bookings.put(bookingId, booking);
        TicketGenerator.printTicket(booking);

        return booking;
    }

    public boolean cancelTicket(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
            return false;
        }

        Movie movie = booking.getMovie();
        movie.cancelSeat(booking.getRow(), booking.getColumn());
        bookings.remove(bookingId);

        System.out.println("\n✅ Booking " + bookingId + " for " + booking.getCustomer().getCustomerName() + 
                           " (Seat " + booking.getSeatLabel() + ") has been successfully cancelled!");
        return true;
    }

    public void displayBookingSummary() {
        if (bookings.isEmpty()) {
            System.out.println("\n⚠️ No bookings found in the system.");
            return;
        }

        System.out.println("\n==========================================================================================");
        System.out.println("                                   BOOKING SUMMARY                                        ");
        System.out.println("==========================================================================================");
        for (Map.Entry<String, Booking> entry : bookings.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println("==========================================================================================");
        System.out.println("Total Bookings: " + bookings.size() + "\n");
    }

    private int[] parseSeatCode(String seatCode) throws InvalidSeatException {
        if (seatCode == null || seatCode.trim().length() < 2 || seatCode.trim().length() > 3) {
            throw new InvalidSeatException("Invalid seat format! Expected format like A1, B3, E5.");
        }

        String cleaned = seatCode.trim().toUpperCase();
        char rowChar = cleaned.charAt(0);
        int row = rowChar - 'A';

        int col;
        try {
            col = Integer.parseInt(cleaned.substring(1)) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidSeatException("Invalid column number in seat code: " + seatCode);
        }

        if (row < 0 || row > 4 || col < 0 || col > 4) {
            throw new InvalidSeatException("Seat " + seatCode.toUpperCase() + " is out of bounds! Rows are A-E and Columns are 1-5.");
        }

        return new int[]{row, col};
    }
}
