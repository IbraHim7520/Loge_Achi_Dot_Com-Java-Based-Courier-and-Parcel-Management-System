package UI;

import custom_exception.InvalidAmountException;
import custom_exception.UnauthorizedAccessException;
import model.Parcel;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class SendParcelPanel extends JPanel {

    private final User user;

    private JTextField parcelNameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField weightField;

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

    private static final Color LIGHT_BLUE =
            new Color(225, 240, 249);

    private static final Color BLUE =
            new Color(0, 97, 153);

    private static final Color SKY_BLUE =
            new Color(181, 219, 241);

    private static final Color BORDER =
            new Color(216, 227, 234);

    private static final Color INPUT_BG =
            new Color(249, 251, 252);

    private static final Color INPUT_BORDER =
            new Color(198, 211, 220);

    public SendParcelPanel(User user) {

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
                    protected void paintComponent(Graphics g) {

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
                createMainContent(),
                BorderLayout.CENTER
        );

        add(
                mainCard,
                BorderLayout.CENTER
        );
    }

    @Override
    protected void paintComponent(Graphics g) {

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
                        195,
                        225,
                        242,
                        95
                )
        );

        g2.fill(
                new Ellipse2D.Float(
                        -110,
                        -120,
                        350,
                        350
                )
        );

        g2.setColor(
                new Color(
                        177,
                        215,
                        237,
                        70
                )
        );

        g2.fill(
                new Ellipse2D.Float(
                        getWidth() - 260,
                        getHeight() - 230,
                        400,
                        400
                )
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
                        "SHIPPING"
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
                        "Send New Parcel"
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

        JLabel subtitle =
                new JLabel(
                        "Enter the receiver and parcel details to create a shipment"
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        subtitle.setForeground(
                TEXT_MUTED
        );

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
                new Color(
                        233,
                        248,
                        240
                )
        );

        status.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        186,
                                        224,
                                        202
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
                new JLabel("●");

        dot.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        dot.setForeground(
                new Color(
                        48,
                        148,
                        94
                )
        );

        JLabel label =
                new JLabel(
                        "READY TO SHIP"
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        label.setForeground(
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
                label,
                BorderLayout.CENTER
        );

        header.add(
                status,
                BorderLayout.EAST
        );

        return header;
    }

    private JPanel createMainContent() {

        JPanel content =
                new JPanel(
                        new GridBagLayout()
                );

        content.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridy = 0;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 0;
        gbc.weightx = 0.5;
        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        8
                );

        JPanel formCard =
                createFormCard();

        content.add(
                formCard,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0.5;
        gbc.insets =
                new Insets(
                        0,
                        8,
                        0,
                        0
                );

        JPanel previewCard =
                createPreviewCard();

        content.add(
                previewCard,
                gbc
        );

        return content;
    }

    private JPanel createFormCard() {

        JPanel card =
                createWhiteCard();

        card.setLayout(
                new BorderLayout(
                        0,
                        15
                )
        );

        JLabel title =
                new JLabel(
                        "Shipment Details"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        title.setForeground(
                TEXT_DARK
        );

        JLabel subtitle =
                new JLabel(
                        "All fields are required"
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

        JPanel top =
                new JPanel();

        top.setOpaque(false);

        top.setLayout(
                new BoxLayout(
                        top,
                        BoxLayout.Y_AXIS
                )
        );

        top.add(title);

        top.add(
                Box.createVerticalStrut(4)
        );

        top.add(subtitle);

        card.add(
                top,
                BorderLayout.NORTH
        );

        JPanel form =
                new JPanel(
                        new GridBagLayout()
                );

        form.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets =
                new Insets(
                        0,
                        0,
                        14,
                        0
                );

        parcelNameField =
                new JTextField();

        addressField =
                new JTextField();

        phoneField =
                new JTextField();

        weightField =
                new JTextField();

        styleInputField(
                parcelNameField
        );

        styleInputField(
                addressField
        );

        styleInputField(
                phoneField
        );

        styleInputField(
                weightField
        );

        gbc.gridy = 0;

        form.add(
                createFieldBlock(
                        "PARCEL NAME",
                        parcelNameField,
                        "Enter parcel name"
                ),
                gbc
        );

        gbc.gridy = 1;

        form.add(
                createFieldBlock(
                        "RECEIVER ADDRESS",
                        addressField,
                        "Enter complete receiver address"
                ),
                gbc
        );

        gbc.gridy = 2;

        form.add(
                createFieldBlock(
                        "RECEIVER PHONE",
                        phoneField,
                        "Enter receiver phone number"
                ),
                gbc
        );

        gbc.gridy = 3;
        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        0
                );

        form.add(
                createFieldBlock(
                        "WEIGHT (KG)",
                        weightField,
                        "Enter parcel weight"
                ),
                gbc
        );

        card.add(
                form,
                BorderLayout.CENTER
        );

        JPanel actions =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                10,
                                0
                        )
                );

        actions.setOpaque(false);

        JButton sendButton =
                createPrimaryButton(
                        "Send Parcel  →"
                );

        JButton clearButton =
                createOutlineButton(
                        "Clear"
                );

        actions.add(sendButton);
        actions.add(clearButton);

        card.add(
                actions,
                BorderLayout.SOUTH
        );

        sendButton.addActionListener(
                e -> sendParcel()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        return card;
    }

    private JPanel createFieldBlock(
            String labelText,
            JTextField field,
            String hint
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                5
                        )
                );

        panel.setOpaque(false);

        JLabel label =
                new JLabel(
                        labelText
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        label.setForeground(
                TEXT_MUTED
        );

        JLabel hintLabel =
                new JLabel(
                        hint
                );

        hintLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        8
                )
        );

        hintLabel.setForeground(
                new Color(
                        150,
                        162,
                        170
                )
        );

        JPanel labelPanel =
                new JPanel(
                        new BorderLayout()
                );

        labelPanel.setOpaque(false);

        labelPanel.add(
                label,
                BorderLayout.WEST
        );

        labelPanel.add(
                hintLabel,
                BorderLayout.EAST
        );

        panel.add(
                labelPanel,
                BorderLayout.NORTH
        );

        panel.add(
                field,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JPanel createPreviewCard() {

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

                        g2.setColor(
                                LIGHT_BLUE
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

                        g2.setColor(
                                new Color(
                                        197,
                                        221,
                                        234
                                )
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

                        g2.dispose();
                    }
                };

        card.setOpaque(false);

        card.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "Shipment Preview"
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
                        "Your parcel journey"
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

        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(4)
        );

        titlePanel.add(subtitle);

        card.add(
                titlePanel,
                BorderLayout.NORTH
        );

        JPanel center =
                new JPanel(
                        new BorderLayout()
                );

        center.setOpaque(false);

        center.add(
                createRouteVisual(),
                BorderLayout.CENTER
        );

        card.add(
                center,
                BorderLayout.CENTER
        );

        JPanel info =
                new JPanel(
                        new GridLayout(
                                3,
                                1,
                                0,
                                7
                        )
                );

        info.setOpaque(false);

        info.add(
                createInfoRow(
                        "FROM",
                        "Your account"
                )
        );

        info.add(
                createInfoRow(
                        "TO",
                        "Receiver destination"
                )
        );

        info.add(
                createInfoRow(
                        "STATUS",
                        "Ready to send"
                )
        );

        card.add(
                info,
                BorderLayout.SOUTH
        );

        return card;
    }

    private JPanel createRouteVisual() {

        JPanel visual =
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

                        int w =
                                getWidth();

                        int h =
                                getHeight();

                        int startX = 45;

                        int endX =
                                Math.max(
                                        w - 45,
                                        startX + 50
                                );

                        int y =
                                h / 2;

                        g2.setColor(
                                new Color(
                                        115,
                                        160,
                                        184,
                                        100
                                )
                        );

                        g2.setStroke(
                                new BasicStroke(
                                        2f,
                                        BasicStroke.CAP_ROUND,
                                        BasicStroke.JOIN_ROUND
                                )
                        );

                        for (
                                int x = startX + 12;
                                x < endX - 10;
                                x += 18
                        ) {

                            g2.drawLine(
                                    x,
                                    y,
                                    Math.min(
                                            x + 9,
                                            endX - 10
                                    ),
                                    y
                            );
                        }

                        g2.setColor(
                                ORANGE
                        );

                        g2.fillOval(
                                startX - 7,
                                y - 7,
                                14,
                                14
                        );

                        g2.setColor(
                                BLUE
                        );

                        g2.fillOval(
                                endX - 7,
                                y - 7,
                                14,
                                14
                        );

                        int boxWidth = 70;
                        int boxHeight = 54;

                        int boxX =
                                Math.max(
                                        startX + 35,
                                        (w - boxWidth) / 2
                                );

                        int boxY =
                                y - boxHeight / 2;

                        g2.setColor(
                                new Color(
                                        245,
                                        181,
                                        78
                                )
                        );

                        g2.fillRoundRect(
                                boxX,
                                boxY,
                                boxWidth,
                                boxHeight,
                                8,
                                8
                        );

                        g2.setColor(
                                ORANGE
                        );

                        g2.fillRect(
                                boxX + 28,
                                boxY - 3,
                                14,
                                boxHeight + 6
                        );

                        g2.setColor(
                                WHITE
                        );

                        g2.fillRoundRect(
                                boxX + 16,
                                boxY + 20,
                                38,
                                18,
                                3,
                                3
                        );

                        g2.setColor(
                                BLACK
                        );

                        g2.setFont(
                                new Font(
                                        "SansSerif",
                                        Font.BOLD,
                                        7
                                )
                        );

                        g2.drawString(
                                "SHIP",
                                boxX + 26,
                                boxY + 32
                        );

                        g2.setColor(
                                new Color(
                                        65,
                                        107,
                                        130
                                )
                        );

                        g2.setFont(
                                new Font(
                                        "SansSerif",
                                        Font.BOLD,
                                        8
                                )
                        );

                        g2.drawString(
                                "YOU",
                                Math.max(
                                        5,
                                        startX - 12
                                ),
                                y + 25
                        );

                        g2.drawString(
                                "DESTINATION",
                                Math.max(
                                        5,
                                        endX - 31
                                ),
                                y + 25
                        );

                        g2.dispose();
                    }
                };

        visual.setOpaque(false);

        return visual;
    }

    private JPanel createInfoRow(
            String labelText,
            String valueText
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout()
                );

        row.setOpaque(true);

        row.setBackground(
                new Color(
                        255,
                        255,
                        255,
                        160
                )
        );

        row.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        205,
                                        221,
                                        231
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

        JLabel label =
                new JLabel(
                        labelText
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        label.setForeground(
                ORANGE
        );

        JLabel value =
                new JLabel(
                        valueText
                );

        value.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        value.setForeground(
                TEXT_DARK
        );

        row.add(
                label,
                BorderLayout.WEST
        );

        row.add(
                value,
                BorderLayout.EAST
        );

        return row;
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

                        g2.dispose();
                    }
                };

        card.setOpaque(false);

        card.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
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
                        12
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
                                11,
                                8,
                                11
                        )
                )
        );

        field.setPreferredSize(
                new Dimension(
                        0,
                        40
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
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
                                                11,
                                                8,
                                                11
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
                                                11,
                                                8,
                                                11
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
                        13
                )
        );

        button.setForeground(
                WHITE
        );

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

    private JButton createOutlineButton(
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

                        if (
                                getModel().isRollover()
                        ) {

                            g2.setColor(
                                    new Color(
                                            248,
                                            116,
                                            35,
                                            18
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
                        }

                        g2.setColor(
                                new Color(
                                        138,
                                        167,
                                        184
                                )
                        );

                        g2.draw(
                                new RoundRectangle2D.Float(
                                        0.5f,
                                        0.5f,
                                        getWidth() - 1,
                                        getHeight() - 1,
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

        button.setForeground(
                TEXT_DARK
        );

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

    private void sendParcel() {

        String parcelName =
                parcelNameField
                        .getText()
                        .trim();

        String address =
                addressField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        String weightText =
                weightField
                        .getText()
                        .trim();

        if (
                parcelName.isEmpty()
                        || address.isEmpty()
                        || phone.isEmpty()
                        || weightText.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields.",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        double weight;

        try {

            weight =
                    Double.parseDouble(
                            weightText
                    );

        } catch (
                NumberFormatException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Weight must be a valid number.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (weight <= 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Weight must be greater than zero.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        try {

            Parcel parcel =
                    new Parcel(user);

            String result =
                    parcel.sendOneParcel(
                            parcelName,
                            address,
                            phone,
                            weight
                    );

            if (
                    result.equals(
                            "Parcel sent successfully!"
                    )
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        result
                                + "\n\nParcel ID: "
                                + parcel.getParcelID()
                                + "\nDelivery Charge: "
                                + parcel.getDeliveryCharge(),
                        "Shipment Created",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        result,
                        "Shipment Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (
                UnauthorizedAccessException
                | InvalidAmountException
                | IllegalArgumentException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearFields() {

        parcelNameField.setText("");
        addressField.setText("");
        phoneField.setText("");
        weightField.setText("");

        parcelNameField.requestFocus();
    }
}