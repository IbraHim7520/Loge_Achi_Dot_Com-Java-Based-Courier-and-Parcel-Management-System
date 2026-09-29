package UI;

import model.Rider;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class RiderDashboard extends JPanel {

    private final User user;
    private final MainFrame mainFrame;

    private static final Color BG =
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

    private static final Color BLUE =
            new Color(0, 97, 153);

    private static final Color LIGHT_BLUE =
            new Color(225, 240, 249);

    private static final Color SKY_BLUE =
            new Color(181, 219, 241);

    private static final Color BORDER =
            new Color(216, 227, 234);

    private static final Color SUCCESS =
            new Color(48, 148, 94);

    private static final Color SUCCESS_BG =
            new Color(233, 248, 240);

    public RiderDashboard(
            User user,
            MainFrame mainFrame
    ) {

        this.user = user;
        this.mainFrame = mainFrame;

        setLayout(
                new BorderLayout()
        );

        setOpaque(false);

        try {

            new Rider(user);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Access Denied",
                    JOptionPane.ERROR_MESSAGE
            );

            if (this.mainFrame != null) {
                this.mainFrame.showLoginFrame();
            }

            return;
        }

        buildUI();
    }

    public RiderDashboard(User user) {
        this(user, null);
    }

    private void buildUI() {

        JPanel mainCard =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
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

                        g2.setColor(
                                WHITE
                        );

                        g2.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        24,
                                        24
                                )
                        );

                        g2.setColor(
                                BORDER
                        );

                        g2.draw(
                                new RoundRectangle2D.Float(
                                        0.5f,
                                        0.5f,
                                        getWidth() - 1,
                                        getHeight() - 1,
                                        24,
                                        24
                                )
                        );

                        g2.setColor(
                                ORANGE
                        );

                        g2.fillRoundRect(
                                30,
                                0,
                                105,
                                4,
                                4,
                                4
                        );

                        g2.dispose();
                    }
                };

        mainCard.setOpaque(false);

        mainCard.setBorder(
                new EmptyBorder(
                        24,
                        28,
                        24,
                        28
                )
        );

        mainCard.add(
                createHeader(),
                BorderLayout.NORTH
        );

        mainCard.add(
                createMainArea(),
                BorderLayout.CENTER
        );

        mainCard.add(
                createFooter(),
                BorderLayout.SOUTH
        );

        add(
                mainCard,
                BorderLayout.CENTER
        );
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

        g2.setColor(
                BG
        );

        g2.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        g2.setColor(
                new Color(
                        196,
                        226,
                        243,
                        90
                )
        );

        g2.fill(
                new Ellipse2D.Float(
                        -120,
                        -120,
                        360,
                        360
                )
        );

        g2.setColor(
                new Color(
                        179,
                        216,
                        237,
                        70
                )
        );

        g2.fill(
                new Ellipse2D.Float(
                        getWidth() - 270,
                        getHeight() - 250,
                        430,
                        430
                )
        );

        g2.setColor(
                new Color(
                        ORANGE.getRed(),
                        ORANGE.getGreen(),
                        ORANGE.getBlue(),
                        70
                )
        );

        g2.fillOval(
                55,
                getHeight() - 80,
                7,
                7
        );

        g2.dispose();
    }

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout(
                                15,
                                0
                        )
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

        JLabel section =
                new JLabel(
                        "RIDER OPERATIONS"
                );

        section.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        section.setForeground(
                ORANGE
        );

        JLabel title =
                new JLabel(
                        "Rider Dashboard"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        29
                )
        );

        title.setForeground(
                BLACK
        );

        JLabel welcome =
                new JLabel(
                        "Welcome back, "
                                + safeValue(
                                user.getUser_name()
                        )
                                + " • Manage your assigned deliveries"
                );

        welcome.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        welcome.setForeground(
                TEXT_MUTED
        );

        titleGroup.add(
                section
        );

        titleGroup.add(
                Box.createVerticalStrut(
                        5
                )
        );

        titleGroup.add(
                title
        );

        titleGroup.add(
                Box.createVerticalStrut(
                        5
                )
        );

        titleGroup.add(
                welcome
        );

        header.add(
                titleGroup,
                BorderLayout.WEST
        );

        JPanel right =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        right.setOpaque(false);

        JPanel online =
                createStatusBadge();

        right.add(
                online
        );

        JLabel riderBadge =
                new JLabel(
                        " RIDER "
                );

        riderBadge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        riderBadge.setForeground(
                WHITE
        );

        riderBadge.setOpaque(true);

        riderBadge.setBackground(
                ORANGE
        );

        riderBadge.setBorder(
                new EmptyBorder(
                        8,
                        10,
                        8,
                        10
                )
        );

        right.add(
                riderBadge
        );

        header.add(
                right,
                BorderLayout.EAST
        );

        return header;
    }

    private JPanel createStatusBadge() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                6,
                                0
                        )
                );

        panel.setOpaque(true);

        panel.setBackground(
                SUCCESS_BG
        );

        panel.setBorder(
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
                                7,
                                9,
                                7,
                                9
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
                        11
                )
        );

        dot.setForeground(
                SUCCESS
        );

        JLabel text =
                new JLabel(
                        "ONLINE"
                );

        text.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        text.setForeground(
                new Color(
                        48,
                        114,
                        76
                )
        );

        panel.add(
                dot,
                BorderLayout.WEST
        );

        panel.add(
                text,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JPanel createMainArea() {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill =
                GridBagConstraints.BOTH;

        gbc.weightx = 1;
        gbc.weighty = 1;

        gbc.gridy = 0;

        gbc.gridx = 0;
        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        8
                );

        panel.add(
                createActionCard(
                        "PENDING",
                        "View Pending Parcels",
                        "See parcels waiting for rider acceptance.",
                        "▤",
                        ORANGE,
                        e -> openPendingParcels()
                ),
                gbc
        );

        gbc.gridx = 1;
        gbc.insets =
                new Insets(
                        0,
                        8,
                        0,
                        0
                );

        panel.add(
                createActionCard(
                        "ASSIGNED",
                        "My Assigned Parcels",
                        "Review parcels currently assigned to you.",
                        "➤",
                        BLUE,
                        e -> openAssignedParcels()
                ),
                gbc
        );

        gbc.gridy = 1;

        gbc.gridx = 0;
        gbc.insets =
                new Insets(
                        16,
                        0,
                        0,
                        8
                );

        panel.add(
                createActionCard(
                        "STATUS",
                        "Update Parcel Status",
                        "Update the delivery progress of your parcels.",
                        "↻",
                        new Color(
                                64,
                                151,
                                194
                        ),
                        e -> openUpdateStatus()
                ),
                gbc
        );

        gbc.gridx = 1;
        gbc.insets =
                new Insets(
                        16,
                        8,
                        0,
                        0
                );

        panel.add(
                createLogoutCard(),
                gbc
        );

        return panel;
    }

    private JPanel createActionCard(
            String category,
            String title,
            String description,
            String icon,
            Color accent,
            java.awt.event.ActionListener action
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                ) {

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

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D)
                                        g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        g2.setColor(
                                hovered
                                        ? new Color(
                                        252,
                                        253,
                                        254
                                )
                                        : WHITE
                        );

                        g2.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        17,
                                        17
                                )
                        );

                        g2.setColor(
                                hovered
                                        ? accent
                                        : BORDER
                        );

                        g2.setStroke(
                                new BasicStroke(
                                        hovered
                                                ? 1.4f
                                                : 1f
                                )
                        );

                        g2.draw(
                                new RoundRectangle2D.Float(
                                        0.5f,
                                        0.5f,
                                        getWidth() - 1,
                                        getHeight() - 1,
                                        17,
                                        17
                                )
                        );

                        g2.setColor(
                                accent
                        );

                        g2.fillRoundRect(
                                18,
                                0,
                                70,
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
                        22,
                        22,
                        20,
                        22
                )
        );

        card.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        JPanel top =
                new JPanel(
                        new BorderLayout()
                );

        top.setOpaque(false);

        JPanel iconPanel =
                new JPanel(
                        new GridBagLayout()
                );

        iconPanel.setOpaque(true);

        iconPanel.setBackground(
                mixWithWhite(
                        accent,
                        0.86f
                )
        );

        iconPanel.setPreferredSize(
                new Dimension(
                        58,
                        58
                )
        );

        iconPanel.setBorder(
                BorderFactory.createLineBorder(
                        mixWithWhite(
                                accent,
                                0.45f
                        ),
                        1,
                        true
                )
        );

        JLabel iconLabel =
                new JLabel(
                        icon
                );

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        iconLabel.setForeground(
                accent
        );

        iconPanel.add(
                iconLabel
        );

        top.add(
                iconPanel,
                BorderLayout.WEST
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

        JPanel categoryPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                0,
                                0
                        )
                );

        categoryPanel.setOpaque(false);

        categoryPanel.add(
                categoryLabel
        );

        top.add(
                categoryPanel,
                BorderLayout.EAST
        );

        card.add(
                top,
                BorderLayout.NORTH
        );

        JPanel text =
                new JPanel();

        text.setOpaque(false);

        text.setLayout(
                new BoxLayout(
                        text,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        titleLabel.setForeground(
                TEXT_DARK
        );

        titleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel descriptionLabel =
                new JLabel(
                        "<html><div style='width:260px'>"
                                + description
                                + "</div></html>"
                );

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        descriptionLabel.setForeground(
                TEXT_MUTED
        );

        descriptionLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        text.add(
                titleLabel
        );

        text.add(
                Box.createVerticalStrut(
                        6
                )
        );

        text.add(
                descriptionLabel
        );

        card.add(
                text,
                BorderLayout.CENTER
        );

        JLabel actionLabel =
                new JLabel(
                        "Open  →"
                );

        actionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        actionLabel.setForeground(
                accent
        );

        actionLabel.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        card.add(
                actionLabel,
                BorderLayout.SOUTH
        );

        card.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        action.actionPerformed(
                                new java.awt.event.ActionEvent(
                                        card,
                                        java.awt.event.ActionEvent.ACTION_PERFORMED,
                                        title
                                )
                        );
                    }
                }
        );

        return card;
    }

    private JPanel createLogoutCard() {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                ) {

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

                        super.paintComponent(g);

                        Graphics2D g2 =
                                (Graphics2D)
                                        g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        g2.setColor(
                                hovered
                                        ? new Color(
                                        255,
                                        247,
                                        243
                                )
                                        : WHITE
                        );

                        g2.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        17,
                                        17
                                )
                        );

                        g2.setColor(
                                hovered
                                        ? ORANGE
                                        : BORDER
                        );

                        g2.draw(
                                new RoundRectangle2D.Float(
                                        0.5f,
                                        0.5f,
                                        getWidth() - 1,
                                        getHeight() - 1,
                                        17,
                                        17
                                )
                        );

                        g2.setColor(
                                ORANGE
                        );

                        g2.fillRoundRect(
                                18,
                                0,
                                70,
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
                        22,
                        22,
                        20,
                        22
                )
        );

        card.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        JPanel iconPanel =
                new JPanel(
                        new GridBagLayout()
                );

        iconPanel.setOpaque(true);

        iconPanel.setBackground(
                new Color(
                        255,
                        243,
                        237
                )
        );

        iconPanel.setPreferredSize(
                new Dimension(
                        58,
                        58
                )
        );

        JLabel icon =
                new JLabel(
                        "↪"
                );

        icon.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        24
                )
        );

        icon.setForeground(
                ORANGE
        );

        iconPanel.add(
                icon
        );

        card.add(
                iconPanel,
                BorderLayout.WEST
        );

        JPanel text =
                new JPanel();

        text.setOpaque(false);

        text.setLayout(
                new BoxLayout(
                        text,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "Logout"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        title.setForeground(
                TEXT_DARK
        );

        JLabel description =
                new JLabel(
                        "<html><div style='width:250px'>"
                                + "Sign out from your rider account."
                                + "</div></html>"
                );

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        description.setForeground(
                TEXT_MUTED
        );

        text.add(title);

        text.add(
                Box.createVerticalStrut(
                        6
                )
        );

        text.add(description);

        card.add(
                text,
                BorderLayout.CENTER
        );

        JLabel action =
                new JLabel(
                        "Sign out  →"
                );

        action.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        action.setForeground(
                ORANGE
        );

        action.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        card.add(
                action,
                BorderLayout.SOUTH
        );

        card.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        logout();
                    }
                }
        );

        return card;
    }

    private JPanel createFooter() {

        JPanel footer =
                new JPanel(
                        new BorderLayout()
                );

        footer.setOpaque(false);

        JLabel riderInfo =
                new JLabel(
                        "Rider ID: "
                                + safeValue(
                                user.getUser_id()
                        )
                );

        riderInfo.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        riderInfo.setForeground(
                TEXT_MUTED
        );

        JLabel info =
                new JLabel(
                        "Parcel operations center"
                );

        info.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        info.setForeground(
                TEXT_MUTED
        );

        footer.add(
                riderInfo,
                BorderLayout.WEST
        );

        footer.add(
                info,
                BorderLayout.EAST
        );

        return footer;
    }

    private void openPendingParcels() {

        UI.PendingParcelsFrame frame =
                new UI.PendingParcelsFrame(
                        user
                );

        frame.setVisible(true);
    }

    private void openAssignedParcels() {

        UI.AssignedParcelsFrame frame =
                new UI.AssignedParcelsFrame(
                        user
                );

        frame.setVisible(true);
    }

    private void openUpdateStatus() {

        UpdateParcelStatusFrame frame =
                new UpdateParcelStatusFrame(
                        user
                );

        frame.setVisible(true);
    }

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            if (mainFrame != null) {
                mainFrame.showLoginFrame();
            }
        }
    }

    private String safeValue(
            String value
    ) {

        return value == null
                ? "N/A"
                : value;
    }

    private Color mixWithWhite(
            Color color,
            float amount
    ) {

        int r =
                (int) (
                        color.getRed()
                                +
                                (255 - color.getRed())
                                        * amount
                );

        int g =
                (int) (
                        color.getGreen()
                                +
                                (255 - color.getGreen())
                                        * amount
                );

        int b =
                (int) (
                        color.getBlue()
                                +
                                (255 - color.getBlue())
                                        * amount
                );

        return new Color(
                r,
                g,
                b
        );
    }
}