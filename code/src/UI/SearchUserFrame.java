package UI;

import custom_exception.NotFoundException;
import file.AdminFile;
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

public class SearchUserFrame extends JPanel {

    private final User user;

    private JTextField userIdField;
    private JTextArea resultArea;

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

    public SearchUserFrame(User user) {

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
                        "ADMIN TOOLS"
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
                        "Search User"
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
                        "Find user information quickly using their User ID"
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
                new JLabel("●");

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
                        "USER DIRECTORY"
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
                        15,
                        0
                )
        );

        JPanel left =
                new JPanel();

        left.setOpaque(false);

        left.setLayout(
                new BoxLayout(
                        left,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel section =
                new JLabel(
                        "USER ID"
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
                        "User Identifier"
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

        left.add(section);

        left.add(
                Box.createVerticalStrut(
                        4
                )
        );

        left.add(title);

        userIdField =
                new JTextField();

        styleInputField(
                userIdField
        );

        userIdField.setToolTipText(
                "Enter User ID"
        );

        JButton searchButton =
                createPrimaryButton(
                        "Search User  →"
                );

        searchButton.setPreferredSize(
                new Dimension(
                        140,
                        42
                )
        );

        card.add(
                left,
                BorderLayout.WEST
        );

        card.add(
                userIdField,
                BorderLayout.CENTER
        );

        card.add(
                searchButton,
                BorderLayout.EAST
        );

        searchButton.addActionListener(
                e -> searchUser()
        );

        userIdField.addActionListener(
                e -> searchUser()
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

                        g2.setColor(
                                BLUE
                        );

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
                        "User Information"
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
                        "Search results will appear in this area"
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

        titleGroup.add(title);

        titleGroup.add(
                Box.createVerticalStrut(
                        4
                )
        );

        titleGroup.add(subtitle);

        header.add(
                titleGroup,
                BorderLayout.WEST
        );

        JLabel ready =
                new JLabel(
                        "SEARCH READY"
                );

        ready.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        8
                )
        );

        ready.setForeground(
                ORANGE
        );

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

        resultArea.setLineWrap(true);

        resultArea.setWrapStyleWord(true);

        resultArea.setCaretColor(
                ORANGE
        );

        resultArea.setBorder(
                new EmptyBorder(
                        20,
                        22,
                        20,
                        22
                )
        );

        resultArea.setText(
                "Enter a User ID above to view user information."
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        resultArea
                );

        scrollPane.setOpaque(false);

        scrollPane.getViewport()
                .setOpaque(true);

        scrollPane.getViewport()
                .setBackground(
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
                .setUnitIncrement(
                        14
                );

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
                        "● Search by exact User ID"
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

        JLabel admin =
                new JLabel(
                        "ADMIN SEARCH"
                );

        admin.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        admin.setForeground(
                BLUE
        );

        footer.add(
                info,
                BorderLayout.WEST
        );

        footer.add(
                admin,
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
                                17,
                                18,
                                17,
                                18
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

    private void searchUser() {

        String userID =
                userIdField
                        .getText()
                        .trim();

        if (userID.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter User ID!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            userIdField.requestFocus();

            return;
        }

        try {

            User foundUser =
                    AdminFile.searchUser(
                            userID
                    );

            StringBuilder result =
                    new StringBuilder();

            result.append(
                    "USER DETAILS\n"
            );

            result.append(
                    "────────────────────────────────────────\n\n"
            );

            result.append(
                    "User ID\n"
            );

            result.append(
                    safeValue(
                            foundUser.getUser_id()
                    )
            );

            result.append(
                    "\n\nName\n"
            );

            result.append(
                    safeValue(
                            foundUser.getUser_name()
                    )
            );

            result.append(
                    "\n\nEmail Address\n"
            );

            result.append(
                    safeValue(
                            foundUser.getUser_email()
                    )
            );

            result.append(
                    "\n\nRole\n"
            );

            result.append(
                    safeValue(
                            foundUser.getUser_role()
                    )
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

            resultArea.setCaretPosition(
                    0
            );

        } catch (NotFoundException e) {

            resultArea.setForeground(
                    new Color(
                            175,
                            75,
                            65
                    )
            );

            resultArea.setText(
                    "User not found.\n\n"
                            + e.getMessage()
            );

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "User Not Found",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {

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
                    "Error",
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