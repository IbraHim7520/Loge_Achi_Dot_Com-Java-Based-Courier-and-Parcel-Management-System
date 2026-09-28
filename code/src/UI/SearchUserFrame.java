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
import java.awt.geom.RoundRectangle2D;

public class SearchUserFrame extends JPanel {

    private final User user;

    private JTextField userIdField;
    private JTextArea resultArea;

    // =========================================================
    // THEME COLORS
    // =========================================================

    private final Color BLUE =
            new Color(0, 97, 153);

    private final Color SKY_BLUE =
            new Color(138, 207, 248);

    private final Color SOFT_YELLOW =
            new Color(244, 235, 108);

    private final Color GOLD =
            new Color(255, 212, 68);

    private final Color BG_TOP =
            new Color(3, 39, 63);

    private final Color BG_BOTTOM =
            new Color(0, 72, 110);

    private final Color CARD_BG =
            new Color(0, 55, 88, 235);

    private final Color CARD_BORDER =
            new Color(138, 207, 248, 90);

    private final Color INPUT_BG =
            new Color(248, 252, 255);

    private final Color INPUT_TEXT =
            new Color(30, 45, 55);

    private final Color INPUT_BORDER =
            new Color(138, 207, 248, 130);

    private final Color TEXT_WHITE =
            Color.WHITE;

    private final Color TEXT_MUTED =
            new Color(190, 220, 235);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public SearchUserFrame(User user) {

        this.user = user;

        setLayout(new BorderLayout());
        setOpaque(false);

        buildUI();
    }

    // =========================================================
    // BUILD UI
    // =========================================================

    private void buildUI() {

        // =====================================================
        // ROOT BACKGROUND
        // =====================================================

        JPanel rootPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2d =
                        (Graphics2D) g.create();

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                int width = getWidth();
                int height = getHeight();

                // Main background gradient
                GradientPaint background =
                        new GradientPaint(
                                0,
                                0,
                                BG_TOP,
                                0,
                                height,
                                BG_BOTTOM
                        );

                g2d.setPaint(background);

                g2d.fillRect(
                        0,
                        0,
                        width,
                        height
                );

                // Decorative glow - top right
                g2d.setColor(
                        new Color(
                                138,
                                207,
                                248,
                                18
                        )
                );

                g2d.fillOval(
                        width - 180,
                        -80,
                        250,
                        250
                );

                // Decorative glow - bottom left
                g2d.setColor(
                        new Color(
                                255,
                                212,
                                68,
                                12
                        )
                );

                g2d.fillOval(
                        -100,
                        height - 160,
                        230,
                        230
                );

                // Small decorative dots
                g2d.setColor(
                        new Color(
                                138,
                                207,
                                248,
                                90
                        )
                );

                for (int i = 0; i < 6; i++) {

                    int x = 35 + (i * 25);
                    int y = 30 + ((i % 2) * 18);

                    g2d.fillOval(
                            x,
                            y,
                            3,
                            3
                    );
                }

                g2d.dispose();
            }
        };

        rootPanel.setOpaque(false);

        rootPanel.setLayout(
                new GridBagLayout()
        );

        // =====================================================
        // MAIN CARD
        // =====================================================

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2d =
                        (Graphics2D) g.create();

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Card background
                g2d.setColor(CARD_BG);

                g2d.fill(
                        new RoundRectangle2D.Float(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                22,
                                22
                        )
                );

                // Card border
                g2d.setColor(CARD_BORDER);

                g2d.setStroke(
                        new BasicStroke(1.2f)
                );

                g2d.draw(
                        new RoundRectangle2D.Float(
                                1,
                                1,
                                getWidth() - 2,
                                getHeight() - 2,
                                22,
                                22
                        )
                );

                g2d.dispose();

                super.paintComponent(g);
            }
        };

        mainPanel.setOpaque(false);

        mainPanel.setLayout(
                new BorderLayout(0, 16)
        );

        mainPanel.setPreferredSize(
                new Dimension(
                        560,
                        470
                )
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        24,
                        30,
                        24,
                        30
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        JPanel titlePanel =
                new JPanel(
                        new BorderLayout()
                );

        titlePanel.setOpaque(false);

        // Small icon
        JLabel iconLabel =
                new JLabel("⌕");

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        27
                )
        );

        iconLabel.setForeground(
                GOLD
        );

        iconLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        iconLabel.setPreferredSize(
                new Dimension(
                        40,
                        40
                )
        );

        titlePanel.add(
                iconLabel,
                BorderLayout.WEST
        );

        JPanel titleTextPanel =
                new JPanel();

        titleTextPanel.setOpaque(false);

        titleTextPanel.setLayout(
                new BoxLayout(
                        titleTextPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "Search User"
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        23
                )
        );

        titleLabel.setForeground(
                TEXT_WHITE
        );

        titleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Find user information using their User ID"
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

        subtitleLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        titleTextPanel.add(
                titleLabel
        );

        titleTextPanel.add(
                Box.createVerticalStrut(3)
        );

        titleTextPanel.add(
                subtitleLabel
        );

        titlePanel.add(
                titleTextPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // CENTER PANEL
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(0, 14)
                );

        centerPanel.setOpaque(false);

        // =====================================================
        // SEARCH ROW
        // =====================================================

        JPanel searchContainer =
                new JPanel(
                        new BorderLayout(
                                0,
                                6
                        )
                );

        searchContainer.setOpaque(false);

        JLabel label =
                new JLabel(
                        "USER ID"
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        label.setForeground(
                SKY_BLUE
        );

        searchContainer.add(
                label,
                BorderLayout.NORTH
        );

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        searchPanel.setOpaque(false);

        userIdField =
                new JTextField();

        styleInputField(
                userIdField
        );

        userIdField.setToolTipText(
                "Enter User ID"
        );

        JButton searchButton =
                createGoldenButton(
                        "Search"
                );

        searchButton.setPreferredSize(
                new Dimension(
                        105,
                        36
                )
        );

        searchPanel.add(
                userIdField,
                BorderLayout.CENTER
        );

        searchPanel.add(
                searchButton,
                BorderLayout.EAST
        );

        searchContainer.add(
                searchPanel,
                BorderLayout.CENTER
        );

        centerPanel.add(
                searchContainer,
                BorderLayout.NORTH
        );

        // =====================================================
        // RESULT HEADER
        // =====================================================

        JLabel resultLabel =
                new JLabel(
                        "SEARCH RESULT"
                );

        resultLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        resultLabel.setForeground(
                SKY_BLUE
        );

        // =====================================================
        // RESULT AREA
        // =====================================================

        resultArea =
                new JTextArea();

        resultArea.setEditable(false);

        resultArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        resultArea.setForeground(
                new Color(
                        30,
                        45,
                        55
                )
        );

        resultArea.setBackground(
                INPUT_BG
        );

        resultArea.setLineWrap(true);

        resultArea.setWrapStyleWord(true);

        resultArea.setBorder(
                new EmptyBorder(
                        14,
                        16,
                        14,
                        16
                )
        );

        JScrollPane resultScrollPane =
                new JScrollPane(
                        resultArea
                );

        resultScrollPane.setOpaque(
                false
        );

        resultScrollPane
                .getViewport()
                .setOpaque(true);

        resultScrollPane
                .getViewport()
                .setBackground(
                        INPUT_BG
                );

        resultScrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                INPUT_BORDER,
                                1,
                                true
                        ),
                        BorderFactory.createEmptyBorder(
                                1,
                                1,
                                1,
                                1
                        )
                )
        );

        JPanel resultContainer =
                new JPanel(
                        new BorderLayout(
                                0,
                                6
                        )
                );

        resultContainer.setOpaque(false);

        resultContainer.add(
                resultLabel,
                BorderLayout.NORTH
        );

        resultContainer.add(
                resultScrollPane,
                BorderLayout.CENTER
        );

        centerPanel.add(
                resultContainer,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM BUTTON
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                0,
                                0
                        )
                );

        bottomPanel.setOpaque(false);

        JButton clearButton =
                createOutlineButton(
                        "Clear"
                );

        clearButton.setPreferredSize(
                new Dimension(
                        90,
                        35
                )
        );

        bottomPanel.add(
                clearButton
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // ADD TO ROOT
        // =====================================================

        rootPanel.add(
                mainPanel
        );

        add(
                rootPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // ACTION LISTENERS
        // =====================================================

        searchButton.addActionListener(
                e -> searchUser()
        );

        userIdField.addActionListener(
                e -> searchUser()
        );

        clearButton.addActionListener(
                e -> {
                    userIdField.setText("");
                    resultArea.setText("");
                    userIdField.requestFocus();
                }
        );
    }

    // =========================================================
    // INPUT FIELD STYLE
    // =========================================================

    private void styleInputField(
            JTextField field
    ) {

        field.setOpaque(true);

        field.setBackground(
                INPUT_BG
        );

        field.setForeground(
                INPUT_TEXT
        );

        field.setCaretColor(
                BLUE
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
                                7,
                                11,
                                7,
                                11
                        )
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
                                                SKY_BLUE,
                                                2,
                                                true
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                6,
                                                10,
                                                6,
                                                10
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
                                                7,
                                                11,
                                                7,
                                                11
                                        )
                                )
                        );
                    }
                }
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        36
                )
        );
    }

    // =========================================================
    // GOLD BUTTON
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
                                        ? SOFT_YELLOW
                                        : GOLD;

                        Color bottom =
                                isHovered
                                        ? GOLD
                                        : new Color(
                                        240,
                                        190,
                                        35
                                );

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
                                        11,
                                        11
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
                new Color(
                        20,
                        55,
                        70
                )
        );

        button.setContentAreaFilled(
                false
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // OUTLINE BUTTON
    // =========================================================

    private JButton createOutlineButton(
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

                        if (isHovered) {

                            g2d.setColor(
                                    new Color(
                                            138,
                                            207,
                                            248,
                                            25
                                    )
                            );

                            g2d.fill(
                                    new RoundRectangle2D.Float(
                                            0,
                                            0,
                                            getWidth(),
                                            getHeight(),
                                            11,
                                            11
                                    )
                            );
                        }

                        g2d.setColor(
                                new Color(
                                        138,
                                        207,
                                        248,
                                        170
                                )
                        );

                        g2d.setStroke(
                                new BasicStroke(
                                        1.2f
                                )
                        );

                        g2d.draw(
                                new RoundRectangle2D.Float(
                                        1,
                                        1,
                                        getWidth() - 2,
                                        getHeight() - 2,
                                        11,
                                        11
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
                TEXT_WHITE
        );

        button.setContentAreaFilled(
                false
        );

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // SEARCH USER
    // =========================================================

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

            return;
        }

        try {

            User foundUser =
                    AdminFile.searchUser(userID);

            StringBuilder result =
                    new StringBuilder();

            result.append(
                    "========== USER DETAILS ==========\n\n"
            );

            result.append("User ID  : ")
                    .append(foundUser.getUser_id())
                    .append("\n");

            result.append("Name     : ")
                    .append(foundUser.getUser_name())
                    .append("\n");

            result.append("Email    : ")
                    .append(foundUser.getUser_email())
                    .append("\n");

            result.append("Role     : ")
                    .append(foundUser.getUser_role())
                    .append("\n\n");

            result.append(
                    "=================================="
            );

            resultArea.setText(
                    result.toString()
            );

        } catch (NotFoundException e) {

            resultArea.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "User Not Found",
                    JOptionPane.INFORMATION_MESSAGE
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
}