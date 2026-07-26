package util;

import model.Booking;

public class TicketGenerator {

    public static void printTicket(Booking booking) {
        System.out.println("\n==============================================");
        System.out.println("            CINEMA TICKET CONFIRMATION        ");
        System.out.println("==============================================");
        System.out.printf("  Booking ID    : %s\n", booking.getBookingId());
        System.out.printf("  Customer Name : %s\n", booking.getCustomer().getCustomerName());
        System.out.printf("  Phone Number  : %s\n", booking.getCustomer().getPhoneNumber());
        System.out.println("----------------------------------------------");
        System.out.printf("  Movie Name    : %s\n", booking.getMovie().getMovieName());
        System.out.printf("  Show Time     : %s\n", booking.getMovie().getShowTime());
        System.out.printf("  Seat Number   : %s\n", booking.getSeatLabel());
        System.out.printf("  Ticket Price  : $%.2f\n", booking.getTotalAmount());
        System.out.println("==============================================");
        System.out.println("        Thank you for booking with us!        ");
        System.out.println("==============================================\n");
    }
}
