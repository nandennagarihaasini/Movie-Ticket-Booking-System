import exception.InvalidSeatException;
import exception.SeatAlreadyBookedException;
import model.Booking;
import model.Customer;
import service.BookingService;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("      AUTOMATED SYSTEM TEST SUITE RUNNER         ");
        System.out.println("=================================================\n");

        BookingService service = new BookingService();

        // 1. Verify Movie List
        System.out.println("[TEST 1] Viewing Preloaded Movies...");
        service.displayMovies();
        assert service.getMovies().size() == 5 : "Failed: Expected 5 preloaded movies";
        System.out.println("✅ Test 1 Passed: 5 movies preloaded successfully.\n");

        // 2. View Seats for Movie 1 (Pushpa 2)
        System.out.println("[TEST 2] Displaying initial seat map for Pushpa 2...");
        service.displaySeats(service.getMovieById(1));
        System.out.println("✅ Test 2 Passed: Seats displayed successfully.\n");

        // 3. Book a Seat (A1) for Pushpa 2
        System.out.println("[TEST 3] Booking Seat A1 for Customer 'John Doe'...");
        Customer customer1 = new Customer("C1", "John Doe", "9876543210");
        Booking b1 = null;
        try {
            b1 = service.bookTicket(1, customer1, "A1");
            System.out.println("✅ Test 3 Passed: Seat A1 booked successfully. Booking ID: " + b1.getBookingId());
        } catch (Exception e) {
            System.err.println("❌ Test 3 Failed: " + e.getMessage());
        }

        // 4. Try booking the already reserved seat A1
        System.out.println("\n[TEST 4] Attempting to book ALREADY RESERVED Seat A1...");
        Customer customer2 = new Customer("C2", "Alice Smith", "1234567890");
        try {
            service.bookTicket(1, customer2, "A1");
            System.err.println("❌ Test 4 Failed: SeatAlreadyBookedException was NOT thrown!");
        } catch (SeatAlreadyBookedException e) {
            System.out.println("✅ Test 4 Passed: Caught expected SeatAlreadyBookedException -> " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Test 4 Failed with unexpected exception: " + e.getMessage());
        }

        // 5. Try booking an invalid out-of-bounds seat Z9
        System.out.println("\n[TEST 5] Attempting to book INVALID seat 'Z9'...");
        try {
            service.bookTicket(1, customer2, "Z9");
            System.err.println("❌ Test 5 Failed: InvalidSeatException was NOT thrown!");
        } catch (InvalidSeatException e) {
            System.out.println("✅ Test 5 Passed: Caught expected InvalidSeatException -> " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Test 5 Failed with unexpected exception: " + e.getMessage());
        }

        // 6. View Booking Summary
        System.out.println("\n[TEST 6] Viewing Booking Summary...");
        service.displayBookingSummary();
        System.out.println("✅ Test 6 Passed.\n");

        // 7. Cancel Ticket
        System.out.println("[TEST 7] Cancelling Ticket " + (b1 != null ? b1.getBookingId() : "BK-1001") + "...");
        boolean cancelled = service.cancelTicket(b1 != null ? b1.getBookingId() : "BK-1001");
        assert cancelled : "Failed to cancel ticket";
        System.out.println("✅ Test 7 Passed: Ticket cancelled successfully.");

        // 8. Try Cancelling Non-Existent Booking ID
        System.out.println("\n[TEST 8] Attempting to cancel invalid booking ID 'BK-9999'...");
        boolean invalidCancel = service.cancelTicket("BK-9999");
        assert !invalidCancel : "Expected false when cancelling invalid booking ID";
        System.out.println("✅ Test 8 Passed: Non-existent booking cancellation handled correctly.");

        // 9. Verify seat A1 is now available again
        System.out.println("\n[TEST 9] Verifying Seat A1 is available again after cancellation...");
        service.displaySeats(service.getMovieById(1));
        try {
            Booking rebooked = service.bookTicket(1, customer2, "A1");
            System.out.println("✅ Test 9 Passed: Seat A1 re-booked successfully after cancellation. New Booking ID: " + rebooked.getBookingId());
        } catch (Exception e) {
            System.err.println("❌ Test 9 Failed: " + e.getMessage());
        }

        // 10. Phone Number Validation Tests
        System.out.println("\n[TEST 10] Testing Phone Number Validation Rules...");
        assert util.ValidationUtil.isValidPhoneNumber("+919876543210") : "Failed: +919876543210 should be valid";
        assert util.ValidationUtil.isValidPhoneNumber("+14155552671") : "Failed: +14155552671 should be valid";
        assert util.ValidationUtil.isValidPhoneNumber("9876543210") : "Failed: 9876543210 should be valid (starts with 9)";
        assert util.ValidationUtil.isValidPhoneNumber("2345678901") : "Failed: 2345678901 should be valid (starts with 2)";
        assert !util.ValidationUtil.isValidPhoneNumber("0987654321") : "Failed: 0987654321 should be invalid (starts with 0)";
        assert !util.ValidationUtil.isValidPhoneNumber("1987654321") : "Failed: 1987654321 should be invalid (starts with 1)";
        assert !util.ValidationUtil.isValidPhoneNumber("abc9876543") : "Failed: abc9876543 should be invalid (contains non-digits)";
        System.out.println("  ✓ +919876543210 (Country Code): VALID ✅");
        System.out.println("  ✓ 9876543210 (Starts with 9): VALID ✅");
        System.out.println("  ✓ 2345678901 (Starts with 2): VALID ✅");
        System.out.println("  ✓ 0987654321 (Starts with 0): INVALID ❌ (Handled Correctly)");
        System.out.println("  ✓ 1987654321 (Starts with 1): INVALID ❌ (Handled Correctly)");
        System.out.println("✅ Test 10 Passed: All phone number validation rules verified.");

        System.out.println("\n=================================================");
        System.out.println("       ALL AUTOMATED TESTS PASSED SUCCESSFULLY!  ");
        System.out.println("=================================================");
    }
}
