package UI;

import custom_exception.InvalidAmountException;
import custom_exception.NotFoundException;
import custom_exception.UnauthorizedAccessException;
import model.Parcel;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class TrackParcelPanel extends JPanel {

    private final User user;

    private JTextField parcelIdField;
    private JTextArea resultArea;

    private static final Color BG = new Color(241, 248, 253);
    private static final Color WHITE = Color.WHITE;
    private static final Color BLACK = new Color(15, 18, 20);
    private static final Color TEXT_DARK = new Color(31, 38, 43);
    private static final Color TEXT_MUTED = new Color(103, 117, 128);

    private static final Color ORANGE = new Color(248, 116, 35);
    private static final Color ORANGE_HOVER = new Color(235, 94, 20);

    private static final Color BLUE = new Color(0, 97, 153);
    private static final Color LIGHT_BLUE = new Color(225, 240, 249);
    private static final Color SKY_BLUE = new Color(181, 219, 241);

    private static final Color BORDER = new Color(216, 227, 234);
    private static final Color INPUT_BG = new Color(249, 251, 252);
    private static final Color INPUT_BORDER = new Color(198, 211, 220);

    private static final Color SUCCESS = new Color(48, 148, 94);
    private static final Color SUCCESS_BG = new Color(233, 248, 240);

    public TrackParcelPanel(User user) {
        this.user = user;

        setOpaque(false);
        setLayout(new BorderLayout());

        buildUI();
    }

    private void buildUI() {

        JPanel mainCard =
                new JPanel(
                        new BorderLayout(
                                0,
                                18
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

                        g2.setColor(WHITE);

                        g2.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        22,
                                        22
                                )
                        );

                        g2.setColor(BORDER);

                        g2.draw(
                                new RoundRectangle2D.Float(
                                        0.5f,
                                        0.5f,
                                        getWidth() - 1,
                                        getHeight() - 1,
                                        22,
                                        22
                                )
                        );

                        g2.setColor(ORANGE);

                        g2.fillRoundRect(
                                26,
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
                createContent(),
                BorderLayout.CENTER
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

        g2.setColor(BG);

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
                        65
                )
        );

        g2.fill(
                new Ellipse2D.Float(
                        getWidth() - 280,
                        getHeight() - 260,
                        450,
                        450
                )
        );

        g2.setColor(
                new Color(
                        ORANGE.getRed(),
                        ORANGE.getGreen(),
                        ORANGE.getBlue(),
                        65
                )
        );

        g2.fillOval(
                55,
                getHeight() - 85,
                7,
                7
        );

        g2.dispose();
    }

    private JPanel createHeader() {

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

        JLabel section =
                new JLabel(
                        "TRACKING"
                );

        section.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        section.setForeground(ORANGE);

        JLabel title =
                new JLabel(
                        "Track Your Parcel"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(BLACK);

        JLabel subtitle =
                new JLabel(
                        "Enter your parcel ID to check the current delivery status"
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        subtitle.setForeground(TEXT_MUTED);

        titleGroup.add(section);

        titleGroup.add(
                Box.createVerticalStrut(5)
        );

        titleGroup.add(title);

        titleGroup.add(
                Box.createVerticalStrut(5)
        );

        titleGroup.add(subtitle);

        header.add(
                titleGroup,
                BorderLayout.WEST
        );

        JPanel status =
                new JPanel(
                        new BorderLayout(
                                7,
                                0
                        )
                );

        status.setOpaque(true);

        status.setBackground(
                SUCCESS_BG
        );

        status.setBorder(
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
                                11,
                                8,
                                11
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

        dot.setForeground(SUCCESS);

        JLabel statusText =
                new JLabel(
                        "LIVE STATUS"
                );

        statusText.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        statusText.setForeground(
                new Color(
                        48,
                        114,
                        76
                )
        );

        status.add(
                dot,
                BorderLayout.WEST
        );

        status.add(
                statusText,
                BorderLayout.CENTER
        );

        header.add(
                status,
                BorderLayout.EAST
        );

        return header;
    }

    private JPanel createContent() {

        JPanel content =
                new JPanel(
                        new BorderLayout(
                                0,
                                16
                        )
                );

        content.setOpaque(false);

        content.add(
                createSearchCard(),
                BorderLayout.NORTH
        );

        content.add(
                createResultCard(),
                BorderLayout.CENTER
        );

        return content;
    }

    private JPanel createSearchCard() {

        JPanel card =
                createWhiteCard();

        card.setLayout(
                new BorderLayout(
                        16,
                        0
                )
        );

        JPanel labelPanel =
                new JPanel();

        labelPanel.setOpaque(false);

        labelPanel.setLayout(
                new BoxLayout(
                        labelPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel small =
                new JLabel(
                        "PARCEL ID"
                );

        small.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        small.setForeground(
                ORANGE
        );

        JLabel title =
                new JLabel(
                        "Tracking Number"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        title.setForeground(
                TEXT_DARK
        );

        labelPanel.add(small);

        labelPanel.add(
                Box.createVerticalStrut(4)
        );

        labelPanel.add(title);

        parcelIdField =
                new JTextField();

        styleInputField(
                parcelIdField
        );

        parcelIdField.setToolTipText(
                "Enter Parcel ID"
        );

        JButton trackButton =
                createPrimaryButton(
                        "Track Parcel  →"
                );

        trackButton.setPreferredSize(
                new Dimension(
                        145,
                        42
                )
        );

        card.add(
                labelPanel,
                BorderLayout.WEST
        );

        card.add(
                parcelIdField,
                BorderLayout.CENTER
        );

        card.add(
                trackButton,
                BorderLayout.EAST
        );

        trackButton.addActionListener(
                e -> trackParcel()
        );

        parcelIdField.addActionListener(
                e -> trackParcel()
        );

        return card;
    }

    private JPanel createResultCard() {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                0,
                                14
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

                        g2.setColor(WHITE);

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

                        g2.setColor(BORDER);

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

                        g2.setColor(BLUE);

                        g2.fillRoundRect(
                                22,
                                0,
                                90,
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
                        "Delivery Information"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        title.setForeground(BLACK);

        JLabel subtitle =
                new JLabel(
                        "Your parcel tracking details will appear here"
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        subtitle.setForeground(TEXT_MUTED);

        titleGroup.add(title);

        titleGroup.add(
                Box.createVerticalStrut(4)
        );

        titleGroup.add(subtitle);

        header.add(
                titleGroup,
                BorderLayout.WEST
        );

        JLabel ready =
                new JLabel(
                        "TRACKING READY"
                );

        ready.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        ready.setForeground(ORANGE);

        ready.setOpaque(true);

        ready.setBackground(
                new Color(
                        255,
                        243,
                        235
                )
        );

        ready.setBorder(
                new EmptyBorder(
                        7,
                        10,
                        7,
                        10
                )
        );

        header.add(
                ready,
                BorderLayout.EAST
        );

        card.add(
                header,
                BorderLayout.NORTH
        );

        resultArea =
                new JTextArea();

        resultArea.setEditable(false);

        resultArea.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        resultArea.setForeground(
                TEXT_MUTED
        );

        resultArea.setBackground(
                new Color(
                        247,
                        250,
                        252
                )
        );

        resultArea.setCaretColor(
                ORANGE
        );

        resultArea.setLineWrap(true);

        resultArea.setWrapStyleWord(true);

        resultArea.setBorder(
                new EmptyBorder(
                        20,
                        22,
                        20,
                        22
                )
        );

        resultArea.setText(
                "Enter a Parcel ID above to view delivery information."
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        resultArea
                );

        scrollPane.setOpaque(false);

        scrollPane.getViewport().setOpaque(true);

        scrollPane.getViewport().setBackground(
                new Color(
                        247,
                        250,
                        252
                )
        );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER,
                        1,
                        true
                )
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(14);

        card.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JPanel footer =
                new JPanel(
                        new BorderLayout()
                );

        footer.setOpaque(false);

        JLabel info =
                new JLabel(
                        "●  Enter a valid parcel ID to retrieve its current status"
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

        JLabel tracking =
                new JLabel(
                        "PARCEL TRACKING"
                );

        tracking.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        tracking.setForeground(
                BLUE
        );

        footer.add(
                info,
                BorderLayout.WEST
        );

        footer.add(
                tracking,
                BorderLayout.EAST
        );

        card.add(
                footer,
                BorderLayout.SOUTH
        );

        return card;
    }

    private JPanel createWhiteCard() {

        JPanel card =
                new JPanel() {

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

                        g2.setColor(WHITE);

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

                        g2.setColor(BORDER);

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

                        g2.dispose();
                    }
                };

        card.setOpaque(false);

        card.setBorder(
                new EmptyBorder(
                        16,
                        18,
                        16,
                        18
                )
        );

        return card;
    }

    private void styleInputField(
            JTextField field
    ) {

        field.setOpaque(true);

        field.setBackground(
                INPUT_BG
        );

        field.setForeground(
                TEXT_DARK
        );

        field.setCaretColor(
                ORANGE
        );

        field.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                INPUT_BORDER,
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                8,
                                12,
                                8,
                                12
                        )
                )
        );

        field.setPreferredSize(
                new Dimension(
                        0,
                        42
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        field.addFocusListener(
                new FocusAdapter() {

                    @Override
                    public void focusGained(
                            FocusEvent e
                    ) {

                        field.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                ORANGE,
                                                1,
                                                true
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                8,
                                                12,
                                                8,
                                                12
                                        )
                                )
                        );
                    }

                    @Override
                    public void focusLost(
                            FocusEvent e
                    ) {

                        field.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                INPUT_BORDER,
                                                1,
                                                true
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                8,
                                                12,
                                                8,
                                                12
                                        )
                                )
                        );
                    }
                }
        );
    }

    private JButton createPrimaryButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

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

                        Color top =
                                getModel().isRollover()
                                        ? new Color(
                                        255,
                                        151,
                                        68
                                )
                                        : new Color(
                                        255,
                                        137,
                                        48
                                );

                        Color bottom =
                                getModel().isRollover()
                                        ? ORANGE_HOVER
                                        : ORANGE;

                        g2.setPaint(
                                new GradientPaint(
                                        0,
                                        0,
                                        top,
                                        getWidth(),
                                        0,
                                        bottom
                                )
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
                        12
                )
        );

        button.setForeground(WHITE);

        button.setContentAreaFilled(false);

        button.setOpaque(false);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    private void trackParcel() {

        String parcelId =
                parcelIdField
                        .getText()
                        .trim();

        if (parcelId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a Parcel ID.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            Parcel parcel =
                    new Parcel(user);

            Parcel tracked =
                    parcel.trackParcel(
                            parcelId
                    );

            StringBuilder result =
                    new StringBuilder();

            result.append(
                    "PARCEL DETAILS\n"
            );

            result.append(
                    "────────────────────────────────────────\n\n"
            );

            result.append(
                    "Parcel ID\n"
            );

            result.append(
                    safeValue(
                            tracked.getParcelID()
                    )
            );

            result.append(
                    "\n\nParcel Name\n"
            );

            result.append(
                    safeValue(
                            tracked.getParcelName()
                    )
            );

            result.append(
                    "\n\nReceiver Address\n"
            );

            result.append(
                    safeValue(
                            tracked.getReciverAddress()
                    )
            );

            result.append(
                    "\n\nReceiver Phone\n"
            );

            result.append(
                    safeValue(
                            tracked.getReciverPhone()
                    )
            );

            result.append(
                    "\n\nWeight\n"
            );

            result.append(
                    safeValue(
                            String.valueOf(
                                    tracked.getWeight()
                            )
                    )
            );

            result.append(
                    " kg"
            );

            result.append(
                    "\n\nDelivery Charge\n"
            );

            result.append(
                    safeValue(
                            String.valueOf(
                                    tracked.getDeliveryCharge()
                            )
                    )
            );

            result.append(
                    "\n\nStatus\n"
            );

            result.append(
                    safeValue(
                            tracked.getParcelStatus()
                    )
            );

            result.append(
                    "\n\nRider ID\n"
            );

            String riderId =
                    tracked.getRiderId();

            result.append(
                    riderId == null
                            || riderId.trim().isEmpty()
                            ? "Not Assigned"
                            : riderId
            );

            result.append(
                    "\n\n────────────────────────────────────────"
            );

            resultArea.setForeground(
                    TEXT_DARK
            );

            resultArea.setText(
                    result.toString()
            );

            resultArea.setCaretPosition(0);

        } catch (NotFoundException e) {

            resultArea.setForeground(
                    new Color(
                            175,
                            75,
                            65
                    )
            );

            resultArea.setText(
                    "Parcel not found.\n\n"
                            + e.getMessage()
            );

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Parcel Not Found",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (
                UnauthorizedAccessException
                | InvalidAmountException e
        ) {

            resultArea.setForeground(
                    new Color(
                            175,
                            75,
                            65
                    )
            );

            resultArea.setText(
                    e.getMessage()
            );

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Tracking Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private String safeValue(
            String value
    ) {

        return value == null
                ? "N/A"
                : value;
    }
}