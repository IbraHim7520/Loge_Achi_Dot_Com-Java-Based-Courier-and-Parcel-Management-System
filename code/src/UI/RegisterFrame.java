package UI;

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

public class RegisterFrame extends JPanel {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private final MainFrame mainFrame;

    // =========================================================
    // COLOR THEME
    // =========================================================

    private final Color BLUE = new Color(0, 97, 153);
    private final Color SKY_BLUE = new Color(138, 207, 248);
    private final Color SOFT_YELLOW = new Color(244, 235, 108);
    private final Color GOLD = new Color(255, 212, 68);

    private final Color BG_TOP = new Color(3, 39, 63);
    private final Color BG_BOTTOM = new Color(0, 72, 110);

    private final Color CARD_BG = new Color(0, 55, 88, 235);
    private final Color CARD_BORDER = new Color(138, 207, 248, 90);

    private final Color INPUT_BG = new Color(8, 67, 99);
    private final Color INPUT_BORDER = new Color(138, 207, 248, 80);

    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_MUTED = new Color(190, 220, 235);

    public RegisterFrame(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());

        // =====================================================
        // MAIN GLASS CARD
        // =====================================================

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

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
                                28,
                                28
                        )
                );

                // Card border
                g2d.setColor(CARD_BORDER);

                g2d.setStroke(
                        new BasicStroke(1.2f)
                );

                g2d.draw(
                        new RoundRectangle2D.Float(
                                0.5f,
                                0.5f,
                                getWidth() - 1,
                                getHeight() - 1,
                                28,
                                28
                        )
                );

                g2d.dispose();
            }
        };

        mainPanel.setOpaque(false);

        mainPanel.setLayout(
                new BorderLayout(0, 8)
        );

        mainPanel.setPreferredSize(
                new Dimension(440, 550)
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        30,
                        38,
                        30,
                        38
                )
        );

        // =====================================================
        // TITLE SECTION
        // =====================================================

        JPanel titlePanel = new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        // Logo
        JLabel logo = new JLabel("▣");

        logo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        logo.setForeground(GOLD);

        logo.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // Brand
        JLabel brandLabel =
                new JLabel(
                        "LOGE ACHI DOT COM"
                );

        brandLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        brandLabel.setForeground(
                SKY_BLUE
        );

        brandLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // Main title
        JLabel registerLabel =
                new JLabel(
                        "Create Your Account"
                );

        registerLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        26
                )
        );

        registerLabel.setForeground(
                TEXT_WHITE
        );

        registerLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // Subtitle
        JLabel subtitleLabel =
                new JLabel(
                        "Join our courier management system"
                );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        subtitleLabel.setForeground(
                TEXT_MUTED
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        titlePanel.add(logo);

        titlePanel.add(
                Box.createVerticalStrut(2)
        );

        titlePanel.add(brandLabel);

        titlePanel.add(
                Box.createVerticalStrut(9)
        );

        titlePanel.add(registerLabel);

        titlePanel.add(
                Box.createVerticalStrut(5)
        );

        titlePanel.add(subtitleLabel);

        titlePanel.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        15,
                        0
                )
        );

        mainPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // FORM
        // =====================================================

        JPanel formPanel = new JPanel();

        formPanel.setOpaque(false);

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );

        // Name
        nameField = new JTextField();

        styleInputField(
                nameField
        );

        // Email
        emailField = new JTextField();

        styleInputField(
                emailField
        );

        // Password
        passwordField =
                new JPasswordField();

        styleInputField(
                passwordField
        );

        // Confirm password
        confirmPasswordField =
                new JPasswordField();

        styleInputField(
                confirmPasswordField
        );

        formPanel.add(
                createFormRow(
                        "👤  FULL NAME *",
                        nameField
                )
        );

        formPanel.add(
                Box.createVerticalStrut(10)
        );

        formPanel.add(
                createFormRow(
                        "✉  EMAIL ADDRESS *",
                        emailField
                )
        );

        formPanel.add(
                Box.createVerticalStrut(10)
        );

        formPanel.add(
                createFormRow(
                        "🔒  PASSWORD *",
                        passwordField
                )
        );

        formPanel.add(
                Box.createVerticalStrut(10)
        );

        formPanel.add(
                createFormRow(
                        "🔒  CONFIRM PASSWORD *",
                        confirmPasswordField
                )
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTON SECTION
        // =====================================================

        JButton registerButton =
                createRegisterButton(
                        "Create Account →"
                );

        JButton backButton =
                createSubtleLinkButton(
                        "Already have an account? Sign in"
                );

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.setOpaque(false);

        buttonPanel.setLayout(
                new BoxLayout(
                        buttonPanel,
                        BoxLayout.Y_AXIS
                )
        );

        registerButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        backButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        buttonPanel.add(
                Box.createVerticalStrut(8)
        );

        buttonPanel.add(
                registerButton
        );

        buttonPanel.add(
                Box.createVerticalStrut(10)
        );

        buttonPanel.add(
                backButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // ACTIONS
        // =====================================================

        registerButton.addActionListener(
                e -> register()
        );

        confirmPasswordField.addActionListener(
                e -> register()
        );

        backButton.addActionListener(e -> {

            if (mainFrame != null) {

                mainFrame.showLoginFrame();
            }
        });

        add(mainPanel);
    }

    // =========================================================
    // BACKGROUND
    // =========================================================

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d =
                (Graphics2D) g.create();

        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Main background gradient
        GradientPaint gradient =
                new GradientPaint(
                        0,
                        0,
                        BG_TOP,
                        getWidth(),
                        getHeight(),
                        BG_BOTTOM
                );

        g2d.setPaint(gradient);

        g2d.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        // =====================================================
        // DECORATIVE GLOW
        // =====================================================

        g2d.setColor(
                new Color(
                        138,
                        207,
                        248,
                        20
                )
        );

        g2d.fillOval(
                -130,
                -100,
                300,
                300
        );

        g2d.setColor(
                new Color(
                        255,
                        212,
                        68,
                        15
                )
        );

        g2d.fillOval(
                getWidth() - 160,
                getHeight() - 170,
                300,
                300
        );

        // Small decorative dots

        g2d.setColor(
                new Color(
                        255,
                        212,
                        68,
                        75
                )
        );

        g2d.fillOval(
                55,
                getHeight() - 90,
                7,
                7
        );

        g2d.setColor(
                new Color(
                        138,
                        207,
                        248,
                        75
                )
        );

        g2d.fillOval(
                getWidth() - 65,
                80,
                6,
                6
        );

        g2d.dispose();
    }

    // =========================================================
    // FORM ROW
    // =========================================================

    private JPanel createFormRow(
            String labelText,
            JTextField field
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout(
                                0,
                                5
                        )
                );

        row.setOpaque(false);

        JLabel label =
                new JLabel(
                        labelText
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        label.setForeground(
                new Color(
                        175,
                        220,
                        240
                )
        );

        row.add(
                label,
                BorderLayout.NORTH
        );

        row.add(
                field,
                BorderLayout.CENTER
        );

        return row;
    }

    // =========================================================
    // INPUT STYLE
    // =========================================================

    private void styleInputField(
            JTextField field
    ) {

        field.setOpaque(true);

        field.setBackground(
                INPUT_BG
        );

        field.setForeground(
                TEXT_WHITE
        );

        field.setCaretColor(
                GOLD
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
                                12,
                                7,
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
                                                SKY_BLUE,
                                                1,
                                                true
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                7,
                                                12,
                                                7,
                                                12
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
                                                12,
                                                7,
                                                12
                                        )
                                )
                        );
                    }
                }
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        38
                )
        );
    }

    // =========================================================
    // REGISTER BUTTON
    // =========================================================

    private JButton createRegisterButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

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

                        Color startColor;
                        Color endColor;

                        if (getModel().isRollover()) {

                            startColor =
                                    new Color(
                                            255,
                                            224,
                                            105
                                    );

                            endColor =
                                    GOLD;

                        } else {

                            startColor =
                                    GOLD;

                            endColor =
                                    new Color(
                                            244,
                                            190,
                                            35
                                    );
                        }

                        GradientPaint gradient =
                                new GradientPaint(
                                        0,
                                        0,
                                        startColor,
                                        getWidth(),
                                        0,
                                        endColor
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
                                        24,
                                        24
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
                        15
                )
        );

        button.setForeground(
                new Color(
                        20,
                        55,
                        75
                )
        );

        button.setContentAreaFilled(false);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        43
                )
        );

        button.setPreferredSize(
                new Dimension(
                        300,
                        43
                )
        );

        return button;
    }

    // =========================================================
    // BACK TO LOGIN BUTTON
    // =========================================================

    private JButton createSubtleLinkButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        button.setForeground(
                SKY_BLUE
        );

        button.setContentAreaFilled(false);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setForeground(
                                SOFT_YELLOW
                        );

                        button.setText(
                                "<html><u>"
                                        + text
                                        + "</u></html>"
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setForeground(
                                SKY_BLUE
                        );

                        button.setText(
                                text
                        );
                    }
                }
        );

        return button;
    }

    // =========================================================
    // REGISTER
    // =========================================================

    private void register() {

        String name =
                nameField
                        .getText()
                        .trim();

        String email =
                emailField
                        .getText()
                        .trim();

        char[] passChars =
                passwordField
                        .getPassword();

        char[] confirmPassChars =
                confirmPasswordField
                        .getPassword();

        // -----------------------------------------------------
        // EMPTY FIELD VALIDATION
        // -----------------------------------------------------

        if (name.isEmpty()
                || email.isEmpty()
                || passChars.length == 0
                || confirmPassChars.length == 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Registration Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // -----------------------------------------------------
        // PASSWORD MATCH
        // -----------------------------------------------------

        if (!Arrays.equals(
                passChars,
                confirmPassChars
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE
            );

            // Clear memory
            Arrays.fill(
                    passChars,
                    '0'
            );

            Arrays.fill(
                    confirmPassChars,
                    '0'
            );

            return;
        }

        String password =
                new String(
                        passChars
                );

        // Clear password arrays
        Arrays.fill(
                passChars,
                '0'
        );

        Arrays.fill(
                confirmPassChars,
                '0'
        );

        // -----------------------------------------------------
        // BACKEND REGISTRATION
        // -----------------------------------------------------

        User user =
                new User(
                        name,
                        email,
                        password
                );

        String result =
                user.register(user);

        // -----------------------------------------------------
        // SUCCESS
        // -----------------------------------------------------

        if (result != null
                && result.startsWith(
                "User registered successfully."
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    result,
                    "Registration Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (mainFrame != null) {

                mainFrame.showLoginFrame();
            }

        }

        // -----------------------------------------------------
        // FAILED
        // -----------------------------------------------------

        else {

            JOptionPane.showMessageDialog(
                    this,
                    result != null
                            ? result
                            : "An unknown error occurred.",
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}

