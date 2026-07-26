package util;

public class ValidationUtil {

    /**
     * Validates phone numbers:
     * - Valid if it starts with '+' (country code) followed by valid digits.
     * - Valid if without '+' it starts with a digit from 2 to 9 (e.g. 9876543210).
     * - Invalid if it starts with 0 or 1 without '+', contains non-digits, or is too short/long.
     */
    public static boolean isValidPhoneNumber(String phone) {
        if (phone == null) {
            return false;
        }
        String trimmed = phone.trim();
        if (trimmed.isEmpty()) {
            return false;
        }

        if (trimmed.startsWith("+")) {
            String digitsOnly = trimmed.substring(1).replaceAll("[\\s-]", "");
            // Must have between 7 and 15 digits, first digit 1-9
            return digitsOnly.length() >= 7 && digitsOnly.length() <= 15 && digitsOnly.matches("[1-9]\\d*");
        } else {
            char firstChar = trimmed.charAt(0);
            if (firstChar < '2' || firstChar > '9') {
                return false;
            }
            String digitsOnly = trimmed.replaceAll("[\\s-]", "");
            return digitsOnly.length() >= 7 && digitsOnly.length() <= 15 && digitsOnly.matches("\\d+");
        }
    }
}
