package UI;

import custom_exception.NotFoundException;
import model.Admin;
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

public class UpdateUserFrame extends JFrame {

    private User user;

    private JTextField userIdField;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;

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

    public UpdateUserFrame(User user) {

        this.user = user;

        setTitle(
                "Update User - Loge Achi Dot Com"
        );

        Dimension screen =
                Toolkit.getDefaultToolkit()
                        .getScreenSize();

        int width =
                Math.max(
                        760,
                        (int) (
                                screen.width * 0.58
                        )
                );

        int height =
                Math.max(
                        620,
                        (int) (
                                screen.height * 0.70
                        )
                );

        setSize(
                width,
                height
        );

        setMinimumSize(
                new Dimension(
                        700,
                        580
                )
        );

        setResizable(true);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLayout(
                new BorderLayout()
        );

        buildUI();
    }

    private void buildUI() {

        JPanel root =
                new JPanel(
                        new BorderLayout()
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
                                        -140,
                                        -130,
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
                                getWidth() - 85,
                                70,
                                7,
                                7
                        );

                        g2.fillOval(
                                50,
                                getHeight() - 80,
                                7,
                                7
                        );

                        g2.dispose();
                    }
                };

        root.setBorder(
                new EmptyBorder(
                        18,
                        22,
                        18,
                        22
                )
        );

        JPanel card =
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
                                        24,
                                        24
                                )
                        );

                        g2.setColor(BORDER);

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

        card.setOpaque(false);

        card.setBorder(
                new EmptyBorder(
                        26,
                        30,
                        26,
                        30
                )
        );

        card.add(
                createHeader(),
                BorderLayout.NORTH
        );

        card.add(
                createMainContent(),
                BorderLayout.CENTER
        );

        card.add(
                createFooter(),
                BorderLayout.SOUTH
        );

        root.add(
                card,
                BorderLayout.CENTER
        );

        add(
                root,
                BorderLayout.CENTER
        );
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
                        "ACCOUNT MANAGEMENT"
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
                        "Update User Account"
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

        JLabel subtitle =
                new JLabel(
                        "Modify account information without changing the user's role"
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
                Box.createVerticalStrut(
                        5
                )
        );

        titleGroup.add(title);

        titleGroup.add(
                Box.createVerticalStrut(
                        5
                )
        );

        titleGroup.add(subtitle);

        header.add(
                titleGroup,
                BorderLayout.WEST
        );

        JPanel badge =
                new JPanel(
                        new BorderLayout(
                                7,
                                0
                        )
                );

        badge.setOpaque(true);

        badge.setBackground(
                SUCCESS_BG
        );

        badge.setBorder(
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

        JLabel text =
                new JLabel(
                        "USER MANAGEMENT"
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

        badge.add(
                dot,
                BorderLayout.WEST
        );

        badge.add(
                text,
                BorderLayout.CENTER
        );

        header.add(
                badge,
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

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.55;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        9
                );

        content.add(
                createFormCard(),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0.45;
        gbc.insets =
                new Insets(
                        0,
                        9,
                        0,
                        0
                );

        content.add(
                createInfoCard(),
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
                        18
                )
        );

        JPanel header =
                new JPanel();

        header.setOpaque(false);

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "Account Details"
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
                        "Enter the information you want to update"
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

        header.add(title);

        header.add(
                Box.createVerticalStrut(
                        4
                )
        );

        header.add(subtitle);

        card.add(
                header,
                BorderLayout.NORTH
        );

        JPanel form =
                new JPanel(
                        new GridLayout(
                                4,
                                1,
                                0,
                                12
                        )
                );

        form.setOpaque(false);

        userIdField =
                new JTextField();

        nameField =
                new JTextField();

        emailField =
                new JTextField();

        passwordField =
                new JPasswordField();

        styleInputField(
                userIdField
        );

        styleInputField(
                nameField
        );

        styleInputField(
                emailField
        );

        styleInputField(
                passwordField
        );

        form.add(
                createFieldBlock(
                        "USER ID *",
                        userIdField,
                        "Enter the target user ID"
                )
        );

        form.add(
                createFieldBlock(
                        "FULL NAME *",
                        nameField,
                        "Enter the new user name"
                )
        );

        form.add(
                createFieldBlock(
                        "EMAIL ADDRESS *",
                        emailField,
                        "Enter the new email address"
                )
        );

        form.add(
                createFieldBlock(
                        "NEW PASSWORD",
                        passwordField,
                        "Leave empty to keep current password"
                )
        );

        card.add(
                form,
                BorderLayout.CENTER
        );

        JPanel passwordHint =
                new JPanel(
                        new BorderLayout()
                );

        passwordHint.setOpaque(true);

        passwordHint.setBackground(
                new Color(
                        247,
                        250,
                        252
                )
        );

        passwordHint.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1,
                                true
                        ),
                        new EmptyBorder(
                                9,
                                11,
                                9,
                                11
                        )
                )
        );

        JLabel note =
                new JLabel(
                        "Password is optional"
                );

        note.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        note.setForeground(
                ORANGE
        );

        JLabel noteText =
                new JLabel(
                        "Leave it blank if you do not want to change it."
                );

        noteText.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        noteText.setForeground(
                TEXT_MUTED
        );

        passwordHint.add(
                note,
                BorderLayout.WEST
        );

        passwordHint.add(
                noteText,
                BorderLayout.EAST
        );

        card.add(
                passwordHint,
                BorderLayout.SOUTH
        );

        return card;
    }

    private JPanel createWhiteCard() {
        return null;
    }

    private JPanel createInfoCard() {

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

                        g2.setColor(BLUE);

                        g2.fillRoundRect(
                                22,
                                0,
                                95,
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
                        "Update Guide"
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
                        "Review before saving changes"
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

        title.add(heading);

        title.add(
                Box.createVerticalStrut(
                        4
                )
        );

        title.add(subtitle);

        card.add(
                title,
                BorderLayout.NORTH
        );

        JPanel guide =
                new JPanel();

        guide.setOpaque(false);

        guide.setLayout(
                new BoxLayout(
                        guide,
                        BoxLayout.Y_AXIS
                )
        );

        guide.add(
                createGuideItem(
                        "1",
                        "Identify User",
                        "Enter the User ID of the account you want to modify.",
                        BLUE
                )
        );

        guide.add(
                Box.createVerticalStrut(
                        12
                )
        );

        guide.add(
                createGuideItem(
                        "2",
                        "Update Details",
                        "Provide the new name and email information.",
                        ORANGE
                )
        );

        guide.add(
                Box.createVerticalStrut(
                        12
                )
        );

        guide.add(
                createGuideItem(
                        "3",
                        "Password",
                        "A blank password keeps the existing password unchanged.",
                        new Color(
                                64,
                                151,
                                194
                        )
                )
        );

        guide.add(
                Box.createVerticalStrut(
                        12
                )
        );

        guide.add(
                createGuideItem(
                        "4",
                        "Save Changes",
                        "Use the Update User button below to apply the changes.",
                        SUCCESS
                )
        );

        card.add(
                guide,
                BorderLayout.CENTER
        );

        JPanel accountBox =
                new JPanel(
                        new BorderLayout(
                                0,
                                6
                        )
                );

        accountBox.setOpaque(true);

        accountBox.setBackground(
                new Color(
                        255,
                        255,
                        255,
                        150
                )
        );

        accountBox.setBorder(
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
                                11,
                                12,
                                11,
                                12
                        )
                )
        );

        JLabel accountTitle =
                new JLabel(
                        "ADMIN ACCESS"
                );

        accountTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        accountTitle.setForeground(
                ORANGE
        );

        JLabel accountText =
                new JLabel(
                        "User account changes are handled through the admin system."
                );

        accountText.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        accountText.setForeground(
                TEXT_MUTED
        );

        accountBox.add(
                accountTitle,
                BorderLayout.NORTH
        );

        accountBox.add(
                accountText,
                BorderLayout.CENTER
        );

        card.add(
                accountBox,
                BorderLayout.SOUTH
        );

        return card;
    }

    private JPanel createGuideItem(
            String number,
            String titleText,
            String description,
            Color accent
    ) {

        JPanel item =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        item.setOpaque(true);

        item.setBackground(
                new Color(
                        255,
                        255,
                        255,
                        165
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
                                10,
                                10,
                                10
                        )
                )
        );

        JPanel badge =
                new JPanel(
                        new GridBagLayout()
                );

        badge.setOpaque(true);

        badge.setBackground(
                mixWithWhite(
                        accent,
                        0.84f
                )
        );

        badge.setPreferredSize(
                new Dimension(
                        35,
                        35
                )
        );

        JLabel numberLabel =
                new JLabel(
                        number
                );

        numberLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        numberLabel.setForeground(
                accent
        );

        badge.add(
                numberLabel
        );

        item.add(
                badge,
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
                        titleText
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        title.setForeground(
                TEXT_DARK
        );

        JLabel descriptionLabel =
                new JLabel(
                        "<html><div style='width:190px'>"
                                + description
                                + "</div></html>"
                );

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        descriptionLabel.setForeground(
                TEXT_MUTED
        );

        text.add(title);

        text.add(
                Box.createVerticalStrut(
                        3
                )
        );

        text.add(
                descriptionLabel
        );

        item.add(
                text,
                BorderLayout.CENTER
        );

        return item;
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
                field,
                BorderLayout.CENTER
        );

        return panel;
    }

    private JPanel createFooter() {

        JPanel footer =
                new JPanel(
                        new BorderLayout()
                );

        footer.setOpaque(false);

        JLabel left =
                new JLabel(
                        "●  Admin account management"
                );

        left.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        left.setForeground(
                TEXT_MUTED
        );

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                9,
                                0
                        )
                );

        buttons.setOpaque(false);

        JButton updateButton =
                createPrimaryButton(
                        "Update User  →"
                );

        updateButton.setPreferredSize(
                new Dimension(
                        145,
                        40
                )
        );

        JButton clearButton =
                createOutlineButton(
                        "Clear"
                );

        clearButton.setPreferredSize(
                new Dimension(
                        85,
                        40
                )
        );

        JButton closeButton =
                createOutlineButton(
                        "Close"
                );

        closeButton.setPreferredSize(
                new Dimension(
                        85,
                        40
                )
        );

        buttons.add(
                updateButton
        );

        buttons.add(
                clearButton
        );

        buttons.add(
                closeButton
        );

        footer.add(
                left,
                BorderLayout.WEST
        );

        footer.add(
                buttons,
                BorderLayout.EAST
        );

        updateButton.addActionListener(
                e -> updateUser()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        return footer;
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

                        g2.setStroke(
                                new BasicStroke(
                                        1.1f
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

    private void clearFields() {

        userIdField.setText("");
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");

        userIdField.requestFocus();
    }

    private void updateUser() {

        String userID =
                userIdField
                        .getText()
                        .trim();

        String name =
                nameField
                        .getText()
                        .trim();

        String email =
                emailField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        if (
                userID.isEmpty()
                        || name.isEmpty()
                        || email.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "User ID, Name and Email are required!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            Admin admin =
                    new Admin(user);

            admin.updateUser(
                    userID,
                    name,
                    email,
                    password
            );

            JOptionPane.showMessageDialog(
                    this,
                    "User updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

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