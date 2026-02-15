import controller.AuthController;
import view.LoginFrame;

import javax.swing.*;

/**
 * Main class - Entry point for Smart Campus Lost and Found System
 */
public class Main {

    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Failed to set look and feel: " + e.getMessage());
        }

        // Run the application on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create auth controller
            AuthController authController = new AuthController();

            // Show login frame
            LoginFrame loginFrame = new LoginFrame(authController);
            loginFrame.setVisible(true);
        });
    }
}
