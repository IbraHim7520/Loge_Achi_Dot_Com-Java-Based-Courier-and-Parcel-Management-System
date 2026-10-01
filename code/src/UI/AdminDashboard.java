package UI;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminDashboard extends JPanel {

    private final User user;
    private final MainFrame mainFrame;

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JButton activeButton;

    private final Color BG = new Color(241, 248, 253);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT_DARK = new Color(31, 38, 43);
    private final Color TEXT_MUTED = new Color(103, 117, 128);
    private final Color ORANGE = new Color(248, 116, 35);
    private final Color BLUE = new Color(0, 97, 153);
    private final Color LIGHT_BLUE = new Color(225, 240, 249);
    private final Color BORDER = new Color(216, 227, 234);
    private final Color SUCCESS = new Color(48, 148, 94);
    private final Color SUCCESS_BG = new Color(233, 248, 240);
    private final Color DANGER = new Color(205, 62, 73);

    public AdminDashboard(User user, MainFrame mainFrame) {
        this.user = user;
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());
        setBackground(BG);

        buildUI();
    }

    public AdminDashboard(User user) {
        this(user, null);
    }

    private void buildUI() {
        add(createSidebar(), BorderLayout.WEST);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(BG);

        addAdminPanels();

        cardLayout.show(contentPanel, "OVERVIEW");
        add(contentPanel, BorderLayout.CENTER);
    }

    private void addAdminPanels() {
        contentPanel.add(createOverviewPanel(), "OVERVIEW");
        contentPanel.add(new AdminUsersFrame(user), "USERS");
        contentPanel.add(new RegisterRiderFrame(user), "REGISTER_RIDER");
        contentPanel.add(new AdminParcelsFrame(user), "PARCELS");
        contentPanel.add(new SearchParcelFrame(user), "SEARCH_PARCEL");
        contentPanel.add(new SearchUserFrame(user), "SEARCH_USER");
        contentPanel.add(new AdminUpdateParcelStatusFrame(user), "UPDATE_STATUS");
        contentPanel.add(new DeleteUserFrame(user), "DELETE_USER");
        contentPanel.add(new DeleteParcelFrame(user), "DELETE_PARCEL");
        contentPanel.add(new AdminStatisticsFrame(), "STATISTICS");
    }

    private JPanel createSidebar() {
        JPanel sidebar = new RoundedPanel(WHITE, 0);
        sidebar.setLayout(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(245, 0));
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BORDER));

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(24, 20, 18, 20));

        JLabel brandLabel = new JLabel("LOGE ACHI");
        brandLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        brandLabel.setForeground(TEXT_DARK);
        brandLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel brandSubLabel = new JLabel("DOT COM");
        brandSubLabel.setFont(new Font("SansSerif", Font.BOLD, 10));
        brandSubLabel.setForeground(ORANGE);
        brandSubLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel roleBadge = new JLabel("ADMIN DASHBOARD");
        roleBadge.setOpaque(true);
        roleBadge.setBackground(LIGHT_BLUE);
        roleBadge.setForeground(BLUE);
        roleBadge.setFont(new Font("SansSerif", Font.BOLD, 9));
        roleBadge.setBorder(new EmptyBorder(6, 9, 6, 9));
        roleBadge.setAlignmentX(Component.LEFT_ALIGNMENT);

        String username = user != null && user.getUser_name() != null ? user.getUser_name() : "Admin";
        JLabel welcomeLabel = new JLabel("Welcome, " + username);
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        welcomeLabel.setForeground(TEXT_MUTED);
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        headerPanel.add(brandLabel);
        headerPanel.add(brandSubLabel);
        headerPanel.add(Box.createVerticalStrut(14));
        headerPanel.add(roleBadge);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(welcomeLabel);

        sidebar.add(headerPanel, BorderLayout.NORTH);

        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(4, 12, 10, 12));

        JButton overviewButton = createNavButton("▦  Overview");
        JButton usersButton = createNavButton("♙  View All Users");
        JButton riderButton = createNavButton("◆  Register Rider");
        JButton parcelsButton = createNavButton("▣  View All Parcels");
        JButton searchParcelButton = createNavButton("⌕  Search Parcel");
        JButton searchUserButton = createNavButton("⌕  Search User");
        JButton updateStatusButton = createNavButton("↻  Update Status");
        JButton statisticsButton = createNavButton("▥  Statistics");
        JButton deleteUserButton = createNavButton("×  Delete User");
        JButton deleteParcelButton = createNavButton("×  Delete Parcel");

        addMenuItem(menuPanel, overviewButton);
        addMenuItem(menuPanel, usersButton);
        addMenuItem(menuPanel, riderButton);
        addMenuItem(menuPanel, parcelsButton);
        addMenuItem(menuPanel, searchParcelButton);
        addMenuItem(menuPanel, searchUserButton);
        addMenuItem(menuPanel, updateStatusButton);
        addMenuItem(menuPanel, statisticsButton);
        addMenuItem(menuPanel, deleteUserButton);
        addMenuItem(menuPanel, deleteParcelButton);

        JScrollPane menuScroll = new JScrollPane(menuPanel);
        menuScroll.setBorder(null);
        menuScroll.setOpaque(false);
        menuScroll.getViewport().setOpaque(false);
        menuScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        menuScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        sidebar.add(menuScroll, BorderLayout.CENTER);

        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        footer.setBorder(new EmptyBorder(10, 12, 16, 12));

        JButton logoutButton = createLogoutButton("↪  Logout");
        footer.add(logoutButton, BorderLayout.CENTER);

        sidebar.add(footer, BorderLayout.SOUTH);

        setActiveButton(overviewButton);

        overviewButton.addActionListener(e -> showPage(overviewButton, "OVERVIEW"));
        usersButton.addActionListener(e -> showPage(usersButton, "USERS"));
        riderButton.addActionListener(e -> showPage(riderButton, "REGISTER_RIDER"));
        parcelsButton.addActionListener(e -> showPage(parcelsButton, "PARCELS"));
        searchParcelButton.addActionListener(e -> showPage(searchParcelButton, "SEARCH_PARCEL"));
        searchUserButton.addActionListener(e -> showPage(searchUserButton, "SEARCH_USER"));
        updateStatusButton.addActionListener(e -> showPage(updateStatusButton, "UPDATE_STATUS"));
        statisticsButton.addActionListener(e -> showPage(statisticsButton, "STATISTICS"));
        deleteUserButton.addActionListener(e -> showPage(deleteUserButton, "DELETE_USER"));
        deleteParcelButton.addActionListener(e -> showPage(deleteParcelButton, "DELETE_PARCEL"));
        logoutButton.addActionListener(e -> handleLogout());

        return sidebar;
    }

    private void addMenuItem(JPanel panel, JButton button) {
        panel.add(button);
        panel.add(Box.createVerticalStrut(5));
    }

    private void showPage(JButton button, String page) {
        setActiveButton(button);
        cardLayout.show(contentPanel, page);
    }

    private JPanel createOverviewPanel() {
        JPanel page = new JPanel(new BorderLayout());
        page.setBackground(BG);
        page.setBorder(new EmptyBorder(26, 30, 26, 30));

        JPanel header = new JPanel(new BorderLayout(14, 0));
        header.setOpaque(false);

        JPanel iconPanel = new RoundedPanel(BLUE, 16);
        iconPanel.setPreferredSize(new Dimension(52, 52));
        iconPanel.setLayout(new GridBagLayout());

        JLabel icon = new JLabel("◆");
        icon.setFont(new Font("SansSerif", Font.BOLD, 24));
        icon.setForeground(Color.WHITE);
        iconPanel.add(icon);

        header.add(iconPanel, BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel section = new JLabel("ADMINISTRATION");
        section.setFont(new Font("SansSerif", Font.BOLD, 11));
        section.setForeground(ORANGE);

        JLabel title = new JLabel("Admin Control Center");
        title.setFont(new Font("SansSerif", Font.BOLD, 27));
        title.setForeground(TEXT_DARK);

        JLabel subtitle = new JLabel("Manage users, parcels, riders and delivery operations");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(TEXT_MUTED);

        titlePanel.add(section);
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(subtitle);

        header.add(titlePanel, BorderLayout.CENTER);

        JLabel statusBadge = new JLabel("●  SYSTEM ONLINE");
        statusBadge.setOpaque(true);
        statusBadge.setBackground(SUCCESS_BG);
        statusBadge.setForeground(SUCCESS);
        statusBadge.setFont(new Font("SansSerif", Font.BOLD, 10));
        statusBadge.setBorder(new EmptyBorder(8, 11, 8, 11));

        header.add(statusBadge, BorderLayout.EAST);
        page.add(header, BorderLayout.NORTH);

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);

        JPanel card = new RoundedPanel(WHITE, 22);
        card.setLayout(new BorderLayout(0, 22));
        card.setBorder(new EmptyBorder(32, 36, 32, 36));

        JLabel cardIcon = new JLabel("▥");
        cardIcon.setHorizontalAlignment(SwingConstants.CENTER);
        cardIcon.setFont(new Font("SansSerif", Font.BOLD, 42));
        cardIcon.setForeground(ORANGE);

        JPanel cardIconPanel = new JPanel(new GridBagLayout());
        cardIconPanel.setOpaque(false);
        cardIconPanel.add(cardIcon);

        card.add(cardIconPanel, BorderLayout.NORTH);

        JPanel cardContent = new JPanel();
        cardContent.setOpaque(false);
        cardContent.setLayout(new BoxLayout(cardContent, BoxLayout.Y_AXIS));

        JLabel welcomeTitle = new JLabel("Welcome to the Admin Dashboard");
        welcomeTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        welcomeTitle.setForeground(TEXT_DARK);
        welcomeTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcomeText = new JLabel("<html><div style='text-align:center'>Use the sidebar to manage the complete courier system.<br>Every management section is available directly inside this dashboard.</div></html>");
        welcomeText.setFont(new Font("SansSerif", Font.PLAIN, 13));
        welcomeText.setForeground(TEXT_MUTED);
        welcomeText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel tips = new JPanel(new GridLayout(1, 3, 12, 0));
        tips.setOpaque(false);

        tips.add(createFeatureCard("USERS", "Manage system accounts", BLUE));
        tips.add(createFeatureCard("PARCELS", "Monitor delivery records", ORANGE));
        tips.add(createFeatureCard("RIDERS", "Manage delivery team", SUCCESS));

        cardContent.add(welcomeTitle);
        cardContent.add(Box.createVerticalStrut(10));
        cardContent.add(welcomeText);
        cardContent.add(Box.createVerticalStrut(28));
        cardContent.add(tips);

        card.add(cardContent, BorderLayout.CENTER);

        JPanel bottomInfo = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        bottomInfo.setOpaque(false);

        JLabel info = new JLabel("Select a management section from the sidebar");
        info.setFont(new Font("SansSerif", Font.PLAIN, 11));
        info.setForeground(TEXT_MUTED);

        bottomInfo.add(info);
        card.add(bottomInfo, BorderLayout.SOUTH);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        center.add(card, gbc);
        page.add(center, BorderLayout.CENTER);

        return page;
    }

    private JPanel createFeatureCard(String title, String description, Color accent) {
        JPanel card = new RoundedPanel(new Color(249, 251, 252), 14);
        card.setLayout(new BorderLayout(0, 8));
        card.setBorder(new EmptyBorder(14, 14, 14, 14));

        JPanel accentBar = new RoundedPanel(accent, 5);
        accentBar.setPreferredSize(new Dimension(0, 4));

        card.add(accentBar, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        titleLabel.setForeground(TEXT_DARK);

        JLabel descriptionLabel = new JLabel("<html>" + description + "</html>");
        descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        descriptionLabel.setForeground(TEXT_MUTED);

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(3));
        textPanel.add(descriptionLabel);

        card.add(textPanel, BorderLayout.CENTER);
        return card;
    }

    private JButton createNavButton(String text) {
        JButton button = new JButton(text) {
            private boolean hovered;

            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        hovered = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        hovered = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (this == activeButton || hovered) {
                    g2.setColor(this == activeButton ? LIGHT_BLUE : new Color(245, 249, 252));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 11, 11);
                }

                if (this == activeButton) {
                    g2.setColor(ORANGE);
                    g2.fillRoundRect(0, 6, 4, getHeight() - 12, 4, 4);
                }

                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setFont(new Font("SansSerif", Font.PLAIN, 13));
        button.setForeground(TEXT_MUTED);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 14, 10, 12));
        button.setPreferredSize(new Dimension(215, 42));
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private JButton createLogoutButton(String text) {
        JButton button = new JButton(text) {
            private boolean hovered;

            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        hovered = true;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        hovered = false;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (hovered) {
                    g2.setColor(new Color(252, 237, 239));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 11, 11);
                }

                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(DANGER);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 14, 10, 12));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private void setActiveButton(JButton button) {
        if (activeButton != null) {
            activeButton.setForeground(TEXT_MUTED);
        }
        activeButton = button;
        activeButton.setForeground(BLUE);
        activeButton.repaint();
    }

    private void handleLogout() {
        int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            if (mainFrame != null) {
                mainFrame.showLoginFrame();
            }
        }
    }

    private static class RoundedPanel extends JPanel {
        private final Color background;
        private final int radius;

        public RoundedPanel(Color background, int radius) {
            this.background = background;
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(background);

            if (radius > 0) {
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            } else {
                g2.fillRect(0, 0, getWidth(), getHeight());
            }

            g2.dispose();
            super.paintComponent(g);
        }
    }
}