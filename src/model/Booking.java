package model;

public class Booking {
    private String bookingId;
    private Customer customer;
    private Movie movie;
    private int row;
    private int column;
    private double totalAmount;

    public Booking(String bookingId, Customer customer, Movie movie, int row, int column, double totalAmount) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.movie = movie;
        this.row = row;
        this.column = column;
        this.totalAmount = totalAmount;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getSeatLabel() {
        char rowChar = (char) ('A' + row);
        int colNum = column + 1;
        return "" + rowChar + colNum;
    }

    @Override
    public String toString() {
        return String.format(
            "Booking ID: %-10s | Customer: %-15s | Movie: %-15s | Seat: %-4s | Time: %-8s | Total: $%.2f",
            bookingId, customer.getCustomerName(), movie.getMovieName(), getSeatLabel(), movie.getShowTime(), totalAmount
        );
    }
}
