package UI;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class UserDashboard extends JFrame {

    private final User user;

    private CardLayout cardLayout;
    private JPanel contentPanel;

    // =========================================================
    // SUB-PANELS
    // =========================================================

    private SendParcelPanel sendParcelPanel;
    private TrackParcelPanel trackParcelPanel;
    private MyParcelsPanel myParcelsPanel;

    // =========================================================
    // ACTIVE SIDEBAR BUTTON
    // =========================================================

    private JButton activeButton = null;

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
    private final Color SIDEBAR_HOVER_BG = new Color(0, 82, 120);

    private final Color BTN_IDLE_TEXT =
            new Color(190, 215, 228);

    private final Color BTN_ACTIVE_BG =
            new Color(0, 97, 153);

    private final Color BTN_ACTIVE_TEXT =
            Color.WHITE;

    private final Color TEXT_WHITE =
            Color.WHITE;

    private final Color TEXT_MUTED =
            new Color(185, 215, 230);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public UserDashboard(User user) {

        this.user = user;

        setTitle(
                "Loge Achi Dot Com - User Dashboard"
        );

        setSize(
                1200,
                650
        );

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setLayout(
                new BorderLayout()
        );

        // =====================================================
        // CONTENT PANEL
        // =====================================================

        cardLayout =
                new CardLayout();

        contentPanel =
                new JPanel(cardLayout) {

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
                                        BG_TOP,
                                        getWidth(),
                                        getHeight(),
                                        BG_BOTTOM
                                );

                        g2d.setPaint(
                                gradient
                        );

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
                                getWidth() - 300,
                                getHeight() - 250,
                                400,
                                400
                        );

                        g2d.dispose();
                    }
                };

        contentPanel.setOpaque(false);

        // =====================================================
        // CREATE SUB-PANELS
        // =====================================================

        try {

            sendParcelPanel =
                    new SendParcelPanel(user);

            trackParcelPanel =
                    new TrackParcelPanel(user);

            myParcelsPanel =
                    new MyParcelsPanel(user);

            // =================================================
            // CARD KEYS
            // =================================================

            contentPanel.add(
                    sendParcelPanel,
                    "SEND"
            );

            contentPanel.add(
                    trackParcelPanel,
                    "TRACK"
            );

            contentPanel.add(
                    myParcelsPanel,
                    "MY_PARCELS"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Error initializing dashboard panels: "
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }

        // =====================================================
        // SIDEBAR
        // =====================================================

        JPanel sidebar =
                createSidebar();

        add(
                sidebar,
                BorderLayout.WEST
        );

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
                        14,
                        14,
                        14,
                        14
                )
        );

        contentWrapper.add(
                contentPanel,
                BorderLayout.CENTER
        );

        add(
                contentWrapper,
                BorderLayout.CENTER
        );

        // =====================================================
        // DEFAULT SCREEN
        // =====================================================

        cardLayout.show(
                contentPanel,
                "SEND"
        );
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private JPanel createSidebar() {

        JPanel sidebar =
                new JPanel(
                        new BorderLayout()
                );

        sidebar.setPreferredSize(
                new Dimension(
                        250,
                        0
                )
        );

        sidebar.setBackground(
                SIDEBAR_BG
        );

        sidebar.setBorder(
                new EmptyBorder(
                        18,
                        14,
                        18,
                        14
                )
        );

        // =====================================================
        // TOP SECTION
        // =====================================================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setOpaque(false);

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        // Logo
        JLabel logoLabel =
                new JLabel("▣");

        logoLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30
                )
        );

        logoLabel.setForeground(
                GOLD
        );

        logoLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // Brand
        JLabel brandLabel =
                new JLabel(
                        "LOGE ACHI DOT COM"
                );

        brandLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        brandLabel.setForeground(
                SKY_BLUE
        );

        brandLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // Subtitle
        JLabel subtitleLabel =
                new JLabel(
                        "Courier Management"
                );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        subtitleLabel.setForeground(
                TEXT_MUTED
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // User
        JLabel userLabel =
                new JLabel(
                        "Welcome, "
                                + (
                                user != null
                                        ? user.getUser_name()
                                        : "User"
                        )
                );

        userLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        userLabel.setForeground(
                TEXT_WHITE
        );

        userLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // Role
        JLabel roleLabel =
                new JLabel(
                        "USER"
                );

        roleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        roleLabel.setForeground(
                BLUE_DARK
        );

        roleLabel.setOpaque(true);

        roleLabel.setBackground(
                SOFT_YELLOW
        );

        roleLabel.setBorder(
                new EmptyBorder(
                        4,
                        10,
                        4,
                        10
                )
        );

        roleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        headerPanel.add(
                logoLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(2)
        );

        headerPanel.add(
                brandLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(3)
        );

        headerPanel.add(
                subtitleLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(18)
        );

        headerPanel.add(
                userLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(7)
        );

        headerPanel.add(
                roleLabel
        );

        headerPanel.add(
                Box.createVerticalStrut(22)
        );

        sidebar.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // MENU
        // =====================================================

        JPanel menuPanel =
                new JPanel();

        menuPanel.setOpaque(false);

        menuPanel.setLayout(
                new BoxLayout(
                        menuPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel menuTitle =
                new JLabel(
                        "  NAVIGATION"
                );

        menuTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        menuTitle.setForeground(
                new Color(
                        138,
                        207,
                        248,
                        160
                )
        );

        menuTitle.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        menuPanel.add(
                menuTitle
        );

        menuPanel.add(
                Box.createVerticalStrut(10)
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JButton sendBtn =
                createNavButton(
                        "▣   Send Parcel"
                );

        JButton trackBtn =
                createNavButton(
                        "⌕   Track Parcel"
                );

        JButton myParcelsBtn =
                createNavButton(
                        "▤   My Parcels"
                );

        menuPanel.add(
                sendBtn
        );

        menuPanel.add(
                Box.createVerticalStrut(8)
        );

        menuPanel.add(
                trackBtn
        );

        menuPanel.add(
                Box.createVerticalStrut(8)
        );

        menuPanel.add(
                myParcelsBtn
        );

        sidebar.add(
                menuPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // FOOTER
        // =====================================================

        JButton logoutBtn =
                createNavButton(
                        "↪   Logout"
                );

        logoutBtn.setForeground(
                new Color(
                        255,
                        170,
                        170
                )
        );

        JPanel footerPanel =
                new JPanel(
                        new BorderLayout()
                );

        footerPanel.setOpaque(false);

        JLabel statusLabel =
                new JLabel(
                        "●  SYSTEM ONLINE"
                );

        statusLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        statusLabel.setForeground(
                SKY_BLUE
        );

        footerPanel.add(
                statusLabel,
                BorderLayout.NORTH
        );

        footerPanel.add(
                Box.createVerticalStrut(7),
                BorderLayout.CENTER
        );

        footerPanel.add(
                logoutBtn,
                BorderLayout.SOUTH
        );

        sidebar.add(
                footerPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // DEFAULT ACTIVE
        // =====================================================

        setActiveButton(
                sendBtn
        );

        // =====================================================
        // NAVIGATION ACTIONS
        // =====================================================

        sendBtn.addActionListener(
                e -> {

                    setActiveButton(
                            sendBtn
                    );

                    cardLayout.show(
                            contentPanel,
                            "SEND"
                    );
                }
        );

        trackBtn.addActionListener(
                e -> {

                    setActiveButton(
                            trackBtn
                    );

                    cardLayout.show(
                            contentPanel,
                            "TRACK"
                    );
                }
        );

        myParcelsBtn.addActionListener(
                e -> {

                    setActiveButton(
                            myParcelsBtn
                    );

                    try {

                        if (myParcelsPanel != null) {

                            myParcelsPanel.loadUserParcels();
                        }

                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Failed to load parcels: "
                                        + ex.getMessage(),
                                "Database Error",
                                JOptionPane.ERROR_MESSAGE
                        );

                        ex.printStackTrace();
                    }

                    cardLayout.show(
                            contentPanel,
                            "MY_PARCELS"
                    );
                }
        );

        logoutBtn.addActionListener(
                e -> {

                    int option =
                            JOptionPane.showConfirmDialog(
                                    this,
                                    "Are you sure you want to log out?",
                                    "Logout Confirmation",
                                    JOptionPane.YES_NO_OPTION
                            );

                    if (
                            option ==
                                    JOptionPane.YES_OPTION
                    ) {

                        dispose();

                        // Existing behavior preserved.
                        // LoginFrame can be opened here if needed.
                        // new LoginFrame().setVisible(true);
                    }
                }
        );

        return sidebar;
    }

    // =========================================================
    // NAVIGATION BUTTON
    // =========================================================

    private JButton createNavButton(
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

                        // =================================================
                        // ACTIVE
                        // =================================================

                        if (
                                this ==
                                        activeButton
                        ) {

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

                            g2d.fill(
                                    new RoundRectangle2D.Float(
                                            0,
                                            0,
                                            getWidth(),
                                            getHeight(),
                                            12,
                                            12
                                    )
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

                        // =================================================
                        // HOVER
                        // =================================================

                        else if (isHovered) {

                            g2d.setColor(
                                    SIDEBAR_HOVER_BG
                            );

                            g2d.fill(
                                    new RoundRectangle2D.Float(
                                            0,
                                            0,
                                            getWidth(),
                                            getHeight(),
                                            12,
                                            12
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
                        215,
                        44
                )
        );

        button.setMaximumSize(
                new Dimension(
                        215,
                        44
                )
        );

        button.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        button.setBorder(
                new EmptyBorder(
                        8,
                        15,
                        8,
                        15
                )
        );

        return button;
    }

    // =========================================================
    // ACTIVE BUTTON
    // =========================================================

    private void setActiveButton(
            JButton button
    ) {

        if (activeButton != null) {

            activeButton.setForeground(
                    BTN_IDLE_TEXT
            );

            activeButton.setFont(
                    new Font(
                            "SansSerif",
                            Font.PLAIN,
                            13
                    )
            );
        }

        activeButton =
                button;

        activeButton.setForeground(
                BTN_ACTIVE_TEXT
        );

        activeButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        repaint();
    }
}