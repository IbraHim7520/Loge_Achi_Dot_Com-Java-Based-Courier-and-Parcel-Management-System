package UI;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
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

    private Timer clockTimer;

    // =========================================================
    // REFERENCE THEME
    // =========================================================

    private static final Color BACKGROUND =
            new Color(241, 248, 253);

    private static final Color WHITE =
            Color.WHITE;

    private static final Color BLACK =
            new Color(15, 18, 20);

    private static final Color TEXT_DARK =
            new Color(31, 38, 43);

    private static final Color TEXT_MUTED =
            new Color(103, 117, 128);

    private static final Color ORANGE =
            new Color(248, 116, 35);

    private static final Color ORANGE_HOVER =
            new Color(235, 94, 20);

    private static final Color LIGHT_BLUE =
            new Color(224, 240, 249);

    private static final Color SKY_BLUE =
            new Color(189, 221, 240);

    private static final Color BLUE =
            new Color(0, 97, 153);

    private static final Color BORDER =
            new Color(218, 228, 235);

    private static final Color SIDEBAR_BG =
            new Color(249, 252, 254);

    private static final Color SIDEBAR_HOVER =
            new Color(236, 244, 249);

    private static final Color SIDEBAR_SELECTED =
            new Color(255, 241, 233);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DashboardLayout(
            User user,
            Runnable logoutCallback
    ) {

        this.currentUser = user;
        this.logoutCallback = logoutCallback;

        setLayout(
                new BorderLayout()
        );

        setOpaque(false);

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
                        g2.setColor(
                                BACKGROUND
                        );

                        g2.fillRect(
                                0,
                                0,
                                getWidth(),
                                getHeight()
                        );

                        // Soft top-left glow
                        g2.setColor(
                                new Color(
                                        202,
                                        229,
                                        243,
                                        90
                                )
                        );

                        g2.fill(
                                new Ellipse2D.Float(
                                        -120,
                                        -130,
                                        350,
                                        350
                                )
                        );

                        // Soft bottom-right glow
                        g2.setColor(
                                new Color(
                                        177,
                                        215,
                                        237,
                                        70
                                )
                        );

                        g2.fill(
                                new Ellipse2D.Float(
                                        getWidth() - 260,
                                        getHeight() - 250,
                                        430,
                                        430
                                )
                        );

                        g2.dispose();
                    }
                };

        bodyPanel.setOpaque(false);

        // =====================================================
        // SIDEBAR
        // =====================================================

        bodyPanel.add(
                createSidebarPanel(),
                BorderLayout.WEST
        );

        // =====================================================
        // CONTENT CARD LAYOUT
        // =====================================================

        contentCardLayout =
                new CardLayout();

        contentArea =
                new JPanel(
                        contentCardLayout
                );

        contentArea.setOpaque(false);

        setupRoleContentPanels();

        // =====================================================
        // CONTENT WRAPPER
        // =====================================================

        JPanel contentWrapper =
                new JPanel(
                        new BorderLayout()
                );

        contentWrapper.setOpaque(false);

        contentWrapper.setBorder(
                new EmptyBorder(
                        16,
                        16,
                        16,
                        16
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
        // COMMON OVERVIEW
        // -----------------------------------------------------

        registerContentPanel(
                "OVERVIEW",
                new DashboardOverviewPanel(
                        currentUser
                )
        );

        String roleStr =
                currentUser.getUser_role() != null
                        ? currentUser
                        .getUser_role()
                        .toUpperCase()
                        : "";

        // =====================================================
        // USER
        // =====================================================

        if (roleStr.equals(
                String.valueOf(
                        User.UserRole.USER
                )
        )) {

            registerContentPanel(
                    "SEND_PARCEL",
                    new SendParcelPanel(
                            currentUser
                    )
            );

            registerContentPanel(
                    "TRACK_PARCEL",
                    new TrackParcelPanel(
                            currentUser
                    )
            );

            registerContentPanel(
                    "MY_PARCELS",
                    new MyParcelsPanel(
                            currentUser
                    )
            );
        }

        // =====================================================
        // RIDER
        // =====================================================

        else if (roleStr.equals(
                String.valueOf(
                        User.UserRole.RIDER
                )
        )) {

            registerContentPanel(
                    "RIDER_PENDING",
                    new PendingParcelsFrame(
                            currentUser
                    )
            );

            registerContentPanel(
                    "RIDER_ASSIGNED",
                    new AssignedParcelsFrame(
                            currentUser
                    )
            );

            registerContentPanel(
                    "RIDER_UPDATE_STATUS",
                    new UpdateParcelStatusFrame(
                            currentUser
                    )
            );
        }

        // =====================================================
        // ADMIN
        // =====================================================

        else if (roleStr.equals(
                String.valueOf(
                        User.UserRole.ADMIN
                )
        )) {

            registerContentPanel(
                    "ADMIN_VIEW_USERS",
                    new AdminUsersFrame(
                            currentUser
                    )
            );

            registerContentPanel(
                    "ADMIN_REGISTER_RIDER",
                    new RegisterRiderFrame(
                            currentUser
                    )
            );

            registerContentPanel(
                    "ADMIN_VIEW_PARCELS",
                    new AdminParcelsFrame(
                            currentUser
                    )
            );

            registerContentPanel(
                    "ADMIN_STATISTICS",
                    new AdminStatisticsFrame()
            );

            registerContentPanel(
                    "ADMIN_SEARCH_USER",
                    new SearchUserFrame(
                            currentUser
                    )
            );

            registerContentPanel(
                    "ADMIN_SEARCH_PARCEL",
                    new SearchParcelFrame(
                            currentUser
                    )
            );

            registerContentPanel(
                    "ADMIN_UPDATE_STATUS",
                    new AdminUpdateParcelStatusFrame(
                            currentUser
                    )
            );

            registerContentPanel(
                    "ADMIN_DELETE_USER",
                    new DeleteUserFrame(
                            currentUser
                    )
            );

            registerContentPanel(
                    "ADMIN_DELETE_PARCEL",
                    new DeleteParcelFrame(
                            currentUser
                    )
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
                new JPanel(
                        new BorderLayout()
                ) {

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

                        // Header background
                        g2.setColor(
                                WHITE
                        );

                        g2.fillRect(
                                0,
                                0,
                                getWidth(),
                                getHeight()
                        );

                        // Bottom line
                        g2.setColor(
                                BORDER
                        );

                        g2.fillRect(
                                0,
                                getHeight() - 1,
                                getWidth(),
                                1
                        );

                        g2.dispose();
                    }
                };

        header.setOpaque(false);

        header.setPreferredSize(
                new Dimension(
                        0,
                        72
                )
        );

        header.setBorder(
                new EmptyBorder(
                        0,
                        24,
                        0,
                        22
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
                                15
                        )
                );

        leftHeader.setOpaque(false);

        // Logo circle
        JPanel logoCircle =
                new JPanel(
                        new GridBagLayout()
                );

        logoCircle.setPreferredSize(
                new Dimension(
                        38,
                        38
                )
        );

        logoCircle.setBackground(
                BLACK
        );

        JLabel logo =
                new JLabel(
                        "L"
                );

        logo.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        19
                )
        );

        logo.setForeground(
                WHITE
        );

        logoCircle.add(
                logo
        );

        // Brand area
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
                        13
                )
        );

        brand.setForeground(
                BLACK
        );

        JLabel subtitle =
                new JLabel(
                        "COURIER MANAGEMENT SYSTEM"
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        subtitle.setForeground(
                ORANGE
        );

        brandPanel.add(
                brand
        );

        brandPanel.add(
                Box.createVerticalStrut(
                        3
                )
        );

        brandPanel.add(
                subtitle
        );

        leftHeader.add(
                logoCircle
        );

        leftHeader.add(
                brandPanel
        );

        header.add(
                leftHeader,
                BorderLayout.WEST
        );

        // =====================================================
        // CENTER HEADER
        // =====================================================

        JPanel centerHeader =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                5,
                                20
                        )
                );

        centerHeader.setOpaque(false);

        centerHeader.add(
                createHeaderPill(
                        "TRACKING",
                        false
                )
        );

        centerHeader.add(
                createHeaderPill(
                        "SHIPPING",
                        false
                )
        );

        centerHeader.add(
                createHeaderPill(
                        "SUPPORT",
                        false
                )
        );

        centerHeader.add(
                createHeaderPill(
                        "ACCOUNT",
                        true
                )
        );

        header.add(
                centerHeader,
                BorderLayout.CENTER
        );

        // =====================================================
        // RIGHT HEADER
        // =====================================================

        JPanel rightHeader =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                9,
                                14
                        )
                );

        rightHeader.setOpaque(false);

        String username =
                currentUser.getUser_name() != null
                        ? currentUser.getUser_name()
                        : "User";

        JLabel userLabel =
                new JLabel(
                        username
                );

        userLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        userLabel.setForeground(
                TEXT_DARK
        );

        // Role badge
        String roleStr =
                currentUser.getUser_role() != null
                        ? currentUser
                        .getUser_role()
                        .toUpperCase()
                        : "USER";

        JLabel roleBadge =
                new JLabel(
                        " " + roleStr + " "
                );

        roleBadge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        roleBadge.setForeground(
                WHITE
        );

        roleBadge.setOpaque(true);

        roleBadge.setBackground(
                ORANGE
        );

        roleBadge.setBorder(
                new EmptyBorder(
                        6,
                        8,
                        6,
                        8
                )
        );

        // Clock
        JLabel clockLabel =
                new JLabel();

        clockLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        clockLabel.setForeground(
                TEXT_MUTED
        );

        clockTimer =
                new Timer(
                        1000,
                        e -> {

                            String time =
                                    new SimpleDateFormat(
                                            "dd MMM yyyy  |  hh:mm:ss a"
                                    ).format(
                                            new Date()
                                    );

                            clockLabel.setText(
                                    time
                            );
                        }
                );

        clockTimer.setInitialDelay(
                0
        );

        clockTimer.start();

        // Logout button
        JButton logoutButton =
                createLogoutButton();

        rightHeader.add(
                userLabel
        );

        rightHeader.add(
                roleBadge
        );

        rightHeader.add(
                clockLabel
        );

        rightHeader.add(
                logoutButton
        );

        header.add(
                rightHeader,
                BorderLayout.EAST
        );

        return header;
    }

    // =========================================================
    // HEADER PILL
    // =========================================================

    private JLabel createHeaderPill(
            String text,
            boolean active
    ) {

        JLabel pill =
                new JLabel(
                        text
                );

        pill.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        pill.setForeground(
                active
                        ? TEXT_DARK
                        : new Color(
                        88,
                        99,
                        108
                )
        );

        pill.setOpaque(true);

        pill.setBackground(
                active
                        ? new Color(
                        230,
                        236,
                        241
                )
                        : new Color(
                        246,
                        249,
                        251
                )
        );

        pill.setBorder(
                new EmptyBorder(
                        8,
                        11,
                        8,
                        11
                )
        );

        return pill;
    }

    // =========================================================
    // LOGOUT BUTTON
    // =========================================================

    private JButton createLogoutButton() {

        JButton button =
                new JButton(
                        "Logout"
                );

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        button.setForeground(
                WHITE
        );

        button.setBackground(
                ORANGE
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

        button.setPreferredSize(
                new Dimension(
                        72,
                        31
                )
        );

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                ORANGE_HOVER
                        );
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                ORANGE
                        );
                    }
                }
        );

        button.addActionListener(
                e -> {

                    int choice =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Are you sure you want to logout?",
                                    "Confirm Logout",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (
                            choice
                                    == JOptionPane.YES_OPTION
                    ) {

                        if (clockTimer != null) {
                            clockTimer.stop();
                        }

                        if (logoutCallback != null) {
                            logoutCallback.run();
                        }
                    }
                }
        );

        return button;
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
                        230,
                        0
                )
        );

        sidebar.setBorder(
                BorderFactory.createMatteBorder(
                        0,
                        0,
                        0,
                        1,
                        BORDER
                )
        );

        // =====================================================
        // SIDEBAR TOP
        // =====================================================

        JPanel top =
                new JPanel();

        top.setOpaque(false);

        top.setLayout(
                new BoxLayout(
                        top,
                        BoxLayout.Y_AXIS
                )
        );

        top.setBorder(
                new EmptyBorder(
                        18,
                        16,
                        15,
                        16
                )
        );

        JLabel menuLabel =
                new JLabel(
                        "MENU"
                );

        menuLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        menuLabel.setForeground(
                TEXT_MUTED
        );

        menuLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        top.add(
                menuLabel
        );

        top.add(
                Box.createVerticalStrut(
                        10
                )
        );

        // =====================================================
        // NAVIGATION
        // =====================================================

        JPanel navigation =
                new JPanel();

        navigation.setOpaque(false);

        navigation.setLayout(
                new BoxLayout(
                        navigation,
                        BoxLayout.Y_AXIS
                )
        );

        // =====================================================
        // OVERVIEW
        // =====================================================

        JButton overviewButton =
                createSidebarButton(
                        "⌂   Overview",
                        "OVERVIEW"
                );

        navigation.add(
                overviewButton
        );

        navigation.add(
                Box.createVerticalStrut(
                        6
                )
        );

        setSelectedSidebarButton(
                overviewButton
        );

        String roleStr =
                currentUser.getUser_role() != null
                        ? currentUser
                        .getUser_role()
                        .toUpperCase()
                        : "";

        // =====================================================
        // USER MENU
        // =====================================================

        if (
                roleStr.equals(
                        String.valueOf(
                                User.UserRole.USER
                        )
                )
        ) {

            addNavItem(
                    navigation,
                    "▣   Send Parcel",
                    "SEND_PARCEL"
            );

            addNavItem(
                    navigation,
                    "⌕   Track Parcel",
                    "TRACK_PARCEL"
            );

            addNavItem(
                    navigation,
                    "▤   My Parcels",
                    "MY_PARCELS"
            );
        }

        // =====================================================
        // RIDER MENU
        // =====================================================

        else if (
                roleStr.equals(
                        String.valueOf(
                                User.UserRole.RIDER
                        )
                )
        ) {

            addNavItem(
                    navigation,
                    "▤   Pending Parcels",
                    "RIDER_PENDING"
            );

            addNavItem(
                    navigation,
                    "➤   Assigned Parcels",
                    "RIDER_ASSIGNED"
            );

            addNavItem(
                    navigation,
                    "↻   Update Status",
                    "RIDER_UPDATE_STATUS"
            );
        }

        // =====================================================
        // ADMIN MENU
        // =====================================================

        else if (
                roleStr.equals(
                        String.valueOf(
                                User.UserRole.ADMIN
                        )
                )
        ) {

            JLabel management =
                    new JLabel(
                            "MANAGEMENT"
                    );

            management.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            8
                    )
            );

            management.setForeground(
                    new Color(
                            145,
                            158,
                            168
                    )
            );

            management.setBorder(
                    new EmptyBorder(
                            12,
                            12,
                            6,
                            0
                    )
            );

            management.setAlignmentX(
                    Component.LEFT_ALIGNMENT
            );

            navigation.add(
                    management
            );

            addNavItem(
                    navigation,
                    "♙   View All Users",
                    "ADMIN_VIEW_USERS"
            );

            addNavItem(
                    navigation,
                    "+   Register Rider",
                    "ADMIN_REGISTER_RIDER"
            );

            addNavItem(
                    navigation,
                    "▣   View All Parcels",
                    "ADMIN_VIEW_PARCELS"
            );

            addNavItem(
                    navigation,
                    "▥   Statistics",
                    "ADMIN_STATISTICS"
            );

            JLabel tools =
                    new JLabel(
                            "TOOLS"
                    );

            tools.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            8
                    )
            );

            tools.setForeground(
                    new Color(
                            145,
                            158,
                            168
                    )
            );

            tools.setBorder(
                    new EmptyBorder(
                            12,
                            12,
                            6,
                            0
                    )
            );

            tools.setAlignmentX(
                    Component.LEFT_ALIGNMENT
            );

            navigation.add(
                    tools
            );

            addNavItem(
                    navigation,
                    "⌕   Search User",
                    "ADMIN_SEARCH_USER"
            );

            addNavItem(
                    navigation,
                    "⌕   Search Parcel",
                    "ADMIN_SEARCH_PARCEL"
            );

            addNavItem(
                    navigation,
                    "↻   Update Status",
                    "ADMIN_UPDATE_STATUS"
            );

            addNavItem(
                    navigation,
                    "×   Delete User",
                    "ADMIN_DELETE_USER"
            );

            addNavItem(
                    navigation,
                    "×   Delete Parcel",
                    "ADMIN_DELETE_PARCEL"
            );
        }

        top.add(
                navigation
        );

        // =====================================================
        // SCROLL
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        top
                );

        scrollPane.setOpaque(false);

        scrollPane.getViewport()
                .setOpaque(false);

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
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
                        10,
                        16,
                        15,
                        16
                )
        );

        JLabel status =
                new JLabel(
                        "●  SYSTEM ONLINE"
                );

        status.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        status.setForeground(
                new Color(
                        63,
                        143,
                        179
                )
        );

        JLabel footerText =
                new JLabel(
                        "Loge Achi Dot Com"
                );

        footerText.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        footerText.setForeground(
                TEXT_MUTED
        );

        footer.add(
                status
        );

        footer.add(
                Box.createVerticalStrut(
                        4
                )
        );

        footer.add(
                footerText
        );

        sidebar.add(
                footer,
                BorderLayout.SOUTH
        );

        return sidebar;
    }

    // =========================================================
    // ADD NAV ITEM
    // =========================================================

    private void addNavItem(
            JPanel parent,
            String text,
            String key
    ) {

        parent.add(
                createSidebarButton(
                        text,
                        key
                )
        );

        parent.add(
                Box.createVerticalStrut(
                        5
                )
        );
    }

    // =========================================================
    // SIDEBAR BUTTON
    // =========================================================

    private JButton createSidebarButton(
            String text,
            String contentKey
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

                        // Selected
                        if (
                                this
                                        == currentSelectedBtn
                        ) {

                            g2.setColor(
                                    SIDEBAR_SELECTED
                            );

                            g2.fillRoundRect(
                                    0,
                                    0,
                                    getWidth(),
                                    getHeight(),
                                    10,
                                    10
                            );

                            // Orange active line
                            g2.setColor(
                                    ORANGE
                            );

                            g2.fillRoundRect(
                                    0,
                                    7,
                                    4,
                                    getHeight() - 14,
                                    4,
                                    4
                            );
                        }

                        // Hover
                        else if (
                                getModel()
                                        .isRollover()
                        ) {

                            g2.setColor(
                                    SIDEBAR_HOVER
                            );

                            g2.fillRoundRect(
                                    0,
                                    0,
                                    getWidth(),
                                    getHeight(),
                                    10,
                                    10
                            );
                        }

                        g2.dispose();

                        super.paintComponent(
                                g
                        );
                    }
                };

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        button.setForeground(
                new Color(
                        83,
                        95,
                        104
                )
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setFocusPainted(false);

        button.setContentAreaFilled(false);

        button.setBorder(
                new EmptyBorder(
                        10,
                        14,
                        10,
                        10
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(
                        205,
                        40
                )
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        // =====================================================
        // HOVER TEXT
        // =====================================================

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        if (
                                button
                                        != currentSelectedBtn
                        ) {

                            button.setForeground(
                                    TEXT_DARK
                            );

                            button.repaint();
                        }
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        if (
                                button
                                        != currentSelectedBtn
                        ) {

                            button.setForeground(
                                    new Color(
                                            83,
                                            95,
                                            104
                                    )
                            );

                            button.repaint();
                        }
                    }
                }
        );

        // =====================================================
        // CLICK
        // =====================================================

        button.addActionListener(
                e -> {

                    setSelectedSidebarButton(
                            button
                    );

                    showContentPanel(
                            contentKey
                    );
                }
        );

        sidebarButtons.add(
                button
        );

        return button;
    }

    // =========================================================
    // SELECT SIDEBAR BUTTON
    // =========================================================

    private void setSelectedSidebarButton(
            JButton button
    ) {

        if (
                currentSelectedBtn
                        != null
        ) {

            currentSelectedBtn.setForeground(
                    new Color(
                            83,
                            95,
                            104
                    )
            );

            currentSelectedBtn.repaint();
        }

        currentSelectedBtn =
                button;

        if (
                currentSelectedBtn
                        != null
        ) {

            currentSelectedBtn.setForeground(
                    ORANGE
            );

            currentSelectedBtn.repaint();
        }
    }

    // =========================================================
    // CONTENT MANAGEMENT
    // =========================================================

    public void registerContentPanel(
            String key,
            JPanel panel
    ) {

        if (
                contentArea == null
                        || panel == null
        ) {

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

        if (
                contentArea == null
                        || contentCardLayout == null
        ) {

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

        if (
                clockTimer != null
        ) {

            clockTimer.stop();
        }

        super.removeNotify();
    }
}