package view;

import controller.AuthController;
import controller.ItemController;
import util.ImageUtil;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

/**
 * Frame for reporting found items
 */
public class ReportFoundItemFrame extends JFrame {

    private AuthController authController;
    private ItemController itemController;

    private JTextField itemNameField;
    private JTextArea descriptionArea;
    private JTextField contactField;
    private JLabel imagePreviewLabel;
    private JButton selectImageButton;
    private JButton submitButton;
    private JButton backButton;

    private File selectedImageFile;

    public ReportFoundItemFrame(AuthController authController) {
        this.authController = authController;
        this.itemController = new ItemController();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Report Found Item");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 700);
        setLocationRelativeTo(null);
        setResizable(true); // Make responsive

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel headerPanel = createHeaderPanel();

        // Form panel
        JPanel formPanel = createFormPanel();

        // Button panel
        JPanel buttonPanel = createButtonPanel();

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));

        JLabel titleLabel = new JLabel("Report Found Item");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Help reunite items with their owners");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
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
                BorderFactory.createEmptyBorder(25, 25, 25, 25)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridwidth = 2;
        gbc.weightx = 1.0; // Allow components to stretch horizontally

        int row = 0;

        // Item Name
        addLabel(panel, gbc, row++, "Item Name *");
        gbc.gridy = row++;
        itemNameField = new JTextField(25);
        styleTextField(itemNameField);
        panel.add(itemNameField, gbc);

        // Description
        gbc.gridy = row++;
        gbc.insets = new Insets(15, 5, 5, 5);
        addLabel(panel, gbc, row - 1, "Description *");

        gbc.gridy = row++;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0; // Explicitly set gridx
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0; // Revert to 0 to ensure visibility based on PreferredSize

        descriptionArea = new JTextArea(5, 20); // Rows, Columns
        descriptionArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBackground(Color.WHITE);
        descriptionArea.setForeground(Color.BLACK);

        JScrollPane descScrollPane = new JScrollPane(descriptionArea);
        descScrollPane.setPreferredSize(new Dimension(400, 100));
        descScrollPane.setMinimumSize(new Dimension(300, 80));
        descScrollPane.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 1));
        panel.add(descScrollPane, gbc);

        // Contact Details
        gbc.gridy = row++;
        gbc.insets = new Insets(15, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        // Reset weighty for subsequent labels
        addLabel(panel, gbc, row - 1, "Contact Details *");

        gbc.gridy = row++;
        gbc.insets = new Insets(5, 5, 5, 5);
        contactField = new JTextField(25);
        styleTextField(contactField);
        panel.add(contactField, gbc);

        // Image Upload
        gbc.gridy = row++;
        gbc.insets = new Insets(15, 5, 5, 5);
        addLabel(panel, gbc, row - 1, "Upload Image (Optional)");

        gbc.gridy = row++;
        gbc.insets = new Insets(5, 5, 5, 5);
        selectImageButton = new JButton("Choose Image");
        selectImageButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        selectImageButton.setPreferredSize(new Dimension(150, 35));
        selectImageButton.setBackground(new Color(52, 168, 83));
        selectImageButton.setForeground(Color.WHITE);
        selectImageButton.setFocusPainted(false);
        selectImageButton.setBorderPainted(false);
        selectImageButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        selectImageButton.addActionListener(e -> selectImage());
        panel.add(selectImageButton, gbc);

        // Image Preview
        gbc.gridy = row++;
        imagePreviewLabel = new JLabel();
        imagePreviewLabel.setPreferredSize(new Dimension(200, 200));
        imagePreviewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagePreviewLabel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        imagePreviewLabel.setIcon(ImageUtil.createPlaceholder(200, 150));
        panel.add(imagePreviewLabel, gbc);

        return panel;
    }

    private void addLabel(JPanel panel, GridBagConstraints gbc, int row, String text) {
        gbc.gridx = 0; // Explicitly set column 0
        gbc.gridy = row;
        gbc.weightx = 1.0; // Ensure label takes full width
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(new Color(51, 51, 51));
        panel.add(label, gbc);
    }

    private void styleTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setPreferredSize(new Dimension(400, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        submitButton = new JButton("Submit Report");
        submitButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        submitButton.setPreferredSize(new Dimension(180, 40));
        submitButton.setBackground(new Color(251, 188, 5));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.addActionListener(e -> handleSubmit());

        backButton = new JButton("Back to Home");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        backButton.setPreferredSize(new Dimension(150, 40));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(67, 97, 238));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(67, 97, 238), 1));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> backToHome());

        panel.add(submitButton);
        panel.add(backButton);

        return panel;
    }

    private void selectImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Image");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image Files", "jpg", "jpeg", "png", "gif", "bmp");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedImageFile = fileChooser.getSelectedFile();

            if (ImageUtil.isValidImageFile(selectedImageFile)) {
                // Show preview
                byte[] imageData = ImageUtil.fileToByteArray(selectedImageFile);
                ImageIcon icon = ImageUtil.byteArrayToImageIcon(imageData);
                ImageIcon preview = ImageUtil.resizeImage(icon, 200, 150);
                imagePreviewLabel.setIcon(preview);

                JOptionPane.showMessageDialog(this,
                        "Image selected successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please select a valid image file",
                        "Invalid File",
                        JOptionPane.ERROR_MESSAGE);
                selectedImageFile = null;
            }
        }
    }

    private void handleSubmit() {
        String itemName = itemNameField.getText().trim();
        String description = descriptionArea.getText().trim();
        String contact = contactField.getText().trim();

        if (itemName.isEmpty() || description.isEmpty() || contact.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in all required fields",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        byte[] imageData = null;
        if (selectedImageFile != null) {
            imageData = ImageUtil.fileToByteArray(selectedImageFile);
        }

        int userId = authController.getCurrentUser().getUserId();
        String result = itemController.reportFoundItem(userId, itemName, description, imageData, contact);

        if ("SUCCESS".equals(result)) {
            JOptionPane.showMessageDialog(this,
                    "Found item reported successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            backToHome();
        } else {
            JOptionPane.showMessageDialog(this,
                    result,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void backToHome() {
        new HomeFrame(authController).setVisible(true);
        dispose();
    }
}
