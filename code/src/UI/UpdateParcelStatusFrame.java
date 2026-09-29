package UI;

import custom_exception.NotFoundException;
import model.Rider;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class UpdateParcelStatusFrame extends JPanel {

    private final User user;

    private JTextField parcelIdField;
    private JComboBox<String> statusComboBox;

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

    private static final Color INPUT_BG =
            new Color(249, 251, 252);

    private static final Color INPUT_BORDER =
            new Color(198, 211, 220);

    private static final Color SUCCESS =
            new Color(48, 148, 94);

    private static final Color SUCCESS_BG =
            new Color(233, 248, 240);

    public UpdateParcelStatusFrame(User user) {

        this.user = user;

        setLayout(
                new BorderLayout()
        );

        setOpaque(false);

        buildUI();
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
                                28,
                                0,
                                110,
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
                        25,
                        28,
                        25,
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

//        mainCard.add(
//                createFooter(),
//                BorderLayout.SOUTH
//        );

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
                        "DELIVERY CONTROL"
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
                        "Update Parcel Status"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                BLACK
        );

        JLabel subtitle =
                new JLabel(
                        "Update the delivery progress of a parcel assigned to you"
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

        titleGroup.add(
                section
        );

        titleGroup.add(
                Box.createVerticalStrut(5)
        );

        titleGroup.add(
                title
        );

        titleGroup.add(
                Box.createVerticalStrut(5)
        );

        titleGroup.add(
                subtitle
        );

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

        dot.setForeground(
                SUCCESS
        );

        JLabel label =
                new JLabel(
                        "STATUS CONTROL"
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
                        new GridLayout(
                                1,
                                2,
                                18,
                                0
                        )
                );

        content.setOpaque(false);

        content.add(
                createFormCard()
        );

        content.add(
                createStatusGuide()
        );

        return content;
    }

    private JPanel createFormCard() {

        JPanel card =
                createWhiteCard();

        card.setLayout(
                new BorderLayout(
                        0,
                        18
                )
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

        JLabel small =
                new JLabel(
                        "PARCEL UPDATE"
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
                        "Change Delivery Status"
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
                        "Choose the new status for the selected parcel"
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

        top.add(
                small
        );

        top.add(
                Box.createVerticalStrut(4)
        );

        top.add(
                title
        );

        top.add(
                Box.createVerticalStrut(4)
        );

        top.add(
                subtitle
        );

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
        gbc.fill =
                GridBagConstraints.HORIZONTAL;
        gbc.anchor =
                GridBagConstraints.NORTHWEST;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        18,
                        0
                );

        parcelIdField =
                new JTextField();

        styleInputField(
                parcelIdField
        );

        gbc.gridy = 0;

        form.add(
                createFieldBlock(
                        "PARCEL ID",
                        parcelIdField,
                        "Enter the parcel ID"
                ),
                gbc
        );

        statusComboBox =
                new JComboBox<>(
                        new String[]{
                                "ON_TRANSIT",
                                "REACHED_DESTINATION",
                                "DELIVERED",
                                "CANCELED"
                        }
                );

        styleComboBox(
                statusComboBox
        );

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        0
                );

        form.add(
                createFieldBlock(
                        "NEW STATUS",
                        statusComboBox,
                        "Select the latest delivery status"
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

        JButton updateButton =
                createPrimaryButton(
                        "Update Status  →"
                );

        JButton clearButton =
                createOutlineButton(
                        "Clear"
                );

        actions.add(
                updateButton
        );

        actions.add(
                clearButton
        );

        card.add(
                actions,
                BorderLayout.SOUTH
        );

        updateButton.addActionListener(
                e -> updateStatus()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        parcelIdField.addActionListener(
                e -> updateStatus()
        );

        return card;
    }

    private JPanel createStatusGuide() {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                0,
                                16
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

                        g2.setColor(
                                BLUE
                        );

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
                        20,
                        20,
                        20
                )
        );

        JPanel title =
                new JPanel();

        title.setOpaque(false);

        title.setLayout(
                new BoxLayout(
                        title,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel heading =
                new JLabel(
                        "Delivery Flow"
                );

        heading.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        heading.setForeground(
                BLACK
        );

        JLabel subtitle =
                new JLabel(
                        "Keep parcel progress up to date"
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

        title.add(
                heading
        );

        title.add(
                Box.createVerticalStrut(4)
        );

        title.add(
                subtitle
        );

        card.add(
                title,
                BorderLayout.NORTH
        );

        JPanel flow =
                new JPanel(
                        new GridBagLayout()
                );

        flow.setOpaque(false);

        String[] statuses = {
                "ON_TRANSIT",
                "REACHED_DESTINATION",
                "DELIVERED"
        };

        Color[] colors = {
                ORANGE,
                BLUE,
                SUCCESS
        };

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        for (int i = 0; i < statuses.length; i++) {

            gbc.gridy = i;
            gbc.insets =
                    new Insets(
                            0,
                            0,
                            i == statuses.length - 1
                                    ? 0
                                    : 10,
                            0
                    );

            flow.add(
                    createStatusItem(
                            statuses[i],
                            colors[i],
                            i + 1
                    ),
                    gbc
            );
        }

        gbc.gridy = 3;
        gbc.insets =
                new Insets(
                        15,
                        0,
                        0,
                        0
                );

        flow.add(
                createCancelItem(),
                gbc
        );

        card.add(
                flow,
                BorderLayout.CENTER
        );

        JLabel note =
                new JLabel(
                        "<html><div style='width:280px'>"
                                + "Select the status that matches the parcel's "
                                + "current delivery progress."
                                + "</div></html>"
                );

        note.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        10
                )
        );

        note.setForeground(
                TEXT_MUTED
        );

        card.add(
                note,
                BorderLayout.SOUTH
        );

        return card;
    }

    private JPanel createStatusItem(
            String status,
            Color accent,
            int step
    ) {

        JPanel item =
                new JPanel(
                        new BorderLayout(
                                12,
                                0
                        )
                );

        item.setOpaque(true);

        item.setBackground(
                new Color(
                        255,
                        255,
                        255,
                        155
                )
        );

        item.setBorder(
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
                                10,
                                11,
                                10,
                                11
                        )
                )
        );

        JPanel number =
                new JPanel(
                        new GridBagLayout()
                );

        number.setOpaque(true);

        number.setBackground(
                mixWithWhite(
                        accent,
                        0.84f
                )
        );

        number.setPreferredSize(
                new Dimension(
                        34,
                        34
                )
        );

        JLabel numberText =
                new JLabel(
                        String.valueOf(step)
                );

        numberText.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        numberText.setForeground(
                accent
        );

        number.add(
                numberText
        );

        item.add(
                number,
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
                        status
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        title.setForeground(
                TEXT_DARK
        );

        JLabel description =
                new JLabel(
                        getStatusDescription(
                                status
                        )
                );

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        8
                )
        );

        description.setForeground(
                TEXT_MUTED
        );

        text.add(
                title
        );

        text.add(
                Box.createVerticalStrut(
                        3
                )
        );

        text.add(
                description
        );

        item.add(
                text,
                BorderLayout.CENTER
        );

        JLabel dot =
                new JLabel(
                        "●"
                );

        dot.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        dot.setForeground(
                accent
        );

        item.add(
                dot,
                BorderLayout.EAST
        );

        return item;
    }

    private JPanel createCancelItem() {

        JPanel item =
                new JPanel(
                        new BorderLayout(
                                12,
                                0
                        )
                );

        item.setOpaque(true);

        item.setBackground(
                new Color(
                        255,
                        245,
                        242
                )
        );

        item.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        242,
                                        206,
                                        199
                                ),
                                1,
                                true
                        ),
                        new EmptyBorder(
                                10,
                                11,
                                10,
                                11
                        )
                )
        );

        JPanel icon =
                new JPanel(
                        new GridBagLayout()
                );

        icon.setOpaque(true);

        icon.setBackground(
                new Color(
                        253,
                        229,
                        224
                )
        );

        icon.setPreferredSize(
                new Dimension(
                        34,
                        34
                )
        );

        JLabel x =
                new JLabel(
                        "×"
                );

        x.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        x.setForeground(
                new Color(
                        190,
                        70,
                        55
                )
        );

        icon.add(x);

        item.add(
                icon,
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
                        "CANCELED"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        title.setForeground(
                new Color(
                        170,
                        65,
                        52
                )
        );

        JLabel description =
                new JLabel(
                        "Use only when delivery is canceled"
                );

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        8
                )
        );

        description.setForeground(
                TEXT_MUTED
        );

        text.add(title);

        text.add(
                Box.createVerticalStrut(
                        3
                )
        );

        text.add(description);

        item.add(
                text,
                BorderLayout.CENTER
        );

        return item;
    }

    private String getStatusDescription(
            String status
    ) {

        if (
                "ON_TRANSIT".equals(
                        status
                )
        ) {
            return "Parcel is currently moving toward destination";
        }

        if (
                "REACHED_DESTINATION".equals(
                        status
                )
        ) {
            return "Parcel has reached the destination area";
        }

        if (
                "DELIVERED".equals(
                        status
                )
        ) {
            return "Parcel has been successfully delivered";
        }

        return "";
    }

    private JPanel createFieldBlock(
            String labelText,
            JComponent component,
            String hint
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                0,
                                6
                        )
                );

        panel.setOpaque(false);

        JPanel labelPanel =
                new JPanel(
                        new BorderLayout()
                );

        labelPanel.setOpaque(false);

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
                component,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JPanel createWhiteCard() {

        JPanel card =
                new JPanel();

        card.setOpaque(true);

        card.setBackground(
                WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1,
                                true
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
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
                                11,
                                8,
                                11
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

    private void styleComboBox(
            JComboBox<String> comboBox
    ) {

        comboBox.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        comboBox.setBackground(
                INPUT_BG
        );

        comboBox.setForeground(
                TEXT_DARK
        );

        comboBox.setOpaque(true);

        comboBox.setFocusable(false);

        comboBox.setPreferredSize(
                new Dimension(
                        0,
                        42
                )
        );

        comboBox.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        comboBox.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                INPUT_BORDER,
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                2,
                                8,
                                2,
                                8
                        )
                )
        );

        comboBox.setRenderer(
                new DefaultListCellRenderer() {

                    @Override
                    public Component
                    getListCellRendererComponent(
                            JList<?> list,
                            Object value,
                            int index,
                            boolean isSelected,
                            boolean cellHasFocus
                    ) {

                        JLabel label =
                                (JLabel) super
                                        .getListCellRendererComponent(
                                                list,
                                                value,
                                                index,
                                                isSelected,
                                                cellHasFocus
                                        );

                        label.setFont(
                                new Font(
                                        "SansSerif",
                                        Font.PLAIN,
                                        12
                                )
                        );

                        label.setBorder(
                                new EmptyBorder(
                                        7,
                                        8,
                                        7,
                                        8
                                )
                        );

                        if (isSelected) {

                            label.setBackground(
                                    BLUE
                            );

                            label.setForeground(
                                    WHITE
                            );

                        } else {

                            label.setBackground(
                                    WHITE
                            );

                            label.setForeground(
                                    TEXT_DARK
                            );
                        }

                        return label;
                    }
                }
        );

        comboBox.setUI(
                new BasicComboBoxUI() {

                    @Override
                    protected JButton
                    createArrowButton() {

                        JButton button =
                                super
                                        .createArrowButton();

                        button.setContentAreaFilled(
                                false
                        );

                        button.setBorder(
                                BorderFactory
                                        .createEmptyBorder()
                        );

                        button.setForeground(
                                ORANGE
                        );

                        return button;
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

                        super.paintComponent(
                                g
                        );
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
                WHITE
        );

        button.setContentAreaFilled(
                false
        );

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

                        super.paintComponent(
                                g
                        );
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

    private void clearForm() {

        parcelIdField.setText("");

        statusComboBox.setSelectedIndex(
                0
        );

        parcelIdField.requestFocus();
    }

    private void updateStatus() {

        String parcelId =
                parcelIdField
                        .getText()
                        .trim();

        String newStatus =
                statusComboBox
                        .getSelectedItem()
                        .toString();

        if (parcelId.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Parcel ID!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            parcelIdField.requestFocus();

            return;
        }

        try {

            Rider rider =
                    new Rider(user);

            rider.updateParcelStatus(
                    parcelId,
                    newStatus
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Parcel status updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();

        } catch (NotFoundException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Update Failed",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
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