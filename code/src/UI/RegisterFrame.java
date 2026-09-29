package UI;

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
import java.util.Arrays;

public class RegisterFrame extends JPanel {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    private final MainFrame mainFrame;

    // =========================================================
    // REFERENCE THEME
    // =========================================================

    private static final Color BG =
            new Color(240, 248, 253);

    private static final Color BG_LIGHT =
            new Color(248, 251, 253);

    private static final Color WHITE =
            Color.WHITE;

    private static final Color BLACK =
            new Color(15, 18, 20);

    private static final Color TEXT_DARK =
            new Color(30, 37, 42);

    private static final Color TEXT_MUTED =
            new Color(105, 118, 128);

    private static final Color ORANGE =
            new Color(248, 116, 35);

    private static final Color ORANGE_HOVER =
            new Color(234, 92, 20);

    private static final Color LIGHT_BLUE =
            new Color(224, 240, 249);

    private static final Color BLUE =
            new Color(65, 155, 202);

    private static final Color SKY_BLUE =
            new Color(181, 219, 241);

    private static final Color BORDER =
            new Color(216, 227, 234);

    private static final Color INPUT_BG =
            new Color(249, 251, 252);

    private static final Color INPUT_BORDER =
            new Color(199, 212, 221);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public RegisterFrame(MainFrame mainFrame) {

        this.mainFrame = mainFrame;

        setLayout(
                new GridBagLayout()
        );

        setOpaque(false);

        buildUI();
    }

    // =========================================================
    // MAIN UI
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
                        900,
                        560
                )
        );

        mainCard.setMaximumSize(
                new Dimension(
                        1120,
                        680
                )
        );

        // =====================================================
        // LEFT VISUAL AREA
        // =====================================================

        JPanel visualPanel =
                createVisualPanel();

        mainCard.add(
                visualPanel
        );

        // =====================================================
        // RIGHT FORM AREA
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

        // Top left glow
        g2.setColor(
                new Color(
                        194,
                        225,
                        243,
                        100
                )
        );

        g2.fill(
                new Ellipse2D.Float(
                        -150,
                        -110,
                        380,
                        380
                )
        );

        // Bottom right glow
        g2.setColor(
                new Color(
                        183,
                        220,
                        240,
                        75
                )
        );

        g2.fill(
                new Ellipse2D.Float(
                        getWidth() - 300,
                        getHeight() - 250,
                        450,
                        450
                )
        );

        // Decorative orange dot
        g2.setColor(
                new Color(
                        ORANGE.getRed(),
                        ORANGE.getGreen(),
                        ORANGE.getBlue(),
                        95
                )
        );

        g2.fillOval(
                60,
                100,
                7,
                7
        );

        g2.dispose();
    }

    // =========================================================
    // LEFT VISUAL PANEL
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

                        // Light blue background
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

                        // Soft blue glow
                        g2.setColor(
                                new Color(
                                        118,
                                        188,
                                        226,
                                        60
                                )
                        );

                        g2.fill(
                                new Ellipse2D.Float(
                                        -100,
                                        getHeight() - 200,
                                        380,
                                        380
                                )
                        );

                        // White floating circle
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

                        // Another circle
                        g2.fillOval(
                                getWidth() - 115,
                                65,
                                60,
                                60
                        );

                        // Route line
                        g2.setColor(
                                new Color(
                                        0,
                                        97,
                                        153,
                                        50
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
                                int x = 35;
                                x < getWidth() - 35;
                                x += 26
                        ) {

                            g2.drawLine(
                                    x,
                                    routeY,
                                    x + 13,
                                    routeY
                            );
                        }

                        // Route endpoints
                        g2.setColor(
                                ORANGE
                        );

                        g2.fillOval(
                                32,
                                routeY - 5,
                                10,
                                10
                        );

                        g2.fillOval(
                                getWidth() - 43,
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
                        28,
                        34,
                        26,
                        34
                )
        );

        // =====================================================
        // TOP BRAND
        // =====================================================

        JPanel topPanel =
                new JPanel(
                        new BorderLayout()
                );

        topPanel.setOpaque(false);

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

        topPanel.add(
                brand,
                BorderLayout.WEST
        );

        topPanel.add(
                service,
                BorderLayout.EAST
        );

        panel.add(
                topPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // CENTER VISUAL
        // =====================================================

        JPanel center =
                new JPanel(
                        new BorderLayout()
                );

        center.setOpaque(false);

        center.add(
                createVisualContent(),
                BorderLayout.CENTER
        );

        panel.add(
                center,
                BorderLayout.CENTER
        );

        // =====================================================
        // FOOTER
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
                        4,
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
    // VISUAL CONTENT
    // =========================================================

    private JPanel createVisualContent() {

        JPanel content =
                new JPanel();

        content.setOpaque(false);

        content.setLayout(
                new BoxLayout(
                        content,
                        BoxLayout.Y_AXIS
                )
        );

        content.setBorder(
                new EmptyBorder(
                        36,
                        0,
                        20,
                        0
                )
        );

        // =====================================================
        // SMALL LABEL
        // =====================================================

        JLabel small =
                new JLabel(
                        "# SIMPLE COURIER MANAGEMENT"
                );

        small.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        small.setForeground(
                TEXT_DARK
        );

        small.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // =====================================================
        // BIG TITLE
        // =====================================================

        JLabel title =
                new JLabel(
                        "<html>" +
                                "CREATE.<br>" +
                                "SHIP.<br>" +
                                "TRACK." +
                                "</html>"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        36
                )
        );

        title.setForeground(
                BLACK
        );

        title.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        JLabel description =
                new JLabel(
                        "<html>" +
                                "<div style='width:310px'>" +
                                "Create your account and start managing " +
                                "parcel deliveries from one simple platform." +
                                "</div>" +
                                "</html>"
                );

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        description.setForeground(
                TEXT_MUTED
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // =====================================================
        // TRACKING READY CARD
        // =====================================================

        JPanel trackingCard =
                createTrackingReadyCard();

        trackingCard.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // =====================================================
        // PACKAGE
        // =====================================================

        JPanel packagePanel =
                createPackageIllustration();

        packagePanel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        content.add(
                small
        );

        content.add(
                Box.createVerticalStrut(9)
        );

        content.add(
                title
        );

        content.add(
                Box.createVerticalStrut(13)
        );

        content.add(
                description
        );

        content.add(
                Box.createVerticalStrut(20)
        );

        content.add(
                trackingCard
        );

        content.add(
                Box.createVerticalStrut(14)
        );

        content.add(
                packagePanel
        );

        return content;
    }

    // =========================================================
    // TRACKING READY CARD
    // =========================================================

    private JPanel createTrackingReadyCard() {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                12,
                                0
                        )
                );

        card.setOpaque(true);

        card.setBackground(
                WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                10,
                                12,
                                10,
                                12
                        )
                )
        );

        card.setMaximumSize(
                new Dimension(
                        400,
                        70
                )
        );

        // =====================================================
        // STATUS ICON
        // =====================================================

        JPanel icon =
                new JPanel(
                        new GridBagLayout()
                );

        icon.setOpaque(true);

        icon.setBackground(
                new Color(
                        255,
                        243,
                        235
                )
        );

        icon.setPreferredSize(
                new Dimension(
                        42,
                        42
                )
        );

        JLabel iconText =
                new JLabel(
                        "●"
                );

        iconText.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        iconText.setForeground(
                ORANGE
        );

        icon.add(
                iconText
        );

        card.add(
                icon,
                BorderLayout.WEST
        );

        // =====================================================
        // TEXT
        // =====================================================

        JPanel textPanel =
                new JPanel();

        textPanel.setOpaque(false);

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel heading =
                new JLabel(
                        "LIVE TRACKING"
                );

        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        heading.setForeground(
                TEXT_DARK
        );

        JLabel sub =
                new JLabel(
                        "Track parcel status anytime"
                );

        sub.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        sub.setForeground(
                TEXT_MUTED
        );

        textPanel.add(
                heading
        );

        textPanel.add(
                Box.createVerticalStrut(4)
        );

        textPanel.add(
                sub
        );

        card.add(
                textPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // READY BADGE
        // =====================================================

        JLabel ready =
                new JLabel(
                        "READY"
                );

        ready.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        ready.setForeground(
                WHITE
        );

        ready.setOpaque(true);

        ready.setBackground(
                ORANGE
        );

        ready.setBorder(
                new EmptyBorder(
                        6,
                        8,
                        6,
                        8
                )
        );

        card.add(
                ready,
                BorderLayout.EAST
        );

        return card;
    }

    // =========================================================
    // PACKAGE ILLUSTRATION
    // =========================================================

    private JPanel createPackageIllustration() {

        JPanel wrapper =
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

                        int w =
                                getWidth();

                        int h =
                                getHeight();

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
                                w - 175,
                                h - 27,
                                145,
                                20
                        );

                        // =================================================
                        // PACKAGE
                        // =================================================

                        int boxX =
                                w - 145;

                        int boxY =
                                18;

                        // Main box
                        g2.setColor(
                                new Color(
                                        244,
                                        181,
                                        78
                                )
                        );

                        g2.fillRoundRect(
                                boxX,
                                boxY,
                                105,
                                78,
                                9,
                                9
                        );

                        // Side
                        g2.setColor(
                                new Color(
                                        216,
                                        143,
                                        51
                                )
                        );

                        Polygon side =
                                new Polygon();

                        side.addPoint(
                                boxX,
                                boxY
                        );

                        side.addPoint(
                                boxX - 18,
                                boxY + 10
                        );

                        side.addPoint(
                                boxX - 18,
                                boxY + 72
                        );

                        side.addPoint(
                                boxX,
                                boxY + 78
                        );

                        g2.fill(
                                side
                        );

                        // Top
                        g2.setColor(
                                new Color(
                                        255,
                                        207,
                                        113
                                )
                        );

                        Polygon top =
                                new Polygon();

                        top.addPoint(
                                boxX - 18,
                                boxY + 10
                        );

                        top.addPoint(
                                boxX + 30,
                                boxY - 14
                        );

                        top.addPoint(
                                boxX + 123,
                                boxY + 10
                        );

                        top.addPoint(
                                boxX + 105,
                                boxY + 20
                        );

                        top.addPoint(
                                boxX,
                                boxY + 20
                        );

                        g2.fill(
                                top
                        );

                        // Tape
                        g2.setColor(
                                ORANGE
                        );

                        g2.fillRect(
                                boxX + 42,
                                boxY - 4,
                                20,
                                85
                        );

                        // Label
                        g2.setColor(
                                WHITE
                        );

                        g2.fillRoundRect(
                                boxX + 25,
                                boxY + 29,
                                57,
                                28,
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
                                "PARCEL",
                                boxX + 35,
                                boxY + 47
                        );

                        g2.dispose();
                    }
                };

        wrapper.setOpaque(false);

        wrapper.setPreferredSize(
                new Dimension(
                        360,
                        120
                )
        );

        wrapper.setMaximumSize(
                new Dimension(
                        420,
                        120
                )
        );

        return wrapper;
    }

    // =========================================================
    // FORM PANEL
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
                        32,
                        48,
                        32,
                        48
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
                        "Create Your Account"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
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
                        "Register to start managing your deliveries"
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

        // =====================================================
        // NAME
        // =====================================================

        JLabel nameLabel =
                createFieldLabel(
                        "FULL NAME *"
                );

        nameField =
                createInputField(
                        "Enter your full name"
                );

        // =====================================================
        // EMAIL
        // =====================================================

        JLabel emailLabel =
                createFieldLabel(
                        "EMAIL ADDRESS *"
                );

        emailField =
                createInputField(
                        "Enter your email address"
                );

        // =====================================================
        // PASSWORD
        // =====================================================

        JLabel passwordLabel =
                createFieldLabel(
                        "PASSWORD *"
                );

        passwordField =
                createPasswordField(
                        "Enter password"
                );

        // =====================================================
        // CONFIRM PASSWORD
        // =====================================================

        JLabel confirmLabel =
                createFieldLabel(
                        "CONFIRM PASSWORD *"
                );

        confirmPasswordField =
                createPasswordField(
                        "Confirm your password"
                );

        // =====================================================
        // REGISTER BUTTON
        // =====================================================

        JButton registerButton =
                createPrimaryButton(
                        "Create Account  →"
                );

        // =====================================================
        // BACK LINK
        // =====================================================

        JButton backButton =
                createLinkButton(
                        "Already have an account? Sign in"
                );

        // =====================================================
        // SECURITY TEXT
        // =====================================================

        JLabel secure =
                new JLabel(
                        "●  Your account information is securely handled"
                );

        secure.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        secure.setForeground(
                new Color(
                        120,
                        133,
                        142
                )
        );

        secure.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // ADD COMPONENTS
        // =====================================================

        form.add(
                title
        );

        form.add(
                Box.createVerticalStrut(6)
        );

        form.add(
                subtitle
        );

        form.add(
                Box.createVerticalStrut(25)
        );

        form.add(
                nameLabel
        );

        form.add(
                Box.createVerticalStrut(6)
        );

        form.add(
                nameField
        );

        form.add(
                Box.createVerticalStrut(12)
        );

        form.add(
                emailLabel
        );

        form.add(
                Box.createVerticalStrut(6)
        );

        form.add(
                emailField
        );

        form.add(
                Box.createVerticalStrut(12)
        );

        form.add(
                passwordLabel
        );

        form.add(
                Box.createVerticalStrut(6)
        );

        form.add(
                passwordField
        );

        form.add(
                Box.createVerticalStrut(12)
        );

        form.add(
                confirmLabel
        );

        form.add(
                Box.createVerticalStrut(6)
        );

        form.add(
                confirmPasswordField
        );

        form.add(
                Box.createVerticalStrut(18)
        );

        form.add(
                registerButton
        );

        form.add(
                Box.createVerticalStrut(11)
        );

        form.add(
                backButton
        );

        form.add(
                Box.createVerticalStrut(14)
        );

        form.add(
                secure
        );

        wrapper.add(
                form
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

        backButton.addActionListener(
                e -> {

                    if (mainFrame != null) {

                        mainFrame.showLoginFrame();
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
                        9
                )
        );

        label.setForeground(
                new Color(
                        70,
                        80,
                        88
                )
        );

        label.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        return label;
    }

    // =========================================================
    // TEXT INPUT
    // =========================================================

    private JTextField createInputField(
            String placeholder
    ) {

        JTextField field =
                new JTextField();

        styleInputField(
                field,
                placeholder
        );

        return field;
    }

    // =========================================================
    // PASSWORD INPUT
    // =========================================================

    private JPasswordField createPasswordField(
            String placeholder
    ) {

        JPasswordField field =
                new JPasswordField();

        field.putClientProperty(
                "placeholder",
                placeholder
        );

        field.setToolTipText(
                placeholder
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
                        12
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

        field.setPreferredSize(
                new Dimension(
                        330,
                        40
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        // =====================================================
        // INITIAL PLACEHOLDER
        // =====================================================

        field.setText(
                placeholder
        );

        if (
                field instanceof JPasswordField
        ) {

            (
                    (JPasswordField)
                            field
            ).setEchoChar(
                    (char) 0
            );
        }

        // =====================================================
        // FOCUS
        // =====================================================

        field.addFocusListener(
                new FocusAdapter() {

                    @Override
                    public void focusGained(
                            FocusEvent e
                    ) {

                        if (
                                isPlaceholder(
                                        field,
                                        placeholder
                                )
                        ) {

                            field.setText(
                                    ""
                            );

                            field.setForeground(
                                    TEXT_DARK
                            );

                            if (
                                    field instanceof
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
                                                12,
                                                9,
                                                12
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
                                field instanceof
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
                                    field instanceof
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

    // =========================================================
    // PLACEHOLDER CHECK
    // =========================================================

    private boolean isPlaceholder(
            JTextField field,
            String placeholder
    ) {

        if (
                field instanceof
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
    // PRIMARY BUTTON
    // =========================================================

    private JButton createPrimaryButton(
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
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        Color start;
                        Color end;

                        if (
                                getModel()
                                        .isRollover()
                        ) {

                            start =
                                    new Color(
                                            255,
                                            150,
                                            67
                                    );

                            end =
                                    ORANGE_HOVER;

                        } else {

                            start =
                                    new Color(
                                            255,
                                            139,
                                            50
                                    );

                            end =
                                    ORANGE;
                        }

                        GradientPaint gradient =
                                new GradientPaint(
                                        0,
                                        0,
                                        start,
                                        getWidth(),
                                        0,
                                        end
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
                        330,
                        44
                )
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        44
                )
        );

        return button;
    }

    // =========================================================
    // BACK LINK
    // =========================================================

    private JButton createLinkButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        button.setForeground(
                ORANGE
        );

        button.setContentAreaFilled(
                false
        );

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
    // REGISTER
    // =========================================================

    private void register() {

        String name;

        if (
                isPlaceholder(
                        nameField,
                        "Enter your full name"
                )
        ) {

            name = "";

        } else {

            name =
                    nameField
                            .getText()
                            .trim();
        }

        String email;

        if (
                isPlaceholder(
                        emailField,
                        "Enter your email address"
                )
        ) {

            email = "";

        } else {

            email =
                    emailField
                            .getText()
                            .trim();
        }

        char[] passChars =
                passwordField
                        .getPassword();

        char[] confirmPassChars =
                confirmPasswordField
                        .getPassword();

        // =====================================================
        // EMPTY FIELD VALIDATION
        // =====================================================

        if (
                name.isEmpty()
                        || email.isEmpty()
                        || isPlaceholder(
                        passwordField,
                        "Enter password"
                )
                        || isPlaceholder(
                        confirmPasswordField,
                        "Confirm your password"
                )
                        || passChars.length == 0
                        || confirmPassChars.length == 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Registration Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // =====================================================
        // PASSWORD MATCH
        // =====================================================

        if (
                !Arrays.equals(
                        passChars,
                        confirmPassChars
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE
            );

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

        // Clear arrays
        Arrays.fill(
                passChars,
                '0'
        );

        Arrays.fill(
                confirmPassChars,
                '0'
        );

        // =====================================================
        // BACKEND REGISTRATION
        // =====================================================

        User user =
                new User(
                        name,
                        email,
                        password
                );

        String result =
                user.register(
                        user
                );

        // =====================================================
        // SUCCESS
        // =====================================================

        if (
                result != null
                        && result.startsWith(
                        "User registered successfully."
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    result,
                    "Registration Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            if (
                    mainFrame != null
            ) {

                mainFrame.showLoginFrame();
            }

        } else {

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