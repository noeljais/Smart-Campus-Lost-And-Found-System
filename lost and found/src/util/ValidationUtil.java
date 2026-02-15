package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * Utility class for validation operations
 */
public class ValidationUtil {
    
    // Email pattern for campus emails
    private static final Pattern CAMPUS_EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(edu|ac\\.in)$");
    
    // General email pattern
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    
    /**
     * Validate campus email
     * @param email Email to validate
     * @return true if valid campus email
     */
    public static boolean isValidCampusEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return CAMPUS_EMAIL_PATTERN.matcher(email.trim()).matches();
    }
    
    /**
     * Validate general email
     * @param email Email to validate
     * @return true if valid email
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }
    
    /**
     * Validate password strength
     * @param password Password to validate
     * @return true if password meets requirements
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        return true;
    }
    
    /**
     * Validate phone number
     * @param phone Phone number to validate
     * @return true if valid phone number
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return true; // Phone is optional
        }
        // Remove spaces and dashes
        String cleanPhone = phone.replaceAll("[\\s-]", "");
        return cleanPhone.matches("^[0-9]{10,15}$");
    }
    
    /**
     * Hash password using SHA-256
     * @param password Plain text password
     * @return Hashed password
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error hashing password: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Validate if string is not empty
     * @param str String to validate
     * @return true if not empty
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
    
    /**
     * Get validation error message for email
     * @param email Email to validate
     * @return Error message or null if valid
     */
    public static String getEmailValidationError(String email) {
        if (email == null || email.trim().isEmpty()) {
            return "Email is required";
        }
        if (!isValidEmail(email)) {
            return "Invalid email format";
        }
        // Allow any valid email format (campus email restriction removed)
        return null;
    }
    
    /**
     * Get validation error message for password
     * @param password Password to validate
     * @return Error message or null if valid
     */
    public static String getPasswordValidationError(String password) {
        if (password == null || password.isEmpty()) {
            return "Password is required";
        }
        if (password.length() < 6) {
            return "Password must be at least 6 characters";
        }
        return null;
    }
}
