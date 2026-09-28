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

    // =========================================================
    // THEME COLORS
    // =========================================================

    private final Color BLUE = new Color(0, 97, 153);
    private final Color SKY_BLUE = new Color(138, 207, 248);
    private final Color SOFT_YELLOW = new Color(244, 235, 108);
    private final Color GOLD = new Color(255, 212, 68);

    private final Color BG_TOP = new Color(3, 39, 63);
    private final Color BG_BOTTOM = new Color(0, 72, 110);

    private final Color CARD_BG = new Color(0, 55, 88, 238);
    private final Color CARD_BORDER = new Color(138, 207, 248, 85);

    private final Color INPUT_BG = new Color(248, 252, 255);
    private final Color INPUT_TEXT = new Color(25, 45, 58);
    private final Color INPUT_BORDER = new Color(138, 207, 248, 130);

    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_MUTED = new Color(190, 220, 235);

    private final Color BTN_TEXT = new Color(20, 55, 70);

    public RegisterRiderFrame(User user) {

        this.user = user;

        setLayout(new BorderLayout());
        setOpaque(false);

        // =====================================================
        // ROOT BACKGROUND
        // =====================================================

        JPanel rootPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2d =
                        (Graphics2D) g.create();

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                int width = getWidth();
                int height = getHeight();

                // Main gradient
                GradientPaint gradient =
                        new GradientPaint(
                                0,
                                0,
                                BG_TOP,
                                0,
                                height,
                                BG_BOTTOM
                        );

                g2d.setPaint(gradient);
                g2d.fillRect(
                        0,
                        0,
                        width,
                        height
                );

                // Decorative glow - top right
                g2d.setColor(
                        new Color(
                                SKY_BLUE.getRed(),
                                SKY_BLUE.getGreen(),
                                SKY_BLUE.getBlue(),
                                25
                        )
                );

                g2d.fillOval(
                        width - 180,
                        -80,
                        260,
                        260
                );

                // Decorative glow - bottom left
                g2d.setColor(
                        new Color(
                                GOLD.getRed(),
                                GOLD.getGreen(),
                                GOLD.getBlue(),
                                18
                        )
                );

                g2d.fillOval(
                        -100,
                        height - 150,
                        230,
                        230
                );

                // Small decorative dots
                g2d.setColor(
                        new Color(
                                255,
                                255,
                                255,
                                45
                        )
                );

                for (int i = 0; i < 8; i++) {

                    int x = 30 + (i * 75);
                    int y = 30 + ((i % 3) * 45);

                    g2d.fillOval(
                            x,
                            y,
                            4,
                            4
                    );
                }

                g2d.dispose();
            }
        };

        rootPanel.setLayout(
                new GridBagLayout()
        );

        // =====================================================
        // MAIN CARD
        // =====================================================

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2d =
                        (Graphics2D) g.create();

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Card background
                g2d.setColor(CARD_BG);

                g2d.fill(
                        new RoundRectangle2D.Float(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                24,
                                24
                        )
                );

                // Top accent
                GradientPaint accent =
                        new GradientPaint(
                                0,
                                0,
                                GOLD,
                                getWidth(),
                                0,
                                SOFT_YELLOW
                        );

                g2d.setPaint(accent);

                g2d.fillRoundRect(
                        0,
                        0,
                        getWidth(),
                        5,
                        24,
                        24
                );

                // Border
                g2d.setColor(CARD_BORDER);

                g2d.setStroke(
                        new BasicStroke(1.2f)
                );

                g2d.draw(
                        new RoundRectangle2D.Float(
                                1,
                                1,
                                getWidth() - 2,
                                getHeight() - 2,
                                24,
                                24
                        )
                );

                g2d.dispose();

                super.paintComponent(g);
            }
        };

        mainPanel.setOpaque(false);

        mainPanel.setLayout(
                new BorderLayout(
                        0,
                        12
                )
        );

        mainPanel.setPreferredSize(
                new Dimension(
                        470,
                        500
                )
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        25,
                        32,
                        25,
                        32
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setOpaque(false);

        // Logo
        JLabel logoLabel =
                new JLabel("▣");

        logoLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        logoLabel.setForeground(GOLD);

        logoLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        logoLabel.setPreferredSize(
                new Dimension(
                        48,
                        48
                )
        );

        JPanel logoPanel =
                new JPanel(
                        new GridBagLayout()
                );

        logoPanel.setOpaque(false);

        logoPanel.add(logoLabel);

        headerPanel.add(
                logoPanel,
                BorderLayout.WEST
        );

        // Header text
        JPanel headerText =
                new JPanel();

        headerText.setOpaque(false);

        headerText.setLayout(
                new BoxLayout(
                        headerText,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel brandLabel =
                new JLabel(
                        "LOGE ACHI DOT COM"
                );

        brandLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        brandLabel.setForeground(
                SKY_BLUE
        );

        JLabel titleLabel =
                new JLabel(
                        "Register New Rider"
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        23
                )
        );

        titleLabel.setForeground(
                TEXT_WHITE
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Add a rider to your delivery team"
                );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        subtitleLabel.setForeground(
                TEXT_MUTED
        );

        headerText.add(brandLabel);
        headerText.add(
                Box.createVerticalStrut(2)
        );
        headerText.add(titleLabel);
        headerText.add(
                Box.createVerticalStrut(3)
        );
        headerText.add(subtitleLabel);

        headerPanel.add(
                headerText,
                BorderLayout.CENTER
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // FORM
        // =====================================================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.weightx = 1.0;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        5,
                        0,
                        5,
                        0
                );

        // Name
        JLabel nameLabel =
                createFormLabel(
                        "RIDER NAME"
                );

        nameField =
                new JTextField();

        styleInputField(
                nameField
        );

        gbc.gridy = 0;

        formPanel.add(
                nameLabel,
                gbc
        );

        gbc.gridy = 1;

        formPanel.add(
                nameField,
                gbc
        );

        // Email
        JLabel emailLabel =
                createFormLabel(
                        "EMAIL ADDRESS"
                );

        emailField =
                new JTextField();

        styleInputField(
                emailField
        );

        gbc.gridy = 2;

        formPanel.add(
                emailLabel,
                gbc
        );

        gbc.gridy = 3;

        formPanel.add(
                emailField,
                gbc
        );

        // Password
        JLabel passwordLabel =
                createFormLabel(
                        "PASSWORD"
                );

        passwordField =
                new JPasswordField();

        styleInputField(
                passwordField
        );

        gbc.gridy = 4;

        formPanel.add(
                passwordLabel,
                gbc
        );

        gbc.gridy = 5;

        formPanel.add(
                passwordField,
                gbc
        );

        // Small information text
        JLabel infoLabel =
                new JLabel(
                        "Rider account will be created with rider access."
                );

        infoLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        infoLabel.setForeground(
                TEXT_MUTED
        );

        gbc.gridy = 6;

        gbc.insets =
                new Insets(
                        8,
                        0,
                        2,
                        0
                );

        formPanel.add(
                infoLabel,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        JButton registerButton =
                createGoldenButton(
                        "✓  Register Rider"
                );

        JButton clearButton =
                createOutlineButton(
                        "Clear"
                );

        registerButton.setPreferredSize(
                new Dimension(
                        155,
                        40
                )
        );

        clearButton.setPreferredSize(
                new Dimension(
                        90,
                        40
                )
        );

        buttonPanel.add(
                registerButton
        );

        buttonPanel.add(
                clearButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        rootPanel.add(
                mainPanel
        );

        add(
                rootPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // ACTION LISTENERS
        // =====================================================

        registerButton.addActionListener(
                e -> registerRider()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );
    }

    // =========================================================
    // FORM LABEL
    // =========================================================

    private JLabel createFormLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        label.setForeground(
                SKY_BLUE
        );

        return label;
    }

    // =========================================================
    // INPUT FIELD STYLE
    // =========================================================

    private void styleInputField(
            JTextField field
    ) {

        field.setOpaque(true);

        field.setBackground(
                INPUT_BG
        );

        field.setForeground(
                INPUT_TEXT
        );

        field.setCaretColor(
                BLUE
        );

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
                                7,
                                11,
                                7,
                                11
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
                                                GOLD,
                                                2,
                                                true
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                6,
                                                10,
                                                6,
                                                10
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
                                                7,
                                                11,
                                                7,
                                                11
                                        )
                                )
                        );
                    }
                }
        );

        field.setPreferredSize(
                new Dimension(
                        0,
                        36
                )
        );
    }

    // =========================================================
    // GOLDEN BUTTON
    // =========================================================

    private JButton createGoldenButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

                    private boolean isHovered =
                            false;

                    {
                        addMouseListener(
                                new MouseAdapter() {

                                    @Override
                                    public void mouseEntered(
                                            MouseEvent e
                                    ) {

                                        isHovered =
                                                true;

                                        repaint();
                                    }

                                    @Override
                                    public void mouseExited(
                                            MouseEvent e
                                    ) {

                                        isHovered =
                                                false;

                                        repaint();
                                    }
                                }
                        );
                    }

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        Graphics2D g2d =
                                (Graphics2D) g.create();

                        g2d.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        Color top =
                                isHovered
                                        ? SOFT_YELLOW
                                        : GOLD;

                        Color bottom =
                                isHovered
                                        ? GOLD
                                        : new Color(
                                        255,
                                        190,
                                        35
                                );

                        GradientPaint gradient =
                                new GradientPaint(
                                        0,
                                        0,
                                        top,
                                        0,
                                        getHeight(),
                                        bottom
                                );

                        g2d.setPaint(
                                gradient
                        );

                        g2d.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        12,
                                        12
                                )
                        );

                        g2d.dispose();

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

        button.setForeground(
                BTN_TEXT
        );

        button.setContentAreaFilled(
                false
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // OUTLINE BUTTON
    // =========================================================

    private JButton createOutlineButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

                    private boolean isHovered =
                            false;

                    {
                        addMouseListener(
                                new MouseAdapter() {

                                    @Override
                                    public void mouseEntered(
                                            MouseEvent e
                                    ) {

                                        isHovered =
                                                true;

                                        repaint();
                                    }

                                    @Override
                                    public void mouseExited(
                                            MouseEvent e
                                    ) {

                                        isHovered =
                                                false;

                                        repaint();
                                    }
                                }
                        );
                    }

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        Graphics2D g2d =
                                (Graphics2D) g.create();

                        g2d.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        if (isHovered) {

                            g2d.setColor(
                                    new Color(
                                            138,
                                            207,
                                            248,
                                            25
                                    )
                            );

                            g2d.fill(
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

                        g2d.setColor(
                                new Color(
                                        138,
                                        207,
                                        248,
                                        170
                                )
                        );

                        g2d.setStroke(
                                new BasicStroke(
                                        1.2f
                                )
                        );

                        g2d.draw(
                                new RoundRectangle2D.Float(
                                        1,
                                        1,
                                        getWidth() - 2,
                                        getHeight() - 2,
                                        12,
                                        12
                                )
                        );

                        g2d.dispose();

                        super.paintComponent(g);
                    }
                };

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        button.setForeground(
                TEXT_WHITE
        );

        button.setContentAreaFilled(
                false
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================

    private void clearForm() {

        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
    }

    // =========================================================
    // REGISTER RIDER
    // =========================================================

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

            // Backend logic unchanged
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

            // Zero out password memory buffer
            Arrays.fill(
                    passwordChars,
                    '\0'
            );
        }
    }
}