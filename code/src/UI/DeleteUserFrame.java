package UI;

import custom_exception.NotFoundException;
import model.Admin;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class DeleteUserFrame extends JPanel {

    private final User user;
    private JTextField userIdField;

    private final Color BG = new Color(241, 248, 253);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT_DARK = new Color(31, 38, 43);
    private final Color TEXT_MUTED = new Color(103, 117, 128);
    private final Color ORANGE = new Color(248, 116, 35);
    private final Color ORANGE_HOVER = new Color(235, 94, 20);
    private final Color BLUE = new Color(0, 97, 153);
    private final Color LIGHT_BLUE = new Color(225, 240, 249);
    private final Color BORDER = new Color(216, 227, 234);
    private final Color INPUT_BG = new Color(249, 251, 252);
    private final Color INPUT_BORDER = new Color(198, 211, 220);
    private final Color DANGER = new Color(205, 62, 73);
    private final Color DANGER_HOVER = new Color(180, 45, 57);
    private final Color DANGER_BG = new Color(252, 237, 239);

    public DeleteUserFrame(User user) {
        this.user = user;

        setLayout(new BorderLayout());
        setBackground(BG);

        buildUI();
    }

    private void buildUI() {

        JPanel page = new JPanel(new BorderLayout(0, 18));
        page.setOpaque(false);
        page.setBorder(new EmptyBorder(24, 28, 24, 28));

        JPanel header = new JPanel(new BorderLayout(14, 0));
        header.setOpaque(false);

        JPanel iconPanel = new RoundedPanel(BLUE, 16);
        iconPanel.setPreferredSize(new Dimension(52, 52));
        iconPanel.setLayout(new GridBagLayout());

        JLabel iconLabel = new JLabel("♙");
        iconLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        iconLabel.setForeground(Color.WHITE);

        iconPanel.add(iconLabel);

        header.add(iconPanel, BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(
                titlePanel,
                BoxLayout.Y_AXIS
        ));

        JLabel sectionLabel = new JLabel("USER MANAGEMENT");
        sectionLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        sectionLabel.setForeground(ORANGE);

        JLabel titleLabel = new JLabel("Delete User");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(TEXT_DARK);

        JLabel subtitleLabel = new JLabel(
                "Remove an existing user account from the system"
        );
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitleLabel.setForeground(TEXT_MUTED);

        titlePanel.add(sectionLabel);
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(subtitleLabel);

        header.add(titlePanel, BorderLayout.CENTER);

        JLabel badge = new JLabel("ADMIN ACTION");
        badge.setOpaque(true);
        badge.setBackground(DANGER_BG);
        badge.setForeground(DANGER);
        badge.setFont(new Font("SansSerif", Font.BOLD, 10));
        badge.setBorder(new EmptyBorder(7, 11, 7, 11));

        header.add(badge, BorderLayout.EAST);

        page.add(header, BorderLayout.NORTH);

        JPanel mainCard = new RoundedPanel(WHITE, 22);
        mainCard.setLayout(new BorderLayout(18, 0));
        mainCard.setBorder(new EmptyBorder(22, 24, 22, 24));

        JPanel formCard = new RoundedPanel(
                new Color(251, 253, 254),
                18
        );

        formCard.setLayout(new GridBagLayout());
        formCard.setBorder(
                new EmptyBorder(24, 24, 24, 24)
        );

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel formTitle = new JLabel("Delete User Account");
        formTitle.setFont(new Font("SansSerif", Font.BOLD, 17));
        formTitle.setForeground(TEXT_DARK);

        JLabel formSubtitle = new JLabel(
                "Enter the User ID of the account you want to remove."
        );
        formSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        formSubtitle.setForeground(TEXT_MUTED);

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 4, 0);
        formCard.add(formTitle, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 22, 0);
        formCard.add(formSubtitle, gbc);

        JLabel userIdLabel = new JLabel("USER ID");
        userIdLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );
        userIdLabel.setForeground(BLUE);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 7, 0);
        formCard.add(userIdLabel, gbc);

        userIdField = new JTextField();
        styleInputField(userIdField);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 20, 0);
        formCard.add(userIdField, gbc);

        JPanel warningCard = new RoundedPanel(
                DANGER_BG,
                14
        );

        warningCard.setLayout(
                new BorderLayout(12, 0)
        );

        warningCard.setBorder(
                new EmptyBorder(14, 15, 14, 15)
        );

        JPanel warningIcon = new RoundedPanel(
                DANGER,
                10
        );

        warningIcon.setPreferredSize(
                new Dimension(34, 34)
        );

        warningIcon.setLayout(
                new GridBagLayout()
        );

        JLabel warningSymbol = new JLabel("!");
        warningSymbol.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );
        warningSymbol.setForeground(Color.WHITE);

        warningIcon.add(warningSymbol);

        warningCard.add(
                warningIcon,
                BorderLayout.WEST
        );

        JLabel warningText = new JLabel(
                "<html><b>Permanent action</b><br>" +
                        "<span style='color:#6f5d62'>" +
                        "Deleting a user removes the account from the system." +
                        "</span></html>"
        );

        warningText.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        warningCard.add(
                warningText,
                BorderLayout.CENTER
        );

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 0, 0);
        formCard.add(warningCard, gbc);

        mainCard.add(
                formCard,
                BorderLayout.CENTER
        );

        JPanel guideCard = new RoundedPanel(
                LIGHT_BLUE,
                18
        );

        guideCard.setLayout(
                new BorderLayout(0, 16)
        );

        guideCard.setBorder(
                new EmptyBorder(
                        22,
                        22,
                        22,
                        22
                )
        );

        guideCard.setPreferredSize(
                new Dimension(270, 0)
        );

        JPanel guideHeader = new JPanel();
        guideHeader.setOpaque(false);
        guideHeader.setLayout(
                new BoxLayout(
                        guideHeader,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel guideTitle = new JLabel("Delete Process");
        guideTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );
        guideTitle.setForeground(TEXT_DARK);

        JLabel guideSubtitle = new JLabel(
                "Follow these steps before deleting"
        );
        guideSubtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );
        guideSubtitle.setForeground(TEXT_MUTED);

        guideHeader.add(guideTitle);
        guideHeader.add(Box.createVerticalStrut(3));
        guideHeader.add(guideSubtitle);

        guideCard.add(
                guideHeader,
                BorderLayout.NORTH
        );

        JPanel steps = new JPanel();
        steps.setOpaque(false);
        steps.setLayout(
                new BoxLayout(
                        steps,
                        BoxLayout.Y_AXIS
                )
        );

        steps.add(
                createStep(
                        "01",
                        "Find User",
                        "Enter the correct User ID."
                )
        );

        steps.add(Box.createVerticalStrut(18));

        steps.add(
                createStep(
                        "02",
                        "Verify",
                        "Check that the selected ID is correct."
                )
        );

        steps.add(Box.createVerticalStrut(18));

        steps.add(
                createStep(
                        "03",
                        "Confirm",
                        "Confirm the delete action."
                )
        );

        guideCard.add(
                steps,
                BorderLayout.CENTER
        );

        JPanel adminInfo = new RoundedPanel(
                WHITE,
                14
        );

        adminInfo.setLayout(
                new BorderLayout(10, 0)
        );

        adminInfo.setBorder(
                new EmptyBorder(
                        13,
                        14,
                        13,
                        14
                )
        );

        JPanel dot = new RoundedPanel(
                ORANGE,
                10
        );

        dot.setPreferredSize(
                new Dimension(10, 10)
        );

        adminInfo.add(
                dot,
                BorderLayout.WEST
        );

        JLabel adminLabel = new JLabel(
                "<html><b>ADMIN ACCESS</b><br>" +
                        "<span style='color:#687580'>" +
                        "Only authorized administrators can remove users." +
                        "</span></html>"
        );

        adminLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        adminInfo.add(
                adminLabel,
                BorderLayout.CENTER
        );

        guideCard.add(
                adminInfo,
                BorderLayout.SOUTH
        );

        mainCard.add(
                guideCard,
                BorderLayout.EAST
        );

        page.add(
                mainCard,
                BorderLayout.CENTER
        );

        JPanel bottomPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        10,
                        0
                )
        );

        bottomPanel.setOpaque(false);

        JButton clearButton =
                createOutlineButton("Clear");

        JButton deleteButton =
                createDeleteButton("Delete User  →");

        clearButton.setPreferredSize(
                new Dimension(95, 40)
        );

        deleteButton.setPreferredSize(
                new Dimension(150, 40)
        );

        bottomPanel.add(clearButton);
        bottomPanel.add(deleteButton);

        page.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(
                page,
                BorderLayout.CENTER
        );

        deleteButton.addActionListener(
                e -> deleteUser()
        );

        clearButton.addActionListener(
                e -> {
                    userIdField.setText("");
                    userIdField.requestFocus();
                }
        );
    }

    private void styleInputField(JTextField field) {

        field.setOpaque(true);
        field.setBackground(WHITE);
        field.setForeground(TEXT_DARK);
        field.setCaretColor(ORANGE);
        field.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                INPUT_BORDER,
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                9,
                                12,
                                9,
                                12
                        )
                )
        );

        field.addFocusListener(
                new FocusAdapter() {

                    @Override
                    public void focusGained(
                            FocusEvent e
                    ) {

                        field.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                ORANGE,
                                                2,
                                                true
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                8,
                                                11,
                                                8,
                                                11
                                        )
                                )
                        );
                    }

                    @Override
                    public void focusLost(
                            FocusEvent e
                    ) {

                        field.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                INPUT_BORDER,
                                                1,
                                                true
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                9,
                                                12,
                                                9,
                                                12
                                        )
                                )
                        );
                    }
                }
        );
    }

    private JPanel createStep(
            String number,
            String title,
            String description
    ) {

        JPanel panel = new JPanel(
                new BorderLayout(12, 0)
        );

        panel.setOpaque(false);

        JPanel numberPanel = new RoundedPanel(
                ORANGE,
                10
        );

        numberPanel.setPreferredSize(
                new Dimension(34, 34)
        );

        numberPanel.setLayout(
                new GridBagLayout()
        );

        JLabel numberLabel = new JLabel(number);
        numberLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );
        numberLabel.setForeground(Color.WHITE);

        numberPanel.add(numberLabel);

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );
        titleLabel.setForeground(TEXT_DARK);

        JLabel descriptionLabel = new JLabel(
                "<html>" + description + "</html>"
        );

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        descriptionLabel.setForeground(
                TEXT_MUTED
        );

        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(3));
        textPanel.add(descriptionLabel);

        panel.add(
                numberPanel,
                BorderLayout.WEST
        );

        panel.add(
                textPanel,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JButton createDeleteButton(String text) {

        JButton button = new JButton(text) {

            private boolean hovered;

            {
                addMouseListener(
                        new MouseAdapter() {

                            @Override
                            public void mouseEntered(
                                    MouseEvent e
                            ) {
                                hovered = true;
                                repaint();
                            }

                            @Override
                            public void mouseExited(
                                    MouseEvent e
                            ) {
                                hovered = false;
                                repaint();
                            }
                        }
                );
            }

            @Override
            protected void paintComponent(
                    Graphics g
            ) {

                Graphics2D g2 =
                        (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(
                        hovered
                                ? DANGER_HOVER
                                : DANGER
                );

                g2.fill(
                        new RoundRectangle2D.Float(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                12,
                                12
                        )
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    private JButton createOutlineButton(String text) {

        JButton button = new JButton(text) {

            private boolean hovered;

            {
                addMouseListener(
                        new MouseAdapter() {

                            @Override
                            public void mouseEntered(
                                    MouseEvent e
                            ) {
                                hovered = true;
                                repaint();
                            }

                            @Override
                            public void mouseExited(
                                    MouseEvent e
                            ) {
                                hovered = false;
                                repaint();
                            }
                        }
                );
            }

            @Override
            protected void paintComponent(
                    Graphics g
            ) {

                Graphics2D g2 =
                        (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                if (hovered) {

                    g2.setColor(LIGHT_BLUE);

                    g2.fill(
                            new RoundRectangle2D.Float(
                                    0,
                                    0,
                                    getWidth(),
                                    getHeight(),
                                    12,
                                    12
                            )
                    );
                }

                g2.setColor(BLUE);
                g2.setStroke(
                        new BasicStroke(1.2f)
                );

                g2.draw(
                        new RoundRectangle2D.Float(
                                1,
                                1,
                                getWidth() - 2,
                                getHeight() - 2,
                                12,
                                12
                        )
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(BLUE);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    private void deleteUser() {

        String userID =
                userIdField
                        .getText()
                        .trim();

        if (userID.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter User ID!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete User ID: "
                                + userID
                                + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            Admin admin =
                    new Admin(user);

            admin.deleteUser(userID);

            JOptionPane.showMessageDialog(
                    this,
                    "User deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            userIdField.setText("");

        } catch (NotFoundException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Delete Failed",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private static class RoundedPanel extends JPanel {

        private final Color background;
        private final int radius;

        public RoundedPanel(
                Color background,
                int radius
        ) {
            this.background = background;
            this.radius = radius;

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(background);

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    radius,
                    radius
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }
}