# Movie Ticket Booking System (Core Java)

A console-based application built in Core Java where customers can view movies, check real-time seat availability (5x5 grid), book tickets, cancel bookings, view booking summaries, and validate phone numbers.

## 📁 Project Structure

```text
MovieTicketBookingSystem/
│
├── model/
│     ├── Movie.java
│     ├── Customer.java
│     └── Booking.java
│
├── service/
│     └── BookingService.java
│
├── exception/
│     ├── SeatAlreadyBookedException.java
│     ├── InvalidSeatException.java
│     └── InvalidPhoneNumberException.java
│
├── util/
│     ├── TicketGenerator.java
│     └── ValidationUtil.java
│
├── Main.java
└── TestRunner.java
```

## ✨ Key Features

- **Preloaded Movie List**: 5 movies preloaded with show times and pricing.
- **5x5 Interactive Seat Grid**: Real-time visual seat matrix (`O` = Available, `X` = Booked).
- **Phone Number Validation**: Accepts country codes (`+`) or numbers starting with digits 2–9.
- **Custom Exception Handling**: Custom exceptions for seat collisions, invalid seat formats, and invalid phone numbers.
- **Printable Receipts**: Auto-generated ASCII ticket confirmation receipts.
- **Ticket Cancellation**: Restores seat availability state and removes bookings.
- **Automated Test Suite**: Built-in `TestRunner.java` verifying all 10 system edge cases.

## 🚀 How to Run

### Run Interactive Application
```bash
javac model/*.java exception/*.java util/*.java service/*.java Main.java
java Main
```

### Run Automated Tests
```bash
javac TestRunner.java
java -ea TestRunner
```
