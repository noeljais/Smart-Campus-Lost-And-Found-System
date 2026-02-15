package view;

import controller.AuthController;
import controller.ItemController;
import model.LostItem;
import util.ImageUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Frame for viewing all lost items
 */
public class ViewLostItemsFrame extends JFrame {

    private AuthController authController;
    private ItemController itemController;
    private JTable itemsTable;
    private DefaultTableModel tableModel;
    private JButton backButton;
    private JButton refreshButton;

    public ViewLostItemsFrame(AuthController authController) {
        this.authController = authController;
        this.itemController = new ItemController();
        initializeUI();
        loadLostItems();
    }

    private void initializeUI() {
        setTitle("View Lost Items");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(245, 245, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        JPanel headerPanel = createHeaderPanel();

        // Table panel
        JPanel tablePanel = createTablePanel();

        // Button panel
        JPanel buttonPanel = createButtonPanel();

        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));

        JLabel titleLabel = new JLabel("Lost Items");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Browse all reported lost items");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(new Color(102, 102, 102));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(subtitleLabel);

        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Create table model
        String[] columnNames = { "Item Name", "Description", "Contact", "Reported By", "Date" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Create table
        itemsTable = new JTable(tableModel);
        itemsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        itemsTable.setRowHeight(40);

        // Custom Header Styling
        JTableHeader header = itemsTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(header.getWidth(), 50));

        // Apply custom renderer to ensure colors are visible on all Look and Feels
        header.setDefaultRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel label = new JLabel(value.toString());
                label.setOpaque(true);
                label.setBackground(new Color(25, 25, 112)); // Midnight Blue
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Segoe UI", Font.BOLD, 14));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY),
                        BorderFactory.createEmptyBorder(10, 5, 10, 5)));
                return label;
            }
        });

        itemsTable.setSelectionBackground(new Color(220, 230, 255));
        itemsTable.setSelectionForeground(Color.BLACK);
        itemsTable.setGridColor(new Color(230, 230, 230));
        itemsTable.setFillsViewportHeight(true);

        // Set column widths
        itemsTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        itemsTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        itemsTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        itemsTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        itemsTable.getColumnModel().getColumn(4).setPreferredWidth(120);

        // Add click listener to show details
        itemsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int row = itemsTable.getSelectedRow();
                    if (row >= 0) {
                        showItemDetails(row);
                    }
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(itemsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        refreshButton.setPreferredSize(new Dimension(120, 35));
        refreshButton.setBackground(new Color(52, 168, 83));
        refreshButton.setForeground(Color.WHITE);
        refreshButton.setFocusPainted(false);
        refreshButton.setContentAreaFilled(true);
        refreshButton.setOpaque(true);
        refreshButton.setBorderPainted(false);
        refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        refreshButton.addActionListener(e -> loadLostItems());

        backButton = new JButton("Back to Home");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        backButton.setPreferredSize(new Dimension(150, 35));
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(new Color(67, 97, 238));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(true);
        backButton.setOpaque(true);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(67, 97, 238), 1));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> backToHome());

        panel.add(refreshButton);
        panel.add(backButton);

        return panel;
    }

    private void loadLostItems() {
        // Clear existing rows
        tableModel.setRowCount(0);

        // Get lost items from database
        List<LostItem> items = itemController.getAllLostItems();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

        for (LostItem item : items) {
            Object[] row = {
                    item.getItemName(),
                    truncateText(item.getDescription(), 100),
                    item.getContactDetails(),
                    item.getReporterName(),
                    dateFormat.format(item.getReportedDate())
            };
            tableModel.addRow(row);
        }

        if (items.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No lost items found",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String truncateText(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength) + "...";
    }

    private void showItemDetails(int row) {
        List<LostItem> items = itemController.getAllLostItems();
        if (row < items.size()) {
            LostItem item = items.get(row);
            showDetailDialog(item);
        }
    }

    private void showDetailDialog(LostItem item) {
        JDialog dialog = new JDialog(this, "Lost Item Details", true);
        dialog.setSize(500, 600);
        dialog.setLocationRelativeTo(this);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Item name
        addDetailField(panel, "Item Name:", item.getItemName());

        // Description
        addDetailField(panel, "Description:", item.getDescription());

        // Contact
        addDetailField(panel, "Contact:", item.getContactDetails());

        // Reported by
        addDetailField(panel, "Reported By:", item.getReporterName() + " (" + item.getReporterEmail() + ")");

        // Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm a");
        addDetailField(panel, "Reported Date:", dateFormat.format(item.getReportedDate()));

        // Image
        if (item.getImageData() != null) {
            panel.add(Box.createRigidArea(new Dimension(0, 15)));
            JLabel imageLabel = new JLabel("Image:");
            imageLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
            imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(imageLabel);

            panel.add(Box.createRigidArea(new Dimension(0, 5)));
            ImageIcon icon = ImageUtil.byteArrayToImageIcon(item.getImageData());
            ImageIcon resized = ImageUtil.resizeImage(icon, 400, 300);
            JLabel imgLabel = new JLabel(resized);
            imgLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            imgLabel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
            panel.add(imgLabel);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        dialog.add(scrollPane);

        dialog.setVisible(true);
    }

    private void addDetailField(JPanel panel, String label, String value) {
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel labelComp = new JLabel(label);
        labelComp.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelComp.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(labelComp);

        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        JTextArea valueComp = new JTextArea(value);
        valueComp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        valueComp.setLineWrap(true);
        valueComp.setWrapStyleWord(true);
        valueComp.setEditable(false);
        valueComp.setBackground(Color.WHITE);
        valueComp.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(valueComp);
    }

    private void backToHome() {
        new HomeFrame(authController).setVisible(true);
        dispose();
    }
}
