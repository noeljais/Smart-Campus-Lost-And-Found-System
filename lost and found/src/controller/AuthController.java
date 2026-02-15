package controller;

import dao.UserDAO;
import model.User;
import util.ValidationUtil;

/**
 * Controller for authentication operations
 */
public class AuthController {
    
    private UserDAO userDAO;
    private User currentUser;
    
    public AuthController() {
        this.userDAO = new UserDAO();
        this.currentUser = null;
    }
    
    /**
     * Register a new user
     * @param fullName Full name
     * @param email Email address
     * @param password Password
     * @param phone Phone number
     * @return Success message or error message
     */
    public String register(String fullName, String email, String password, String phone) {
        // Validate inputs
        if (!ValidationUtil.isNotEmpty(fullName)) {
            return "Full name is required";
        }
        
        String emailError = ValidationUtil.getEmailValidationError(email);
        if (emailError != null) {
            return emailError;
        }
        
        String passwordError = ValidationUtil.getPasswordValidationError(password);
        if (passwordError != null) {
            return passwordError;
        }
        
        if (!ValidationUtil.isValidPhone(phone)) {
            return "Invalid phone number format";
        }
        
        // Check if email already exists
        if (userDAO.emailExists(email)) {
            return "Email already registered";
        }
        
        // Hash password
        String passwordHash = ValidationUtil.hashPassword(password);
        if (passwordHash == null) {
            return "Error processing password";
        }
        
        // Create user
        User user = new User(fullName, email, passwordHash, phone);
        boolean success = userDAO.createUser(user);
        
        if (success) {
            return "SUCCESS";
        } else {
            return "Registration failed. Please try again.";
        }
    }
    
    /**
     * Login user
     * @param email Email address
     * @param password Password
     * @return Success message or error message
     */
    public String login(String email, String password) {
        // Validate inputs
        if (!ValidationUtil.isNotEmpty(email)) {
            return "Email is required";
        }
        
        if (!ValidationUtil.isNotEmpty(password)) {
            return "Password is required";
        }
        
        // Hash password
        String passwordHash = ValidationUtil.hashPassword(password);
        if (passwordHash == null) {
            return "Error processing password";
        }
        
        // Authenticate user
        User user = userDAO.authenticateUser(email, passwordHash);
        
        if (user != null) {
            this.currentUser = user;
            return "SUCCESS";
        } else {
            return "Invalid email or password";
        }
    }
    
    /**
     * Logout current user
     */
    public void logout() {
        this.currentUser = null;
    }
    
    /**
     * Get current logged-in user
     * @return Current user or null
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Check if user is logged in
     * @return true if user is logged in
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
}
