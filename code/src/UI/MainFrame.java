package UI;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
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
    // COLOR PALETTE & DESIGN CONSTANTS
    // =========================================================

    private static final Color BG_GRADIENT_START = new Color(241, 247, 253);
    private static final Color BG_GRADIENT_END = new Color(228, 238, 248);

    private static final Color WHITE = Color.WHITE;
    private static final Color BLACK = new Color(20, 24, 30);

    private static final Color TEXT_DARK = new Color(28, 36, 45);
    private static final Color TEXT_MUTED = new Color(110, 124, 137);

    private static final Color ORANGE = new Color(248, 116, 35);
    private static final Color ORANGE_HOVER = new Color(225, 95, 20);

    private static final Color BORDER = new Color(215, 226, 236);
    private static final Color BORDER_FOCUS = new Color(160, 190, 225);

    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 48);
    private static final Font FONT_SUBTITLE = new Font("Segoe UI", Font.BOLD, 12);
    private static final Font FONT_BODY = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 12);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public MainFrame() {

        setTitle("Loge Achi Dot Com - Courier Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screen.width * 0.78);
        int height = (int) (screen.height * 0.78);

        setSize(width, height);
        setMinimumSize(new Dimension(980, 680));
        setLocationRelativeTo(null);

        // System Look & Feel Polish
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        mainCardLayout = new CardLayout();
        mainContainer = new JPanel(mainCardLayout);

        // Landing Page Register
        mainContainer.add(createLandingPagePanel(), VIEW_LANDING);
        add(mainContainer);
    }

    // =========================================================
    // LANDING PAGE
    // =========================================================

    private JPanel createLandingPagePanel() {

        JPanel root = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                // Smooth Linear Gradient Background
                GradientPaint bgGradient = new GradientPaint(
                        0, 0, BG_GRADIENT_START,
                        0, getHeight(), BG_GRADIENT_END
                );
                g2.setPaint(bgGradient);
                g2.fillRect(0, 0, getWidth(), getHeight());

                // Subtle Decorative Glow Elements
                g2.setColor(new Color(205, 228, 246, 120));
                g2.fill(new Ellipse2D.Float(-150, -150, 480, 480));

                g2.setColor(new Color(190, 220, 244, 90));
                g2.fill(new Ellipse2D.Float(getWidth() - 350, getHeight() - 320, 550, 550));

                g2.dispose();
            }
        };

        root.setOpaque(false);

        // Top Navigation & Hero Content
        root.add(createTopNavigation(), BorderLayout.NORTH);
        root.add(createHeroSection(), BorderLayout.CENTER);

        return root;
    }

    // =========================================================
    // TOP NAVIGATION
    // =========================================================

    private JPanel createTopNavigation() {

        JPanel nav = new JPanel(new BorderLayout());
        nav.setOpaque(false);
        nav.setBorder(new EmptyBorder(22, 36, 15, 36));

        // Logo Section
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        logoPanel.setOpaque(false);

        JPanel logoCircle = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(BLACK);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        logoCircle.setOpaque(false);
        logoCircle.setPreferredSize(new Dimension(38, 38));

        JLabel logoIcon = new JLabel("L");
        logoIcon.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logoIcon.setForeground(WHITE);
        logoCircle.add(logoIcon);

        JLabel logoText = new JLabel("LOGE ACHI");
        logoText.setFont(new Font("Segoe UI", Font.BOLD, 17));
        logoText.setForeground(BLACK);

        logoPanel.add(logoCircle);
        logoPanel.add(logoText);

        nav.add(logoPanel, BorderLayout.WEST);

        // Navigation Links
        JPanel links = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        links.setOpaque(false);

        links.add(createNavPill("TRACKING", true));
        links.add(createNavPill("SHIPPING", false));
        links.add(createNavPill("SUPPORT", false));
        links.add(createNavPill("ACCOUNT", false));

        nav.add(links, BorderLayout.CENTER);

        // Partner Button
        JButton partnerButton = createCustomButton("BECOME A PARTNER", ORANGE, ORANGE_HOVER);
        partnerButton.setPreferredSize(new Dimension(160, 40));
        partnerButton.addActionListener(e -> showLoginFrame());

        nav.add(partnerButton, BorderLayout.EAST);

        return nav;
    }

    // =========================================================
    // NAV PILL
    // =========================================================

    private JButton createNavPill(String text, boolean active) {

        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (active) {
                    g2.setColor(new Color(220, 230, 240));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                } else if (getModel().isRollover()) {
                    g2.setColor(new Color(230, 238, 245));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 16, 16);
                }

                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setFont(new Font("Segoe UI", Font.BOLD, 10));
        button.setForeground(active ? TEXT_DARK : TEXT_MUTED);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setBorder(new EmptyBorder(8, 16, 8, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    // =========================================================
    // HERO SECTION
    // =========================================================

    private JPanel createHeroSection() {

        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setOpaque(false);
        wrapper.setBorder(new EmptyBorder(10, 36, 30, 36));

        JPanel hero = new JPanel(new GridLayout(1, 2, 20, 0));
        hero.setOpaque(false);

        hero.add(createHeroLeft());
        hero.add(createHeroVisual());

        wrapper.add(hero);
        return wrapper;
    }

    // =========================================================
    // HERO LEFT
    // =========================================================

    private JPanel createHeroLeft() {

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 10, 20, 20));

        JLabel smallTitle = new JLabel("#1 COURIER SERVICE MANAGEMENT");
        smallTitle.setFont(FONT_SUBTITLE);
        smallTitle.setForeground(ORANGE);
        smallTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel title = new JLabel("<html>EASY &amp; FAST<br><font color='#F87423'>SHIPMENT</font></html>");
        title.setFont(FONT_TITLE);
        title.setForeground(BLACK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel description = new JLabel(
                "<html><div style='width:380px; line-height:1.4;'>" +
                        "Send, track and manage your parcels seamlessly with a simple, " +
                        "secure, and modern courier management solution." +
                        "</div></html>"
        );
        description.setFont(FONT_BODY);
        description.setForeground(TEXT_MUTED);
        description.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel trackingCard = createTrackingCard();
        trackingCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton startButton = createCustomButton("START SHIPPING", ORANGE, ORANGE_HOVER);
        startButton.setPreferredSize(new Dimension(165, 45));
        startButton.setMaximumSize(new Dimension(165, 45));
        startButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        startButton.addActionListener(e -> showLoginFrame());

        panel.add(smallTitle);
        panel.add(Box.createVerticalStrut(10));
        panel.add(title);
        panel.add(Box.createVerticalStrut(14));
        panel.add(description);
        panel.add(Box.createVerticalStrut(24));
        panel.add(trackingCard);
        panel.add(Box.createVerticalStrut(22));
        panel.add(startButton);

        return panel;
    }

    // =========================================================
    // TRACKING CARD
    // =========================================================

    private JPanel createTrackingCard() {

        JPanel card = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Soft Card Shadow
                g2.setColor(new Color(0, 0, 0, 8));
                g2.fillRoundRect(2, 4, getWidth() - 4, getHeight() - 4, 16, 16);

                // Card Body
                g2.setColor(WHITE);
                g2.fillRoundRect(0, 0, getWidth() - 2, getHeight() - 4, 16, 16);

                // Border
                g2.setColor(BORDER);
                g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 4, 16, 16);

                g2.dispose();
                super.paintComponent(g);
            }
        };

        card.setOpaque(false);
        card.setBorder(new EmptyBorder(12, 16, 14, 16));
        card.setPreferredSize(new Dimension(460, 105));
        card.setMaximumSize(new Dimension(500, 105));

        // Tabs Section
        JPanel tabs = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        tabs.setOpaque(false);

        tabs.add(createTabLabel("GET TRANSIT TIMES", true));
        tabs.add(createTabLabel("TRACK", false));
        tabs.add(createTabLabel("SHIP", false));

        card.add(tabs, BorderLayout.NORTH);

        // Input Fields Section
        JPanel fields = new JPanel(new GridBagLayout());
        fields.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 4, 0, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField from = createSearchField("Dhaka");
        gbc.gridx = 0;
        gbc.weightx = 1;
        fields.add(from, gbc);

        JLabel arrow = new JLabel("→", SwingConstants.CENTER);
        arrow.setFont(new Font("Segoe UI", Font.BOLD, 16));
        arrow.setForeground(TEXT_MUTED);
        gbc.gridx = 1;
        gbc.weightx = 0;
        fields.add(arrow, gbc);

        JTextField destination = createSearchField("Destination");
        gbc.gridx = 2;
        gbc.weightx = 1;
        fields.add(destination, gbc);

        JButton getButton = createCustomButton("GET", ORANGE, ORANGE_HOVER);
        getButton.setPreferredSize(new Dimension(70, 36));
        gbc.gridx = 3;
        gbc.weightx = 0;
        fields.add(getButton, gbc);

        card.add(fields, BorderLayout.CENTER);

        return card;
    }

    private JTextField createSearchField(String placeholder) {

        JTextField field = new JTextField(placeholder) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(250, 252, 254));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);

                g2.setColor(hasFocus() ? BORDER_FOCUS : BORDER);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);

                g2.dispose();
                super.paintComponent(g);
            }
        };

        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setForeground(TEXT_MUTED);
        field.setOpaque(false);
        field.setBorder(new EmptyBorder(6, 10, 6, 10));
        field.setPreferredSize(new Dimension(135, 36));

        // Focus Placeholder Handling
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(TEXT_DARK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(TEXT_MUTED);
                }
            }
        });

        return field;
    }

    private JLabel createTabLabel(String text, boolean active) {

        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 10));
        label.setForeground(active ? TEXT_DARK : TEXT_MUTED);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (active) {
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, ORANGE));
        }

        return label;
    }

    // =========================================================
    // HERO VISUAL (3D / VECTOR COURIER ART)
    // =========================================================

    private JPanel createHeroVisual() {

        JPanel visual = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = getWidth();
                int h = getHeight();

                // Card Background
                GradientPaint cardBg = new GradientPaint(
                        0, 0, new Color(52, 140, 212),
                        0, h, new Color(20, 80, 145)
                );

                g2.setPaint(cardBg);
                g2.fill(new RoundRectangle2D.Float(15, 15, w - 30, h - 30, 30, 30));

                // Decorative Ambient Circles
                g2.setColor(new Color(255, 255, 255, 25));
                g2.fillOval(w - 180, -30, 220, 220);
                g2.fillOval(-40, h - 160, 200, 200);

                // Package Visual Stack
                int centerX = w / 2;
                int baseY = h - 80;

                // Ground Shadow
                g2.setColor(new Color(0, 0, 0, 40));
                g2.fillOval(centerX - 80, baseY + 15, 160, 18);

                // Parcel Container Box Stack
                int boxW = 100;
                int boxH = 34;

                for (int i = 0; i < 6; i++) {

                    int x = centerX - (boxW / 2);
                    int y = baseY - (i * 32);

                    // Alternating Color Scheme
                    Color boxColor = (i % 2 == 0) ? new Color(245, 130, 48) : new Color(238, 242, 246);
                    Color tapeColor = (i % 2 == 0) ? new Color(220, 95, 25) : ORANGE;

                    g2.setColor(boxColor);
                    g2.fillRoundRect(x, y, boxW, boxH, 8, 8);

                    // Package Tape Accent
                    g2.setColor(tapeColor);
                    g2.fillRect(x + (boxW / 2) - 8, y, 16, boxH);

                    // Box Border Line
                    g2.setColor(new Color(0, 0, 0, 20));
                    g2.drawRoundRect(x, y, boxW, boxH, 8, 8);
                }

                // Top Open Isometric Parcel Box
                int topY = baseY - (6 * 32) - 10;

                Path2D topFlap = new Path2D.Double();
                topFlap.moveTo(centerX - 50, topY + 20);
                topFlap.lineTo(centerX, topY);
                topFlap.lineTo(centerX + 50, topY + 20);
                topFlap.lineTo(centerX, topY + 40);
                topFlap.closePath();

                g2.setColor(new Color(255, 255, 255));
                g2.fill(topFlap);

                g2.setColor(ORANGE);
                g2.fillRect(centerX - 6, topY + 10, 12, 22);

                // Status Tag Badge
                g2.setColor(WHITE);
                g2.fillRoundRect(centerX - 35, baseY - 90, 70, 22, 11, 11);

                g2.setColor(BLACK);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 9));
                g2.drawString("PARCEL PRO", centerX - 26, baseY - 75);

                g2.dispose();
            }
        };

        visual.setOpaque(false);
        return visual;
    }

    // =========================================================
    // BUTTON CREATOR
    // =========================================================

    private JButton createCustomButton(String text, Color baseColor, Color hoverColor) {

        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                Color currentBg = getModel().isRollover() ? hoverColor : baseColor;

                // Smooth Button Shadow
                if (getModel().isRollover()) {
                    g2.setColor(new Color(0, 0, 0, 25));
                    g2.fillRoundRect(0, 2, getWidth(), getHeight() - 2, 20, 20);
                }

                g2.setColor(currentBg);
                g2.fillRoundRect(0, 0, getWidth(), getHeight() - (getModel().isRollover() ? 2 : 0), 20, 20);

                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setFont(FONT_BUTTON);
        button.setForeground(WHITE);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    // =========================================================
    // NAVIGATION CONTROLLERS
    // =========================================================

    public void showLandingPage() {
        mainCardLayout.show(mainContainer, VIEW_LANDING);
        mainContainer.revalidate();
        mainContainer.repaint();
    }

    public void showLoginFrame() {
        if (loginPanel == null) {
            loginPanel = new LoginFrame(this);
            mainContainer.add(loginPanel, VIEW_LOGIN);
        }
        mainCardLayout.show(mainContainer, VIEW_LOGIN);
        mainContainer.revalidate();
        mainContainer.repaint();
    }

    public void showRegisterFrame() {
        if (registerPanel == null) {
            registerPanel = new RegisterFrame(this);
            mainContainer.add(registerPanel, VIEW_REGISTER);
        }
        mainCardLayout.show(mainContainer, VIEW_REGISTER);
        mainContainer.revalidate();
        mainContainer.repaint();
    }

    public void loadDashboard(User user) {
        if (dashboardPanel != null) {
            mainContainer.remove(dashboardPanel);
        }
        dashboardPanel = new DashboardLayout(user, this::showLandingPage);
        mainContainer.add(dashboardPanel, VIEW_DASHBOARD);

        mainCardLayout.show(mainContainer, VIEW_DASHBOARD);
        mainContainer.revalidate();
        mainContainer.repaint();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}