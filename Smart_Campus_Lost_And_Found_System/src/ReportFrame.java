import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.nio.file.Files;

public class ReportFrame extends JFrame {
    private byte[] imageData = null;

    public ReportFrame(boolean isLost) {
        // Basic window setup
        setTitle("Report " + (isLost ? "Lost" : "Found") + " Item");
        setSize(450, 550);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        // GridBagLayout is best for keeping everything lined up
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 10, 10, 10);
        g.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel title = new JLabel("Report " + (isLost ? "Lost" : "Found") + " Item", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        panel.add(title, g);

        // Item Name
        g.gridwidth = 1;
        g.gridx = 0;
        g.gridy = 1;
        panel.add(new JLabel("Item Name:"), g);
        JTextField nameField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 1;
        panel.add(nameField, g);

        // Description
        g.gridx = 0;
        g.gridy = 2;
        panel.add(new JLabel("Description:"), g);
        JTextArea descArea = new JTextArea(4, 15);
        descArea.setLineWrap(true);
        descArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        g.gridx = 1;
        g.gridy = 2;
        panel.add(new JScrollPane(descArea), g);

        // Contact Info
        g.gridx = 0;
        g.gridy = 3;
        panel.add(new JLabel("Contact Info:"), g);
        JTextField contactField = new JTextField(15);
        g.gridx = 1;
        g.gridy = 3;
        panel.add(contactField, g);

        // Image Selection
        g.gridx = 0;
        g.gridy = 4;
        panel.add(new JLabel("Image:"), g);
        JButton uploadBtn = new JButton("Select Image");
        g.gridx = 1;
        g.gridy = 4;
        panel.add(uploadBtn, g);

        // Submit Button
        JButton submitBtn = new JButton("Submit Report");
        submitBtn.setBackground(isLost ? new Color(220, 53, 69) : new Color(40, 167, 69));
        submitBtn.setForeground(Color.WHITE);
        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 2;
        panel.add(submitBtn, g);

        // Upload Button Logic
        uploadBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    imageData = Files.readAllBytes(chooser.getSelectedFile().toPath());
                    uploadBtn.setText("Image Selected!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Submit Button Logic
        submitBtn.addActionListener(e -> {
            try {
                String table = isLost ? "lost_items" : "found_items";
                String sql = "INSERT INTO " + table
                        + " (user_id, item_name, description, contact_details, image_data) VALUES(?,?,?,?,?)";
                PreparedStatement ps = DB.get().prepareStatement(sql);
                ps.setInt(1, LoginSignupFrame.loggedInUserId);
                ps.setString(2, nameField.getText());
                ps.setString(3, descArea.getText());
                ps.setString(4, contactField.getText());
                ps.setBytes(5, imageData);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Report Submitted!");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        add(new JScrollPane(panel));
    }
}
