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
    private ParcelGraphPanel graphPanel;

    private JLabel totalUsersValue;
    private JLabel totalRidersValue;
    private JLabel totalParcelsValue;
    private JLabel deliveredValue;
    private JLabel canceledValue;

    private JLabel activeParcelsValue;
    private JLabel deliveryRateValue;
    private JLabel canceledRateValue;

    private final Color BG = new Color(241, 248, 253);
    private final Color SUCCESS_BG = new Color(233, 248, 240);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT_DARK = new Color(31, 38, 43);
    private final Color TEXT_MUTED = new Color(103, 117, 128);

    private final Color ORANGE = new Color(248, 116, 35);
    private final Color ORANGE_HOVER = new Color(235, 94, 20);

    private final Color BLUE = new Color(0, 97, 153);
    private final Color LIGHT_BLUE = new Color(225, 240, 249);

    private final Color BORDER = new Color(216, 227, 234);

    private final Color GREEN = new Color(48, 148, 94);
    private final Color GREEN_BG = new Color(233, 248, 240);

    private final Color RED = new Color(205, 62, 73);
    private final Color RED_BG = new Color(252, 237, 239);

    private final Color GOLD = new Color(220, 165, 20);
    private final Color GOLD_BG = new Color(255, 248, 226);

    public AdminStatisticsFrame() {

        statistics = new AdminStatistics();

        setLayout(new BorderLayout());
        setBackground(BG);

        buildUI();
        refreshStatistics();
    }

    private void buildUI() {

        JPanel page = new JPanel(
                new BorderLayout(0, 18)
        );

        page.setOpaque(false);

        page.setBorder(
                new EmptyBorder(
                        24,
                        28,
                        24,
                        28
                )
        );

        JPanel header = new JPanel(
                new BorderLayout(14, 0)
        );

        header.setOpaque(false);

        JPanel iconPanel = new RoundedPanel(
                BLUE,
                16
        );

        iconPanel.setPreferredSize(
                new Dimension(
                        52,
                        52
                )
        );

        iconPanel.setLayout(
                new GridBagLayout()
        );

        JLabel iconLabel = new JLabel("▥");

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25
                )
        );

        iconLabel.setForeground(
                Color.WHITE
        );

        iconPanel.add(iconLabel);

        header.add(
                iconPanel,
                BorderLayout.WEST
        );

        JPanel titlePanel = new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel sectionLabel = new JLabel(
                "ADMINISTRATION"
        );

        sectionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        sectionLabel.setForeground(
                ORANGE
        );

        JLabel titleLabel = new JLabel(
                "System Statistics"
        );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        26
                )
        );

        titleLabel.setForeground(
                TEXT_DARK
        );

        JLabel subtitleLabel = new JLabel(
                "Monitor users, riders and parcel delivery activity"
        );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        subtitleLabel.setForeground(
                TEXT_MUTED
        );

        titlePanel.add(sectionLabel);
        titlePanel.add(
                Box.createVerticalStrut(3)
        );
        titlePanel.add(titleLabel);
        titlePanel.add(
                Box.createVerticalStrut(3)
        );
        titlePanel.add(subtitleLabel);

        header.add(
                titlePanel,
                BorderLayout.CENTER
        );

        JLabel liveBadge = new JLabel(
                "LIVE OVERVIEW"
        );

        liveBadge.setOpaque(true);

        liveBadge.setBackground(
                GREEN_BG
        );

        liveBadge.setForeground(
                GREEN
        );

        liveBadge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        liveBadge.setBorder(
                new EmptyBorder(
                        7,
                        11,
                        7,
                        11
                )
        );

        header.add(
                liveBadge,
                BorderLayout.EAST
        );

        page.add(
                header,
                BorderLayout.NORTH
        );

        JPanel mainCard = new RoundedPanel(
                WHITE,
                22
        );

        mainCard.setLayout(
                new BorderLayout(
                        0,
                        18
                )
        );

        mainCard.setBorder(
                new EmptyBorder(
                        20,
                        22,
                        20,
                        22
                )
        );

        JPanel overviewHeader = new JPanel(
                new BorderLayout()
        );

        overviewHeader.setOpaque(false);

        JPanel overviewTitlePanel = new JPanel();

        overviewTitlePanel.setOpaque(false);

        overviewTitlePanel.setLayout(
                new BoxLayout(
                        overviewTitlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel overviewTitle = new JLabel(
                "Performance Overview"
        );

        overviewTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        overviewTitle.setForeground(
                TEXT_DARK
        );

        JLabel overviewSubtitle = new JLabel(
                "Key system counts and current parcel activity"
        );

        overviewSubtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        overviewSubtitle.setForeground(
                TEXT_MUTED
        );

        overviewTitlePanel.add(
                overviewTitle
        );

        overviewTitlePanel.add(
                Box.createVerticalStrut(3)
        );

        overviewTitlePanel.add(
                overviewSubtitle
        );

        overviewHeader.add(
                overviewTitlePanel,
                BorderLayout.WEST
        );

        JPanel liveDotPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        6,
                        0
                )
        );

        liveDotPanel.setOpaque(false);

        JPanel liveDot = new RoundedPanel(
                GREEN,
                10
        );

        liveDot.setPreferredSize(
                new Dimension(
                        9,
                        9
                )
        );

        JLabel liveText = new JLabel(
                "Current Data"
        );

        liveText.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        liveText.setForeground(
                TEXT_MUTED
        );

        liveDotPanel.add(
                liveDot
        );

        liveDotPanel.add(
                liveText
        );

        overviewHeader.add(
                liveDotPanel,
                BorderLayout.EAST
        );

        mainCard.add(
                overviewHeader,
                BorderLayout.NORTH
        );

        statsGridPanel = new JPanel(
                new GridLayout(
                        2,
                        3,
                        12,
                        12
                )
        );

        statsGridPanel.setOpaque(false);

        mainCard.add(
                statsGridPanel,
                BorderLayout.CENTER
        );

        JPanel lowerSection = new JPanel(
                new BorderLayout(
                        16,
                        0
                )
        );

        lowerSection.setOpaque(false);

        JPanel graphCard = new RoundedPanel(
                new Color(251, 253, 254),
                18
        );

        graphCard.setLayout(
                new BorderLayout(
                        0,
                        12
                )
        );

        graphCard.setBorder(
                new EmptyBorder(
                        18,
                        18,
                        18,
                        18
                )
        );

        JPanel graphHeader = new JPanel(
                new BorderLayout()
        );

        graphHeader.setOpaque(false);

        JPanel graphTitlePanel = new JPanel();

        graphTitlePanel.setOpaque(false);

        graphTitlePanel.setLayout(
                new BoxLayout(
                        graphTitlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel graphTitle = new JLabel(
                "Parcel Status Overview"
        );

        graphTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        graphTitle.setForeground(
                TEXT_DARK
        );

        JLabel graphSubtitle = new JLabel(
                "Current distribution of parcel states"
        );

        graphSubtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        graphSubtitle.setForeground(
                TEXT_MUTED
        );

        graphTitlePanel.add(
                graphTitle
        );

        graphTitlePanel.add(
                Box.createVerticalStrut(3)
        );

        graphTitlePanel.add(
                graphSubtitle
        );

        graphHeader.add(
                graphTitlePanel,
                BorderLayout.WEST
        );

        JLabel snapshotBadge = new JLabel(
                "SNAPSHOT"
        );

        snapshotBadge.setOpaque(true);

        snapshotBadge.setBackground(
                LIGHT_BLUE
        );

        snapshotBadge.setForeground(
                BLUE
        );

        snapshotBadge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        snapshotBadge.setBorder(
                new EmptyBorder(
                        6,
                        9,
                        6,
                        9
                )
        );

        graphHeader.add(
                snapshotBadge,
                BorderLayout.EAST
        );

        graphCard.add(
                graphHeader,
                BorderLayout.NORTH
        );

        graphPanel = new ParcelGraphPanel();

        graphCard.add(
                graphPanel,
                BorderLayout.CENTER
        );

        lowerSection.add(
                graphCard,
                BorderLayout.CENTER
        );

        JPanel insightCard = new RoundedPanel(
                LIGHT_BLUE,
                18
        );

        insightCard.setLayout(
                new BorderLayout(
                        0,
                        14
                )
        );

        insightCard.setBorder(
                new EmptyBorder(
                        18,
                        18,
                        18,
                        18
                )
        );

        insightCard.setPreferredSize(
                new Dimension(
                        255,
                        0
                )
        );

        JLabel insightTitle = new JLabel(
                "Quick Summary"
        );

        insightTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        insightTitle.setForeground(
                TEXT_DARK
        );

        insightCard.add(
                insightTitle,
                BorderLayout.NORTH
        );

        JPanel insightRows = new JPanel();

        insightRows.setOpaque(false);

        insightRows.setLayout(
                new BoxLayout(
                        insightRows,
                        BoxLayout.Y_AXIS
                )
        );

        insightRows.add(
                createInsightRow(
                        "Active Parcels",
                        activeParcelsValue =
                                new JLabel("0"),
                        BLUE
                )
        );

        insightRows.add(
                Box.createVerticalStrut(12)
        );

        insightRows.add(
                createInsightRow(
                        "Delivery Rate",
                        deliveryRateValue =
                                new JLabel("0%"),
                        GREEN
                )
        );

        insightRows.add(
                Box.createVerticalStrut(12)
        );

        insightRows.add(
                createInsightRow(
                        "Cancellation Rate",
                        canceledRateValue =
                                new JLabel("0%"),
                        RED
                )
        );

        insightCard.add(
                insightRows,
                BorderLayout.CENTER
        );

        JPanel noteCard = new RoundedPanel(
                WHITE,
                13
        );

        noteCard.setLayout(
                new BorderLayout(
                        10,
                        0
                )
        );

        noteCard.setBorder(
                new EmptyBorder(
                        12,
                        13,
                        12,
                        13
                )
        );

        JPanel noteDot = new RoundedPanel(
                ORANGE,
                10
        );

        noteDot.setPreferredSize(
                new Dimension(
                        9,
                        9
                )
        );

        noteCard.add(
                noteDot,
                BorderLayout.WEST
        );

        JLabel noteText = new JLabel(
                "<html><b>System Snapshot</b><br>" +
                        "<span style='color:#687580'>" +
                        "Values reflect the currently loaded statistics." +
                        "</span></html>"
        );

        noteText.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        noteCard.add(
                noteText,
                BorderLayout.CENTER
        );

        insightCard.add(
                noteCard,
                BorderLayout.SOUTH
        );

        lowerSection.add(
                insightCard,
                BorderLayout.EAST
        );

        JPanel centerContainer = new JPanel(
                new BorderLayout(
                        0,
                        16
                )
        );

        centerContainer.setOpaque(false);

        centerContainer.add(
                mainCard,
                BorderLayout.CENTER
        );

        mainCard.remove(
                statsGridPanel
        );

        JPanel contentCard = new JPanel(
                new BorderLayout(
                        0,
                        18
                )
        );

        contentCard.setOpaque(false);

        JPanel statsSection = new RoundedPanel(
                WHITE,
                22
        );

        statsSection.setLayout(
                new BorderLayout(
                        0,
                        16
                )
        );

        statsSection.setBorder(
                new EmptyBorder(
                        20,
                        22,
                        20,
                        22
                )
        );

        statsSection.add(
                overviewHeader,
                BorderLayout.NORTH
        );

        statsSection.add(
                statsGridPanel,
                BorderLayout.CENTER
        );

        contentCard.add(
                statsSection,
                BorderLayout.NORTH
        );

        contentCard.add(
                lowerSection,
                BorderLayout.CENTER
        );

        JPanel bottomPanel = new JPanel(
                new BorderLayout()
        );

        bottomPanel.setOpaque(false);

        JLabel footerText = new JLabel(
                "Refresh the statistics to load the latest system data."
        );

        footerText.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        footerText.setForeground(
                TEXT_MUTED
        );

        bottomPanel.add(
                footerText,
                BorderLayout.WEST
        );

        JButton refreshButton =
                createPrimaryButton(
                        "↻  Refresh Data"
                );

        refreshButton.setPreferredSize(
                new Dimension(
                        145,
                        40
                )
        );

        refreshButton.addActionListener(
                e -> refreshStatistics()
        );

        bottomPanel.add(
                refreshButton,
                BorderLayout.EAST
        );

        contentCard.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        page.add(
                contentCard,
                BorderLayout.CENTER
        );

        add(
                page,
                BorderLayout.CENTER
        );
    }

    private JPanel createStatisticCard(
            String title,
            String tag,
            JLabel valueLabel,
            Color accent,
            Color softBackground
    ) {

        JPanel card = new RoundedPanel(
                WHITE,
                16
        );

        card.setLayout(
                new BorderLayout(
                        0,
                        10
                )
        );

        card.setBorder(
                new EmptyBorder(
                        16,
                        16,
                        16,
                        16
                )
        );

        JPanel topPanel = new JPanel(
                new BorderLayout()
        );

        topPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(
                title
        );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        titleLabel.setForeground(
                TEXT_DARK
        );

        JLabel tagLabel = new JLabel(
                tag
        );

        tagLabel.setOpaque(true);

        tagLabel.setBackground(
                softBackground
        );

        tagLabel.setForeground(
                accent
        );

        tagLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        tagLabel.setBorder(
                new EmptyBorder(
                        5,
                        7,
                        5,
                        7
                )
        );

        topPanel.add(
                titleLabel,
                BorderLayout.CENTER
        );

        topPanel.add(
                tagLabel,
                BorderLayout.EAST
        );

        card.add(
                topPanel,
                BorderLayout.NORTH
        );

        valueLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        valueLabel.setForeground(
                accent
        );

        card.add(
                valueLabel,
                BorderLayout.CENTER
        );

        JPanel accentLine = new JPanel();

        accentLine.setBackground(
                accent
        );

        accentLine.setPreferredSize(
                new Dimension(
                        0,
                        3
                )
        );

        card.add(
                accentLine,
                BorderLayout.SOUTH
        );

        return card;
    }

    private JPanel createInsightRow(
            String title,
            JLabel valueLabel,
            Color accent
    ) {

        JPanel row = new JPanel(
                new BorderLayout(
                        10,
                        0
                )
        );

        row.setOpaque(false);

        JPanel accentPanel = new RoundedPanel(
                accent,
                8
        );

        accentPanel.setPreferredSize(
                new Dimension(
                        7,
                        38
                )
        );

        row.add(
                accentPanel,
                BorderLayout.WEST
        );

        JLabel titleLabel = new JLabel(
                title
        );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        titleLabel.setForeground(
                TEXT_MUTED
        );

        row.add(
                titleLabel,
                BorderLayout.CENTER
        );

        valueLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        valueLabel.setForeground(
                accent
        );

        row.add(
                valueLabel,
                BorderLayout.EAST
        );

        return row;
    }

    private void populateStatistics() {

        statsGridPanel.removeAll();

        totalUsersValue = new JLabel(
                String.valueOf(
                        statistics.getTotal_users()
                )
        );

        totalRidersValue = new JLabel(
                String.valueOf(
                        statistics.getTotal_riders()
                )
        );

        totalParcelsValue = new JLabel(
                String.valueOf(
                        statistics.getTotal_parcels()
                )
        );

        deliveredValue = new JLabel(
                String.valueOf(
                        statistics.getTotal_delivered_parcels()
                )
        );

        canceledValue = new JLabel(
                String.valueOf(
                        statistics.getTotal_canceled_parcels()
                )
        );

        statsGridPanel.add(
                createStatisticCard(
                        "Registered Users",
                        "USERS",
                        totalUsersValue,
                        BLUE,
                        LIGHT_BLUE
                )
        );

        statsGridPanel.add(
                createStatisticCard(
                        "Delivery Riders",
                        "RIDERS",
                        totalRidersValue,
                        ORANGE,
                        new Color(255, 239, 229)
                )
        );

        statsGridPanel.add(
                createStatisticCard(
                        "Total Parcels",
                        "PARCELS",
                        totalParcelsValue,
                        GOLD,
                        GOLD_BG
                )
        );

        statsGridPanel.add(
                createStatisticCard(
                        "Delivered Parcels",
                        "DELIVERED",
                        deliveredValue,
                        GREEN,
                        GREEN_BG
                )
        );

        statsGridPanel.add(
                createStatisticCard(
                        "Canceled Parcels",
                        "CANCELED",
                        canceledValue,
                        RED,
                        RED_BG
                )
        );

        statsGridPanel.add(
                createStatisticCard(
                        "System Status",
                        "ACTIVE",
                        new JLabel("ONLINE"),
                        GREEN,
                        GREEN_BG
                )
        );

        updateDerivedStatistics();

        statsGridPanel.revalidate();
        statsGridPanel.repaint();

        if (graphPanel != null) {
            graphPanel.updateData(
                    statistics.getTotal_parcels(),
                    statistics.getTotal_delivered_parcels(),
                    statistics.getTotal_canceled_parcels()
            );
        }
    }

    private void updateDerivedStatistics() {

        int total =
                statistics.getTotal_parcels();

        int delivered =
                statistics.getTotal_delivered_parcels();

        int canceled =
                statistics.getTotal_canceled_parcels();

        int active =
                Math.max(
                        0,
                        total - delivered - canceled
                );

        activeParcelsValue.setText(
                String.valueOf(active)
        );

        double deliveryRate =
                total > 0
                        ? (delivered * 100.0) / total
                        : 0;

        double canceledRate =
                total > 0
                        ? (canceled * 100.0) / total
                        : 0;

        deliveryRateValue.setText(
                String.format(
                        "%.1f%%",
                        deliveryRate
                )
        );

        canceledRateValue.setText(
                String.format(
                        "%.1f%%",
                        canceledRate
                )
        );
    }

    private JButton createPrimaryButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

                    private boolean hovered;

                    {
                        addMouseListener(
                                new MouseAdapter() {

                                    @Override
                                    public void mouseEntered(
                                            MouseEvent e
                                    ) {
                                        hovered = true;
                                        repaint();
                                    }

                                    @Override
                                    public void mouseExited(
                                            MouseEvent e
                                    ) {
                                        hovered = false;
                                        repaint();
                                    }
                                }
                        );
                    }

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        Graphics2D g2 =
                                (Graphics2D) g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        g2.setColor(
                                hovered
                                        ? ORANGE_HOVER
                                        : ORANGE
                        );

                        g2.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        12,
                                        12
                                )
                        );

                        g2.dispose();

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
                Color.WHITE
        );

        button.setContentAreaFilled(
                false
        );

        button.setOpaque(false);

        button.setBorderPainted(
                false
        );

        button.setFocusPainted(
                false
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    public void refreshStatistics() {

        if (statistics == null) {

            statistics =
                    new AdminStatistics();

        } else {

            statistics.loadStatistics();
        }

        populateStatistics();
    }

    private static class ParcelGraphPanel
            extends JPanel {

        private int total;
        private int delivered;
        private int canceled;

        private final Color BLUE =
                new Color(0, 97, 153);

        private final Color ORANGE =
                new Color(248, 116, 35);

        private final Color GREEN =
                new Color(48, 148, 94);

        private final Color RED =
                new Color(205, 62, 73);

        private final Color GRID =
                new Color(220, 229, 235);

        private final Color TEXT =
                new Color(103, 117, 128);

        public ParcelGraphPanel() {

            setOpaque(false);

            setPreferredSize(
                    new Dimension(
                            500,
                            240
                    )
            );
        }

        public void updateData(
                int total,
                int delivered,
                int canceled
        ) {

            this.total = Math.max(
                    0,
                    total
            );

            this.delivered = Math.max(
                    0,
                    delivered
            );

            this.canceled = Math.max(
                    0,
                    canceled
            );

            repaint();
        }

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

            int width = getWidth();
            int height = getHeight();

            int left = 50;
            int right = 25;
            int top = 18;
            int bottom = 48;

            int chartWidth =
                    Math.max(
                            1,
                            width - left - right
                    );

            int chartHeight =
                    Math.max(
                            1,
                            height - top - bottom
                    );

            int active =
                    Math.max(
                            0,
                            total - delivered - canceled
                    );

            int maxValue =
                    Math.max(
                            1,
                            total
                    );

            for (int i = 0; i <= 4; i++) {

                int y =
                        top +
                                (chartHeight * i / 4);

                g2.setColor(
                        GRID
                );

                g2.drawLine(
                        left,
                        y,
                        width - right,
                        y
                );

                int labelValue =
                        maxValue -
                                (maxValue * i / 4);

                g2.setColor(
                        TEXT
                );

                g2.setFont(
                        new Font(
                                "SansSerif",
                                Font.PLAIN,
                                9
                        )
                );

                g2.drawString(
                        String.valueOf(
                                labelValue
                        ),
                        18,
                        y + 4
                );
            }

            String[] labels = {
                    "Active",
                    "Delivered",
                    "Canceled"
            };

            int[] values = {
                    active,
                    delivered,
                    canceled
            };

            Color[] colors = {
                    BLUE,
                    GREEN,
                    RED
            };

            int groupWidth =
                    chartWidth / 3;

            int barWidth =
                    Math.min(
                            70,
                            Math.max(
                                    35,
                                    groupWidth / 3
                            )
                    );

            for (int i = 0; i < 3; i++) {

                int value =
                        values[i];

                int barHeight =
                        total > 0
                                ? (int) (
                                (value /
                                        (double) maxValue)
                                        * chartHeight
                        )
                                : 0;

                int centerX =
                        left +
                                groupWidth * i +
                                groupWidth / 2;

                int x =
                        centerX -
                                barWidth / 2;

                int y =
                        top +
                                chartHeight -
                                barHeight;

                g2.setColor(
                        colors[i]
                );

                g2.fillRoundRect(
                        x,
                        y,
                        barWidth,
                        Math.max(
                                2,
                                barHeight
                        ),
                        10,
                        10
                );

                g2.setColor(
                        TEXT
                );

                g2.setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                10
                        )
                );

                String valueText =
                        String.valueOf(
                                value
                        );

                FontMetrics fm =
                        g2.getFontMetrics();

                g2.drawString(
                        valueText,
                        centerX -
                                fm.stringWidth(
                                        valueText
                                ) / 2,
                        Math.max(
                                top + 12,
                                y - 7
                        )
                );

                g2.setFont(
                        new Font(
                                "SansSerif",
                                Font.PLAIN,
                                10
                        )
                );

                g2.drawString(
                        labels[i],
                        centerX -
                                g2.getFontMetrics()
                                        .stringWidth(
                                                labels[i]
                                        ) / 2,
                        height - 17
                );
            }

            g2.setColor(
                    GRID
            );

            g2.drawLine(
                    left,
                    top + chartHeight,
                    width - right,
                    top + chartHeight
            );

            g2.dispose();
        }
    }

    private static class RoundedPanel
            extends JPanel {

        private final Color background;
        private final int radius;

        public RoundedPanel(
                Color background,
                int radius
        ) {

            this.background = background;
            this.radius = radius;

            setOpaque(false);
        }

        @Override
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    background
            );

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    radius,
                    radius
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }
}