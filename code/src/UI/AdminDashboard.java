package UI;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class AdminDashboard extends JPanel {

    private final User user;
    private final MainFrame mainFrame;

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JButton activeButton = null;

    // =========================
    // THEME COLORS
    // =========================

    private final Color BLUE = new Color(0, 97, 153);
    private final Color SKY_BLUE = new Color(138, 207, 248);
    private final Color SOFT_YELLOW = new Color(244, 235, 108);
    private final Color GOLD = new Color(255, 212, 68);

    private final Color SIDEBAR_TOP = new Color(3, 39, 63);
    private final Color SIDEBAR_BOTTOM = new Color(0, 55, 88);

    private final Color CONTENT_TOP = new Color(3, 39, 63);
    private final Color CONTENT_BOTTOM = new Color(0, 72, 110);

    private final Color CARD_BG = new Color(0, 55, 88, 238);
    private final Color CARD_BORDER = new Color(138, 207, 248, 80);

    private final Color BTN_IDLE_TEXT = new Color(190, 220, 235);
    private final Color BTN_ACTIVE_BG = new Color(255, 212, 68, 30);
    private final Color BTN_ACTIVE_TEXT = GOLD;

    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_MUTED = new Color(190, 220, 235);

    public AdminDashboard(
            User user,
            MainFrame mainFrame
    ) {
        this.user = user;
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setOpaque(false);

        buildUI();
    }

    public AdminDashboard(User user) {
        this(user, null);
    }

    // =========================
    // BUILD UI
    // =========================

    private void buildUI() {

        // =========================
        // SIDEBAR
        // =========================

        JPanel sidebar = createSidebar();

        add(
                sidebar,
                BorderLayout.WEST
        );

        // =========================
        // CONTENT AREA
        // =========================

        cardLayout = new CardLayout();

        contentPanel = new JPanel(cardLayout) {

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

                GradientPaint gradient =
                        new GradientPaint(
                                0,
                                0,
                                CONTENT_TOP,
                                0,
                                getHeight(),
                                CONTENT_BOTTOM
                        );

                g2d.setPaint(gradient);

                g2d.fillRect(
                        0,
                        0,
                        getWidth(),
                        getHeight()
                );

                g2d.dispose();
            }
        };

        contentPanel.setOpaque(false);

        // =========================
        // DEFAULT OVERVIEW
        // =========================

        JPanel overviewPanel =
                createOverviewPanel();

        contentPanel.add(
                overviewPanel,
                "OVERVIEW"
        );

        add(
                contentPanel,
                BorderLayout.CENTER
        );

        cardLayout.show(
                contentPanel,
                "OVERVIEW"
        );
    }

    // =========================
    // SIDEBAR
    // =========================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel(
                        new BorderLayout()
                ) {

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

                        GradientPaint gradient =
                                new GradientPaint(
                                        0,
                                        0,
                                        SIDEBAR_TOP,
                                        0,
                                        getHeight(),
                                        SIDEBAR_BOTTOM
                                );

                        g2d.setPaint(gradient);

                        g2d.fillRect(
                                0,
                                0,
                                getWidth(),
                                getHeight()
                        );

                        // Right-side separator
                        g2d.setColor(
                                new Color(
                                        138,
                                        207,
                                        248,
                                        45
                                )
                        );

                        g2d.fillRect(
                                getWidth() - 1,
                                0,
                                1,
                                getHeight()
                        );

                        g2d.dispose();
                    }
                };

        sidebar.setPreferredSize(
                new Dimension(
                        250,
                        0
                )
        );

        sidebar.setOpaque(false);

        sidebar.setBorder(
                new EmptyBorder(
                        22,
                        16,
                        18,
                        16
                )
        );

        // =========================
        // HEADER
        // =========================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setOpaque(false);

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        // Brand
        JLabel brandLabel =
                new JLabel(
                        "LOGE ACHI"
                );

        brandLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        23
                )
        );

        brandLabel.setForeground(
                Color.WHITE
        );

        brandLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // Brand second part
        JLabel brandSubLabel =
                new JLabel(
                        "DOT COM"
                );

        brandSubLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        brandSubLabel.setForeground(
                GOLD
        );

        brandSubLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        // Role badge
        JLabel roleBadge =
                new JLabel(
                        "  ADMIN DASHBOARD  "
                );

        roleBadge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        roleBadge.setForeground(
                new Color(
                        0,
                        55,
                        88
                )
        );

        roleBadge.setOpaque(true);

        roleBadge.setBackground(
                GOLD
        );

        roleBadge.setBorder(
                new EmptyBorder(
                        5,
                        8,
                        5,
                        8
                )
        );

        roleBadge.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        String username =
                (
                        user != null
                                && user.getUser_name() != null
                )
                        ? user.getUser_name()
                        : "Admin";

        JLabel welcomeLabel =
                new JLabel(
                        "Welcome, " + username
                );

        welcomeLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        welcomeLabel.setForeground(
                TEXT_MUTED
        );

        welcomeLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        headerPanel.add(
                brandLabel
        );

        headerPanel.add(
                brandSubLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(12)
        );

        headerPanel.add(
                roleBadge
        );

        headerPanel.add(
                Box.createVerticalStrut(9)
        );

        headerPanel.add(
                welcomeLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(22)
        );

        sidebar.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =========================
        // NAVIGATION
        // =========================

        JPanel menuPanel =
                new JPanel();

        menuPanel.setOpaque(false);

        menuPanel.setLayout(
                new BoxLayout(
                        menuPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JButton overviewBtn =
                createNavButton(
                        "▦  Overview"
                );

        JButton viewUsersBtn =
                createNavButton(
                        "♙  View All Users"
                );

        JButton registerRiderBtn =
                createNavButton(
                        "◆  Register Rider"
                );

        JButton viewParcelsBtn =
                createNavButton(
                        "▣  View All Parcels"
                );

        JButton searchParcelBtn =
                createNavButton(
                        "⌕  Search Parcel"
                );

        JButton searchUserBtn =
                createNavButton(
                        "⌕  Search User"
                );

        JButton updateStatusBtn =
                createNavButton(
                        "↻  Update Status"
                );

        JButton deleteUserBtn =
                createNavButton(
                        "×  Delete User"
                );

        JButton deleteParcelBtn =
                createNavButton(
                        "▣  Delete Parcel"
                );

        menuPanel.add(
                overviewBtn
        );

        menuPanel.add(
                Box.createVerticalStrut(5)
        );

        menuPanel.add(
                viewUsersBtn
        );

        menuPanel.add(
                Box.createVerticalStrut(5)
        );

        menuPanel.add(
                registerRiderBtn
        );

        menuPanel.add(
                Box.createVerticalStrut(5)
        );

        menuPanel.add(
                viewParcelsBtn
        );

        menuPanel.add(
                Box.createVerticalStrut(5)
        );

        menuPanel.add(
                searchParcelBtn
        );

        menuPanel.add(
                Box.createVerticalStrut(5)
        );

        menuPanel.add(
                searchUserBtn
        );

        menuPanel.add(
                Box.createVerticalStrut(5)
        );

        menuPanel.add(
                updateStatusBtn
        );

        menuPanel.add(
                Box.createVerticalStrut(5)
        );

        menuPanel.add(
                deleteUserBtn
        );

        menuPanel.add(
                Box.createVerticalStrut(5)
        );

        menuPanel.add(
                deleteParcelBtn
        );

        // =========================
        // SCROLL MENU
        // =========================

        JScrollPane scrollMenu =
                new JScrollPane(
                        menuPanel
                );

        scrollMenu.setOpaque(false);

        scrollMenu.getViewport()
                .setOpaque(false);

        scrollMenu.setBorder(null);

        scrollMenu.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollMenu.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        sidebar.add(
                scrollMenu,
                BorderLayout.CENTER
        );

        // =========================
        // LOGOUT
        // =========================

        JButton logoutBtn =
                createNavButton(
                        "↪  Logout"
                );

        logoutBtn.setForeground(
                new Color(
                        255,
                        150,
                        150
                )
        );

        JPanel footerPanel =
                new JPanel(
                        new BorderLayout()
                );

        footerPanel.setOpaque(false);

        footerPanel.setBorder(
                new EmptyBorder(
                        10,
                        0,
                        0,
                        0
                )
        );

        footerPanel.add(
                logoutBtn,
                BorderLayout.SOUTH
        );

        sidebar.add(
                footerPanel,
                BorderLayout.SOUTH
        );

        // =========================
        // ACTION LISTENERS
        // =========================

        setActiveButton(
                overviewBtn
        );

        overviewBtn.addActionListener(
                e -> {

                    setActiveButton(
                            overviewBtn
                    );

                    cardLayout.show(
                            contentPanel,
                            "OVERVIEW"
                    );
                }
        );

        viewUsersBtn.addActionListener(
                e -> {

                    setActiveButton(
                            viewUsersBtn
                    );

                    new AdminUsersFrame(
                            user
                    ).setVisible(true);
                }
        );

        registerRiderBtn.addActionListener(
                e -> {

                    setActiveButton(
                            registerRiderBtn
                    );

                    new RegisterRiderFrame(
                            user
                    ).setVisible(true);
                }
        );

        viewParcelsBtn.addActionListener(
                e -> {

                    setActiveButton(
                            viewParcelsBtn
                    );

                    new AdminParcelsFrame(
                            user
                    ).setVisible(true);
                }
        );

        searchParcelBtn.addActionListener(
                e -> {

                    setActiveButton(
                            searchParcelBtn
                    );

                    new SearchParcelFrame(
                            user
                    ).setVisible(true);
                }
        );

        searchUserBtn.addActionListener(
                e -> {

                    setActiveButton(
                            searchUserBtn
                    );

                    new SearchUserFrame(
                            user
                    ).setVisible(true);
                }
        );

        updateStatusBtn.addActionListener(
                e -> {

                    setActiveButton(
                            updateStatusBtn
                    );

                    new AdminUpdateParcelStatusFrame(
                            user
                    ).setVisible(true);
                }
        );

        deleteUserBtn.addActionListener(
                e -> {

                    setActiveButton(
                            deleteUserBtn
                    );

                    new DeleteUserFrame(
                            user
                    ).setVisible(true);
                }
        );

        deleteParcelBtn.addActionListener(
                e -> {

                    setActiveButton(
                            deleteParcelBtn
                    );

                    new DeleteParcelFrame(
                            user
                    ).setVisible(true);
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

                    if (
                            choice
                                    == JOptionPane.YES_OPTION
                    ) {

                        if (
                                mainFrame != null
                        ) {

                            mainFrame.showLoginFrame();
                        }
                    }
                }
        );

        return sidebar;
    }

    // =========================
    // OVERVIEW PANEL
    // =========================

    private JPanel createOverviewPanel() {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setOpaque(false);

        // =========================
        // MAIN CARD
        // =========================

        JPanel card =
                new JPanel() {

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
                                        new Color(
                                                0,
                                                62,
                                                96,
                                                245
                                        ),
                                        0,
                                        getHeight(),
                                        new Color(
                                                0,
                                                45,
                                                73,
                                                245
                                        )
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
                                        20,
                                        20
                                )
                        );

                        // Border
                        g2d.setColor(
                                CARD_BORDER
                        );

                        g2d.draw(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth() - 1,
                                        getHeight() - 1,
                                        20,
                                        20
                                )
                        );

                        // Gold top accent
                        g2d.setColor(
                                GOLD
                        );

                        g2d.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        5,
                                        20,
                                        20
                                )
                        );

                        g2d.dispose();
                    }
                };

        card.setOpaque(false);

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        card.setPreferredSize(
                new Dimension(
                        560,
                        350
                )
        );

        card.setBorder(
                new EmptyBorder(
                        42,
                        42,
                        42,
                        42
                )
        );

        // =========================
        // ICON
        // =========================

        JPanel iconPanel =
                new JPanel(
                        new GridBagLayout()
                );

        iconPanel.setOpaque(true);

        iconPanel.setBackground(
                new Color(
                        138,
                        207,
                        248,
                        20
                )
        );

        iconPanel.setPreferredSize(
                new Dimension(
                        64,
                        64
                )
        );

        iconPanel.setMaximumSize(
                new Dimension(
                        64,
                        64
                )
        );

        iconPanel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                138,
                                207,
                                248,
                                80
                        ),
                        1
                )
        );

        JLabel icon =
                new JLabel(
                        "◆"
                );

        icon.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        26
                )
        );

        icon.setForeground(
                GOLD
        );

        iconPanel.add(
                icon
        );

        iconPanel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =========================
        // TITLE
        // =========================

        JLabel title =
                new JLabel(
                        "Admin Control Center"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        27
                )
        );

        title.setForeground(
                TEXT_WHITE
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =========================
        // SUBTITLE
        // =========================

        JLabel sub =
                new JLabel(
                        "Select an option from the sidebar to manage system operations."
                );

        sub.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        sub.setForeground(
                TEXT_MUTED
        );

        sub.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =========================
        // STATUS
        // =========================

        JLabel status =
                new JLabel(
                        "●  SYSTEM ONLINE"
                );

        status.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        status.setForeground(
                SKY_BLUE
        );

        status.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // =========================
        // ADD COMPONENTS
        // =========================

        card.add(
                iconPanel
        );

        card.add(
                Box.createVerticalStrut(22)
        );

        card.add(
                title
        );

        card.add(
                Box.createVerticalStrut(10)
        );

        card.add(
                sub
        );

        card.add(
                Box.createVerticalStrut(25)
        );

        card.add(
                status
        );

        panel.add(
                card
        );

        return panel;
    }

    // =========================
    // NAV BUTTON
    // =========================

    private JButton createNavButton(
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

                        if (
                                this
                                        == activeButton
                        ) {

                            g2d.setColor(
                                    BTN_ACTIVE_BG
                            );

                            g2d.fill(
                                    new RoundRectangle2D.Float(
                                            0,
                                            0,
                                            getWidth(),
                                            getHeight(),
                                            10,
                                            10
                                    )
                            );

                            // Active left indicator
                            g2d.setColor(
                                    GOLD
                            );

                            g2d.fill(
                                    new RoundRectangle2D.Float(
                                            0,
                                            5,
                                            4,
                                            getHeight() - 10,
                                            4,
                                            4
                                    )
                            );
                        }

                        g2d.dispose();

                        super.paintComponent(g);
                    }
                };

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        button.setForeground(
                BTN_IDLE_TEXT
        );

        button.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        button.setContentAreaFilled(
                false
        );

        button.setOpaque(false);

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
                        220,
                        40
                )
        );

        button.setPreferredSize(
                new Dimension(
                        220,
                        40
                )
        );

        button.setBorder(
                new EmptyBorder(
                        7,
                        14,
                        7,
                        12
                )
        );

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        if (
                                button
                                        != activeButton
                        ) {

                            button.setForeground(
                                    TEXT_WHITE
                            );
                        }
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        if (
                                button
                                        != activeButton
                        ) {

                            button.setForeground(
                                    BTN_IDLE_TEXT
                            );
                        }
                    }
                }
        );

        return button;
    }

    // =========================
    // ACTIVE BUTTON
    // =========================

    private void setActiveButton(
            JButton button
    ) {

        if (
                activeButton
                        != null
        ) {

            activeButton.setForeground(
                    BTN_IDLE_TEXT
            );
        }

        activeButton = button;

        activeButton.setForeground(
                BTN_ACTIVE_TEXT
        );

        repaint();
    }
}
