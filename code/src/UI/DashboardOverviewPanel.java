package UI;

import custom_exception.UnauthorizedAccessException;
import model.AdminStatistics;
import model.Parcel;
import model.Rider;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class DashboardOverviewPanel extends JPanel {

    // =========================================================
    // REFERENCE THEME
    // =========================================================

    private static final Color BG =
            new Color(241, 248, 253);

    private static final Color WHITE =
            Color.WHITE;

    private static final Color BLACK =
            new Color(15, 18, 20);

    private static final Color TEXT_DARK =
            new Color(30, 37, 42);

    private static final Color TEXT_MUTED =
            new Color(103, 117, 128);

    private static final Color ORANGE =
            new Color(248, 116, 35);

    private static final Color ORANGE_LIGHT =
            new Color(255, 243, 235);

    private static final Color BLUE =
            new Color(0, 97, 153);

    private static final Color LIGHT_BLUE =
            new Color(222, 239, 249);

    private static final Color SKY_BLUE =
            new Color(181, 219, 241);

    private static final Color BORDER =
            new Color(216, 227, 234);

    private static final Color SUCCESS =
            new Color(43, 151, 98);

    private static final Color SUCCESS_LIGHT =
            new Color(233, 248, 240);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DashboardOverviewPanel(User user) {

        setOpaque(false);

        setLayout(
                new BorderLayout(
                        0,
                        18
                )
        );

        setBorder(
                new EmptyBorder(
                        18,
                        20,
                        18,
                        20
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        add(
                createHeader(user),
                BorderLayout.NORTH
        );

        // =====================================================
        // MAIN CONTENT
        // =====================================================

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                0,
                                18
                        )
                );

        center.setOpaque(false);

        // Statistics
        JPanel statsGrid =
                createStatisticsSection(user);

        center.add(
                statsGrid,
                BorderLayout.NORTH
        );

        // Account information
        JPanel detailsPanel =
                createAccountDetails(user);

        center.add(
                detailsPanel,
                BorderLayout.CENTER
        );

        add(
                center,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeader(User user) {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JPanel left =
                new JPanel();

        left.setOpaque(false);

        left.setLayout(
                new BoxLayout(
                        left,
                        BoxLayout.Y_AXIS
                )
        );

        // Small section label
        JLabel sectionLabel =
                new JLabel(
                        "CONTROL CENTER"
                );

        sectionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        sectionLabel.setForeground(
                ORANGE
        );

        // Main title
        JLabel title =
                new JLabel(
                        "Dashboard Overview"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        27
                )
        );

        title.setForeground(
                BLACK
        );

        // Subtitle
        String userName =
                user.getUser_name() != null
                        ? user.getUser_name()
                        : "User";

        JLabel subtitle =
                new JLabel(
                        "Welcome back, "
                                + userName
                                + "  •  Here's your account summary"
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        subtitle.setForeground(
                TEXT_MUTED
        );

        left.add(
                sectionLabel
        );

        left.add(
                Box.createVerticalStrut(5)
        );

        left.add(
                title
        );

        left.add(
                Box.createVerticalStrut(5)
        );

        left.add(
                subtitle
        );

        // =====================================================
        // RIGHT STATUS BADGE
        // =====================================================

        JPanel statusCard =
                new JPanel(
                        new BorderLayout(
                                8,
                                0
                        )
                );

        statusCard.setOpaque(true);

        statusCard.setBackground(
                SUCCESS_LIGHT
        );

        statusCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        188,
                                        226,
                                        204
                                ),
                                1,
                                true
                        ),
                        new EmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        JLabel dot =
                new JLabel(
                        "●"
                );

        dot.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        dot.setForeground(
                SUCCESS
        );

        JLabel online =
                new JLabel(
                        "SYSTEM ONLINE"
                );

        online.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        online.setForeground(
                new Color(
                        50,
                        111,
                        76
                )
        );

        statusCard.add(
                dot,
                BorderLayout.WEST
        );

        statusCard.add(
                online,
                BorderLayout.CENTER
        );

        header.add(
                left,
                BorderLayout.WEST
        );

        header.add(
                statusCard,
                BorderLayout.EAST
        );

        return header;
    }

    // =========================================================
    // STATISTICS SECTION
    // =========================================================

    private JPanel createStatisticsSection(
            User user
    ) {

        JPanel statsGrid =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                14,
                                0
                        )
                );

        statsGrid.setOpaque(false);

        String roleStr =
                user.getUser_role() != null
                        ? user.getUser_role().toUpperCase()
                        : "";

        // =====================================================
        // ADMIN
        // =====================================================

        if (
                roleStr.equalsIgnoreCase(
                        String.valueOf(
                                User.UserRole.ADMIN
                        )
                )
        ) {

            AdminStatistics stats =
                    new AdminStatistics();

            statsGrid.add(
                    createStatCard(
                            "TOTAL USERS",
                            String.valueOf(
                                    stats.getTotal_users()
                            ),
                            "USERS",
                            BLUE
                    )
            );

            statsGrid.add(
                    createStatCard(
                            "TOTAL RIDERS",
                            String.valueOf(
                                    stats.getTotal_riders()
                            ),
                            "RIDERS",
                            ORANGE
                    )
            );

            statsGrid.add(
                    createStatCard(
                            "TOTAL PARCELS",
                            String.valueOf(
                                    stats.getTotal_parcels()
                            ),
                            "PARCELS",
                            new Color(
                                    64,
                                    151,
                                    194
                            )
                    ) );
        }

        // =====================================================
        // RIDER
        // =====================================================

        else if (
                roleStr.equalsIgnoreCase(
                        String.valueOf(
                                User.UserRole.RIDER
                        )
                )
        ) {

            try {

                Rider rider =
                        new Rider(user);

                statsGrid.add(
                        createStatCard(
                                "RIDER STATUS",
                                "ACTIVE",
                                "STATUS",
                                SUCCESS
                        )
                );

                statsGrid.add(
                        createStatCard(
                                "RIDER ID",
                                safeValue(
                                        user.getUser_id()
                                ),
                                "ID",
                                BLUE
                        )
                );

                statsGrid.add(
                        createStatCard(
                                "ROLE",
                                safeValue(
                                        user.getUser_role()
                                ),
                                "ROLE",
                                ORANGE
                        ));

            } catch (
                    UnauthorizedAccessException e
            ) {

                statsGrid.add(
                        createStatCard(
                                "ACCESS",
                                "DENIED",
                                "SECURITY",
                                ORANGE
                        )
                );

                statsGrid.add(
                        createStatCard(
                                "USER ID",
                                safeValue(
                                        user.getUser_id()
                                ),
                                "ID",
                                BLUE
                        )
                );

                statsGrid.add(
                        createStatCard(
                                "ROLE",
                                safeValue(
                                        user.getUser_role()
                                ),
                                "ROLE",
                                ORANGE
                        )
                );
            }
        }

        // =====================================================
        // USER
        // =====================================================

        else if (
                roleStr.equalsIgnoreCase(
                        String.valueOf(
                                User.UserRole.USER
                        )
                )
        ) {

            try {

                Parcel parcelModel =
                        new Parcel(user);

                statsGrid.add(
                        createStatCard(
                                "SENDER ID",
                                safeValue(
                                        user.getUser_id()
                                ),
                                "ID",
                                BLUE
                        )
                );

                statsGrid.add(
                        createStatCard(
                                "ACCOUNT",
                                safeValue(
                                        user.getUser_email()
                                ),
                                "EMAIL",
                                ORANGE
                        )
                );

                statsGrid.add(
                        createStatCard(
                                "ROLE",
                                safeValue(
                                        user.getUser_role()
                                ),
                                "ROLE",
                                new Color(
                                        64,
                                        151,
                                        194
                                )
                        ));

            } catch (
                    UnauthorizedAccessException e
            ) {

                statsGrid.add(
                        createStatCard(
                                "ACCESS",
                                "DENIED",
                                "SECURITY",
                                ORANGE
                        )
                );

                statsGrid.add(
                        createStatCard(
                                "SENDER ID",
                                safeValue(
                                        user.getUser_id()
                                ),
                                "ID",
                                BLUE
                        )
                );

                statsGrid.add(
                        createStatCard(
                                "ROLE",
                                safeValue(
                                        user.getUser_role()
                                ),
                                "ROLE",
                                new Color(
                                        64,
                                        151,
                                        194
                                )
                        )
                );
            }
        }

        return statsGrid;
    }

    // =========================================================
    // STAT CARD
    // =========================================================

    private JPanel createStatCard(
            String title,
            String value,
            String category,
            Color accent
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                13,
                                0
                        )
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

                        // White card
                        g2.setColor(
                                WHITE
                        );

                        g2.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        16,
                                        16
                                )
                        );

                        // Border
                        g2.setColor(
                                BORDER
                        );

                        g2.draw(
                                new RoundRectangle2D.Float(
                                        0.5f,
                                        0.5f,
                                        getWidth() - 1,
                                        getHeight() - 1,
                                        16,
                                        16
                                )
                        );

                        // Orange top accent
                        g2.setColor(
                                accent
                        );

                        g2.fillRoundRect(
                                20,
                                0,
                                Math.min(
                                        70,
                                        getWidth() - 40
                                ),
                                4,
                                4,
                                4
                        );

                        g2.dispose();
                    }
                };

        card.setOpaque(false);

        card.setBorder(
                new EmptyBorder(
                        16,
                        16,
                        16,
                        16
                )
        );

        card.setPreferredSize(
                new Dimension(
                        220,
                        105
                )
        );

        // =====================================================
        // ICON
        // =====================================================

        JPanel iconPanel =
                new JPanel(
                        new GridBagLayout()
                );

        iconPanel.setOpaque(true);

        iconPanel.setBackground(
                mixWithWhite(
                        accent,
                        0.12f
                )
        );

        iconPanel.setPreferredSize(
                new Dimension(
                        48,
                        48
                )
        );

        iconPanel.setBorder(
                BorderFactory.createLineBorder(
                        mixWithWhite(
                                accent,
                                0.55f
                        ),
                        1,
                        true
                )
        );

        JLabel icon =
                new JLabel(
                        category
                                .substring(
                                        0,
                                        1
                                )
                );

        icon.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        icon.setForeground(
                accent
        );

        iconPanel.add(
                icon
        );

        // =====================================================
        // TEXT
        // =====================================================

        JPanel text =
                new JPanel();

        text.setOpaque(false);

        text.setLayout(
                new BoxLayout(
                        text,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel categoryLabel =
                new JLabel(
                        category
                );

        categoryLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        categoryLabel.setForeground(
                accent
        );

        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        titleLabel.setForeground(
                TEXT_MUTED
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
                        Font.BOLD,
                        17
                )
        );

        valueLabel.setForeground(
                TEXT_DARK
        );

        text.add(
                categoryLabel
        );

        text.add(
                Box.createVerticalStrut(
                        4
                )
        );

        text.add(
                titleLabel
        );

        text.add(
                Box.createVerticalStrut(
                        4
                )
        );

        text.add(
                valueLabel
        );

        card.add(
                iconPanel,
                BorderLayout.WEST
        );

        card.add(
                text,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // ACCOUNT DETAILS
    // =========================================================

    private JPanel createAccountDetails(
            User user
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
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

                        // Card
                        g2.setColor(
                                WHITE
                        );

                        g2.fill(
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
                        g2.setColor(
                                BORDER
                        );

                        g2.draw(
                                new RoundRectangle2D.Float(
                                        0.5f,
                                        0.5f,
                                        getWidth() - 1,
                                        getHeight() - 1,
                                        18,
                                        18
                                )
                        );

                        // Orange top line
                        g2.setColor(
                                ORANGE
                        );

                        g2.fillRoundRect(
                                22,
                                0,
                                85,
                                4,
                                4,
                                4
                        );

                        g2.dispose();
                    }
                };

        card.setOpaque(false);

        card.setBorder(
                new EmptyBorder(
                        20,
                        22,
                        20,
                        22
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JPanel titleGroup =
                new JPanel();

        titleGroup.setOpaque(false);

        titleGroup.setLayout(
                new BoxLayout(
                        titleGroup,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "Account Summary"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        title.setForeground(
                BLACK
        );

        JLabel subtitle =
                new JLabel(
                        "Your registered account information"
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        subtitle.setForeground(
                TEXT_MUTED
        );

        titleGroup.add(
                title
        );

        titleGroup.add(
                Box.createVerticalStrut(
                        4
                )
        );

        titleGroup.add(
                subtitle
        );

        header.add(
                titleGroup,
                BorderLayout.WEST
        );

        // =====================================================
        // ROLE BADGE
        // =====================================================

        String role =
                user.getUser_role() != null
                        ? user.getUser_role().toUpperCase()
                        : "USER";

        JLabel roleBadge =
                new JLabel(
                        "  " + role + "  "
                );

        roleBadge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
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
                        7,
                        10,
                        7,
                        10
                )
        );

        header.add(
                roleBadge,
                BorderLayout.EAST
        );

        card.add(
                header,
                BorderLayout.NORTH
        );

        // =====================================================
        // DETAILS
        // =====================================================

        JPanel details =
                new JPanel();

        details.setOpaque(false);

        details.setLayout(
                new BoxLayout(
                        details,
                        BoxLayout.Y_AXIS
                )
        );

        String roleStr =
                user.getUser_role() != null
                        ? user.getUser_role().toUpperCase()
                        : "";

        // -----------------------------------------------------
        // COMMON USER INFORMATION
        // -----------------------------------------------------

        addDetailRow(
                details,
                "User ID",
                user.getUser_id()
        );

        addDetailRow(
                details,
                "Full Name",
                user.getUser_name()
        );

        addDetailRow(
                details,
                "Email Address",
                user.getUser_email()
        );

        addDetailRow(
                details,
                "Account Role",
                user.getUser_role()
        );

        // -----------------------------------------------------
        // ADMIN EXTRA DETAILS
        // -----------------------------------------------------

        if (
                roleStr.equalsIgnoreCase(
                        String.valueOf(
                                User.UserRole.ADMIN
                        )
                )
        ) {

            AdminStatistics stats =
                    new AdminStatistics();

            addDetailRow(
                    details,
                    "Delivered Parcels",
                    String.valueOf(
                            stats.getTotal_delivered_parcels()
                    )
            );

            addDetailRow(
                    details,
                    "Canceled Parcels",
                    String.valueOf(
                            stats.getTotal_canceled_parcels()
                    )
            );
        }

        // =====================================================
        // DETAILS SCROLL
        // =====================================================

        JScrollPane scroll =
                new JScrollPane(
                        details
                );

        scroll.setOpaque(false);

        scroll.getViewport()
                .setOpaque(false);

        scroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scroll.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        scroll.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scroll.getVerticalScrollBar()
                .setUnitIncrement(
                        12
                );

        card.add(
                scroll,
                BorderLayout.CENTER
        );

        return card;
    }

    // =========================================================
    // DETAIL ROW
    // =========================================================

    private void addDetailRow(
            JPanel parent,
            String label,
            String value
    ) {

        JPanel row =
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

                        g2.setColor(
                                new Color(
                                        235,
                                        241,
                                        245
                                )
                        );

                        g2.fillRoundRect(
                                0,
                                getHeight() - 1,
                                getWidth(),
                                1,
                                1,
                                1
                        );

                        g2.dispose();
                    }
                };

        row.setOpaque(false);

        row.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        row.setBorder(
                new EmptyBorder(
                        6,
                        2,
                        6,
                        2
                )
        );

        // Label
        JLabel key =
                new JLabel(
                        label
                );

        key.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        key.setForeground(
                TEXT_MUTED
        );

        // Value
        JLabel val =
                new JLabel(
                        value != null
                                ? value
                                : "N/A"
                );

        val.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        val.setForeground(
                TEXT_DARK
        );

        row.add(
                key,
                BorderLayout.WEST
        );

        row.add(
                val,
                BorderLayout.EAST
        );

        parent.add(
                row
        );
    }

    // =========================================================
    // SAFE VALUE
    // =========================================================

    private String safeValue(
            String value
    ) {

        return value != null
                ? value
                : "N/A";
    }

    // =========================================================
    // COLOR HELPER
    // =========================================================

    private Color mixWithWhite(
            Color color,
            float amount
    ) {

        int r =
                (int)
                        (
                                color.getRed()
                                        +
                                        (
                                                255
                                                        - color.getRed()
                                        )
                                                * amount
                        );

        int g =
                (int)
                        (
                                color.getGreen()
                                        +
                                        (
                                                255
                                                        - color.getGreen()
                                        )
                                                * amount
                        );

        int b =
                (int)
                        (
                                color.getBlue()
                                        +
                                        (
                                                255
                                                        - color.getBlue()
                                        )
                                                * amount
                        );

        return new Color(
                r,
                g,
                b
        );
    }
}