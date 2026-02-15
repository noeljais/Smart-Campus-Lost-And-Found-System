package view;

import controller.AuthController;
import model.User;

import javax.swing.*;
import java.awt.*;

/**
 * Home frame with main navigation
 */
public class HomeFrame extends JFrame {

    private AuthController authController;
    private User currentUser;

    public HomeFrame(AuthController authController) {
        this.authController = authController;
        this.currentUser = authController.getCurrentUser();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Smart Campus Lost and Found - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setResizable(true);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(245, 245, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Header panel
        JPanel headerPanel = createHeaderPanel();

        // Menu panel
        JPanel menuPanel = createMenuPanel();

        // Footer panel
        JPanel footerPanel = createFooterPanel();

        // Add panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JLabel titleLabel = new JLabel("Welcome, " + currentUser.getFullName() + "!");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("What would you like to do today?");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(102, 102, 102));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(subtitleLabel);

        return panel;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(245, 245, 250));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // Row 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(createMenuButton("📋 View Lost Items",
                new Color(67, 97, 238),
                e -> openViewLostItems()), gbc);

        gbc.gridx = 1;
        panel.add(createMenuButton("📦 View Found Items",
                new Color(52, 168, 83),
                e -> openViewFoundItems()), gbc);

        // Row 2
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(createMenuButton("❌ Report Lost Item",
                new Color(234, 67, 53),
                e -> openReportLostItem()), gbc);

        gbc.gridx = 1;
        panel.add(createMenuButton("✅ Report Found Item",
                new Color(251, 188, 5),
                e -> openReportFoundItem()), gbc);

        // Row 3
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(createMenuButton("📊 My Report History",
                new Color(156, 39, 176),
                e -> openReportHistory()), gbc);

        return panel;
    }

    private JButton createMenuButton(String text, Color bgColor, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setPreferredSize(new Dimension(250, 100));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(listener);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private JPanel createFooterPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        logoutButton.setPreferredSize(new Dimension(150, 35));
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setForeground(new Color(234, 67, 53));
        logoutButton.setFocusPainted(false);
        logoutButton.setBorder(BorderFactory.createLineBorder(new Color(234, 67, 53), 1));
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(e -> handleLogout());

        panel.add(logoutButton);

        return panel;
    }

    private void openViewLostItems() {
        new ViewLostItemsFrame(authController).setVisible(true);
        dispose();
    }

    private void openViewFoundItems() {
        new ViewFoundItemsFrame(authController).setVisible(true);
        dispose();
    }

    private void openReportLostItem() {
        new ReportLostItemFrame(authController).setVisible(true);
        dispose();
    }

    private void openReportFoundItem() {
        new ReportFoundItemFrame(authController).setVisible(true);
        dispose();
    }

    private void openReportHistory() {
        new ReportHistoryFrame(authController).setVisible(true);
        dispose();
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            authController.logout();
            new LoginFrame(authController).setVisible(true);
            dispose();
        }
    }
}
