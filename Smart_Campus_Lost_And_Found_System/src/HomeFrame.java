import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

        public HomeFrame() {
                setTitle("Lost & Found Home");
                setSize(500, 600);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setLayout(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column

                // --- SECTION 1: REPORT LOST ---
                JPanel p1 = new JPanel(new BorderLayout());
                p1.setBorder(BorderFactory.createTitledBorder("Lost Something?"));
                JButton btnLost = new JButton("Report Lost Item");
                btnLost.setBackground(new Color(220, 53, 69)); // Red
                btnLost.setForeground(Color.WHITE);
                btnLost.addActionListener(e -> new ReportFrame(true).setVisible(true));
                p1.add(new JLabel("Report an item you lost on campus."), BorderLayout.CENTER);
                p1.add(btnLost, BorderLayout.SOUTH);
                add(p1);

                // --- SECTION 2: REPORT FOUND ---
                JPanel p2 = new JPanel(new BorderLayout());
                p2.setBorder(BorderFactory.createTitledBorder("Found Something?"));
                JButton btnFound = new JButton("Report Found Item");
                btnFound.setBackground(new Color(40, 167, 69)); // Green
                btnFound.setForeground(Color.WHITE);
                btnFound.addActionListener(e -> new ReportFrame(false).setVisible(true));
                p2.add(new JLabel("Report an item you found on campus."), BorderLayout.CENTER);
                p2.add(btnFound, BorderLayout.SOUTH);
                add(p2);

                // --- SECTION 3: BROWSE ---
                JPanel p3 = new JPanel(new BorderLayout());
                p3.setBorder(BorderFactory.createTitledBorder("Search"));
                JButton btnBrowse = new JButton("Browse All Items");
                btnBrowse.setBackground(new Color(0, 123, 255)); // Blue
                btnBrowse.setForeground(Color.WHITE);
                btnBrowse.addActionListener(e -> new ViewItemsFrame(0).setVisible(true));
                p3.add(new JLabel("View and search for all reported items."), BorderLayout.CENTER);
                p3.add(btnBrowse, BorderLayout.SOUTH);
                add(p3);
        }
}
