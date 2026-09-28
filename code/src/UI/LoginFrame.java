package UI;

import custom_exception.NotFoundException;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class LoginFrame extends JPanel {

    private JTextField emailField;
    private JPasswordField passwordField;
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

    public LoginFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());

        // =====================================================
        // MAIN GLASS CARD
        // =====================================================

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();

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
                g2d.setStroke(new BasicStroke(1.2f));

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
        mainPanel.setLayout(new BorderLayout());

        mainPanel.setPreferredSize(
                new Dimension(430, 500)
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        32,
                        38,
                        32,
                        38
                )
        );

        // =====================================================
        // TOP BRAND SECTION
        // =====================================================

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        // Small logo circle
        JLabel logo = new JLabel("▣");
        logo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25
                )
        );
        logo.setForeground(GOLD);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Brand name
        JLabel brandLabel = new JLabel(
                "LOGE ACHI DOT COM"
        );

        brandLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        brandLabel.setForeground(SKY_BLUE);
        brandLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Main title
        JLabel loginLabel = new JLabel(
                "Welcome Back"
        );

        loginLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        27
                )
        );

        loginLabel.setForeground(TEXT_WHITE);
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel subtitleLabel = new JLabel(
                "Sign in to manage your parcels"
        );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        subtitleLabel.setForeground(TEXT_MUTED);
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titlePanel.add(logo);
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(brandLabel);
        titlePanel.add(Box.createVerticalStrut(10));
        titlePanel.add(loginLabel);
        titlePanel.add(Box.createVerticalStrut(5));
        titlePanel.add(subtitleLabel);

        titlePanel.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        24,
                        0
                )
        );

        mainPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // FORM SECTION
        // =====================================================

        JPanel formPanel = new JPanel();

        formPanel.setOpaque(false);

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );



        emailField = createIconTextField(
                "👤  username / email"
        );



        passwordField = createIconPasswordField(
                "🔒  password"
        );


        formPanel.add(Box.createVerticalStrut(7));
        formPanel.add(emailField);

        formPanel.add(
                Box.createVerticalStrut(18)
        );


        formPanel.add(Box.createVerticalStrut(7));
        formPanel.add(passwordField);

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTON + FOOTER SECTION
        // =====================================================

        JButton loginButton =
                createLoginButton("Sign In →");

        JButton registerButton =
                createRegisterButton(
                        "Don't have an account? Create one"
                );

        JPanel buttonPanel = new JPanel();

        buttonPanel.setOpaque(false);

        buttonPanel.setLayout(
                new BoxLayout(
                        buttonPanel,
                        BoxLayout.Y_AXIS
                )
        );

        loginButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        registerButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        buttonPanel.add(
                Box.createVerticalStrut(10)
        );

        buttonPanel.add(loginButton);

        buttonPanel.add(
                Box.createVerticalStrut(13)
        );

        buttonPanel.add(registerButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // ACTIONS
        // =====================================================

        loginButton.addActionListener(
                e -> handleLogin()
        );

        passwordField.addActionListener(
                e -> handleLogin()
        );

        registerButton.addActionListener(e -> {

            if (mainFrame != null) {
                mainFrame.showRegisterFrame();
            }

        });

        add(mainPanel);

        // =====================================================
        // DECORATIVE BACKGROUND ELEMENTS
        // =====================================================

        // Nothing functional here.
        // Background is painted below.
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

        // Main gradient
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
        // DECORATIVE GLOW CIRCLES
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
                -120,
                -100,
                300,
                300
        );

        g2d.setColor(
                new Color(
                        255,
                        212,
                        68,
                        16
                )
        );

        g2d.fillOval(
                getWidth() - 170,
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
                        80
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
                        80
                )
        );

        g2d.fillOval(
                getWidth() - 70,
                80,
                6,
                6
        );

        g2d.dispose();
    }

    // =========================================================
    // FIELD LABEL
    // =========================================================

    private JLabel createFieldLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        label.setForeground(
                new Color(
                        175,
                        220,
                        240
                )
        );

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return label;
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private JTextField createIconTextField(
            String placeholder
    ) {

        JTextField field =
                new JTextField(placeholder);

        styleInputField(
                field,
                placeholder
        );

        return field;
    }

    // =========================================================
    // PASSWORD FIELD
    // =========================================================

    private JPasswordField createIconPasswordField(
            String placeholder
    ) {

        JPasswordField field =
                new JPasswordField(placeholder);

        field.setEchoChar((char) 0);

        styleInputField(
                field,
                placeholder
        );

        return field;
    }

    // =========================================================
    // INPUT STYLE
    // =========================================================

    private void styleInputField(
            JTextField field,
            String placeholder
    ) {

        field.setOpaque(true);

        field.setBackground(
                INPUT_BG
        );

        field.setForeground(
                TEXT_MUTED
        );

        field.setCaretColor(
                GOLD
        );

        field.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
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
                                13,
                                9,
                                13
                        )
                )
        );

        field.addFocusListener(
                new FocusAdapter() {

                    @Override
                    public void focusGained(
                            FocusEvent e
                    ) {

                        boolean placeholderActive =
                                isPlaceholderShowing(
                                        field,
                                        placeholder
                                );

                        if (placeholderActive) {

                            field.setText("");

                            field.setForeground(
                                    TEXT_WHITE
                            );

                            if (field instanceof JPasswordField) {

                                ((JPasswordField) field)
                                        .setEchoChar('•');
                            }
                        }

                        field.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                SKY_BLUE,
                                                1,
                                                true
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                9,
                                                13,
                                                9,
                                                13
                                        )
                                )
                        );
                    }

                    @Override
                    public void focusLost(
                            FocusEvent e
                    ) {

                        boolean empty =
                                (field instanceof JPasswordField)
                                        ? ((JPasswordField) field)
                                        .getPassword()
                                        .length == 0
                                        : field.getText()
                                        .trim()
                                        .isEmpty();

                        if (empty) {

                            field.setForeground(
                                    TEXT_MUTED
                            );

                            field.setText(
                                    placeholder
                            );

                            if (field instanceof JPasswordField) {

                                ((JPasswordField) field)
                                        .setEchoChar((char) 0);
                            }
                        }

                        field.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                INPUT_BORDER,
                                                1,
                                                true
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                9,
                                                13,
                                                9,
                                                13
                                        )
                                )
                        );
                    }
                }
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        43
                )
        );
    }

    // =========================================================
    // PLACEHOLDER CHECK
    // =========================================================

    private boolean isPlaceholderShowing(
            JTextField field,
            String placeholder
    ) {

        if (field instanceof JPasswordField) {

            JPasswordField passwordField =
                    (JPasswordField) field;

            return passwordField.getEchoChar()
                    == (char) 0
                    &&
                    new String(
                            passwordField.getPassword()
                    ).equals(placeholder);
        }

        return field.getText()
                .equals(placeholder);
    }

    // =========================================================
    // LOGIN BUTTON
    // =========================================================

    private JButton createLoginButton(
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

                        g2d.setPaint(gradient);

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
                        44
                )
        );

        button.setPreferredSize(
                new Dimension(
                        300,
                        44
                )
        );

        return button;
    }

    // =========================================================
    // REGISTER BUTTON
    // =========================================================

    private JButton createRegisterButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
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
    // LOGIN HANDLER
    // =========================================================

    private void handleLogin() {

        try {

            login();

        } catch (NotFoundException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "User or account record not found.",
                    "Account Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "An unexpected error occurred: "
                            + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // LOGIN
    // =========================================================

    private void login()
            throws NotFoundException {

        boolean isEmailPlaceholder =
                isPlaceholderShowing(
                        emailField,
                        "👤  username / email"
                );

        boolean isPassPlaceholder =
                isPlaceholderShowing(
                        passwordField,
                        "🔒  password"
                );

        String email =
                isEmailPlaceholder
                        ? ""
                        : emailField
                        .getText()
                        .trim();

        String password =
                isPassPlaceholder
                        ? ""
                        : new String(
                        passwordField
                                .getPassword()
                );

        // -----------------------------------------------------
        // VALIDATION
        // -----------------------------------------------------

        if (email.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both email/username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // -----------------------------------------------------
        // BACKEND LOGIN
        // -----------------------------------------------------

        User loginUser =
                new User(
                        email,
                        password
                );

        String result =
                loginUser.login(loginUser);

        // -----------------------------------------------------
        // SUCCESS
        // -----------------------------------------------------

        if ("User login successful."
                .equals(result)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login Successful!\nWelcome "
                            + loginUser.getUser_name(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (mainFrame != null) {

                mainFrame.loadDashboard(
                        loginUser
                );
            }

        }

        // -----------------------------------------------------
        // FAILED
        // -----------------------------------------------------

        else {

            JOptionPane.showMessageDialog(
                    this,
                    result,
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
