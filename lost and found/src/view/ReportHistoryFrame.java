package view;

import controller.AuthController;
import controller.ItemController;
import model.LostItem;
import model.FoundItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Frame for viewing user's report history
 */
public class ReportHistoryFrame extends JFrame {

    private AuthController authController;
    private ItemController itemController;
    private JTable itemsTable;
    private DefaultTableModel tableModel;
    private JButton backButton;
    private JButton refreshButton;
    private JButton deleteButton;
    private List<LostItem> currentLostItems;
    private List<FoundItem> currentFoundItems;

    public ReportHistoryFrame(AuthController authController) {
        this.authController = authController;
        this.itemController = new ItemController();
        initializeUI();
        loadReportHistory();
    }

    private void initializeUI() {
        setTitle("My Report History");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 650);
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

        JLabel titleLabel = new JLabel("My Report History");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Manage your reported items and delete them once resolved");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
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
        String[] columnNames = { "ID", "Type", "Item Name", "Description", "Contact", "Date" };
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Create table
        itemsTable = new JTable(tableModel);
        itemsTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        itemsTable.setRowHeight(60);

        // Custom Header Styling
        JTableHeader header = itemsTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(header.getWidth(), 50));

        // Apply custom renderer to ensure colors are visible on all Look and Feels
        header.setDefaultRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {

                JLabel label = new JLabel(value.toString());
                label.setOpaque(true);
                label.setBackground(new Color(156, 39, 176)); // Purple
                label.setForeground(Color.WHITE);
                label.setFont(new Font("Segoe UI", Font.BOLD, 13));
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 1, Color.GRAY),
                        BorderFactory.createEmptyBorder(10, 5, 10, 5)));
                return label;
            }
        });

        itemsTable.setSelectionBackground(new Color(240, 220, 255));
        itemsTable.setSelectionForeground(Color.BLACK);
        itemsTable.setGridColor(new Color(230, 230, 230));

        // Set column widths and hide ID column
        itemsTable.getColumnModel().getColumn(0).setMinWidth(0);
        itemsTable.getColumnModel().getColumn(0).setMaxWidth(0);
        itemsTable.getColumnModel().getColumn(0).setPreferredWidth(0);

        itemsTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        itemsTable.getColumnModel().getColumn(2).setPreferredWidth(150);
        itemsTable.getColumnModel().getColumn(3).setPreferredWidth(300);
        itemsTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        itemsTable.getColumnModel().getColumn(5).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(itemsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panel.setBackground(new Color(245, 245, 250));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        refreshButton = new JButton("Refresh");
        styleButton(refreshButton, new Color(156, 39, 176), Color.WHITE);
        refreshButton.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        refreshButton.addActionListener(e -> loadReportHistory());

        deleteButton = new JButton("Delete Item");
        styleButton(deleteButton, new Color(230, 57, 70), Color.WHITE);
        deleteButton.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        deleteButton.addActionListener(e -> deleteSelectedItem());

        backButton = new JButton("Back to Home");
        styleButton(backButton, Color.WHITE, new Color(67, 97, 238));
        backButton.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        backButton.setBorder(BorderFactory.createLineBorder(new Color(67, 97, 238), 1));
        backButton.addActionListener(e -> backToHome());

        panel.add(refreshButton);
        panel.add(deleteButton);
        panel.add(backButton);

        return panel;
    }

    private void styleButton(JButton button, Color bgColor, Color fgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setPreferredSize(new Dimension(150, 45));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setBorder(BorderFactory.createLineBorder(bgColor.darker(), 1));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Remove modern Windows button styling that hides background
        button.setRolloverEnabled(false);
    }

    private void deleteSelectedItem() {
        int selectedRow = itemsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.", "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int itemId = (int) tableModel.getValueAt(selectedRow, 0);
        String type = (String) tableModel.getValueAt(selectedRow, 1);
        String itemName = (String) tableModel.getValueAt(selectedRow, 2);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete the " + type.toLowerCase() + " item: " + itemName + "?\n" +
                        "This action cannot be undone.",
                "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success;
            if ("LOST".equals(type)) {
                success = itemController.deleteLostItem(itemId);
            } else {
                success = itemController.deleteFoundItem(itemId);
            }

            if (success) {
                JOptionPane.showMessageDialog(this, "Item deleted successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                loadReportHistory();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete item. Please try again.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loadReportHistory() {
        // Clear existing rows
        tableModel.setRowCount(0);

        int userId = authController.getCurrentUser().getUserId();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");

        // Get user's lost items
        currentLostItems = itemController.getUserLostItems(userId);
        for (LostItem item : currentLostItems) {
            Object[] row = {
                    item.getItemId(),
                    "LOST",
                    item.getItemName(),
                    truncateText(item.getDescription(), 100),
                    item.getContactDetails(),
                    dateFormat.format(item.getReportedDate())
            };
            tableModel.addRow(row);
        }

        // Get user's found items
        currentFoundItems = itemController.getUserFoundItems(userId);
        for (FoundItem item : currentFoundItems) {
            Object[] row = {
                    item.getItemId(),
                    "FOUND",
                    item.getItemName(),
                    truncateText(item.getDescription(), 100),
                    item.getContactDetails(),
                    dateFormat.format(item.getReportedDate())
            };
            tableModel.addRow(row);
        }

        if (currentLostItems.isEmpty() && currentFoundItems.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "You haven't reported any items yet",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private String truncateText(String text, int maxLength) {
        if (text == null)
            return "";
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength) + "...";
    }

    private void backToHome() {
        new HomeFrame(authController).setVisible(true);
        dispose();
    }
}
