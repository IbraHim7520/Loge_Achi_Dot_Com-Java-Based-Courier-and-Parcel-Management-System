package UI;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DashboardLayout extends JPanel {

    private final User currentUser;
    private final Runnable logoutCallback;

    private JPanel contentArea;
    private CardLayout contentCardLayout;

    private final List<JButton> sidebarButtons = new ArrayList<>();
    private JButton currentSelectedBtn = null;

    // =========================================================
    // THEME
    // =========================================================

    private final Color BLUE = new Color(0, 97, 153);
    private final Color BLUE_DARK = new Color(0, 61, 95);

    private final Color SKY_BLUE = new Color(138, 207, 248);
    private final Color SOFT_YELLOW = new Color(244, 235, 108);
    private final Color GOLD = new Color(255, 212, 68);

    private final Color BG_TOP = new Color(3, 39, 63);
    private final Color BG_BOTTOM = new Color(0, 72, 110);

    private final Color SIDEBAR_BG = new Color(2, 48, 73);
    private final Color SIDEBAR_HOVER = new Color(0, 82, 120);
    private final Color SIDEBAR_SELECTED = new Color(0, 97, 153);

    private final Color HEADER_BG = new Color(3, 49, 74);

    private final Color CARD_BG = new Color(255, 255, 255, 15);
    private final Color CARD_BORDER = new Color(138, 207, 248, 40);

    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_MUTED = new Color(185, 215, 230);

    private Timer clockTimer;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DashboardLayout(
            User user,
            Runnable logoutCallback
    ) {

        this.currentUser = user;
        this.logoutCallback = logoutCallback;

        setLayout(new BorderLayout());

        // =====================================================
        // HEADER
        // =====================================================

        add(
                createHeaderPanel(),
                BorderLayout.NORTH
        );

        // =====================================================
        // MAIN BODY
        // =====================================================

        JPanel bodyPanel =
                new JPanel(new BorderLayout()) {

                    @Override
                    protected void paintComponent(Graphics g) {

                        super.paintComponent(g);

                        Graphics2D g2d =
                                (Graphics2D) g.create();

                        g2d.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

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

                        // Decorative glow
                        g2d.setColor(
                                new Color(
                                        138,
                                        207,
                                        248,
                                        12
                                )
                        );

                        g2d.fillOval(
                                getWidth() - 250,
                                getHeight() - 250,
                                400,
                                400
                        );

                        g2d.setColor(
                                new Color(
                                        255,
                                        212,
                                        68,
                                        8
                                )
                        );

                        g2d.fillOval(
                                -180,
                                -180,
                                350,
                                350
                        );

                        g2d.dispose();
                    }
                };

        // =====================================================
        // SIDEBAR
        // =====================================================

        bodyPanel.add(
                createSidebarPanel(),
                BorderLayout.WEST
        );

        // =====================================================
        // CONTENT CARDLAYOUT
        // =====================================================

        contentCardLayout =
                new CardLayout();

        contentArea =
                new JPanel(contentCardLayout);

        contentArea.setOpaque(false);

        setupRoleContentPanels();

        // Content wrapper gives breathing room around panels
        JPanel contentWrapper =
                new JPanel(new BorderLayout());

        contentWrapper.setOpaque(false);

        contentWrapper.setBorder(
                new EmptyBorder(
                        14,
                        14,
                        14,
                        14
                )
        );

        contentWrapper.add(
                contentArea,
                BorderLayout.CENTER
        );

        bodyPanel.add(
                contentWrapper,
                BorderLayout.CENTER
        );

        add(
                bodyPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // CONTENT PANELS
    // =========================================================

    private void setupRoleContentPanels() {

        // -----------------------------------------------------
        // COMMON
        // -----------------------------------------------------

        registerContentPanel(
                "OVERVIEW",
                new DashboardOverviewPanel(currentUser)
        );

        String roleStr =
                currentUser.getUser_role() != null
                        ? currentUser.getUser_role().toUpperCase()
                        : "";

        // =====================================================
        // USER
        // =====================================================

        if (roleStr.equals(
                String.valueOf(User.UserRole.USER)
        )) {

            registerContentPanel(
                    "SEND_PARCEL",
                    new SendParcelPanel(currentUser)
            );

            registerContentPanel(
                    "TRACK_PARCEL",
                    new TrackParcelPanel(currentUser)
            );

            registerContentPanel(
                    "MY_PARCELS",
                    new MyParcelsPanel(currentUser)
            );
        }

        // =====================================================
        // RIDER
        // =====================================================

        else if (roleStr.equals(
                String.valueOf(User.UserRole.RIDER)
        )) {

            registerContentPanel(
                    "RIDER_PENDING",
                    new PendingParcelsFrame(currentUser)
            );

            registerContentPanel(
                    "RIDER_ASSIGNED",
                    new AssignedParcelsFrame(currentUser)
            );

            registerContentPanel(
                    "RIDER_UPDATE_STATUS",
                    new UpdateParcelStatusFrame(currentUser)
            );
        }

        // =====================================================
        // ADMIN
        // =====================================================

        else if (roleStr.equals(
                String.valueOf(User.UserRole.ADMIN)
        )) {

            registerContentPanel(
                    "ADMIN_VIEW_USERS",
                    new AdminUsersFrame(currentUser)
            );

            registerContentPanel(
                    "ADMIN_REGISTER_RIDER",
                    new RegisterRiderFrame(currentUser)
            );

            registerContentPanel(
                    "ADMIN_VIEW_PARCELS",
                    new AdminParcelsFrame(currentUser)
            );

            registerContentPanel(
                    "ADMIN_STATISTICS",
                    new AdminStatisticsFrame()
            );

            registerContentPanel(
                    "ADMIN_SEARCH_USER",
                    new SearchUserFrame(currentUser)
            );

            registerContentPanel(
                    "ADMIN_SEARCH_PARCEL",
                    new SearchParcelFrame(currentUser)
            );

            registerContentPanel(
                    "ADMIN_UPDATE_STATUS",
                    new AdminUpdateParcelStatusFrame(currentUser)
            );

            registerContentPanel(
                    "ADMIN_DELETE_USER",
                    new DeleteUserFrame(currentUser)
            );

            registerContentPanel(
                    "ADMIN_DELETE_PARCEL",
                    new DeleteParcelFrame(currentUser)
            );
        }

        contentCardLayout.show(
                contentArea,
                "OVERVIEW"
        );
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeaderPanel() {

        JPanel header =
                new JPanel(new BorderLayout()) {

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

                        GradientPaint gradient =
                                new GradientPaint(
                                        0,
                                        0,
                                        HEADER_BG,
                                        getWidth(),
                                        0,
                                        BLUE_DARK
                                );

                        g2d.setPaint(gradient);

                        g2d.fillRect(
                                0,
                                0,
                                getWidth(),
                                getHeight()
                        );

                        // Bottom separator
                        g2d.setColor(
                                new Color(
                                        138,
                                        207,
                                        248,
                                        45
                                )
                        );

                        g2d.fillRect(
                                0,
                                getHeight() - 1,
                                getWidth(),
                                1
                        );

                        g2d.dispose();
                    }
                };

        header.setOpaque(false);

        header.setPreferredSize(
                new Dimension(
                        0,
                        66
                )
        );

        header.setBorder(
                new EmptyBorder(
                        0,
                        20,
                        0,
                        18
                )
        );

        // =====================================================
        // LEFT HEADER
        // =====================================================

        JPanel leftHeader =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                9
                        )
                );

        leftHeader.setOpaque(false);

        // Logo
        JLabel logo =
                new JLabel("▣");

        logo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25
                )
        );

        logo.setForeground(
                GOLD
        );

        leftHeader.add(logo);

        // Brand section
        JPanel brandPanel =
                new JPanel();

        brandPanel.setOpaque(false);

        brandPanel.setLayout(
                new BoxLayout(
                        brandPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel brand =
                new JLabel(
                        "LOGE ACHI DOT COM"
                );

        brand.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        brand.setForeground(
                SKY_BLUE
        );

        JLabel dashboardText =
                new JLabel(
                        "Courier Management Dashboard"
                );

        dashboardText.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        dashboardText.setForeground(
                TEXT_MUTED
        );

        brandPanel.add(brand);
        brandPanel.add(dashboardText);

        leftHeader.add(brandPanel);

        // =====================================================
        // RIGHT HEADER
        // =====================================================

        JPanel rightHeader =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                15
                        )
                );

        rightHeader.setOpaque(false);

        // User name
        JLabel userLabel =
                new JLabel(
                        currentUser.getUser_name()
                );

        userLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        userLabel.setForeground(
                TEXT_WHITE
        );

        // Role
        String roleStr =
                currentUser.getUser_role() != null
                        ? currentUser.getUser_role().toUpperCase()
                        : "";

        JLabel roleBadge =
                new JLabel(
                        "  " + roleStr + "  "
                );

        roleBadge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        roleBadge.setForeground(
                BLUE_DARK
        );

        roleBadge.setOpaque(true);

        roleBadge.setBackground(
                SOFT_YELLOW
        );

        roleBadge.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        7,
                        5,
                        7
                )
        );

        // Clock
        JLabel clockLabel =
                new JLabel();

        clockLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        clockLabel.setForeground(
                TEXT_MUTED
        );

        clockTimer =
                new Timer(
                        1000,
                        e -> {

                            String timeStr =
                                    new SimpleDateFormat(
                                            "EEE, dd MMM yyyy  |  hh:mm:ss a"
                                    ).format(
                                            new Date()
                                    );

                            clockLabel.setText(
                                    timeStr
                            );
                        }
                );

        clockTimer.setInitialDelay(0);

        clockTimer.start();

        // =====================================================
        // LOGOUT BUTTON
        // =====================================================

        JButton logoutBtn =
                new JButton("Logout");

        logoutBtn.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        logoutBtn.setForeground(
                TEXT_WHITE
        );

        logoutBtn.setBackground(
                new Color(
                        0,
                        82,
                        120
                )
        );

        logoutBtn.setFocusPainted(false);

        logoutBtn.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                138,
                                207,
                                248,
                                80
                        ),
                        1,
                        true
                )
        );

        logoutBtn.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        logoutBtn.setPreferredSize(
                new Dimension(
                        82,
                        32
                )
        );

        logoutBtn.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        logoutBtn.setBackground(
                                new Color(
                                        0,
                                        110,
                                        150
                                )
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        logoutBtn.setBackground(
                                new Color(
                                        0,
                                        82,
                                        120
                                )
                        );
                    }
                }
        );

        logoutBtn.addActionListener(
                e -> {

                    int choice =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Are you sure you want to logout?",
                                    "Confirm Logout",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (choice ==
                            JOptionPane.YES_OPTION) {

                        if (clockTimer != null) {
                            clockTimer.stop();
                        }

                        if (logoutCallback != null) {
                            logoutCallback.run();
                        }
                    }
                }
        );

        rightHeader.add(userLabel);
        rightHeader.add(roleBadge);
        rightHeader.add(clockLabel);
        rightHeader.add(logoutBtn);

        header.add(
                leftHeader,
                BorderLayout.WEST
        );

        header.add(
                rightHeader,
                BorderLayout.EAST
        );

        return header;
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebarPanel() {

        JPanel sidebar =
                new JPanel(
                        new BorderLayout()
                );

        sidebar.setBackground(
                SIDEBAR_BG
        );

        sidebar.setPreferredSize(
                new Dimension(
                        245,
                        0
                )
        );

        // =====================================================
        // SIDEBAR TOP / BRAND
        // =====================================================

        JPanel sidebarTop =
                new JPanel();

        sidebarTop.setOpaque(false);

        sidebarTop.setLayout(
                new BoxLayout(
                        sidebarTop,
                        BoxLayout.Y_AXIS
                )
        );

        sidebarTop.setBorder(
                new EmptyBorder(
                        18,
                        16,
                        15,
                        16
                )
        );

        JLabel menuTitle =
                new JLabel(
                        "MAIN MENU"
                );

        menuTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        menuTitle.setForeground(
                new Color(
                        138,
                        207,
                        248,
                        150
                )
        );

        menuTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        sidebarTop.add(menuTitle);

        sidebarTop.add(
                Box.createVerticalStrut(10)
        );

        // =====================================================
        // NAVIGATION PANEL
        // =====================================================

        JPanel navigationPanel =
                new JPanel();

        navigationPanel.setOpaque(false);

        navigationPanel.setLayout(
                new BoxLayout(
                        navigationPanel,
                        BoxLayout.Y_AXIS
                )
        );

        // Overview
        JButton overviewBtn =
                createSidebarButton(
                        "⌂   Overview",
                        () -> showContentPanel(
                                "OVERVIEW"
                        )
                );

        navigationPanel.add(overviewBtn);

        navigationPanel.add(
                Box.createVerticalStrut(7)
        );

        setSelectedSidebarButton(
                overviewBtn
        );

        String roleStr =
                currentUser.getUser_role() != null
                        ? currentUser.getUser_role().toUpperCase()
                        : "";

        // =====================================================
        // USER
        // =====================================================

        if (roleStr.equals(
                String.valueOf(User.UserRole.USER)
        )) {

            navigationPanel.add(
                    createNavigationButton(
                            "▣   Send Parcel",
                            "SEND_PARCEL"
                    )
            );

            navigationPanel.add(
                    Box.createVerticalStrut(7)
            );

            navigationPanel.add(
                    createNavigationButton(
                            "⌕   Track Parcel",
                            "TRACK_PARCEL"
                    )
            );

            navigationPanel.add(
                    Box.createVerticalStrut(7)
            );

            navigationPanel.add(
                    createNavigationButton(
                            "▤   My Parcels",
                            "MY_PARCELS"
                    )
            );
        }

        // =====================================================
        // RIDER
        // =====================================================

        else if (roleStr.equals(
                String.valueOf(User.UserRole.RIDER)
        )) {

            navigationPanel.add(
                    createNavigationButton(
                            "▤   Pending Parcels",
                            "RIDER_PENDING"
                    )
            );

            navigationPanel.add(
                    Box.createVerticalStrut(7)
            );

            navigationPanel.add(
                    createNavigationButton(
                            "➤   My Assigned Parcels",
                            "RIDER_ASSIGNED"
                    )
            );

            navigationPanel.add(
                    Box.createVerticalStrut(7)
            );

            navigationPanel.add(
                    createNavigationButton(
                            "↻   Update Status",
                            "RIDER_UPDATE_STATUS"
                    )
            );
        }

        // =====================================================
        // ADMIN
        // =====================================================

        else if (roleStr.equals(
                String.valueOf(User.UserRole.ADMIN)
        )) {

            navigationPanel.add(
                    createNavigationButton(
                            "♙   View All Users",
                            "ADMIN_VIEW_USERS"
                    )
            );

            navigationPanel.add(
                    Box.createVerticalStrut(7)
            );

            navigationPanel.add(
                    createNavigationButton(
                            "+   Register Rider",
                            "ADMIN_REGISTER_RIDER"
                    )
            );

            navigationPanel.add(
                    Box.createVerticalStrut(7)
            );

            navigationPanel.add(
                    createNavigationButton(
                            "▣   View All Parcels",
                            "ADMIN_VIEW_PARCELS"
                    )
            );

            navigationPanel.add(
                    Box.createVerticalStrut(7)
            );

            navigationPanel.add(
                    createNavigationButton(
                            "▥   Statistics",
                            "ADMIN_STATISTICS"
                    )
            );

            navigationPanel.add(
                    Box.createVerticalStrut(7)
            );

            navigationPanel.add(
                    createNavigationButton(
                            "⌕   Search User",
                            "ADMIN_SEARCH_USER"
                    )
            );

            navigationPanel.add(
                    Box.createVerticalStrut(7)
            );

            navigationPanel.add(
                    createNavigationButton(
                            "⌕   Search Parcel",
                            "ADMIN_SEARCH_PARCEL"
                    )
            );

            navigationPanel.add(
                    Box.createVerticalStrut(7)
            );

            navigationPanel.add(
                    createNavigationButton(
                            "↻   Update Status",
                            "ADMIN_UPDATE_STATUS"
                    )
            );

            navigationPanel.add(
                    Box.createVerticalStrut(7)
            );

            navigationPanel.add(
                    createNavigationButton(
                            "×   Delete User",
                            "ADMIN_DELETE_USER"
                    )
            );

            navigationPanel.add(
                    Box.createVerticalStrut(7)
            );

            navigationPanel.add(
                    createNavigationButton(
                            "×   Delete Parcel",
                            "ADMIN_DELETE_PARCEL"
                    )
            );
        }

        sidebarTop.add(
                navigationPanel
        );

        // =====================================================
        // SCROLL SUPPORT
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        sidebarTop
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.setOpaque(false);

        scrollPane.getViewport()
                .setOpaque(false);

        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        sidebar.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // SIDEBAR FOOTER
        // =====================================================

        JPanel footer =
                new JPanel();

        footer.setOpaque(false);

        footer.setLayout(
                new BoxLayout(
                        footer,
                        BoxLayout.Y_AXIS
                )
        );

        footer.setBorder(
                new EmptyBorder(
                        12,
                        16,
                        15,
                        16
                )
        );

        JLabel footerLine =
                new JLabel(
                        "● SYSTEM ONLINE"
                );

        footerLine.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        footerLine.setForeground(
                new Color(
                        138,
                        207,
                        248
                )
        );

        JLabel footerText =
                new JLabel(
                        "Courier Management System"
                );

        footerText.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        footerText.setForeground(
                new Color(
                        160,
                        195,
                        210
                )
        );

        footer.add(footerLine);

        footer.add(
                Box.createVerticalStrut(4)
        );

        footer.add(footerText);

        sidebar.add(
                footer,
                BorderLayout.SOUTH
        );

        return sidebar;
    }

    // =========================================================
    // CREATE NAVIGATION BUTTON
    // =========================================================

    private JButton createNavigationButton(
            String text,
            String contentKey
    ) {

        return createSidebarButton(
                text,
                () -> showContentPanel(
                        contentKey
                )
        );
    }

    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private JButton createSidebarButton(
            String text,
            Runnable onClick
    ) {

        JButton btn =
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

                        // Selected button
                        if (this == currentSelectedBtn) {

                            GradientPaint gradient =
                                    new GradientPaint(
                                            0,
                                            0,
                                            new Color(
                                                    0,
                                                    110,
                                                    160
                                            ),
                                            getWidth(),
                                            0,
                                            BLUE
                                    );

                            g2d.setPaint(
                                    gradient
                            );

                            g2d.fillRoundRect(
                                    0,
                                    0,
                                    getWidth(),
                                    getHeight(),
                                    10,
                                    10
                            );

                            // Gold active indicator
                            g2d.setColor(
                                    GOLD
                            );

                            g2d.fillRoundRect(
                                    0,
                                    7,
                                    4,
                                    getHeight() - 14,
                                    4,
                                    4
                            );

                        }

                        // Hover background
                        else if (getModel().isRollover()) {

                            g2d.setColor(
                                    SIDEBAR_HOVER
                            );

                            g2d.fillRoundRect(
                                    0,
                                    0,
                                    getWidth(),
                                    getHeight(),
                                    10,
                                    10
                            );
                        }

                        g2d.dispose();

                        super.paintComponent(g);
                    }
                };

        btn.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        btn.setForeground(
                TEXT_MUTED
        );

        btn.setFocusPainted(false);

        btn.setContentAreaFilled(false);

        btn.setBorder(
                new EmptyBorder(
                        10,
                        15,
                        10,
                        10
                )
        );

        btn.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        btn.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        43
                )
        );

        btn.setPreferredSize(
                new Dimension(
                        215,
                        43
                )
        );

        btn.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        // =====================================================
        // HOVER
        // =====================================================

        btn.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        if (btn != currentSelectedBtn) {

                            btn.setForeground(
                                    TEXT_WHITE
                            );

                            btn.repaint();
                        }
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        if (btn != currentSelectedBtn) {

                            btn.setForeground(
                                    TEXT_MUTED
                            );

                            btn.repaint();
                        }
                    }
                }
        );

        // =====================================================
        // CLICK
        // =====================================================

        btn.addActionListener(
                e -> {

                    setSelectedSidebarButton(
                            btn
                    );

                    if (onClick != null) {

                        onClick.run();
                    }
                }
        );

        sidebarButtons.add(
                btn
        );

        return btn;
    }

    // =========================================================
    // SELECTED SIDEBAR BUTTON
    // =========================================================

    private void setSelectedSidebarButton(
            JButton targetBtn
    ) {

        if (currentSelectedBtn != null) {

            currentSelectedBtn.setForeground(
                    TEXT_MUTED
            );

            currentSelectedBtn.repaint();
        }

        currentSelectedBtn =
                targetBtn;

        if (currentSelectedBtn != null) {

            currentSelectedBtn.setForeground(
                    TEXT_WHITE
            );

            currentSelectedBtn.repaint();
        }
    }

    // =========================================================
    // CARD MANAGEMENT
    // =========================================================

    public void registerContentPanel(
            String key,
            JPanel panel
    ) {

        if (contentArea == null ||
                panel == null) {

            return;
        }

        contentArea.add(
                panel,
                key
        );
    }

    public void showContentPanel(
            String key
    ) {

        if (contentArea == null ||
                contentCardLayout == null) {

            return;
        }

        contentCardLayout.show(
                contentArea,
                key
        );

        contentArea.revalidate();

        contentArea.repaint();
    }

    // =========================================================
    // CLEANUP
    // =========================================================

    @Override
    public void removeNotify() {

        if (clockTimer != null) {

            clockTimer.stop();
        }

        super.removeNotify();
    }
}
