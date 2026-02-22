import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.security.MessageDigest;

public class LoginSignupFrame extends JFrame {
    // These store the user info once they log in
    static int loggedInUserId = -1;
    static String loggedInName = "";

    public LoginSignupFrame() {
        setTitle("Lost & Found - Login/Signup");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // A Tabbed Pane lets us switch between Login and Signup
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Login", createLoginPanel());
        tabs.addTab("Signup", createSignupPanel());

        add(tabs);
    }

    // Simplest possible way to create the Login screen
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10); // Spacing around elements
        g.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Login", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2; // Span across 2 columns
        panel.add(title, g);

        // Email Label and Field
        g.gridwidth = 1; // Back to 1 column
        g.gridx = 0;
        g.gridy = 1;
        panel.add(new JLabel("Email:"), g);

        JTextField emailField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 1;
        panel.add(emailField, g);

        // Password Label and Field
        g.gridx = 0;
        g.gridy = 2;
        panel.add(new JLabel("Password:"), g);

        JPasswordField passField = new JPasswordField(15);
        g.gridx = 1;
        g.gridy = 2;
        panel.add(passField, g);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0, 123, 255)); // Blue color
        loginBtn.setForeground(Color.WHITE); // White text
        g.gridx = 0;
        g.gridy = 3;
        g.gridwidth = 2;
        panel.add(loginBtn, g);

        // What happens when you click Login
        loginBtn.addActionListener(e -> {
            try {
                Connection conn = DB.get();
                PreparedStatement ps = conn
                        .prepareStatement("SELECT user_id, full_name FROM users WHERE email=? AND password_hash=?");
                ps.setString(1, emailField.getText());
                ps.setString(2, hashPassword(new String(passField.getPassword())));
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    loggedInUserId = rs.getInt(1);
                    loggedInName = rs.getString(2);
                    dispose(); // Close this window
                    new HomeFrame().setVisible(true); // Open Home
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Email or Password");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return panel;
    }

    // Simplest possible way to create the Signup screen
    private JPanel createSignupPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(8, 8, 8, 8);
        g.fill = GridBagConstraints.HORIZONTAL;

        JLabel title = new JLabel("Create Account", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        panel.add(title, g);

        // Name
        g.gridwidth = 1;
        g.gridx = 0;
        g.gridy = 1;
        panel.add(new JLabel("Full Name:"), g);
        JTextField nameField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 1;
        panel.add(nameField, g);

        // Email
        g.gridx = 0;
        g.gridy = 2;
        panel.add(new JLabel("Email:"), g);
        JTextField emailField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 2;
        panel.add(emailField, g);

        // Password
        g.gridx = 0;
        g.gridy = 3;
        panel.add(new JLabel("Password:"), g);
        JPasswordField passField = new JPasswordField(15);
        g.gridx = 1;
        g.gridy = 3;
        panel.add(passField, g);

        // Phone
        g.gridx = 0;
        g.gridy = 4;
        panel.add(new JLabel("Phone:"), g);
        JTextField phoneField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 4;
        panel.add(phoneField, g);

        // Signup Button
        JButton signupBtn = new JButton("Sign Up");
        signupBtn.setBackground(new Color(40, 167, 69)); // Green color
        signupBtn.setForeground(Color.WHITE);
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 2;
        panel.add(signupBtn, g);

        signupBtn.addActionListener(e -> {
            try {
                Connection conn = DB.get();
                PreparedStatement ps = conn
                        .prepareStatement("INSERT INTO users(full_name,email,password_hash,phone) VALUES(?,?,?,?)");
                ps.setString(1, nameField.getText());
                ps.setString(2, emailField.getText());
                ps.setString(3, hashPassword(new String(passField.getPassword())));
                ps.setString(4, phoneField.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Account Created! Now please Login.");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        return panel;
    }

    // This converts the password to a secure code
    private String hashPassword(String input) {
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash)
                hexString.append(String.format("%02x", b));
            return hexString.toString();
        } catch (Exception e) {
            return input;
        }
    }
}
