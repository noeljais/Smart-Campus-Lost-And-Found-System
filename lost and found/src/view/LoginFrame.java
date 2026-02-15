package view;

import controller.AuthController;

import javax.swing.*;
import java.awt.*;

/**
 * Login frame for user authentication
 */
public class LoginFrame extends JFrame {

    private AuthController authController;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;

    public LoginFrame(AuthController authController) {
        this.authController = authController;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Smart Campus Lost and Found - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 500);
        setLocationRelativeTo(null);
        setResizable(true);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header panel
        JPanel headerPanel = createHeaderPanel();

        // Form panel
        JPanel formPanel = createFormPanel();

        // Button panel
        JPanel buttonPanel = createButtonPanel();

        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JLabel titleLabel = new JLabel("Lost & Found System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Campus Community Portal");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(102, 102, 102));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(subtitleLabel);

        return panel;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0; // Responsive layout

        // Email label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel emailLabel = new JLabel("Email Address");
        emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        emailLabel.setForeground(new Color(51, 51, 51));
        panel.add(emailLabel, gbc);

        // Email field
        gbc.gridy = 1;
        emailField = new JTextField(20);
        emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailField.setPreferredSize(new Dimension(300, 35));
        emailField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        panel.add(emailField, gbc);

        // Password label
        gbc.gridy = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        passwordLabel.setForeground(new Color(51, 51, 51));
        panel.add(passwordLabel, gbc);

        // Password field
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 5);
        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(300, 35));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        panel.add(passwordField, gbc);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Login button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        loginButton.setPreferredSize(new Dimension(300, 40));
        loginButton.setBackground(new Color(67, 97, 238));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> handleLogin());
        panel.add(loginButton, gbc);

        // Separator
        gbc.gridy = 1;
        gbc.insets = new Insets(15, 5, 10, 5);
        JLabel orLabel = new JLabel("Don't have an account?");
        orLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        orLabel.setForeground(new Color(102, 102, 102));
        orLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(orLabel, gbc);

        // Signup button
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        signupButton = new JButton("Create Account");
        signupButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        signupButton.setPreferredSize(new Dimension(300, 35));
        signupButton.setBackground(Color.WHITE);
        signupButton.setForeground(new Color(67, 97, 238));
        signupButton.setFocusPainted(false);
        signupButton.setBorder(BorderFactory.createLineBorder(new Color(67, 97, 238), 1));
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signupButton.addActionListener(e -> openSignupFrame());
        panel.add(signupButton, gbc);

        return panel;
    }

    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all fields",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String result = authController.login(email, password);

        if ("SUCCESS".equals(result)) {
            JOptionPane.showMessageDialog(this,
                    "Login successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            // Open home frame
            new HomeFrame(authController).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    result,
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openSignupFrame() {
        new SignupFrame(authController).setVisible(true);
        dispose();
    }
}
