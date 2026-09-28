package UI;

import javax.swing.*;
import java.awt.*;

public class RegistrationPage extends JPanel {

    private final Color NAVY = Color.decode("#0B1220");
    private final Color BLUE = Color.decode("#2563EB");
    private final Color BACKGROUND = Color.decode("#F8FAFC");

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmField;

    public RegistrationPage() {

        setLayout(new GridLayout(1, 2));
        setBackground(BACKGROUND);

        add(createLeftPanel());
        add(createRegistrationPanel());
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

        logo.setForeground(
                Color.decode("#22D3EE")
        );

        JLabel title = new JLabel(
                "<html><center>"
                        + "Start your delivery<br>"
                        + "journey today."
                        + "</center></html>"
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        title.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                32
        ));

        title.setForeground(Color.WHITE);

        JLabel description = new JLabel(
                "<html><center>"
                        + "Create an account and manage your parcels<br>"
                        + "with a simple and modern system."
                        + "</center></html>"
        );

        description.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

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

    private JPanel createRegistrationPanel() {

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(
                panel,
                BoxLayout.Y_AXIS
        ));

        panel.setBackground(BACKGROUND);

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        55, 90, 55, 90
                )
        );

        JLabel title = new JLabel(
                "Create Account"
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        title.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                30
        ));

        title.setForeground(NAVY);

        JLabel subtitle = new JLabel(
                "Register to start using ParcelFlow"
        );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        subtitle.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                14
        ));

        subtitle.setForeground(
                Color.decode("#64748B")
        );

        nameField = createTextField(
                "Full Name"
        );

        emailField = createTextField(
                "Email"
        );

        passwordField = createPasswordField(
                "Password"
        );

        confirmField = createPasswordField(
                "Confirm Password"
        );

        JButton registerButton =
                new JButton("Create Account");

        registerButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        registerButton.setMaximumSize(
                new Dimension(360, 48)
        );

        registerButton.setBackground(BLUE);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                15
        ));

        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);

        registerButton.addActionListener(
                e -> register()
        );

        JButton login = createLinkButton(
                "Already have an account? Login"
        );

        login.addActionListener(
                e -> MainUI.showLoginPage()
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
        panel.add(Box.createVerticalStrut(25));

        panel.add(createLabel("Full Name"));
        panel.add(nameField);

        panel.add(Box.createVerticalStrut(10));

        panel.add(createLabel("Email"));
        panel.add(emailField);

        panel.add(Box.createVerticalStrut(10));

        panel.add(createLabel("Password"));
        panel.add(passwordField);

        panel.add(Box.createVerticalStrut(10));

        panel.add(createLabel("Confirm Password"));
        panel.add(confirmField);

        panel.add(Box.createVerticalStrut(20));
        panel.add(registerButton);
        panel.add(Box.createVerticalStrut(8));
        panel.add(login);
        panel.add(Box.createVerticalStrut(3));
        panel.add(back);

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private void register() {

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        String password =
                new String(passwordField.getPassword());

        String confirm =
                new String(confirmField.getPassword());

        if (name.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || confirm.isEmpty()) {

            JOptionPane.showMessageDialog(
                    MainUI.getFrame(),
                    "Please fill all fields.",
                    "Registration",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!password.equals(confirm)) {

            JOptionPane.showMessageDialog(
                    MainUI.getFrame(),
                    "Passwords do not match.",
                    "Registration",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        JOptionPane.showMessageDialog(
                MainUI.getFrame(),
                "Backend registration will be connected next.",
                "ParcelFlow",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

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

    private JTextField createTextField(
            String prompt
    ) {

        JTextField field = new JTextField();

        field.setMaximumSize(
                new Dimension(360, 42)
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
                new Dimension(360, 42)
        );

        field.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                14
        ));

        field.setToolTipText(prompt);

        return field;
    }

    private JButton createLinkButton(
            String text
    ) {

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
        button.setBackground(BACKGROUND);

        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }
}