package UI;

import UI.components.Navbar;

import javax.swing.*;
import java.awt.*;

public class LandingPage extends JPanel {

    private final Color NAVY = Color.decode("#0B1220");
    private final Color BLUE = Color.decode("#2563EB");
    private final Color CYAN = Color.decode("#22D3EE");
    private final Color BACKGROUND = Color.decode("#F8FAFC");

    public LandingPage() {

        setLayout(new BorderLayout());
        setBackground(BACKGROUND);

        add(new Navbar(), BorderLayout.NORTH);
        add(createMainContent(), BorderLayout.CENTER);
    }

    private JPanel createMainContent() {

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(BACKGROUND);
        main.setBorder(BorderFactory.createEmptyBorder(
                50, 60, 40, 60
        ));

        JLabel smallTitle = new JLabel(
                "SMART PARCEL MANAGEMENT"
        );

        smallTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        smallTitle.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                13
        ));
        smallTitle.setForeground(BLUE);

        JLabel title = new JLabel(
                "<html><center>Move Smart.<br>Deliver Faster.</center></html>"
        );

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                50
        ));
        title.setForeground(NAVY);

        JLabel description = new JLabel(
                "<html><center>"
                        + "A smarter way to manage parcels, riders and deliveries.<br>"
                        + "Send, track and manage your parcels from one powerful system."
                        + "</center></html>"
        );

        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                16
        ));
        description.setForeground(Color.decode("#64748B"));

        JPanel buttons = createActionButtons();

        JPanel features = createFeatures();

        main.add(smallTitle);
        main.add(Box.createVerticalStrut(15));
        main.add(title);
        main.add(Box.createVerticalStrut(15));
        main.add(description);
        main.add(Box.createVerticalStrut(25));
        main.add(buttons);
        main.add(Box.createVerticalStrut(35));
        main.add(features);

        return main;
    }

    private JPanel createActionButtons() {

        JPanel panel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 0)
        );

        panel.setOpaque(false);

        JButton getStarted = createPrimaryButton(
                "Get Started"
        );

        JButton login = createSecondaryButton(
                "Login"
        );

        getStarted.addActionListener(
                e -> MainUI.showRegistrationPage()
        );

        login.addActionListener(
                e -> MainUI.showLoginPage()
        );

        panel.add(getStarted);
        panel.add(login);

        return panel;
    }

    private JButton createPrimaryButton(String text) {

        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(150, 48));
        button.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                14
        ));

        button.setForeground(Color.WHITE);
        button.setBackground(BLUE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private JButton createSecondaryButton(String text) {

        JButton button = new JButton(text);

        button.setPreferredSize(new Dimension(150, 48));
        button.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                14
        ));

        button.setForeground(NAVY);
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.setBorder(BorderFactory.createLineBorder(
                Color.decode("#CBD5E1")
        ));

        return button;
    }

    private JPanel createFeatures() {

        JPanel panel = new JPanel(
                new GridLayout(1, 3, 20, 0)
        );

        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(750, 130));

        panel.add(createFeatureCard(
                "📦",
                "Easy Parcel",
                "Send and manage parcels easily."
        ));

        panel.add(createFeatureCard(
                "📍",
                "Track Delivery",
                "Track your parcel status anytime."
        ));

        panel.add(createFeatureCard(
                "🛡",
                "Secure System",
                "Role-based access keeps your data safe."
        ));

        return panel;
    }

    private JPanel createFeatureCard(
            String icon,
            String title,
            String description
    ) {

        JPanel card = new JPanel();

        card.setLayout(new BoxLayout(
                card,
                BoxLayout.Y_AXIS
        ));

        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                        Color.decode("#E2E8F0")
                ),
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        ));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                25
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                15
        ));
        titleLabel.setForeground(NAVY);

        JLabel descriptionLabel = new JLabel(
                "<html><center>" + description + "</center></html>"
        );

        descriptionLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        descriptionLabel.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                12
        ));

        descriptionLabel.setForeground(
                Color.decode("#64748B")
        );

        card.add(iconLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(descriptionLabel);

        return card;
    }
}