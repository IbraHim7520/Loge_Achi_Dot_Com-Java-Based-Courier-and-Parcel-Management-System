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
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class LoginFrame extends JPanel {

    private JTextField emailField;
    private JPasswordField passwordField;
    private final MainFrame mainFrame;

    // =========================================================
    // REFERENCE UI THEME
    // =========================================================

    private static final Color BG = new Color(240, 248, 253);
    private static final Color BG_LIGHT = new Color(248, 251, 253);

    private static final Color WHITE = Color.WHITE;
    private static final Color BLACK = new Color(15, 18, 20);

    private static final Color TEXT_DARK = new Color(29, 35, 40);
    private static final Color TEXT_MUTED = new Color(105, 118, 128);

    private static final Color ORANGE = new Color(248, 116, 35);
    private static final Color ORANGE_HOVER = new Color(234, 92, 20);

    private static final Color LIGHT_BLUE = new Color(220, 238, 249);
    private static final Color SKY_BLUE = new Color(188, 221, 241);

    private static final Color BORDER = new Color(220, 228, 234);
    private static final Color INPUT_BG = new Color(249, 251, 252);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public LoginFrame(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(
                new GridBagLayout()
        );

        setOpaque(false);

        buildUI();
    }

    // =========================================================
    // BUILD UI
    // =========================================================

    private void buildUI() {

        JPanel mainCard =
                new JPanel(
                        new GridLayout(
                                1,
                                2
                        )
                ) {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        g2.setColor(
                                WHITE
                        );

                        g2.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        28,
                                        28
                                )
                        );

                        g2.setColor(
                                BORDER
                        );

                        g2.setStroke(
                                new BasicStroke(1f)
                        );

                        g2.draw(
                                new RoundRectangle2D.Float(
                                        0.5f,
                                        0.5f,
                                        getWidth() - 1,
                                        getHeight() - 1,
                                        28,
                                        28
                                )
                        );

                        g2.dispose();
                    }
                };

        mainCard.setOpaque(false);

        mainCard.setPreferredSize(
                new Dimension(
                        880,
                        530
                )
        );

        mainCard.setMaximumSize(
                new Dimension(
                        1100,
                        620
                )
        );

        // =====================================================
        // LEFT VISUAL PANEL
        // =====================================================

        JPanel visualPanel =
                createVisualPanel();

        mainCard.add(
                visualPanel
        );

        // =====================================================
        // RIGHT LOGIN PANEL
        // =====================================================

        JPanel formPanel =
                createFormPanel();

        mainCard.add(
                formPanel
        );

        add(
                mainCard
        );
    }

    // =========================================================
    // BACKGROUND
    // =========================================================

    @Override
    protected void paintComponent(
            Graphics g
    ) {

        super.paintComponent(g);

        Graphics2D g2 =
                (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Main background
        g2.setColor(
                BG
        );

        g2.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        // Top-left soft glow
        g2.setColor(
                new Color(
                        198,
                        226,
                        243,
                        90
                )
        );

        g2.fill(
                new Ellipse2D.Float(
                        -140,
                        -120,
                        360,
                        360
                )
        );

        // Bottom-right soft glow
        g2.setColor(
                new Color(
                        183,
                        219,
                        240,
                        75
                )
        );

        g2.fill(
                new Ellipse2D.Float(
                        getWidth() - 260,
                        getHeight() - 220,
                        400,
                        400
                )
        );

        // Small orange dot
        g2.setColor(
                new Color(
                        ORANGE.getRed(),
                        ORANGE.getGreen(),
                        ORANGE.getBlue(),
                        90
                )
        );

        g2.fillOval(
                55,
                90,
                7,
                7
        );

        g2.dispose();
    }

    // =========================================================
    // LEFT VISUAL SECTION
    // =========================================================

    private JPanel createVisualPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                ) {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        // =================================================
                        // LIGHT BLUE VISUAL BACKGROUND
                        // =================================================

                        g2.setColor(
                                new Color(
                                        225,
                                        240,
                                        249
                                )
                        );

                        g2.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        28,
                                        28
                                )
                        );

                        // =================================================
                        // BLUE GLOW
                        // =================================================

                        g2.setColor(
                                new Color(
                                        123,
                                        190,
                                        226,
                                        70
                                )
                        );

                        g2.fill(
                                new Ellipse2D.Float(
                                        -90,
                                        getHeight() - 180,
                                        350,
                                        350
                                )
                        );

                        // =================================================
                        // DECORATIVE WHITE CIRCLES
                        // =================================================

                        g2.setColor(
                                new Color(
                                        255,
                                        255,
                                        255,
                                        120
                                )
                        );

                        g2.fillOval(
                                45,
                                55,
                                90,
                                90
                        );

                        g2.fillOval(
                                getWidth() - 130,
                                55,
                                65,
                                65
                        );

                        // =================================================
                        // ROUTE LINE
                        // =================================================

                        g2.setColor(
                                new Color(
                                        0,
                                        97,
                                        153,
                                        55
                                )
                        );

                        g2.setStroke(
                                new BasicStroke(
                                        2f,
                                        BasicStroke.CAP_ROUND,
                                        BasicStroke.JOIN_ROUND
                                )
                        );

                        int routeY =
                                getHeight() - 70;

                        for (
                                int x = 40;
                                x < getWidth() - 40;
                                x += 28
                        ) {

                            g2.drawLine(
                                    x,
                                    routeY,
                                    x + 14,
                                    routeY
                            );
                        }

                        // Route points
                        g2.setColor(
                                ORANGE
                        );

                        g2.fillOval(
                                38,
                                routeY - 5,
                                10,
                                10
                        );

                        g2.fillOval(
                                getWidth() - 48,
                                routeY - 5,
                                10,
                                10
                        );

                        g2.dispose();
                    }
                };

        panel.setOpaque(false);

        panel.setBorder(
                new EmptyBorder(
                        30,
                        34,
                        30,
                        34
                )
        );

        // =====================================================
        // BRAND
        // =====================================================

        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setOpaque(false);

        JLabel brand =
                new JLabel(
                        "LOGE ACHI DOT COM"
                );

        brand.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        brand.setForeground(
                BLACK
        );

        JLabel service =
                new JLabel(
                        "COURIER SERVICE"
                );

        service.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        service.setForeground(
                ORANGE
        );

        top.add(
                brand,
                BorderLayout.WEST
        );

        top.add(
                service,
                BorderLayout.EAST
        );

        panel.add(
                top,
                BorderLayout.NORTH
        );

        // =====================================================
        // CENTER VISUAL
        // =====================================================

        JPanel visual =
                new JPanel()
                {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D)
                                        g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        int w =
                                getWidth();

                        int h =
                                getHeight();

                        // =================================================
                        // ORANGE SMALL LABEL
                        // =================================================

                        g2.setColor(
                                ORANGE
                        );

                        g2.setFont(
                                new Font(
                                        "SansSerif",
                                        Font.BOLD,
                                        10
                                )
                        );

                        g2.drawString(
                                "#1 COURIER EXPERIENCE",
                                0,
                                25
                        );

                        // =================================================
                        // HEADING
                        // =================================================

                        g2.setColor(
                                BLACK
                        );

                        g2.setFont(
                                new Font(
                                        "SansSerif",
                                        Font.BOLD,
                                        32
                                )
                        );

                        g2.drawString(
                                "MOVE.",
                                0,
                                70
                        );

                        g2.drawString(
                                "TRACK.",
                                0,
                                108
                        );

                        g2.drawString(
                                "DELIVER.",
                                0,
                                146
                        );

                        // =================================================
                        // DESCRIPTION
                        // =================================================

                        g2.setColor(
                                TEXT_MUTED
                        );

                        g2.setFont(
                                new Font(
                                        "SansSerif",
                                        Font.PLAIN,
                                        12
                                )
                        );

                        g2.drawString(
                                "Simple parcel management",
                                0,
                                181
                        );

                        g2.drawString(
                                "from pickup to delivery.",
                                0,
                                199
                        );

                        // =================================================
                        // PACKAGE ILLUSTRATION
                        // =================================================

                        int boxX =
                                w - 145;

                        int boxY =
                                h / 2 - 45;

                        // Shadow
                        g2.setColor(
                                new Color(
                                        0,
                                        0,
                                        0,
                                        18
                                )
                        );

                        g2.fillOval(
                                boxX - 10,
                                boxY + 100,
                                145,
                                25
                        );

                        // Main package
                        g2.setColor(
                                new Color(
                                        246,
                                        179,
                                        72
                                )
                        );

                        g2.fillRoundRect(
                                boxX,
                                boxY,
                                120,
                                95,
                                10,
                                10
                        );

                        // Package left side
                        g2.setColor(
                                new Color(
                                        218,
                                        143,
                                        48
                                )
                        );

                        Polygon side =
                                new Polygon();

                        side.addPoint(
                                boxX,
                                boxY
                        );

                        side.addPoint(
                                boxX - 22,
                                boxY + 13
                        );

                        side.addPoint(
                                boxX - 22,
                                boxY + 88
                        );

                        side.addPoint(
                                boxX,
                                boxY + 95
                        );

                        g2.fill(
                                side
                        );

                        // Package top
                        g2.setColor(
                                new Color(
                                        255,
                                        204,
                                        108
                                )
                        );

                        Polygon topBox =
                                new Polygon();

                        topBox.addPoint(
                                boxX - 22,
                                boxY + 13
                        );

                        topBox.addPoint(
                                boxX + 35,
                                boxY - 14
                        );

                        topBox.addPoint(
                                boxX + 142,
                                boxY + 13
                        );

                        topBox.addPoint(
                                boxX + 120,
                                boxY + 26
                        );

                        topBox.addPoint(
                                boxX,
                                boxY + 26
                        );

                        g2.fill(
                                topBox
                        );

                        // =================================================
                        // ORANGE TAPE
                        // =================================================

                        g2.setColor(
                                ORANGE
                        );

                        g2.fillRect(
                                boxX + 48,
                                boxY - 5,
                                24,
                                102
                        );

                        // =================================================
                        // PACKAGE LABEL
                        // =================================================

                        g2.setColor(
                                WHITE
                        );

                        g2.fillRoundRect(
                                boxX + 28,
                                boxY + 36,
                                65,
                                29,
                                5,
                                5
                        );

                        g2.setColor(
                                BLACK
                        );

                        g2.setFont(
                                new Font(
                                        "SansSerif",
                                        Font.BOLD,
                                        8
                                )
                        );

                        g2.drawString(
                                "SHIP",
                                boxX + 47,
                                boxY + 54
                        );

                        // =================================================
                        // SMALL ORANGE CIRCLE
                        // =================================================

                        g2.setColor(
                                ORANGE
                        );

                        g2.fillOval(
                                boxX - 58,
                                boxY + 42,
                                35,
                                35
                        );

                        g2.setColor(
                                WHITE
                        );

                        g2.setFont(
                                new Font(
                                        "SansSerif",
                                        Font.BOLD,
                                        15
                                )
                        );

                        g2.drawString(
                                "→",
                                boxX - 49,
                                boxY + 66
                        );

                        g2.dispose();
                    }
                };

        visual.setOpaque(false);

        panel.add(
                visual,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM TEXT
        // =====================================================

        JLabel footer =
                new JLabel(
                        "Fast  •  Simple  •  Reliable"
                );

        footer.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        footer.setForeground(
                TEXT_DARK
        );

        footer.setBorder(
                new EmptyBorder(
                        5,
                        0,
                        5,
                        0
                )
        );

        panel.add(
                footer,
                BorderLayout.SOUTH
        );

        return panel;
    }

    // =========================================================
    // LOGIN FORM PANEL
    // =========================================================

    private JPanel createFormPanel() {

        JPanel wrapper =
                new JPanel(
                        new GridBagLayout()
                );

        wrapper.setBackground(
                WHITE
        );

        wrapper.setBorder(
                new EmptyBorder(
                        35,
                        50,
                        35,
                        50
                )
        );

        JPanel form =
                new JPanel();

        form.setOpaque(false);

        form.setLayout(
                new BoxLayout(
                        form,
                        BoxLayout.Y_AXIS
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
                new JLabel(
                        "Welcome Back"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        29
                )
        );

        title.setForeground(
                BLACK
        );

        title.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // =====================================================
        // SUBTITLE
        // =====================================================

        JLabel subtitle =
                new JLabel(
                        "Sign in to continue to your account"
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        subtitle.setForeground(
                TEXT_MUTED
        );

        subtitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        emailField =
                createTextField(
                        "👤  username / email"
                );



        passwordField =
                createPasswordField(
                        "🔒  password"
                );

        // =====================================================
        // REMEMBER / HELP AREA
        // =====================================================

        JPanel helperPanel =
                new JPanel(
                        new BorderLayout()
                );

        helperPanel.setOpaque(false);

        JLabel secureLabel =
                new JLabel(
                        "● Secure account access"
                );

        secureLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        secureLabel.setForeground(
                new Color(
                        114,
                        131,
                        142
                )
        );

        JLabel requiredLabel =
                new JLabel(
                        "Required fields"
                );

        requiredLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        requiredLabel.setForeground(
                TEXT_MUTED
        );

        helperPanel.add(
                secureLabel,
                BorderLayout.WEST
        );

        helperPanel.add(
                requiredLabel,
                BorderLayout.EAST
        );

        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        JButton loginButton =
                createLoginButton(
                        "Sign In  →"
                );

        loginButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // =====================================================
        // REGISTER
        // =====================================================

        JButton registerButton =
                createRegisterButton(
                        "Don't have an account? Create one"
                );

        registerButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // ADD EVERYTHING
        // =====================================================

        form.add(
                title
        );

        form.add(
                Box.createVerticalStrut(7)
        );

        form.add(
                subtitle
        );

        form.add(
                Box.createVerticalStrut(30)
        );



        form.add(
                Box.createVerticalStrut(7)
        );

        form.add(
                emailField
        );

        form.add(
                Box.createVerticalStrut(17)
        );



        form.add(
                Box.createVerticalStrut(7)
        );

        form.add(
                passwordField
        );

        form.add(
                Box.createVerticalStrut(10)
        );

        form.add(
                helperPanel
        );

        form.add(
                Box.createVerticalStrut(23)
        );

        form.add(
                loginButton
        );

        form.add(
                Box.createVerticalStrut(16)
        );

        form.add(
                registerButton
        );

        wrapper.add(
                form
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

        registerButton.addActionListener(
                e -> {

                    if (mainFrame != null) {

                        mainFrame.showRegisterFrame();
                    }
                }
        );

        return wrapper;
    }

    // =========================================================
    // FIELD LABEL
    // =========================================================

    private JLabel createFieldLabel(
            String text
    ) {

        JLabel label =
                new JLabel(
                        text
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
                        69,
                        79,
                        87
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

    private JTextField createTextField(
            String placeholder
    ) {

        JTextField field =
                new JTextField(
                        placeholder
                );

        styleInputField(
                field,
                placeholder
        );

        return field;
    }

    // =========================================================
    // PASSWORD FIELD
    // =========================================================

    private JPasswordField createPasswordField(
            String placeholder
    ) {

        JPasswordField field =
                new JPasswordField(
                        placeholder
                );

        field.setEchoChar(
                (char) 0
        );

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
                ORANGE
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
                                new Color(
                                        202,
                                        212,
                                        219
                                ),
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

        field.setPreferredSize(
                new Dimension(
                        320,
                        44
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        44
                )
        );

        field.addFocusListener(
                new FocusAdapter() {

                    @Override
                    public void focusGained(
                            FocusEvent e
                    ) {

                        if (
                                isPlaceholderShowing(
                                        field,
                                        placeholder
                                )
                        ) {

                            field.setText("");

                            field.setForeground(
                                    TEXT_DARK
                            );

                            if (
                                    field
                                            instanceof
                                            JPasswordField
                            ) {

                                (
                                        (JPasswordField)
                                                field
                                ).setEchoChar(
                                        '•'
                                );
                            }
                        }

                        field.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                ORANGE,
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

                        boolean empty;

                        if (
                                field
                                        instanceof
                                        JPasswordField
                        ) {

                            empty =
                                    (
                                            (
                                                    JPasswordField)
                                                    field
                                    ).getPassword()
                                            .length
                                            == 0;

                        } else {

                            empty =
                                    field.getText()
                                            .trim()
                                            .isEmpty();
                        }

                        if (empty) {

                            field.setText(
                                    placeholder
                            );

                            field.setForeground(
                                    TEXT_MUTED
                            );

                            if (
                                    field
                                            instanceof
                                            JPasswordField
                            ) {

                                (
                                        (JPasswordField)
                                                field
                                ).setEchoChar(
                                        (char) 0
                                );
                            }
                        }

                        field.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                new Color(
                                                        202,
                                                        212,
                                                        219
                                                ),
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
    }

    // =========================================================
    // PLACEHOLDER CHECK
    // =========================================================

    private boolean isPlaceholderShowing(
            JTextField field,
            String placeholder
    ) {

        if (
                field
                        instanceof
                        JPasswordField
        ) {

            JPasswordField passwordField =
                    (JPasswordField)
                            field;

            return passwordField
                    .getEchoChar()
                    == (char) 0
                    &&
                    new String(
                            passwordField
                                    .getPassword()
                    ).equals(
                            placeholder
                    );
        }

        return field.getText()
                .equals(
                        placeholder
                );
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

                        Graphics2D g2 =
                                (Graphics2D)
                                        g.create();

                        g2.setRenderingHint(
                                RenderingHints
                                        .KEY_ANTIALIASING,
                                RenderingHints
                                        .VALUE_ANTIALIAS_ON
                        );

                        Color top;
                        Color bottom;

                        if (
                                getModel()
                                        .isRollover()
                        ) {

                            top =
                                    new Color(
                                            255,
                                            153,
                                            70
                                    );

                            bottom =
                                    ORANGE_HOVER;

                        } else {

                            top =
                                    new Color(
                                            255,
                                            137,
                                            48
                                    );

                            bottom =
                                    ORANGE;
                        }

                        GradientPaint gradient =
                                new GradientPaint(
                                        0,
                                        0,
                                        top,
                                        getWidth(),
                                        0,
                                        bottom
                                );

                        g2.setPaint(
                                gradient
                        );

                        g2.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        22,
                                        22
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
                        14
                )
        );

        button.setForeground(
                WHITE
        );

        button.setContentAreaFilled(
                false
        );

        button.setOpaque(false);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(
                        320,
                        45
                )
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
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
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        button.setForeground(
                ORANGE
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

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setForeground(
                                ORANGE_HOVER
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
                                ORANGE
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

        // =====================================================
        // VALIDATION
        // =====================================================

        if (
                email.isEmpty()
                        || password.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter both email/username and password.",
                    "Login Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =====================================================
        // BACKEND LOGIN
        // =====================================================

        User loginUser =
                new User(
                        email,
                        password
                );

        String result =
                loginUser.login(
                        loginUser
                );

        // =====================================================
        // SUCCESS
        // =====================================================

        if (
                "User login successful."
                        .equals(result)
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login Successful!\nWelcome "
                            + loginUser.getUser_name(),
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (
                    mainFrame != null
            ) {

                mainFrame.loadDashboard(
                        loginUser
                );
            }

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    result,
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
