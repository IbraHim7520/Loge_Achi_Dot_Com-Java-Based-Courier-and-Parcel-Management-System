package UI.components;

import UI.MainUI;

import javax.swing.*;
import java.awt.*;

public class Navbar extends JPanel {

    private final Color NAVY = Color.decode("#0B1220");
    private final Color BLUE = Color.decode("#2563EB");

    public Navbar() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createMatteBorder(
                0, 0, 1, 0, Color.decode("#E2E8F0")
        ));

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(
                        0, 0, 1, 0, Color.decode("#E2E8F0")
                ),
                BorderFactory.createEmptyBorder(18, 45, 18, 45)
        ));

        JLabel logo = new JLabel("ParcelFlow");
        logo.setFont(new Font("SansSerif", Font.BOLD, 24));
        logo.setForeground(NAVY);

        JLabel dot = new JLabel(" ●");
        dot.setFont(new Font("SansSerif", Font.BOLD, 16));
        dot.setForeground(BLUE);

        JPanel logoPanel = new JPanel(new FlowLayout(
                FlowLayout.LEFT, 0, 0
        ));
        logoPanel.setOpaque(false);
        logoPanel.add(logo);
        logoPanel.add(dot);

        JPanel buttons = new JPanel(new FlowLayout(
                FlowLayout.RIGHT, 12, 0
        ));
        buttons.setOpaque(false);

        JButton home = createButton("Home", false);
        JButton login = createButton("Login", false);
        JButton register = createButton("Register", true);

        home.addActionListener(e -> MainUI.showLandingPage());
        login.addActionListener(e -> MainUI.showLoginPage());
        register.addActionListener(e -> MainUI.showRegistrationPage());

        buttons.add(home);
        buttons.add(login);
        buttons.add(register);

        add(logoPanel, BorderLayout.WEST);
        add(buttons, BorderLayout.EAST);
    }

    private JButton createButton(String text, boolean primary) {

        JButton button = new JButton(text);

        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorderPainted(false);

        if (primary) {
            button.setForeground(Color.WHITE);
            button.setBackground(BLUE);
            button.setBorder(BorderFactory.createEmptyBorder(
                    10, 20, 10, 20
            ));
        } else {
            button.setForeground(Color.decode("#475569"));
            button.setBackground(Color.WHITE);
            button.setBorder(BorderFactory.createEmptyBorder(
                    10, 15, 10, 15
            ));
        }

        button.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (!primary) {
                    button.setForeground(BLUE);
                    button.setBackground(Color.decode("#EFF6FF"));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (!primary) {
                    button.setForeground(Color.decode("#475569"));
                    button.setBackground(Color.WHITE);
                }
            }
        });

        return button;
    }
}