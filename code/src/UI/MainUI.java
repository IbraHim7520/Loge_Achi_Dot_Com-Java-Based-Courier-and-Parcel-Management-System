package UI;

import javax.swing.*;
import java.awt.*;

public class MainUI {

    private static JFrame frame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}

            frame = new JFrame("ParcelFlow - Parcel Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 750);
            frame.setMinimumSize(new Dimension(1000, 650));
            frame.setLocationRelativeTo(null);

            showLandingPage();

            frame.setVisible(true);
        });
    }

    public static void showLandingPage() {
        changePage(new LandingPage());
    }

    public static void showLoginPage() {
        changePage(new LoginPage());
    }

    public static void showRegistrationPage() {
        changePage(new RegistrationPage());
    }

    private static void changePage(JPanel panel) {
        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
    }

    public static JFrame getFrame() {
        return frame;
    }
}