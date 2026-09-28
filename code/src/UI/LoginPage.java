package UI;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JPanel {

    private final Color NAVY = Color.decode("#0B1220");
    private final Color BLUE = Color.decode("#2563EB");
    private final Color BACKGROUND = Color.decode("#F8FAFC");

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPage() {

        setLayout(new GridLayout(1, 2));
        setBackground(BACKGROUND);

        add(createLeftPanel());
        add(createLoginPanel());
    }

    private JPanel createLeftPanel() {

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(
                panel,
                BoxLayout.Y_AXIS
        ));

        panel.setBackground(NAVY);
        panel.setBorder(BorderFactory.createEmptyBorder(
                100, 70, 100, 70
        ));

        JLabel logo = new JLabel("ParcelFlow");

        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logo.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                28
        ));
        logo.setForeground(Color.decode("#22D3EE"));

        JLabel title = new JLabel(
                "<html><center>"
                        + "Manage every delivery.<br>"
                        + "From pickup to destination."
                        + "</center></html>"
        );

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                32
        ));
        title.setForeground(Color.WHITE);

        JLabel description = new JLabel(
                "<html><center>"
                        + "A complete parcel management system<br>"
                        + "for users, riders and administrators."
                        + "</center></html>"
        );

        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                14
        ));
        description.setForeground(
                Color.decode("#94A3B8")
        );

        panel.add(Box.createVerticalGlue());
        panel.add(logo);
        panel.add(Box.createVerticalStrut(30));
        panel.add(title);
        panel.add(Box.createVerticalStrut(20));
        panel.add(description);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createLoginPanel() {

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(
                panel,
                BoxLayout.Y_AXIS
        ));

        panel.setBackground(BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(
                80, 90, 80, 90
        ));

        JLabel title = new JLabel("Welcome Back");

        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                30
        ));
        title.setForeground(NAVY);

        JLabel subtitle = new JLabel(
                "Login to access your ParcelFlow account"
        );

        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                14
        ));
        subtitle.setForeground(
                Color.decode("#64748B")
        );

        emailField = createTextField(
                "Email"
        );

        passwordField = createPasswordField(
                "Password"
        );

        JButton loginButton = new JButton("Login");

        loginButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginButton.setMaximumSize(
                new Dimension(360, 48)
        );

        loginButton.setBackground(BLUE);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                15
        ));

        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        loginButton.addActionListener(
                e -> login()
        );

        JButton register = createLinkButton(
                "Don't have an account? Create one"
        );

        register.addActionListener(
                e -> MainUI.showRegistrationPage()
        );

        JButton back = createLinkButton(
                "← Back to Home"
        );

        back.addActionListener(
                e -> MainUI.showLandingPage()
        );

        panel.add(Box.createVerticalGlue());
        panel.add(title);
        panel.add(Box.createVerticalStrut(8));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(35));

        panel.add(createLabel("Email"));
        panel.add(emailField);

        panel.add(Box.createVerticalStrut(15));

        panel.add(createLabel("Password"));
        panel.add(passwordField);

        panel.add(Box.createVerticalStrut(25));
        panel.add(loginButton);
        panel.add(Box.createVerticalStrut(12));
        panel.add(register);
        panel.add(Box.createVerticalStrut(5));
        panel.add(back);

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private void login() {

        String email = emailField.getText().trim();

        String password =
                new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    MainUI.getFrame(),
                    "Please enter email and password.",
                    "Login",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        JOptionPane.showMessageDialog(
                MainUI.getFrame(),
                "Backend login will be connected next.",
                "ParcelFlow",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                13
        ));

        label.setForeground(
                Color.decode("#334155")
        );

        return label;
    }

    private JTextField createTextField(String prompt) {

        JTextField field = new JTextField();

        field.setMaximumSize(
                new Dimension(360, 45)
        );

        field.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                14
        ));

        field.setToolTipText(prompt);

        return field;
    }

    private JPasswordField createPasswordField(
            String prompt
    ) {

        JPasswordField field =
                new JPasswordField();

        field.setMaximumSize(
                new Dimension(360, 45)
        );

        field.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                14
        ));

        field.setToolTipText(prompt);

        return field;
    }

    private JButton createLinkButton(String text) {

        JButton button = new JButton(text);

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                13
        ));

        button.setForeground(BLUE);
        button.setBackground(
                Color.decode("#F8FAFC")
        );

        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }
}