import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;

public class ViewItemsFrame extends JFrame {
    private static final Font MAIN_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 14);

    public ViewItemsFrame(int startIndex) {
        setTitle("Browse Items");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabs.setBackground(Color.WHITE);
        tabs.addTab("Lost Items", createPanel(
                "SELECT item_name, description, contact_details, reported_date, status, image_data FROM lost_items ORDER BY reported_date DESC",
                false));
        tabs.addTab("Found Items", createPanel(
                "SELECT item_name, description, contact_details, reported_date, status, image_data FROM found_items ORDER BY reported_date DESC",
                false));
        tabs.addTab("My History", createPanel(
                "SELECT 'Lost' as type, item_id, item_name, description, contact_details, reported_date, status, image_data FROM lost_items WHERE user_id="
                        + LoginSignupFrame.loggedInUserId
                        + " UNION ALL SELECT 'Found', item_id, item_name, description, contact_details, reported_date, status, image_data FROM found_items WHERE user_id="
                        + LoginSignupFrame.loggedInUserId + " ORDER BY reported_date DESC",
                true));

        tabs.setSelectedIndex(startIndex);
        add(tabs);
    }

    private JPanel createPanel(String query, boolean isHistory) {
        JPanel container = new JPanel(new BorderLayout());
        container.setBackground(Color.WHITE);
        container.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] columns = isHistory
                ? new String[] { "Type", "Name", "Description", "Contact", "Date", "Status", "Action", "Delete" }
                : new String[] { "Name", "Description", "Contact", "Date", "Status", "Action" };

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };

        if (isHistory) {
            model.addColumn("ID"); // index 8
            model.addColumn("IMG"); // index 9
        } else {
            model.addColumn("IMG"); // index 6
        }

        try {
            ResultSet rs = DB.get().createStatement().executeQuery(query);
            while (rs.next()) {
                if (isHistory) {
                    model.addRow(new Object[] {
                            rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5),
                            rs.getTimestamp(6), rs.getString(7), "View Image", "Delete",
                            rs.getInt(2), rs.getBytes(8)
                    });
                } else {
                    model.addRow(new Object[] {
                            rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4),
                            rs.getString(5), "View Image",
                            rs.getBytes(6)
                    });
                }
            }
        } catch (Exception e) {
        }

        JTable table = new JTable(model);
        table.setRowHeight(35);
        table.setFont(MAIN_FONT);
        table.getTableHeader().setFont(HEADER_FONT);
        table.getTableHeader().setBackground(new Color(245, 245, 247));
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionBackground(new Color(230, 240, 255));
        table.setGridColor(new Color(240, 240, 240));

        // Hide technical columns
        int imgDataCol = isHistory ? 9 : 6;
        int idCol = isHistory ? 8 : -1;
        table.getColumnModel().getColumn(imgDataCol).setMinWidth(0);
        table.getColumnModel().getColumn(imgDataCol).setMaxWidth(0);
        if (isHistory) {
            table.getColumnModel().getColumn(idCol).setMinWidth(0);
            table.getColumnModel().getColumn(idCol).setMaxWidth(0);
        }

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                if (row == -1)
                    return;

                int viewImgCol = isHistory ? 6 : 5;
                int delCol = isHistory ? 7 : -1;

                if (col == viewImgCol) {
                    byte[] data = (byte[]) model.getValueAt(row, imgDataCol);
                    if (data != null)
                        showImage(data);
                    else
                        JOptionPane.showMessageDialog(null, "No image available");
                } else if (isHistory && col == delCol) {
                    deleteItem((String) model.getValueAt(row, 0), (int) model.getValueAt(row, idCol));
                }
            }
        });

        container.add(new JScrollPane(table), BorderLayout.CENTER);
        return container;
    }

    private void showImage(byte[] data) {
        try {
            Image img = ImageIO.read(new ByteArrayInputStream(data));
            JFrame f = new JFrame("Item Image");
            JLabel imgLabel = new JLabel(new ImageIcon(img.getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
            f.add(new JScrollPane(imgLabel));
            f.setSize(600, 600);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error showing image");
        }
    }

    private void deleteItem(String type, int id) {
        if (JOptionPane.showConfirmDialog(null, "Delete this report?", "Confirm", 0) == 0) {
            try {
                String table = type.equalsIgnoreCase("Lost") ? "lost_items" : "found_items";
                DB.get().createStatement().executeUpdate("DELETE FROM " + table + " WHERE item_id=" + id);
                dispose();
                new ViewItemsFrame(2).setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error deleting item");
            }
        }
    }
}
