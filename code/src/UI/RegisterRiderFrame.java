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
import java.util.Arrays;

public class RegisterRiderFrame extends JPanel {

    private final User user;

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    private final Color BG = new Color(241, 248, 253);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT_DARK = new Color(31, 38, 43);
    private final Color TEXT_MUTED = new Color(103, 117, 128);
    private final Color ORANGE = new Color(248, 116, 35);
    private final Color ORANGE_HOVER = new Color(235, 94, 20);
    private final Color BLUE = new Color(0, 97, 153);
    private final Color LIGHT_BLUE = new Color(225, 240, 249);
    private final Color BORDER = new Color(216, 227, 234);
    private final Color INPUT_BORDER = new Color(198, 211, 220);
    private final Color INPUT_BG = new Color(249, 251, 252);
    private final Color SUCCESS = new Color(48, 148, 94);
    private final Color SUCCESS_BG = new Color(233, 248, 240);

    public RegisterRiderFrame(User user) {

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

        JLabel iconLabel = new JLabel("♟");
        iconLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        iconLabel.setForeground(Color.WHITE);

        iconPanel.add(iconLabel);

        header.add(iconPanel, BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel sectionLabel = new JLabel("RIDER MANAGEMENT");
        sectionLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        sectionLabel.setForeground(ORANGE);

        JLabel titleLabel = new JLabel("Register New Rider");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(TEXT_DARK);

        JLabel subtitleLabel = new JLabel(
                "Add a new rider to your delivery team"
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
        badge.setBackground(SUCCESS_BG);
        badge.setForeground(SUCCESS);
        badge.setFont(new Font("SansSerif", Font.BOLD, 10));
        badge.setBorder(new EmptyBorder(7, 11, 7, 11));

        header.add(badge, BorderLayout.EAST);

        page.add(header, BorderLayout.NORTH);

        JPanel mainCard = new RoundedPanel(WHITE, 22);
        mainCard.setLayout(new BorderLayout(18, 0));
        mainCard.setBorder(new EmptyBorder(22, 24, 22, 24));

        JPanel formCard = new RoundedPanel(new Color(251, 253, 254), 18);
        formCard.setLayout(new GridBagLayout());
        formCard.setBorder(new EmptyBorder(22, 22, 22, 22));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 7, 0);

        JLabel formTitle = new JLabel("Rider Information");
        formTitle.setFont(new Font("SansSerif", Font.BOLD, 17));
        formTitle.setForeground(TEXT_DARK);

        JLabel formSubtitle = new JLabel(
                "Enter the account details for the new rider"
        );
        formSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        formSubtitle.setForeground(TEXT_MUTED);

        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 3, 0);
        formCard.add(formTitle, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 18, 0);
        formCard.add(formSubtitle, gbc);

        JLabel nameLabel = createFormLabel("RIDER NAME");
        nameField = new JTextField();
        styleInputField(nameField);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 6, 0);
        formCard.add(nameLabel, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 14, 0);
        formCard.add(nameField, gbc);

        JLabel emailLabel = createFormLabel("EMAIL ADDRESS");
        emailField = new JTextField();
        styleInputField(emailField);

        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 6, 0);
        formCard.add(emailLabel, gbc);

        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 14, 0);
        formCard.add(emailField, gbc);

        JLabel passwordLabel = createFormLabel("PASSWORD");
        passwordField = new JPasswordField();
        styleInputField(passwordField);

        gbc.gridy = 6;
        gbc.insets = new Insets(0, 0, 6, 0);
        formCard.add(passwordLabel, gbc);

        gbc.gridy = 7;
        gbc.insets = new Insets(0, 0, 14, 0);
        formCard.add(passwordField, gbc);

        JLabel infoLabel = new JLabel(
                "The account will be created with rider access."
        );
        infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        infoLabel.setForeground(TEXT_MUTED);

        gbc.gridy = 8;
        gbc.insets = new Insets(0, 0, 0, 0);
        formCard.add(infoLabel, gbc);

        mainCard.add(formCard, BorderLayout.CENTER);

        JPanel guideCard = new RoundedPanel(LIGHT_BLUE, 18);
        guideCard.setLayout(new BorderLayout(0, 16));
        guideCard.setBorder(new EmptyBorder(22, 22, 22, 22));
        guideCard.setPreferredSize(new Dimension(270, 0));

        JPanel guideHeader = new JPanel();
        guideHeader.setOpaque(false);
        guideHeader.setLayout(new BoxLayout(guideHeader, BoxLayout.Y_AXIS));

        JLabel guideTitle = new JLabel("Rider Setup");
        guideTitle.setFont(new Font("SansSerif", Font.BOLD, 17));
        guideTitle.setForeground(TEXT_DARK);

        JLabel guideSubtitle = new JLabel(
                "Account creation overview"
        );
        guideSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        guideSubtitle.setForeground(TEXT_MUTED);

        guideHeader.add(guideTitle);
        guideHeader.add(Box.createVerticalStrut(3));
        guideHeader.add(guideSubtitle);

        guideCard.add(guideHeader, BorderLayout.NORTH);

        JPanel steps = new JPanel();
        steps.setOpaque(false);
        steps.setLayout(new BoxLayout(steps, BoxLayout.Y_AXIS));

        steps.add(createStep(
                "01",
                "Rider Name",
                "Enter the rider's full name."
        ));

        steps.add(Box.createVerticalStrut(14));

        steps.add(createStep(
                "02",
                "Email Address",
                "Use the account email for login."
        ));

        steps.add(Box.createVerticalStrut(14));

        steps.add(createStep(
                "03",
                "Password",
                "Set the rider's account password."
        ));

        guideCard.add(steps, BorderLayout.CENTER);

        JPanel accessInfo = new RoundedPanel(WHITE, 14);
        accessInfo.setLayout(new BorderLayout(10, 0));
        accessInfo.setBorder(new EmptyBorder(13, 14, 13, 14));

        JPanel accessDot = new RoundedPanel(ORANGE, 10);
        accessDot.setPreferredSize(new Dimension(10, 10));

        accessInfo.add(accessDot, BorderLayout.WEST);

        JLabel accessLabel = new JLabel(
                "<html><b>RIDER ACCESS</b><br>" +
                        "<span style='color:#687580'>The new account will be registered as a rider.</span></html>"
        );
        accessLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));

        accessInfo.add(accessLabel, BorderLayout.CENTER);

        guideCard.add(accessInfo, BorderLayout.SOUTH);

        mainCard.add(guideCard, BorderLayout.EAST);

        page.add(mainCard, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 10, 0)
        );
        bottomPanel.setOpaque(false);

        JButton clearButton = createOutlineButton("Clear");
        clearButton.setPreferredSize(new Dimension(95, 40));

        JButton registerButton = createPrimaryButton(
                "Register Rider  →"
        );
        registerButton.setPreferredSize(new Dimension(160, 40));

        bottomPanel.add(clearButton);
        bottomPanel.add(registerButton);

        page.add(bottomPanel, BorderLayout.SOUTH);

        add(page, BorderLayout.CENTER);

        registerButton.addActionListener(
                e -> registerRider()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );
    }

    private JLabel createFormLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        label.setForeground(BLUE);

        return label;
    }

    private JPanel createStep(
            String number,
            String title,
            String description
    ) {

        JPanel panel = new JPanel(new BorderLayout(12, 0));
        panel.setOpaque(false);

        JPanel numberPanel = new RoundedPanel(ORANGE, 10);
        numberPanel.setPreferredSize(new Dimension(34, 34));
        numberPanel.setLayout(new GridBagLayout());

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
        descriptionLabel.setForeground(TEXT_MUTED);

        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(3));
        textPanel.add(descriptionLabel);

        panel.add(numberPanel, BorderLayout.WEST);
        panel.add(textPanel, BorderLayout.CENTER);

        return panel;
    }

    private void styleInputField(JTextField field) {

        field.setOpaque(true);
        field.setBackground(INPUT_BG);
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
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        field.addFocusListener(
                new FocusAdapter() {

                    @Override
                    public void focusGained(FocusEvent e) {

                        field.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                ORANGE,
                                                2,
                                                true
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                7,
                                                11,
                                                7,
                                                11
                                        )
                                )
                        );
                    }

                    @Override
                    public void focusLost(FocusEvent e) {

                        field.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                INPUT_BORDER,
                                                1,
                                                true
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                8,
                                                12,
                                                8,
                                                12
                                        )
                                )
                        );
                    }
                }
        );
    }

    private JButton createPrimaryButton(String text) {

        JButton button = new JButton(text) {

            private boolean hovered;

            {
                addMouseListener(
                        new MouseAdapter() {

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                hovered = true;
                                repaint();
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                hovered = false;
                                repaint();
                            }
                        }
                );
            }

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(
                        hovered
                                ? ORANGE_HOVER
                                : ORANGE
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
                            public void mouseEntered(MouseEvent e) {
                                hovered = true;
                                repaint();
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                hovered = false;
                                repaint();
                            }
                        }
                );
            }

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();

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
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    private void clearForm() {

        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");

        nameField.requestFocus();
    }

    private void registerRider() {

        String name =
                nameField.getText().trim();

        String email =
                emailField.getText().trim();

        char[] passwordChars =
                passwordField.getPassword();

        if (
                name.isEmpty()
                        || email.isEmpty()
                        || passwordChars.length == 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            Admin admin =
                    new Admin(user);

            String password =
                    new String(passwordChars);

            admin.registerNewRider(
                    name,
                    email,
                    password
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Rider registered successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();

        } catch (NotFoundException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } finally {

            Arrays.fill(
                    passwordChars,
                    '\0'
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
        protected void paintComponent(Graphics g) {

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