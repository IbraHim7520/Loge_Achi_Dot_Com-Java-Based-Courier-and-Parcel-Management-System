package UI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sidebar extends JPanel {

    private final Color NAVY = Color.decode("#0B1220");
    private final Color BLUE = Color.decode("#2563EB");
    private final Color WHITE = Color.WHITE;
    private final Color TEXT = Color.decode("#94A3B8");
    private final Color HOVER = Color.decode("#172554");

    private JPanel menuPanel;

    public Sidebar(String role) {

        setPreferredSize(new Dimension(245, 0));
        setBackground(NAVY);
        setLayout(new BorderLayout());

        add(createHeader(), BorderLayout.NORTH);

        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(NAVY);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(
                20, 12, 20, 12
        ));

        addMenuItems(role);

        JScrollPane scrollPane = new JScrollPane(menuPanel);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );
        scrollPane.getViewport().setBackground(NAVY);

        add(scrollPane, BorderLayout.CENTER);

        add(createFooter(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {

        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(
                header,
                BoxLayout.Y_AXIS
        ));

        header.setBackground(NAVY);
        header.setBorder(BorderFactory.createEmptyBorder(
                25, 20, 20, 20
        ));

        JLabel logo = new JLabel("ParcelFlow");

        logo.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                23
        ));

        logo.setForeground(WHITE);

        JLabel subtitle = new JLabel(
                "Parcel Management System"
        );

        subtitle.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                11
        ));

        subtitle.setForeground(TEXT);

        header.add(logo);
        header.add(Box.createVerticalStrut(5));
        header.add(subtitle);

        return header;
    }

    private void addMenuItems(String role) {

        addSectionTitle("MAIN");

        addMenuButton(
                "⌂",
                "Dashboard"
        );

        if (role.equalsIgnoreCase("USER")) {

            addSectionTitle("PARCEL");

            addMenuButton(
                    "□",
                    "Send Parcel"
            );

            addMenuButton(
                    "▤",
                    "My Parcels"
            );

            addMenuButton(
                    "⌖",
                    "Track Parcel"
            );

            addMenuButton(
                    "×",
                    "Cancel Parcel"
            );

            addMenuButton(
                    "⌫",
                    "Delete Parcel"
            );

            addSectionTitle("ACCOUNT");

            addMenuButton(
                    "♙",
                    "Profile"
            );
        }

        else if (role.equalsIgnoreCase("RIDER")) {

            addSectionTitle("DELIVERY");

            addMenuButton(
                    "▤",
                    "Pending Parcels"
            );

            addMenuButton(
                    "□",
                    "Assigned Parcels"
            );

            addMenuButton(
                    "↻",
                    "Update Status"
            );

            addSectionTitle("ACCOUNT");

            addMenuButton(
                    "♙",
                    "Profile"
            );
        }

        else if (role.equalsIgnoreCase("ADMIN")) {

            addSectionTitle("MANAGEMENT");

            addMenuButton(
                    "♙",
                    "Manage Users"
            );

            addMenuButton(
                    "□",
                    "Manage Riders"
            );

            addMenuButton(
                    "▤",
                    "Manage Parcels"
            );

            addMenuButton(
                    "⌕",
                    "Search"
            );

            addSectionTitle("REPORTS");

            addMenuButton(
                    "▥",
                    "Statistics"
            );

            addSectionTitle("ACCOUNT");

            addMenuButton(
                    "♙",
                    "Profile"
            );
        }
    }

    private void addSectionTitle(String title) {

        JLabel label = new JLabel(title);

        label.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                10
        ));

        label.setForeground(
                Color.decode("#64748B")
        );

        label.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 10, 8, 5
                )
        );

        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        menuPanel.add(label);
    }

    private void addMenuButton(
            String icon,
            String text
    ) {

        JPanel button = new JPanel(
                new BorderLayout()
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        button.setBackground(NAVY);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        0, 10, 0, 10
                )
        );

        JLabel iconLabel = new JLabel(icon);

        iconLabel.setPreferredSize(
                new Dimension(30, 30)
        );

        iconLabel.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                17
        ));

        iconLabel.setForeground(TEXT);

        JLabel textLabel = new JLabel(text);

        textLabel.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                13
        ));

        textLabel.setForeground(TEXT);

        button.add(iconLabel, BorderLayout.WEST);
        button.add(textLabel, BorderLayout.CENTER);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {

                button.setBackground(HOVER);

                iconLabel.setForeground(WHITE);
                textLabel.setForeground(WHITE);
            }

            @Override
            public void mouseExited(MouseEvent e) {

                button.setBackground(NAVY);

                iconLabel.setForeground(TEXT);
                textLabel.setForeground(TEXT);
            }

            @Override
            public void mouseClicked(MouseEvent e) {

                System.out.println(
                        "Clicked: " + text
                );
            }
        });

        menuPanel.add(button);
        menuPanel.add(Box.createVerticalStrut(4));
    }

    private JPanel createFooter() {

        JPanel footer = new JPanel();

        footer.setLayout(new BoxLayout(
                footer,
                BoxLayout.Y_AXIS
        ));

        footer.setBackground(NAVY);

        footer.setBorder(
                BorderFactory.createEmptyBorder(
                        10, 12, 20, 12
                )
        );

        JButton logout = new JButton("↪  Logout");

        logout.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        logout.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        logout.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                13
        ));

        logout.setForeground(
                Color.decode("#FCA5A5")
        );

        logout.setBackground(NAVY);
        logout.setFocusPainted(false);
        logout.setBorderPainted(false);
        logout.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        logout.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                logout.setBackground(
                        Color.decode("#3F1720")
                );
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logout.setBackground(NAVY);
            }
        });

        logout.addActionListener(e ->
                System.out.println("Logout clicked")
        );

        footer.add(logout);

        return footer;
    }
}