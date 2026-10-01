package UI;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDashboard extends JPanel {

    private final User currentUser;
    private final Runnable logoutCallback;

    private JPanel contentArea;
    private CardLayout contentCardLayout;

    private final List<JButton> sidebarButtons = new ArrayList<>();
    private JButton currentSelectedBtn;

    private JLabel clockLabel;
    private JPanel centerHeader;
    private JPanel rightHeader;
    private Timer clockTimer;

    private static final Color BACKGROUND = new Color(241, 248, 253);
    private static final Color WHITE = Color.WHITE;
    private static final Color BLACK = new Color(15, 18, 20);
    private static final Color TEXT_DARK = new Color(31, 38, 43);
    private static final Color TEXT_MUTED = new Color(103, 117, 128);
    private static final Color ORANGE = new Color(248, 116, 35);
    private static final Color ORANGE_HOVER = new Color(235, 94, 20);
    private static final Color SIDEBAR_BG = new Color(249, 252, 254);
    private static final Color SIDEBAR_HOVER = new Color(236, 244, 249);
    private static final Color SIDEBAR_SELECTED = new Color(255, 241, 233);
    private static final Color BORDER = new Color(216, 227, 234);

    public UserDashboard(User user, Runnable logoutCallback) {
        this.currentUser = user;
        this.logoutCallback = logoutCallback;

        setLayout(new BorderLayout());
        setOpaque(false);

        add(createHeaderPanel(), BorderLayout.NORTH);
        add(createBodyPanel(), BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateResponsiveHeader();
            }
        });
    }

    private JPanel createBodyPanel() {
        JPanel bodyPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(BACKGROUND);
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(new Color(197, 225, 241, 85));
                g2.fill(new Ellipse2D.Float(-120, -120, 350, 350));

                g2.setColor(new Color(178, 216, 237, 65));
                g2.fill(new Ellipse2D.Float(getWidth() - 270, getHeight() - 240, 430, 430));

                g2.dispose();
            }
        };

        bodyPanel.setOpaque(false);
        bodyPanel.add(createSidebarPanel(), BorderLayout.WEST);

        contentCardLayout = new CardLayout();
        contentArea = new JPanel(contentCardLayout);
        contentArea.setOpaque(false);

        setupRoleContentPanels();

        JPanel contentSurface = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(255, 255, 255, 140));
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 20, 20));

                g2.setColor(new Color(255, 255, 255, 180));
                g2.draw(new RoundRectangle2D.Float(0.5f, 0.5f, getWidth() - 1, getHeight() - 1, 20, 20));

                g2.dispose();
            }
        };

        contentSurface.setOpaque(false);
        contentSurface.setBorder(new EmptyBorder(1, 1, 1, 1));
        contentSurface.add(contentArea, BorderLayout.CENTER);

        JPanel contentWrapper = new JPanel(new BorderLayout());
        contentWrapper.setOpaque(false);
        contentWrapper.setBorder(new EmptyBorder(14, 14, 14, 14));
        contentWrapper.add(contentSurface, BorderLayout.CENTER);

        bodyPanel.add(contentWrapper, BorderLayout.CENTER);
        return bodyPanel;
    }

    private void setupRoleContentPanels() {
        registerContentPanel("OVERVIEW", new DashboardOverviewPanel(currentUser));

        String roleStr = currentUser.getUser_role() != null ? currentUser.getUser_role().toUpperCase() : "";

        if (roleStr.equals(String.valueOf(User.UserRole.USER))) {
            registerContentPanel("SEND_PARCEL", new SendParcelPanel(currentUser));
            registerContentPanel("TRACK_PARCEL", new TrackParcelPanel(currentUser));
            registerContentPanel("MY_PARCELS", new MyParcelsPanel(currentUser));
        } else if (roleStr.equals(String.valueOf(User.UserRole.RIDER))) {
            registerContentPanel("RIDER_PENDING", new PendingParcelsFrame(currentUser));
            registerContentPanel("RIDER_ASSIGNED", new AssignedParcelsFrame(currentUser));
            registerContentPanel("RIDER_UPDATE_STATUS", new UpdateParcelStatusFrame(currentUser));
        } else if (roleStr.equals(String.valueOf(User.UserRole.ADMIN))) {
            registerContentPanel("ADMIN_VIEW_USERS", new AdminUsersFrame(currentUser));
            registerContentPanel("ADMIN_REGISTER_RIDER", new RegisterRiderFrame(currentUser));
            registerContentPanel("ADMIN_VIEW_PARCELS", new AdminParcelsFrame(currentUser));
            registerContentPanel("ADMIN_STATISTICS", new AdminStatisticsFrame());
            registerContentPanel("ADMIN_SEARCH_USER", new SearchUserFrame(currentUser));
            registerContentPanel("ADMIN_SEARCH_PARCEL", new SearchParcelFrame(currentUser));
            registerContentPanel("ADMIN_UPDATE_STATUS", new AdminUpdateParcelStatusFrame(currentUser));
            registerContentPanel("ADMIN_DELETE_USER", new DeleteUserFrame(currentUser));
            registerContentPanel("ADMIN_DELETE_PARCEL", new DeleteParcelFrame(currentUser));
        }

        contentCardLayout.show(contentArea, "OVERVIEW");
    }

    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(WHITE);
                g2.fillRect(0, 0, getWidth(), getHeight());

                g2.setColor(BORDER);
                g2.fillRect(0, getHeight() - 1, getWidth(), 1);

                g2.dispose();
            }
        };

        header.setOpaque(false);
        header.setPreferredSize(new Dimension(0, 72));
        header.setBorder(new EmptyBorder(0, 22, 0, 20));

        JPanel leftHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        leftHeader.setOpaque(false);

        JPanel logoCircle = new JPanel(new GridBagLayout());
        logoCircle.setPreferredSize(new Dimension(38, 38));
        logoCircle.setBackground(BLACK);

        JLabel logo = new JLabel("L");
        logo.setFont(new Font("SansSerif", Font.BOLD, 19));
        logo.setForeground(WHITE);
        logoCircle.add(logo);

        JPanel brandPanel = new JPanel();
        brandPanel.setOpaque(false);
        brandPanel.setLayout(new BoxLayout(brandPanel, BoxLayout.Y_AXIS));

        JLabel brand = new JLabel("LOGE ACHI DOT COM");
        brand.setFont(new Font("SansSerif", Font.BOLD, 13));
        brand.setForeground(BLACK);

        JLabel service = new JLabel("COURIER MANAGEMENT SYSTEM");
        service.setFont(new Font("SansSerif", Font.BOLD, 8));
        service.setForeground(ORANGE);

        brandPanel.add(brand);
        brandPanel.add(Box.createVerticalStrut(3));
        brandPanel.add(service);

        leftHeader.add(logoCircle);
        leftHeader.add(brandPanel);
        header.add(leftHeader, BorderLayout.WEST);

        centerHeader = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 20));
        centerHeader.setOpaque(false);
        centerHeader.add(createHeaderPill("TRACKING", false));
        centerHeader.add(createHeaderPill("SHIPPING", false));
        centerHeader.add(createHeaderPill("SUPPORT", false));
        centerHeader.add(createHeaderPill("ACCOUNT", true));
        header.add(centerHeader, BorderLayout.CENTER);

        rightHeader = new JPanel(new FlowLayout(FlowLayout.RIGHT, 9, 14));
        rightHeader.setOpaque(false);

        String username = currentUser.getUser_name() != null ? currentUser.getUser_name() : "User";
        JLabel userLabel = new JLabel(username);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        userLabel.setForeground(TEXT_DARK);

        String role = currentUser.getUser_role() != null ? currentUser.getUser_role().toUpperCase() : "USER";
        JLabel roleBadge = new JLabel(" " + role + " ");
        roleBadge.setFont(new Font("SansSerif", Font.BOLD, 8));
        roleBadge.setForeground(WHITE);
        roleBadge.setOpaque(true);
        roleBadge.setBackground(ORANGE);
        roleBadge.setBorder(new EmptyBorder(6, 8, 6, 8));

        clockLabel = new JLabel();
        clockLabel.setFont(new Font("SansSerif", Font.PLAIN, 9));
        clockLabel.setForeground(TEXT_MUTED);

        clockTimer = new Timer(1000, e -> updateClock());
        clockTimer.setInitialDelay(0);
        clockTimer.start();

        JButton logoutButton = createLogoutButton();

        rightHeader.add(userLabel);
        rightHeader.add(roleBadge);
        rightHeader.add(clockLabel);
        rightHeader.add(logoutButton);

        header.add(rightHeader, BorderLayout.EAST);
        updateResponsiveHeader();

        return header;
    }

    private void updateClock() {
        if (clockLabel == null) return;
        clockLabel.setText(new SimpleDateFormat("dd MMM yyyy | hh:mm:ss a").format(new Date()));
    }

    private void updateResponsiveHeader() {
        if (centerHeader == null || rightHeader == null) return;
        int width = getWidth();
        centerHeader.setVisible(width >= 980);
        if (clockLabel != null) clockLabel.setVisible(width >= 850);
        rightHeader.revalidate();
        rightHeader.repaint();
        revalidate();
        repaint();
    }

    private JLabel createHeaderPill(String text, boolean active) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 8));
        label.setForeground(active ? TEXT_DARK : new Color(88, 99, 108));
        label.setOpaque(true);
        label.setBackground(active ? new Color(230, 236, 241) : new Color(246, 249, 251));
        label.setBorder(new EmptyBorder(8, 11, 8, 11));
        return label;
    }

    private JButton createLogoutButton() {
        JButton button = new JButton("Logout");
        button.setFont(new Font("SansSerif", Font.BOLD, 9));
        button.setForeground(WHITE);
        button.setBackground(ORANGE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(72, 31));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(ORANGE_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(ORANGE);
            }
        });

        button.addActionListener(e -> {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                if (clockTimer != null) clockTimer.stop();
                if (logoutCallback != null) logoutCallback.run();
            }
        });

        return button;
    }

    private JPanel createSidebarPanel() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setBackground(SIDEBAR_BG);
        sidebar.setPreferredSize(new Dimension(225, 0));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDER));

        JPanel sidebarContent = new JPanel();
        sidebarContent.setOpaque(false);
        sidebarContent.setLayout(new BoxLayout(sidebarContent, BoxLayout.Y_AXIS));
        sidebarContent.setBorder(new EmptyBorder(18, 14, 15, 14));

        JLabel menuLabel = new JLabel("MENU");
        menuLabel.setFont(new Font("SansSerif", Font.BOLD, 9));
        menuLabel.setForeground(TEXT_MUTED);
        menuLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        sidebarContent.add(menuLabel);
        sidebarContent.add(Box.createVerticalStrut(10));

        JButton overviewButton = createSidebarButton("⌂   Overview", "OVERVIEW");
        sidebarContent.add(overviewButton);
        sidebarContent.add(Box.createVerticalStrut(6));
        setSelectedSidebarButton(overviewButton);

        String roleStr = currentUser.getUser_role() != null ? currentUser.getUser_role().toUpperCase() : "";

        if (roleStr.equals(String.valueOf(User.UserRole.USER))) {
            addNavItem(sidebarContent, "▣   Send Parcel", "SEND_PARCEL");
            addNavItem(sidebarContent, "⌕   Track Parcel", "TRACK_PARCEL");
            addNavItem(sidebarContent, "▤   My Parcels", "MY_PARCELS");
        } else if (roleStr.equals(String.valueOf(User.UserRole.RIDER))) {
            addNavItem(sidebarContent, "▤   Pending Parcels", "RIDER_PENDING");
            addNavItem(sidebarContent, "➤   Assigned Parcels", "RIDER_ASSIGNED");
            addNavItem(sidebarContent, "↻   Update Status", "RIDER_UPDATE_STATUS");
        } else if (roleStr.equals(String.valueOf(User.UserRole.ADMIN))) {
            addSectionLabel(sidebarContent, "MANAGEMENT");
            addNavItem(sidebarContent, "♙   View All Users", "ADMIN_VIEW_USERS");
            addNavItem(sidebarContent, "+   Register Rider", "ADMIN_REGISTER_RIDER");
            addNavItem(sidebarContent, "▣   View All Parcels", "ADMIN_VIEW_PARCELS");
            addNavItem(sidebarContent, "▥   Statistics", "ADMIN_STATISTICS");

            addSectionLabel(sidebarContent, "TOOLS");
            addNavItem(sidebarContent, "⌕   Search User", "ADMIN_SEARCH_USER");
            addNavItem(sidebarContent, "⌕   Search Parcel", "ADMIN_SEARCH_PARCEL");
            addNavItem(sidebarContent, "↻   Update Status", "ADMIN_UPDATE_STATUS");
            addNavItem(sidebarContent, "×   Delete User", "ADMIN_DELETE_USER");
            addNavItem(sidebarContent, "×   Delete Parcel", "ADMIN_DELETE_PARCEL");
        }

        JScrollPane scrollPane = new JScrollPane(sidebarContent);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        sidebar.add(scrollPane, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setOpaque(false);
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBorder(new EmptyBorder(10, 15, 15, 15));

        JLabel online = new JLabel("●  SYSTEM ONLINE");
        online.setFont(new Font("SansSerif", Font.BOLD, 9));
        online.setForeground(new Color(63, 143, 179));

        JLabel footerText = new JLabel("Loge Achi Dot Com");
        footerText.setFont(new Font("SansSerif", Font.PLAIN, 9));
        footerText.setForeground(TEXT_MUTED);

        footer.add(online);
        footer.add(Box.createVerticalStrut(4));
        footer.add(footerText);

        sidebar.add(footer, BorderLayout.SOUTH);
        return sidebar;
    }

    private void addSectionLabel(JPanel parent, String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("SansSerif", Font.BOLD, 8));
        label.setForeground(new Color(145, 158, 168));
        label.setBorder(new EmptyBorder(13, 12, 6, 0));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        parent.add(label);
    }

    private void addNavItem(JPanel parent, String text, String key) {
        parent.add(createSidebarButton(text, key));
        parent.add(Box.createVerticalStrut(5));
    }

    private JButton createSidebarButton(String text, String contentKey) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (this == currentSelectedBtn) {
                    g2.setColor(SIDEBAR_SELECTED);
                    g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));

                    g2.setColor(ORANGE);
                    g2.fillRoundRect(0, 7, 4, getHeight() - 14, 4, 4);
                } else if (getModel().isRollover()) {
                    g2.setColor(SIDEBAR_HOVER);
                    g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));
                }

                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setFont(new Font("SansSerif", Font.PLAIN, 12));
        button.setForeground(new Color(83, 95, 104));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(new EmptyBorder(10, 14, 10, 10));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(195, 41));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 41));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button != currentSelectedBtn) {
                    button.setForeground(TEXT_DARK);
                    button.repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != currentSelectedBtn) {
                    button.setForeground(new Color(83, 95, 104));
                    button.repaint();
                }
            }
        });

        button.addActionListener(e -> {
            setSelectedSidebarButton(button);
            showContentPanel(contentKey);
        });

        sidebarButtons.add(button);
        return button;
    }

    private void setSelectedSidebarButton(JButton button) {
        if (currentSelectedBtn != null) {
            currentSelectedBtn.setForeground(new Color(83, 95, 104));
            currentSelectedBtn.repaint();
        }
        currentSelectedBtn = button;
        if (currentSelectedBtn != null) {
            currentSelectedBtn.setForeground(ORANGE);
            currentSelectedBtn.repaint();
        }
    }

    public void registerContentPanel(String key, JPanel panel) {
        if (contentArea == null || panel == null) return;
        contentArea.add(panel, key);
    }

    public void showContentPanel(String key) {
        if (contentArea == null || contentCardLayout == null) return;
        contentCardLayout.show(contentArea, key);
        contentArea.revalidate();
        contentArea.repaint();
    }

    @Override
    public void removeNotify() {
        if (clockTimer != null) clockTimer.stop();
        super.removeNotify();
    }
}