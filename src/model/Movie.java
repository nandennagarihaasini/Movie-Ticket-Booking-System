package model;

public class Movie {
    private int movieId;
    private String movieName;
    private String showTime;
    private double ticketPrice;
    private boolean[][] seats; // 5x5 seat layout

    public Movie(int movieId, String movieName, String showTime, double ticketPrice) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.showTime = showTime;
        this.ticketPrice = ticketPrice;
        this.seats = new boolean[5][5]; // false = available, true = booked
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public boolean[][] getSeats() {
        return seats;
    }

    public boolean isSeatBooked(int row, int col) {
        return seats[row][col];
    }

    public void bookSeat(int row, int col) {
        seats[row][col] = true;
    }

    public void cancelSeat(int row, int col) {
        seats[row][col] = false;
    }

    @Override
    public String toString() {
        return String.format("ID: %-2d | Movie: %-15s | Show Time: %-10s | Price: $%.2f",
                movieId, movieName, showTime, ticketPrice);
    }
}
