package UI;

import model.Rider;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class RiderDashboard extends JPanel {

    private final User user;
    private final MainFrame mainFrame;

    // =========================================================
    // THEME COLORS
    // =========================================================

    private final Color BLUE =
            new Color(0, 97, 153);

    private final Color SKY_BLUE =
            new Color(138, 207, 248);

    private final Color SOFT_YELLOW =
            new Color(244, 235, 108);

    private final Color GOLD =
            new Color(255, 212, 68);

    private final Color BG_TOP =
            new Color(3, 39, 63);

    private final Color BG_BOTTOM =
            new Color(0, 72, 110);

    private final Color CARD_BG =
            new Color(0, 55, 88, 235);

    private final Color CARD_BORDER =
            new Color(138, 207, 248, 90);

    private final Color BTN_TEXT =
            new Color(20, 55, 70);

    private final Color TEXT_WHITE =
            Color.WHITE;

    private final Color TEXT_SUBTITLE =
            new Color(190, 220, 235);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public RiderDashboard(
            User user,
            MainFrame mainFrame
    ) {

        this.user = user;
        this.mainFrame = mainFrame;

        setLayout(
                new GridBagLayout()
        );

        try {

            new Rider(user);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Access Denied",
                    JOptionPane.ERROR_MESSAGE
            );

            if (this.mainFrame != null) {
                this.mainFrame.showLoginFrame();
            }

            return;
        }

        buildUI();
    }

    // =========================================================
    // BACKWARD COMPATIBILITY CONSTRUCTOR
    // =========================================================

    public RiderDashboard(User user) {
        this(user, null);
    }

    // =========================================================
    // BACKGROUND
    // =========================================================

    @Override
    protected void paintComponent(
            Graphics g
    ) {

        super.paintComponent(g);

        Graphics2D g2d =
                (Graphics2D) g.create();

        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        int width = getWidth();
        int height = getHeight();

        // Main blue gradient
        GradientPaint background =
                new GradientPaint(
                        0,
                        0,
                        BG_TOP,
                        0,
                        height,
                        BG_BOTTOM
                );

        g2d.setPaint(
                background
        );

        g2d.fillRect(
                0,
                0,
                width,
                height
        );

        // Top-right glow
        g2d.setColor(
                new Color(
                        138,
                        207,
                        248,
                        18
                )
        );

        g2d.fillOval(
                width - 220,
                -100,
                300,
                300
        );

        // Bottom-left glow
        g2d.setColor(
                new Color(
                        255,
                        212,
                        68,
                        12
                )
        );

        g2d.fillOval(
                -120,
                height - 190,
                270,
                270
        );

        // Decorative dots
        g2d.setColor(
                new Color(
                        138,
                        207,
                        248,
                        90
                )
        );

        for (int i = 0; i < 7; i++) {

            int x =
                    35 + (i * 25);

            int y =
                    30 + ((i % 2) * 18);

            g2d.fillOval(
                    x,
                    y,
                    3,
                    3
            );
        }

        g2d.dispose();
    }

    // =========================================================
    // BUILD UI
    // =========================================================

    private void buildUI() {

        // =====================================================
        // MAIN CARD
        // =====================================================

        JPanel mainPanel =
                new JPanel() {

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

                        // Glass card
                        g2d.setColor(
                                CARD_BG
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

                        // Border
                        g2d.setColor(
                                CARD_BORDER
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
                        22
                )
        );

        mainPanel.setPreferredSize(
                new Dimension(
                        620,
                        450
                )
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        28,
                        32,
                        26,
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

        // Rider icon
        JLabel iconLabel =
                new JLabel("♙");

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30
                )
        );

        iconLabel.setForeground(
                GOLD
        );

        iconLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        iconLabel.setPreferredSize(
                new Dimension(
                        50,
                        50
                )
        );

        headerPanel.add(
                iconLabel,
                BorderLayout.WEST
        );

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "Rider Dashboard"
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        27
                )
        );

        titleLabel.setForeground(
                TEXT_WHITE
        );

        titleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel welcomeLabel =
                new JLabel(
                        "Welcome back, "
                                + user.getUser_name()
                                + "!"
                );

        welcomeLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        welcomeLabel.setForeground(
                TEXT_SUBTITLE
        );

        welcomeLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        titlePanel.add(
                titleLabel
        );

        titlePanel.add(
                Box.createVerticalStrut(
                        4
                )
        );

        titlePanel.add(
                welcomeLabel
        );

        headerPanel.add(
                titlePanel,
                BorderLayout.CENTER
        );

        // Role badge
        JLabel roleBadge =
                new JLabel(
                        "RIDER"
                );

        roleBadge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        roleBadge.setForeground(
                BTN_TEXT
        );

        roleBadge.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        roleBadge.setOpaque(true);

        roleBadge.setBackground(
                GOLD
        );

        roleBadge.setBorder(
                BorderFactory.createEmptyBorder(
                        6,
                        12,
                        6,
                        12
                )
        );

        headerPanel.add(
                roleBadge,
                BorderLayout.EAST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                16,
                                16
                        )
                );

        buttonPanel.setOpaque(false);

        JButton pendingButton =
                createGoldenButton(
                        "▣  View Pending Parcels"
                );

        JButton assignedButton =
                createGoldenButton(
                        "☷  My Assigned Parcels"
                );

        JButton updateButton =
                createGoldenButton(
                        "↻  Update Parcel Status"
                );

        JButton logoutButton =
                createOutlineButton(
                        "⇥  Logout"
                );

        pendingButton.addActionListener(
                e -> openPendingParcels()
        );

        assignedButton.addActionListener(
                e -> openAssignedParcels()
        );

        updateButton.addActionListener(
                e -> openUpdateStatus()
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        buttonPanel.add(
                pendingButton
        );

        buttonPanel.add(
                assignedButton
        );

        buttonPanel.add(
                updateButton
        );

        buttonPanel.add(
                logoutButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // FOOTER
        // =====================================================

        JPanel footerPanel =
                new JPanel(
                        new BorderLayout()
                );

        footerPanel.setOpaque(false);

        JLabel riderInfo =
                new JLabel(
                        "Rider ID: "
                                + user.getUser_id()
                );

        riderInfo.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        riderInfo.setForeground(
                TEXT_SUBTITLE
        );

        footerPanel.add(
                riderInfo,
                BorderLayout.WEST
        );

        JLabel statusLabel =
                new JLabel(
                        "● ONLINE"
                );

        statusLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        statusLabel.setForeground(
                SKY_BLUE
        );

        footerPanel.add(
                statusLabel,
                BorderLayout.EAST
        );

        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );

        add(
                mainPanel
        );
    }

    // =========================================================
    // GOLD BUTTON
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

                                        isHovered = true;
                                        repaint();
                                    }

                                    @Override
                                    public void mouseExited(
                                            MouseEvent e
                                    ) {

                                        isHovered = false;
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
                                        240,
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
                                        14,
                                        14
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

                                        isHovered = true;
                                        repaint();
                                    }

                                    @Override
                                    public void mouseExited(
                                            MouseEvent e
                                    ) {

                                        isHovered = false;
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
                                            14,
                                            14
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
                                        14,
                                        14
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

    // =====================================================
    // OPEN PENDING PARCELS
    // =====================================================

    private void openPendingParcels() {

        UI.PendingParcelsFrame frame =
                new UI.PendingParcelsFrame(user);

        frame.setVisible(true);
    }

    // =====================================================
    // OPEN ASSIGNED PARCELS
    // =====================================================

    private void openAssignedParcels() {

        UI.AssignedParcelsFrame frame =
                new UI.AssignedParcelsFrame(user);

        frame.setVisible(true);
    }

    // =====================================================
    // OPEN UPDATE STATUS
    // =====================================================

    private void openUpdateStatus() {

        UpdateParcelStatusFrame frame =
                new UpdateParcelStatusFrame(user);

        frame.setVisible(true);
    }

    // =====================================================
    // LOGOUT
    // =====================================================

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (result == JOptionPane.YES_OPTION) {

            if (mainFrame != null) {
                mainFrame.showLoginFrame();
            }
        }
    }
}