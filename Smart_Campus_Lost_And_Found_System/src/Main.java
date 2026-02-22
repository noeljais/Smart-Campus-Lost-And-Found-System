import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] a) {
        try {
            // Use Nimbus or System Look and Feel
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            // Global UI overrides for a more modern look
            UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 13));
            UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));
            UIManager.put("TabbedPane.font", new Font("Segoe UI", Font.BOLD, 13));

        } catch (Exception e) {
        }
        SwingUtilities.invokeLater(() -> new LoginSignupFrame().setVisible(true));
    }
}
