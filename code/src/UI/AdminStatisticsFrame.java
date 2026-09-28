package UI;

import model.AdminStatistics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class AdminStatisticsFrame extends JPanel {

    private AdminStatistics statistics;
    private JPanel statsGridPanel;

    // =========================================================
    // THEME COLORS
    // =========================================================

    private final Color BLUE = new Color(0, 97, 153);
    private final Color SKY_BLUE = new Color(138, 207, 248);
    private final Color SOFT_YELLOW = new Color(244, 235, 108);
    private final Color GOLD = new Color(255, 212, 68);

    private final Color CARD_BG = new Color(0, 55, 88, 238);
    private final Color CARD_BORDER = new Color(138, 207, 248, 90);

    private final Color STAT_CARD_BG = new Color(5, 63, 92, 245);
    private final Color STAT_CARD_BORDER = new Color(138, 207, 248, 70);

    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_MUTED = new Color(190, 220, 235);

    // Button colors
    private final Color BTN_TOP = new Color(255, 224, 90);
    private final Color BTN_BOTTOM = new Color(255, 212, 68);
    private final Color BTN_HOVER_TOP = new Color(255, 235, 135);
    private final Color BTN_HOVER_BOTTOM = new Color(255, 202, 45);
    private final Color BTN_TEXT = new Color(35, 55, 65);

    public AdminStatisticsFrame() {

        statistics = new AdminStatistics();

        setLayout(new BorderLayout());
        setOpaque(false);

        buildUI();
        refreshStatistics();
    }

    // =========================================================
    // BUILD UI
    // =========================================================

    private void buildUI() {

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2d =
                        (Graphics2D) g.create();

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Main card
                g2d.setColor(CARD_BG);

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

                // Card border
                g2d.setColor(CARD_BORDER);

                g2d.setStroke(
                        new BasicStroke(1f)
                );

                g2d.draw(
                        new RoundRectangle2D.Float(
                                0.5f,
                                0.5f,
                                getWidth() - 1,
                                getHeight() - 1,
                                18,
                                18
                        )
                );

                g2d.dispose();
            }
        };

        mainPanel.setOpaque(false);

        mainPanel.setLayout(
                new BorderLayout(0, 20)
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        24,
                        28,
                        24,
                        28
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel titlePanel =
                new JPanel(new BorderLayout());

        titlePanel.setOpaque(false);

        // Gold accent
        JPanel titleAccent = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 =
                        (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(GOLD);

                g2.fillRoundRect(
                        0,
                        2,
                        5,
                        48,
                        4,
                        4
                );

                g2.dispose();
            }
        };

        titleAccent.setOpaque(false);

        titleAccent.setPreferredSize(
                new Dimension(12, 52)
        );

        JPanel headerTextGroup =
                new JPanel();

        headerTextGroup.setLayout(
                new BoxLayout(
                        headerTextGroup,
                        BoxLayout.Y_AXIS
                )
        );

        headerTextGroup.setOpaque(false);

        JLabel titleLabel =
                new JLabel(
                        "System Statistics Overview"
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        titleLabel.setForeground(
                TEXT_WHITE
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Real-time telemetry and operation counts"
                );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        subtitleLabel.setForeground(
                TEXT_MUTED
        );

        headerTextGroup.add(
                titleLabel
        );

        headerTextGroup.add(
                Box.createVerticalStrut(5)
        );

        headerTextGroup.add(
                subtitleLabel
        );

        JPanel headerLeft =
                new JPanel(
                        new BorderLayout(8, 0)
                );

        headerLeft.setOpaque(false);

        headerLeft.add(
                titleAccent,
                BorderLayout.WEST
        );

        headerLeft.add(
                headerTextGroup,
                BorderLayout.CENTER
        );

        // Header badge
        JLabel badge =
                new JLabel("LIVE STATISTICS");

        badge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        badge.setForeground(
                new Color(255, 245, 180)
        );

        badge.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        badge.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        255,
                                        212,
                                        68,
                                        130
                                ),
                                1
                        ),
                        new EmptyBorder(
                                6,
                                12,
                                6,
                                12
                        )
                )
        );

        titlePanel.add(
                headerLeft,
                BorderLayout.WEST
        );

        titlePanel.add(
                badge,
                BorderLayout.EAST
        );

        mainPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // STATISTICS GRID
        // =====================================================

        statsGridPanel =
                new JPanel(
                        new GridLayout(
                                5,
                                1,
                                0,
                                12
                        )
                );

        statsGridPanel.setOpaque(false);

        mainPanel.add(
                statsGridPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM BUTTON
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                0
                        )
                );

        bottomPanel.setOpaque(false);

        JButton refreshButton =
                createGoldenButton(
                        "Refresh Data"
                );

        refreshButton.setPreferredSize(
                new Dimension(
                        140,
                        38
                )
        );

        refreshButton.addActionListener(
                e -> refreshStatistics()
        );

        bottomPanel.add(
                refreshButton
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // POPULATE STATISTICS
    // =========================================================

    private void populateStatistics() {

        statsGridPanel.removeAll();

        addStatisticCard(
                "Total Registered Users",
                String.valueOf(
                        statistics.getTotal_users()
                ),
                "USERS"
        );

        addStatisticCard(
                "Registered Delivery Riders",
                String.valueOf(
                        statistics.getTotal_riders()
                ),
                "RIDERS"
        );

        addStatisticCard(
                "Total Parcels Created",
                String.valueOf(
                        statistics.getTotal_parcels()
                ),
                "PARCELS"
        );

        addStatisticCard(
                "Successfully Delivered Parcels",
                String.valueOf(
                        statistics.getTotal_delivered_parcels()
                ),
                "DELIVERED"
        );

        addStatisticCard(
                "Canceled / Voided Parcels",
                String.valueOf(
                        statistics.getTotal_canceled_parcels()
                ),
                "CANCELED"
        );

        statsGridPanel.revalidate();
        statsGridPanel.repaint();
    }

    // =========================================================
    // STATISTIC CARD
    // =========================================================

    private void addStatisticCard(
            String name,
            String value,
            String tag
    ) {

        JPanel statRow =
                new JPanel(
                        new BorderLayout(15, 0)
                ) {

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
                                STAT_CARD_BG
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

                        // Card border
                        g2d.setColor(
                                STAT_CARD_BORDER
                        );

                        g2d.setStroke(
                                new BasicStroke(1f)
                        );

                        g2d.draw(
                                new RoundRectangle2D.Float(
                                        0.5f,
                                        0.5f,
                                        getWidth() - 1,
                                        getHeight() - 1,
                                        12,
                                        12
                                )
                        );

                        // Small gold accent
                        g2d.setColor(
                                GOLD
                        );

                        g2d.fillRoundRect(
                                0,
                                10,
                                4,
                                getHeight() - 20,
                                3,
                                3
                        );

                        g2d.dispose();
                    }
                };

        statRow.setOpaque(false);

        statRow.setBorder(
                new EmptyBorder(
                        12,
                        20,
                        12,
                        20
                )
        );

        // Name
        JLabel nameLabel =
                new JLabel(name);

        nameLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        nameLabel.setForeground(
                TEXT_WHITE
        );

        // Tag
        JLabel tagLabel =
                new JLabel(tag);

        tagLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        tagLabel.setForeground(
                SKY_BLUE
        );

        tagLabel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        138,
                                        207,
                                        248,
                                        90
                                ),
                                1
                        ),
                        new EmptyBorder(
                                4,
                                8,
                                4,
                                8
                        )
                )
        );

        // Value
        JLabel valueLabel =
                new JLabel(
                        value,
                        SwingConstants.RIGHT
                );

        valueLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        valueLabel.setForeground(
                GOLD
        );

        // Left side
        JPanel leftPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                0,
                                0
                        )
                );

        leftPanel.setOpaque(false);

        leftPanel.add(
                nameLabel
        );

        leftPanel.add(
                Box.createHorizontalStrut(12)
        );

        leftPanel.add(
                tagLabel
        );

        statRow.add(
                leftPanel,
                BorderLayout.WEST
        );

        statRow.add(
                valueLabel,
                BorderLayout.EAST
        );

        statsGridPanel.add(
                statRow
        );
    }

    // =========================================================
    // GOLDEN BUTTON
    // =========================================================

    private JButton createGoldenButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

                    private boolean isHovered = false;

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
                                        ? BTN_HOVER_TOP
                                        : BTN_TOP;

                        Color bottom =
                                isHovered
                                        ? BTN_HOVER_BOTTOM
                                        : BTN_BOTTOM;

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
                                        10,
                                        10
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

        button.setContentAreaFilled(false);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // REFRESH STATISTICS
    // =========================================================

    public void refreshStatistics() {

        if (statistics == null) {

            statistics =
                    new AdminStatistics();

        } else {

            statistics.loadStatistics();
        }

        populateStatistics();
    }
}

