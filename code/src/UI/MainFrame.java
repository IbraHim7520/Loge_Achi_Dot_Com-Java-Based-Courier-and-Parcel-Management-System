package UI;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Ellipse2D;

public class MainFrame extends JFrame {

    private final CardLayout mainCardLayout;
    private final JPanel mainContainer;

    private static final String VIEW_LANDING = "LANDING";
    private static final String VIEW_LOGIN = "LOGIN";
    private static final String VIEW_REGISTER = "REGISTER";
    private static final String VIEW_DASHBOARD = "DASHBOARD";

    private LoginFrame loginPanel;
    private RegisterFrame registerPanel;
    private DashboardLayout dashboardPanel;

    // =========================================================
    // REFERENCE UI COLORS
    // =========================================================

    private static final Color BG = new Color(239, 247, 253);
    private static final Color BG_LIGHT = new Color(247, 251, 255);

    private static final Color WHITE = Color.WHITE;
    private static final Color BLACK = new Color(13, 16, 19);

    private static final Color TEXT_DARK = new Color(22, 27, 31);
    private static final Color TEXT_MUTED = new Color(103, 116, 126);

    private static final Color ORANGE = new Color(248, 116, 35);
    private static final Color ORANGE_HOVER = new Color(235, 94, 20);

    private static final Color LIGHT_BLUE = new Color(220, 237, 249);
    private static final Color SKY_BLUE = new Color(196, 226, 244);

    private static final Color BORDER = new Color(220, 229, 236);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MainFrame() {

        setTitle(
                "Loge Achi Dot Com - Courier Management System"
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        // 75% of screen
        Dimension screen =
                Toolkit.getDefaultToolkit()
                        .getScreenSize();

        int width =
                (int) (screen.width * 0.75);

        int height =
                (int) (screen.height * 0.75);

        setSize(
                width,
                height
        );

        setMinimumSize(
                new Dimension(
                        950,
                        650
                )
        );

        setLocationRelativeTo(null);

        // =====================================================
        // MAIN CARD LAYOUT
        // =====================================================

        mainCardLayout =
                new CardLayout();

        mainContainer =
                new JPanel(
                        mainCardLayout
                );

        mainContainer.setBackground(
                BG
        );

        // Landing
        mainContainer.add(
                createLandingPagePanel(),
                VIEW_LANDING
        );

        add(
                mainContainer
        );
    }

    // =========================================================
    // LANDING PAGE
    // =========================================================

    private JPanel createLandingPagePanel() {

        JPanel root =
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

                        // Background
                        g2.setColor(BG);

                        g2.fillRect(
                                0,
                                0,
                                getWidth(),
                                getHeight()
                        );

                        // Top soft glow
                        g2.setColor(
                                new Color(
                                        210,
                                        232,
                                        247,
                                        130
                                )
                        );

                        g2.fill(
                                new Ellipse2D.Float(
                                        -120,
                                        -160,
                                        430,
                                        430
                                )
                        );

                        // Bottom blue glow
                        g2.setColor(
                                new Color(
                                        183,
                                        218,
                                        241,
                                        100
                                )
                        );

                        g2.fill(
                                new Ellipse2D.Float(
                                        getWidth() - 330,
                                        getHeight() - 280,
                                        500,
                                        500
                                )
                        );

                        g2.dispose();
                    }
                };

        root.setOpaque(false);

        // =====================================================
        // TOP NAVIGATION
        // =====================================================

        root.add(
                createTopNavigation(),
                BorderLayout.NORTH
        );

        // =====================================================
        // HERO
        // =====================================================

        root.add(
                createHeroSection(),
                BorderLayout.CENTER
        );

        return root;
    }

    // =========================================================
    // TOP NAVIGATION
    // =========================================================

    private JPanel createTopNavigation() {

        JPanel nav =
                new JPanel(
                        new BorderLayout()
                );

        nav.setOpaque(false);

        nav.setBorder(
                new EmptyBorder(
                        20,
                        28,
                        12,
                        28
                )
        );

        // =====================================================
        // LOGO
        // =====================================================

        JPanel logoPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                0
                        )
                );

        logoPanel.setOpaque(false);

        JPanel logoCircle =
                new JPanel(
                        new GridBagLayout()
                );

        logoCircle.setPreferredSize(
                new Dimension(
                        34,
                        34
                )
        );

        logoCircle.setBackground(
                BLACK
        );

        JLabel logoIcon =
                new JLabel("L");

        logoIcon.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        logoIcon.setForeground(
                WHITE
        );

        logoCircle.add(
                logoIcon
        );

        JLabel logoText =
                new JLabel(
                        "LOGE ACHI"
                );

        logoText.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        logoText.setForeground(
                BLACK
        );

        logoPanel.add(
                logoCircle
        );

        logoPanel.add(
                logoText
        );

        nav.add(
                logoPanel,
                BorderLayout.WEST
        );

        // =====================================================
        // NAV LINKS
        // =====================================================

        JPanel links =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                5,
                                0
                        )
                );

        links.setOpaque(false);

        links.add(
                createNavPill(
                        "TRACKING",
                        true
                )
        );

        links.add(
                createNavPill(
                        "SHIPPING",
                        false
                )
        );

        links.add(
                createNavPill(
                        "SUPPORT",
                        false
                )
        );

        links.add(
                createNavPill(
                        "ACCOUNT",
                        false
                )
        );

        nav.add(
                links,
                BorderLayout.CENTER
        );

        // =====================================================
        // RIGHT BUTTON
        // =====================================================

        JButton partnerButton =
                createOrangeButton(
                        "BECOME A PARTNER"
                );

        partnerButton.setPreferredSize(
                new Dimension(
                        145,
                        38
                )
        );

        partnerButton.addActionListener(
                e -> showLoginFrame()
        );

        nav.add(
                partnerButton,
                BorderLayout.EAST
        );

        return nav;
    }

    // =========================================================
    // NAV PILL
    // =========================================================

    private JButton createNavPill(
            String text,
            boolean active
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        button.setForeground(
                active
                        ? TEXT_DARK
                        : new Color(
                        65,
                        73,
                        80
                )
        );

        button.setBackground(
                active
                        ? new Color(
                        225,
                        232,
                        237
                )
                        : new Color(
                        245,
                        248,
                        250
                )
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);

        button.setBorder(
                new EmptyBorder(
                        8,
                        13,
                        8,
                        13
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // HERO SECTION
    // =========================================================

    private JPanel createHeroSection() {

        JPanel wrapper =
                new JPanel(
                        new GridBagLayout()
                );

        wrapper.setOpaque(false);

        wrapper.setBorder(
                new EmptyBorder(
                        5,
                        30,
                        30,
                        30
                )
        );

        JPanel hero =
                new JPanel(
                        new GridLayout(
                                1,
                                2
                        )
                );

        hero.setOpaque(false);

        // =====================================================
        // LEFT SIDE
        // =====================================================

        JPanel left =
                createHeroLeft();

        // =====================================================
        // RIGHT SIDE
        // =====================================================

        JPanel right =
                createHeroVisual();

        hero.add(left);
        hero.add(right);

        wrapper.add(
                hero
        );

        return wrapper;
    }

    // =========================================================
    // HERO LEFT
    // =========================================================

    private JPanel createHeroLeft() {

        JPanel panel =
                new JPanel();

        panel.setOpaque(false);

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                new EmptyBorder(
                        45,
                        30,
                        20,
                        20
                )
        );

        JLabel smallTitle =
                new JLabel(
                        "#1 COURIER SERVICE"
                );

        smallTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        smallTitle.setForeground(
                TEXT_DARK
        );

        smallTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // =====================================================
        // BIG TITLE
        // =====================================================

        JLabel title =
                new JLabel(
                        "<html>" +
                                "EASY &amp; FAST<br>" +
                                "SHIPMENT" +
                                "</html>"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        46
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
                                "<div style='width:390px'>" +
                                "Send, track and manage your parcels " +
                                "easily with a simple and reliable " +
                                "courier management system." +
                                "</div>" +
                                "</html>"
                );

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        description.setForeground(
                TEXT_MUTED
        );

        description.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // =====================================================
        // SHIPPING SEARCH CARD
        // =====================================================

        JPanel trackingCard =
                createTrackingCard();

        trackingCard.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // =====================================================
        // BUTTON
        // =====================================================

        JButton startButton =
                createOrangeButton(
                        "START SHIPPING"
                );

        startButton.setPreferredSize(
                new Dimension(
                        155,
                        42
                )
        );

        startButton.setMaximumSize(
                new Dimension(
                        155,
                        42
                )
        );

        startButton.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        startButton.addActionListener(
                e -> showLoginFrame()
        );

        panel.add(
                smallTitle
        );

        panel.add(
                Box.createVerticalStrut(10)
        );

        panel.add(
                title
        );

        panel.add(
                Box.createVerticalStrut(15)
        );

        panel.add(
                description
        );

        panel.add(
                Box.createVerticalStrut(22)
        );

        panel.add(
                trackingCard
        );

        panel.add(
                Box.createVerticalStrut(18)
        );

        panel.add(
                startButton
        );

        return panel;
    }

    // =========================================================
    // TRACKING CARD
    // =========================================================

    private JPanel createTrackingCard() {

        JPanel card =
                new JPanel();

        card.setLayout(
                new BorderLayout()
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
                                7,
                                8,
                                7,
                                8
                        )
                )
        );

        card.setPreferredSize(
                new Dimension(
                        430,
                        90
                )
        );

        card.setMaximumSize(
                new Dimension(
                        500,
                        90
                )
        );

        // =====================================================
        // TABS
        // =====================================================

        JPanel tabs =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                8,
                                0
                        )
                );

        tabs.setOpaque(false);

        JLabel transit =
                createTabLabel(
                        "GET TRANSIT TIMES",
                        true
                );

        JLabel track =
                createTabLabel(
                        "TRACK",
                        false
                );

        JLabel ship =
                createTabLabel(
                        "SHIP",
                        false
                );

        tabs.add(transit);
        tabs.add(track);
        tabs.add(ship);

        card.add(
                tabs,
                BorderLayout.NORTH
        );

        // =====================================================
        // INPUT AREA
        // =====================================================

        JPanel fields =
                new JPanel(
                        new GridBagLayout()
                );

        fields.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridy = 0;
        gbc.insets =
                new Insets(
                        8,
                        5,
                        0,
                        5
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // From
        JTextField from =
                createSearchField(
                        "Dhaka"
                );

        gbc.gridx = 0;
        gbc.weightx = 1;

        fields.add(
                from,
                gbc
        );

        // Arrow
        JLabel arrow =
                new JLabel(
                        "→"
                );

        arrow.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        arrow.setForeground(
                BLACK
        );

        gbc.gridx = 1;
        gbc.weightx = 0;

        fields.add(
                arrow,
                gbc
        );

        // Destination
        JTextField destination =
                createSearchField(
                        "Destination"
                );

        gbc.gridx = 2;
        gbc.weightx = 1;

        fields.add(
                destination,
                gbc
        );

        // Get button
        JButton getButton =
                createOrangeButton(
                        "GET"
                );

        getButton.setPreferredSize(
                new Dimension(
                        65,
                        35
                )
        );

        gbc.gridx = 3;
        gbc.weightx = 0;

        fields.add(
                getButton,
                gbc
        );

        card.add(
                fields,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // SEARCH FIELD
    // =========================================================

    private JTextField createSearchField(
            String placeholder
    ) {

        JTextField field =
                new JTextField();

        field.setText(
                placeholder
        );

        field.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        field.setForeground(
                TEXT_MUTED
        );

        field.setBackground(
                new Color(
                        250,
                        252,
                        253
                )
        );

        field.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                195,
                                204,
                                211
                        ),
                        1
                )
        );

        field.setPreferredSize(
                new Dimension(
                        130,
                        35
                )
        );

        return field;
    }

    // =========================================================
    // TAB LABEL
    // =========================================================

    private JLabel createTabLabel(
            String text,
            boolean active
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
                active
                        ? TEXT_DARK
                        : TEXT_MUTED
        );

        if (active) {

            label.setOpaque(true);

            label.setBackground(
                    new Color(
                            235,
                            241,
                            245
                    )
            );

            label.setBorder(
                    new EmptyBorder(
                            7,
                            10,
                            7,
                            10
                    )
            );
        }

        return label;
    }

    // =========================================================
    // HERO VISUAL
    // =========================================================

    private JPanel createHeroVisual() {

        JPanel visual =
                new JPanel() {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D)
                                        g.create();

                        g2.setRenderingHint(
                                RenderingHints
                                        .KEY_ANTIALIASING,
                                RenderingHints
                                        .VALUE_ANTIALIAS_ON
                        );

                        int w =
                                getWidth();

                        int h =
                                getHeight();

                        // =================================================
                        // IMAGE-LIKE BLUE AREA
                        // =================================================

                        GradientPaint water =
                                new GradientPaint(
                                        0,
                                        0,
                                        new Color(
                                                73,
                                                170,
                                                222
                                        ),
                                        0,
                                        h,
                                        new Color(
                                                12,
                                                91,
                                                151
                                        )
                                );

                        g2.setPaint(
                                water
                        );

                        g2.fill(
                                new RoundRectangle2D.Float(
                                        20,
                                        25,
                                        w - 40,
                                        h - 50,
                                        25,
                                        25
                                )
                        );

                        // =================================================
                        // WATER HIGHLIGHTS
                        // =================================================

                        g2.setColor(
                                new Color(
                                        255,
                                        255,
                                        255,
                                        35
                                )
                        );

                        for (
                                int i = 0;
                                i < 10;
                                i++
                        ) {

                            int x =
                                    30
                                            + i * 45;

                            int y =
                                    70
                                            + (i % 3) * 35;

                            g2.fillOval(
                                    x,
                                    y,
                                    60,
                                    2
                            );
                        }

                        // =================================================
                        // COURIER PACKAGE STACK
                        // =================================================

                        int centerX =
                                w / 2 + 25;

                        int baseY =
                                h - 60;

                        int boxWidth = 80;
                        int boxHeight = 28;

                        // Tower
                        for (
                                int i = 0;
                                i < 8;
                                i++
                        ) {

                            int y =
                                    baseY
                                            - i
                                            * 28;

                            int x =
                                    centerX
                                            - boxWidth / 2;

                            Color boxColor;

                            if (i % 3 == 0) {

                                boxColor =
                                        new Color(
                                                245,
                                                100,
                                                45
                                        );

                            } else if (
                                    i % 3 == 1
                            ) {

                                boxColor =
                                        new Color(
                                                35,
                                                105,
                                                170
                                        );

                            } else {

                                boxColor =
                                        new Color(
                                                248,
                                                196,
                                                55
                                        );
                            }

                            g2.setColor(
                                    boxColor
                            );

                            g2.fillRect(
                                    x,
                                    y,
                                    boxWidth,
                                    boxHeight
                            );

                            g2.setColor(
                                    new Color(
                                            255,
                                            255,
                                            255,
                                            100
                                    )
                            );

                            g2.drawRect(
                                    x,
                                    y,
                                    boxWidth,
                                    boxHeight
                            );
                        }

                        // =================================================
                        // TOP PACKAGE
                        // =================================================

                        int topY =
                                baseY
                                        - 8 * 28
                                        - 25;

                        g2.setColor(
                                new Color(
                                        245,
                                        248,
                                        250
                                )
                        );

                        Polygon topBox =
                                new Polygon();

                        topBox.addPoint(
                                centerX - 42,
                                topY + 25
                        );

                        topBox.addPoint(
                                centerX,
                                topY
                        );

                        topBox.addPoint(
                                centerX + 42,
                                topY + 25
                        );

                        topBox.addPoint(
                                centerX,
                                topY + 45
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
                                centerX - 5,
                                topY + 10,
                                10,
                                32
                        );

                        // =================================================
                        // LABEL
                        // =================================================

                        g2.setColor(
                                WHITE
                        );

                        g2.fillRoundRect(
                                centerX - 27,
                                baseY - 4 * 28,
                                54,
                                18,
                                4,
                                4
                        );

                        g2.setColor(
                                BLACK
                        );

                        g2.setFont(
                                new Font(
                                        "SansSerif",
                                        Font.BOLD,
                                        7
                                )
                        );

                        g2.drawString(
                                "PARCEL",
                                centerX - 18,
                                baseY - 4 * 28 + 12
                        );

                        g2.dispose();
                    }
                };

        visual.setOpaque(false);

        return visual;
    }

    // =========================================================
    // ORANGE BUTTON
    // =========================================================

    private JButton createOrangeButton(
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

                        Color color =
                                getModel()
                                        .isRollover()
                                        ? ORANGE_HOVER
                                        : ORANGE;

                        g2.setColor(
                                color
                        );

                        g2.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        18,
                                        18
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
                        10
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

        return button;
    }

    // =========================================================
    // SHOW LANDING PAGE
    // =========================================================

    public void showLandingPage() {

        mainCardLayout.show(
                mainContainer,
                VIEW_LANDING
        );

        mainContainer.revalidate();
        mainContainer.repaint();
    }

    // =========================================================
    // SHOW LOGIN
    // =========================================================

    public void showLoginFrame() {

        if (loginPanel == null) {

            loginPanel =
                    new LoginFrame(this);

            mainContainer.add(
                    loginPanel,
                    VIEW_LOGIN
            );
        }

        mainCardLayout.show(
                mainContainer,
                VIEW_LOGIN
        );

        mainContainer.revalidate();
        mainContainer.repaint();
    }

    // =========================================================
    // SHOW REGISTER
    // =========================================================

    public void showRegisterFrame() {

        if (registerPanel == null) {

            registerPanel =
                    new RegisterFrame(this);

            mainContainer.add(
                    registerPanel,
                    VIEW_REGISTER
            );
        }

        mainCardLayout.show(
                mainContainer,
                VIEW_REGISTER
        );

        mainContainer.revalidate();
        mainContainer.repaint();
    }

    // =========================================================
    // LOAD DASHBOARD
    // =========================================================

    public void loadDashboard(
            User user
    ) {

        if (dashboardPanel != null) {

            mainContainer.remove(
                    dashboardPanel
            );
        }

        dashboardPanel =
                new DashboardLayout(
                        user,
                        this::showLandingPage
                );

        mainContainer.add(
                dashboardPanel,
                VIEW_DASHBOARD
        );

        mainCardLayout.show(
                mainContainer,
                VIEW_DASHBOARD
        );

        mainContainer.revalidate();
        mainContainer.repaint();
    }


    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    MainFrame frame =
                            new MainFrame();

                    frame.setVisible(true);
                }
        );
    }
}