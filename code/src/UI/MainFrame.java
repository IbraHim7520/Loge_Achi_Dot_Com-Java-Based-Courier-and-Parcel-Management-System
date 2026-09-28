package UI;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

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
    // NEW COLOR THEME
    // =========================================================

    private final Color BLUE = new Color(0, 97, 153);
    private final Color SKY_BLUE = new Color(138, 207, 248);
    private final Color SOFT_YELLOW = new Color(244, 235, 108);
    private final Color GOLD = new Color(255, 212, 68);

    private final Color BG_TOP = new Color(3, 39, 63);
    private final Color BG_BOTTOM = new Color(0, 72, 110);

    private final Color CARD_BG = new Color(0, 55, 88, 235);
    private final Color CARD_BORDER = new Color(138, 207, 248, 80);

    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_MUTED = new Color(205, 230, 242);

    public MainFrame() {

        setTitle("Loge Achi Dot Com - Courier Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 70% of screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) (screenSize.width * 0.70);
        int height = (int) (screenSize.height * 0.70);

        setSize(width, height);
        setMinimumSize(new Dimension(800, 550));

        setLocationRelativeTo(null);

        // Main CardLayout
        mainCardLayout = new CardLayout();
        mainContainer = new JPanel(mainCardLayout);

        // Landing page
        mainContainer.add(
                createLandingPagePanel(),
                VIEW_LANDING
        );

        add(mainContainer);
    }

    // =========================================================
    // LANDING PAGE
    // =========================================================

    private JPanel createLandingPagePanel() {

        JPanel backgroundPanel = new JPanel(new BorderLayout()) {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Main background gradient
                GradientPaint gradient = new GradientPaint(
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

                // =============================================
                // Decorative glowing circles
                // =============================================

                g2d.setColor(
                        new Color(
                                SKY_BLUE.getRed(),
                                SKY_BLUE.getGreen(),
                                SKY_BLUE.getBlue(),
                                35
                        )
                );

                g2d.fill(
                        new Ellipse2D.Float(
                                -100,
                                -100,
                                300,
                                300
                        )
                );

                g2d.setColor(
                        new Color(
                                GOLD.getRed(),
                                GOLD.getGreen(),
                                GOLD.getBlue(),
                                25
                        )
                );

                g2d.fill(
                        new Ellipse2D.Float(
                                getWidth() - 230,
                                getHeight() - 190,
                                350,
                                350
                        )
                );

                // =============================================
                // Decorative courier route
                // =============================================

                g2d.setColor(
                        new Color(138, 207, 248, 45)
                );

                g2d.setStroke(
                        new BasicStroke(
                                2f,
                                BasicStroke.CAP_ROUND,
                                BasicStroke.JOIN_ROUND
                        )
                );

                int routeY = getHeight() - 65;

                for (int x = 0; x < getWidth(); x += 32) {

                    g2d.drawLine(
                            x,
                            routeY,
                            Math.min(x + 15, getWidth()),
                            routeY
                    );
                }

                // Route dots
                g2d.setColor(
                        new Color(255, 212, 68, 150)
                );

                g2d.fill(
                        new Ellipse2D.Float(
                                80,
                                routeY - 5,
                                10,
                                10
                        )
                );

                g2d.fill(
                        new Ellipse2D.Float(
                                getWidth() - 90,
                                routeY - 5,
                                10,
                                10
                        )
                );

                g2d.dispose();
            }
        };

        // =====================================================
        // TOP BAR
        // =====================================================

        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);
        topBar.setBorder(
                new EmptyBorder(
                        22,
                        32,
                        10,
                        32
                )
        );

        JLabel brandLabel =
                new JLabel("LOGE ACHI DOT COM");

        brandLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        brandLabel.setForeground(SKY_BLUE);

        topBar.add(
                brandLabel,
                BorderLayout.WEST
        );

        JLabel serviceLabel =
                new JLabel("COURIER MANAGEMENT SYSTEM");

        serviceLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        serviceLabel.setForeground(
                new Color(255, 255, 255, 170)
        );

        topBar.add(
                serviceLabel,
                BorderLayout.EAST
        );

        backgroundPanel.add(
                topBar,
                BorderLayout.NORTH
        );

        // =====================================================
        // CENTER CONTENT
        // =====================================================

        JPanel centerWrapper =
                new JPanel(
                        new GridBagLayout()
                );

        centerWrapper.setOpaque(false);

        JPanel centerCard = createCenterCard();

        centerWrapper.add(centerCard);

        backgroundPanel.add(
                centerWrapper,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM INFO
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                20,
                                5
                        )
                );

        bottomPanel.setOpaque(false);

        bottomPanel.add(
                createInfoLabel("● EASY PARCEL MANAGEMENT")
        );

        bottomPanel.add(
                createInfoLabel("● FAST TRACKING")
        );

        bottomPanel.add(
                createInfoLabel("● SECURE DELIVERY")
        );

        backgroundPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        return backgroundPanel;
    }

    // =========================================================
    // CENTER CARD
    // =========================================================

    private JPanel createCenterCard() {

        JPanel centerCard = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2d =
                        (Graphics2D) g.create();

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Card
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

                // Border
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

                // Small gold accent line
                g2d.setColor(GOLD);

                g2d.fill(
                        new RoundRectangle2D.Float(
                                45,
                                0,
                                90,
                                4,
                                4,
                                4
                        )
                );

                g2d.dispose();
            }
        };

        centerCard.setOpaque(false);

        centerCard.setLayout(
                new BoxLayout(
                        centerCard,
                        BoxLayout.Y_AXIS
                )
        );

        centerCard.setBorder(
                new EmptyBorder(
                        32,
                        55,
                        32,
                        55
                )
        );

        // =====================================================
        // PACKAGE ICON
        // =====================================================

        JLabel packageIcon =
                new JLabel("▣");

        packageIcon.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        38
                )
        );

        packageIcon.setForeground(GOLD);

        packageIcon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // WELCOME
        // =====================================================

        JLabel welcomeLabel =
                new JLabel("WELCOME TO");

        welcomeLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        welcomeLabel.setForeground(
                new Color(138, 207, 248)
        );

        welcomeLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // MAIN HEADING
        // =====================================================

        JLabel headingLabel =
                new JLabel(
                        "Loge Achi Dot Com"
                );

        headingLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        34
                )
        );

        headingLabel.setForeground(
                TEXT_WHITE
        );

        headingLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // TAGLINE
        // =====================================================

        JLabel tagLineLabel =
                new JLabel(
                        "Move. Track. Deliver."
                );

        tagLineLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        tagLineLabel.setForeground(
                GOLD
        );

        tagLineLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        JLabel descriptionLabel =
                new JLabel(
                        "<html>" +
                                "<div style='text-align:center; width:460px;'>" +
                                "A simple and reliable courier management platform " +
                                "for sending parcels, tracking deliveries, and managing " +
                                "rider operations from one place." +
                                "</div>" +
                                "</html>"
                );

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        descriptionLabel.setForeground(
                TEXT_MUTED
        );

        descriptionLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // MINI FEATURE CARDS
        // =====================================================

        JPanel featurePanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                0
                        )
                );

        featurePanel.setOpaque(false);
        featurePanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        featurePanel.add(
                createFeaturePill(
                        "SEND",
                        SKY_BLUE
                )
        );

        featurePanel.add(
                createFeaturePill(
                        "TRACK",
                        SOFT_YELLOW
                )
        );

        featurePanel.add(
                createFeaturePill(
                        "DELIVER",
                        GOLD
                )
        );

        // =====================================================
        // CTA TEXT
        // =====================================================

        JLabel callToActionLabel =
                new JLabel(
                        "Ready to manage your delivery?"
                );

        callToActionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        callToActionLabel.setForeground(
                TEXT_WHITE
        );

        callToActionLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =====================================================
        // GET STARTED
        // =====================================================

        JButton getStartedBtn =
                createCustomButton(
                        "Get Started  →"
                );

        getStartedBtn.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        getStartedBtn.addActionListener(
                e -> showLoginFrame()
        );

        // =====================================================
        // ADD COMPONENTS
        // =====================================================

        centerCard.add(packageIcon);

        centerCard.add(
                Box.createVerticalStrut(6)
        );

        centerCard.add(
                welcomeLabel
        );

        centerCard.add(
                Box.createVerticalStrut(4)
        );

        centerCard.add(
                headingLabel
        );

        centerCard.add(
                Box.createVerticalStrut(7)
        );

        centerCard.add(
                tagLineLabel
        );

        centerCard.add(
                Box.createVerticalStrut(16)
        );

        centerCard.add(
                descriptionLabel
        );

        centerCard.add(
                Box.createVerticalStrut(18)
        );

        centerCard.add(
                featurePanel
        );

        centerCard.add(
                Box.createVerticalStrut(20)
        );

        centerCard.add(
                callToActionLabel
        );

        centerCard.add(
                Box.createVerticalStrut(12)
        );

        centerCard.add(
                getStartedBtn
        );

        return centerCard;
    }

    // =========================================================
    // FEATURE PILL
    // =========================================================

    private JPanel createFeaturePill(
            String text,
            Color accent
    ) {

        JPanel pill = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        12,
                        6
                )
        ) {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2d =
                        (Graphics2D) g.create();

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2d.setColor(
                        new Color(
                                255,
                                255,
                                255,
                                18
                        )
                );

                g2d.fill(
                        new RoundRectangle2D.Float(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                18,
                                18
                        )
                );

                g2d.setColor(
                        new Color(
                                accent.getRed(),
                                accent.getGreen(),
                                accent.getBlue(),
                                100
                        )
                );

                g2d.draw(
                        new RoundRectangle2D.Float(
                                0,
                                0,
                                getWidth() - 1,
                                getHeight() - 1,
                                18,
                                18
                        )
                );

                g2d.dispose();

                super.paintComponent(g);
            }
        };

        pill.setOpaque(false);

        JLabel dot =
                new JLabel("●");

        dot.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        dot.setForeground(accent);

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        label.setForeground(
                Color.WHITE
        );

        pill.add(dot);
        pill.add(label);

        return pill;
    }

    // =========================================================
    // BOTTOM INFO LABEL
    // =========================================================

    private JLabel createInfoLabel(String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        label.setForeground(
                new Color(
                        210,
                        235,
                        245,
                        180
                )
        );

        return label;
    }

    // =========================================================
    // CUSTOM BUTTON
    // =========================================================

    private JButton createCustomButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        Graphics2D g2d =
                                (Graphics2D)
                                        g.create();

                        g2d.setRenderingHint(
                                RenderingHints
                                        .KEY_ANTIALIASING,
                                RenderingHints
                                        .VALUE_ANTIALIAS_ON
                        );

                        Color topColor;
                        Color bottomColor;

                        if (getModel()
                                .isRollover()) {

                            topColor = SOFT_YELLOW;
                            bottomColor = GOLD;

                        } else {

                            topColor = GOLD;
                            bottomColor =
                                    new Color(
                                            255,
                                            190,
                                            35
                                    );
                        }

                        GradientPaint gradient =
                                new GradientPaint(
                                        0,
                                        0,
                                        topColor,
                                        0,
                                        getHeight(),
                                        bottomColor
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
                        14
                )
        );

        button.setForeground(
                BLUE
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

        button.setMaximumSize(
                new Dimension(
                        210,
                        44
                )
        );

        button.setPreferredSize(
                new Dimension(
                        210,
                        44
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

    public void loadDashboard(User user) {

        // Remove previous dashboard if exists
        if (dashboardPanel != null) {

            mainContainer.remove(
                    dashboardPanel
            );
        }

        // Create new dashboard for logged-in user
        dashboardPanel =
                new DashboardLayout(
                        user,
                        this::showLandingPage
                );

        // Add dashboard to main CardLayout
        mainContainer.add(
                dashboardPanel,
                VIEW_DASHBOARD
        );

        // Show dashboard
        mainCardLayout.show(
                mainContainer,
                VIEW_DASHBOARD
        );

        mainContainer.revalidate();
        mainContainer.repaint();
    }

    // =========================================================
    // MAIN METHOD
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MainFrame frame =
                    new MainFrame();

            frame.setVisible(true);
        });
    }
}