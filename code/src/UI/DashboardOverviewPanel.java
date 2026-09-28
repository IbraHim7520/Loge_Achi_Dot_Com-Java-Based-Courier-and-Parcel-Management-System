package UI;

import custom_exception.UnauthorizedAccessException;
import model.AdminStatistics;
import model.Parcel;
import model.Rider;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class DashboardOverviewPanel extends JPanel {

    // =========================================================
    // Theme
    // =========================================================

    private final Color BLUE = new Color(0, 97, 153);
    private final Color SKY_BLUE = new Color(138, 207, 248);
    private final Color SOFT_YELLOW = new Color(244, 235, 108);
    private final Color GOLD = new Color(255, 212, 68);

    private final Color BG_TOP = new Color(3, 39, 63);
    private final Color BG_BOTTOM = new Color(0, 72, 110);

    private final Color CARD_BG = new Color(0, 55, 88, 238);
    private final Color CARD_BORDER = new Color(138, 207, 248, 85);

    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_MUTED = new Color(190, 220, 235);

    public DashboardOverviewPanel(User user) {

        setOpaque(false);

        setLayout(
                new BorderLayout(
                        0,
                        22
                )
        );

        setBorder(
                new EmptyBorder(
                        25,
                        28,
                        25,
                        28
                )
        );

        // =====================================================
        // Header
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

        JLabel welcomeHeader =
                new JLabel(
                        "Dashboard Overview"
                );

        welcomeHeader.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        welcomeHeader.setForeground(
                TEXT_WHITE
        );

        JLabel subtitle =
                new JLabel(
                        "Welcome back, "
                                + (user.getUser_name() != null
                                ? user.getUser_name()
                                : "User")
                                + " • Here's your account summary"
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        subtitle.setForeground(
                TEXT_MUTED
        );

        headerPanel.add(
                welcomeHeader
        );

        headerPanel.add(
                Box.createVerticalStrut(5)
        );

        headerPanel.add(
                subtitle
        );

        add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // Statistics Grid
        // =====================================================

        JPanel statsGrid =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                18,
                                0
                        )
                );

        statsGrid.setOpaque(false);

        // =====================================================
        // Account Details Card
        // =====================================================

        JPanel detailsPanel =
                createCardPanel();

        detailsPanel.setLayout(
                new BorderLayout(
                        0,
                        15
                )
        );

        JLabel detailsTitle =
                new JLabel(
                        "Account Summary"
                );

        detailsTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        detailsTitle.setForeground(
                TEXT_WHITE
        );

        detailsPanel.add(
                detailsTitle,
                BorderLayout.NORTH
        );

        JPanel detailsContent =
                new JPanel();

        detailsContent.setLayout(
                new BoxLayout(
                        detailsContent,
                        BoxLayout.Y_AXIS
                )
        );

        detailsContent.setOpaque(false);

        // =====================================================
        // Role Based Information
        // =====================================================

        String roleStr =
                user.getUser_role() != null
                        ? user.getUser_role().toUpperCase()
                        : "";

        // -----------------------------------------------------
        // ADMIN
        // -----------------------------------------------------

        if (roleStr.equalsIgnoreCase(
                String.valueOf(User.UserRole.ADMIN)
        )) {

            AdminStatistics stats =
                    new AdminStatistics();

            statsGrid.add(
                    createStatCard(
                            "Total Users",
                            String.valueOf(
                                    stats.getTotal_users()
                            ),
                            "♟"
                    )
            );

            statsGrid.add(
                    createStatCard(
                            "Total Riders",
                            String.valueOf(
                                    stats.getTotal_riders()
                            ),
                            "♙"
                    )
            );

            statsGrid.add(
                    createStatCard(
                            "Total Parcels",
                            String.valueOf(
                                    stats.getTotal_parcels()
                            ),
                            "▣"
                    )
            );

            addDetailRow(
                    detailsContent,
                    "User ID",
                    user.getUser_id()
            );

            addDetailRow(
                    detailsContent,
                    "Name",
                    user.getUser_name()
            );

            addDetailRow(
                    detailsContent,
                    "Email",
                    user.getUser_email()
            );

            addDetailRow(
                    detailsContent,
                    "Role",
                    user.getUser_role()
            );

            addDetailRow(
                    detailsContent,
                    "Delivered Parcels",
                    String.valueOf(
                            stats.getTotal_delivered_parcels()
                    )
            );

            addDetailRow(
                    detailsContent,
                    "Canceled Parcels",
                    String.valueOf(
                            stats.getTotal_canceled_parcels()
                    )
            );

        }

        // -----------------------------------------------------
        // RIDER
        // -----------------------------------------------------

        else if (roleStr.equalsIgnoreCase(
                String.valueOf(User.UserRole.RIDER)
        )) {

            try {

                Rider rider =
                        new Rider(user);

                statsGrid.add(
                        createStatCard(
                                "Rider Status",
                                "Active",
                                "♙"
                        )
                );

                statsGrid.add(
                        createStatCard(
                                "User ID",
                                user.getUser_id(),
                                "ID"
                        )
                );

                statsGrid.add(
                        createStatCard(
                                "Role",
                                user.getUser_role(),
                                "R"
                        )
                );

                addDetailRow(
                        detailsContent,
                        "User ID",
                        user.getUser_id()
                );

                addDetailRow(
                        detailsContent,
                        "Name",
                        user.getUser_name()
                );

                addDetailRow(
                        detailsContent,
                        "Email",
                        user.getUser_email()
                );

                addDetailRow(
                        detailsContent,
                        "Role",
                        user.getUser_role()
                );

            } catch (
                    UnauthorizedAccessException e
            ) {

                addDetailRow(
                        detailsContent,
                        "Access Status",
                        "Denied: " + e.getMessage()
                );
            }
        }

        // -----------------------------------------------------
        // USER
        // -----------------------------------------------------

        else if (roleStr.equalsIgnoreCase(
                String.valueOf(User.UserRole.USER)
        )) {

            try {

                Parcel parcelModel =
                        new Parcel(user);

                statsGrid.add(
                        createStatCard(
                                "Sender ID",
                                user.getUser_id(),
                                "ID"
                        )
                );

                statsGrid.add(
                        createStatCard(
                                "User Email",
                                user.getUser_email(),
                                "@"
                        )
                );

                statsGrid.add(
                        createStatCard(
                                "Role",
                                user.getUser_role(),
                                "U"
                        )
                );

                addDetailRow(
                        detailsContent,
                        "User ID",
                        user.getUser_id()
                );

                addDetailRow(
                        detailsContent,
                        "Name",
                        user.getUser_name()
                );

                addDetailRow(
                        detailsContent,
                        "Email",
                        user.getUser_email()
                );

                addDetailRow(
                        detailsContent,
                        "Role",
                        user.getUser_role()
                );

            } catch (
                    UnauthorizedAccessException e
            ) {

                addDetailRow(
                        detailsContent,
                        "Access Status",
                        "Denied: " + e.getMessage()
                );
            }
        }

        // =====================================================
        // Scrollable Account Details
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        detailsContent
                );

        scrollPane.setOpaque(false);

        scrollPane.getViewport()
                .setOpaque(false);

        scrollPane.setBorder(null);

        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(12);

        detailsPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // Center Container
        // =====================================================

        JPanel centerContainer =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        centerContainer.setOpaque(false);

        centerContainer.add(
                statsGrid,
                BorderLayout.NORTH
        );

        centerContainer.add(
                detailsPanel,
                BorderLayout.CENTER
        );

        add(
                centerContainer,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // Card
    // =========================================================

    private JPanel createCardPanel() {

        JPanel panel =
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

                        // Card background
                        g2d.setColor(
                                CARD_BG
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

                        // Border
                        g2d.setColor(
                                CARD_BORDER
                        );

                        g2d.setStroke(
                                new BasicStroke(
                                        1f
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

                        // Gold top accent
                        g2d.setColor(
                                GOLD
                        );

                        g2d.fillRoundRect(
                                20,
                                0,
                                getWidth() - 40,
                                3,
                                3,
                                3
                        );

                        g2d.dispose();
                    }
                };

        panel.setOpaque(false);

        panel.setBorder(
                new EmptyBorder(
                        20,
                        22,
                        20,
                        22
                )
        );

        return panel;
    }

    // =========================================================
    // Statistics Card
    // =========================================================

    private JPanel createStatCard(
            String title,
            String value,
            String icon
    ) {

        JPanel card =
                createCardPanel();

        card.setLayout(
                new BorderLayout(
                        15,
                        0
                )
        );

        // Icon container
        JPanel iconContainer =
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

                        g2d.setColor(
                                new Color(
                                        SKY_BLUE.getRed(),
                                        SKY_BLUE.getGreen(),
                                        SKY_BLUE.getBlue(),
                                        30
                                )
                        );

                        g2d.fillRoundRect(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                12,
                                12
                        );

                        g2d.setColor(
                                new Color(
                                        SKY_BLUE.getRed(),
                                        SKY_BLUE.getGreen(),
                                        SKY_BLUE.getBlue(),
                                        80
                                )
                        );

                        g2d.drawRoundRect(
                                0,
                                0,
                                getWidth() - 1,
                                getHeight() - 1,
                                12,
                                12
                        );

                        g2d.dispose();
                    }
                };

        iconContainer.setOpaque(false);

        iconContainer.setPreferredSize(
                new Dimension(
                        55,
                        55
                )
        );

        JLabel iconLabel =
                new JLabel(
                        icon,
                        SwingConstants.CENTER
                );

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        iconLabel.setForeground(
                SKY_BLUE
        );

        iconContainer.setLayout(
                new BorderLayout()
        );

        iconContainer.add(
                iconLabel,
                BorderLayout.CENTER
        );

        // Text
        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        titleLabel.setForeground(
                TEXT_MUTED
        );

        JLabel valLabel =
                new JLabel(
                        value != null
                                ? value
                                : "N/A"
                );

        valLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        valLabel.setForeground(
                GOLD
        );

        JPanel textPanel =
                new JPanel();

        textPanel.setOpaque(false);

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        textPanel.add(
                Box.createVerticalGlue()
        );

        textPanel.add(
                titleLabel
        );

        textPanel.add(
                Box.createVerticalStrut(4)
        );

        textPanel.add(
                valLabel
        );

        textPanel.add(
                Box.createVerticalGlue()
        );

        card.add(
                iconContainer,
                BorderLayout.WEST
        );

        card.add(
                textPanel,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // Detail Row
    // =========================================================

    private void addDetailRow(
            JPanel parent,
            String label,
            String value
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout()
                );

        row.setOpaque(false);

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        32
                )
        );

        JLabel keyLabel =
                new JLabel(
                        label + ":"
                );

        keyLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        keyLabel.setForeground(
                SKY_BLUE
        );

        JLabel valueLabel =
                new JLabel(
                        value != null
                                ? value
                                : "N/A"
                );

        valueLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        valueLabel.setForeground(
                TEXT_WHITE
        );

        row.add(
                keyLabel,
                BorderLayout.WEST
        );

        row.add(
                valueLabel,
                BorderLayout.EAST
        );

        parent.add(row);

        parent.add(
                Box.createVerticalStrut(8)
        );
    }
}
