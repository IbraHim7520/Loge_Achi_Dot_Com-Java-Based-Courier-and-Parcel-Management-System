package UI;

import custom_exception.InvalidAmountException;
import custom_exception.NotFoundException;
import custom_exception.UnauthorizedAccessException;
import file.AdminFile;
import model.Parcel;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class SearchParcelFrame extends JPanel {

    private final User user;

    private JTextField parcelIdField;
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

    public SearchParcelFrame(User user) {

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

                // Top-right decorative glow
                g2d.setColor(
                        new Color(
                                138,
                                207,
                                248,
                                18
                        )
                );

                g2d.fillOval(
                        width - 190,
                        -90,
                        260,
                        260
                );

                // Bottom-left decorative glow
                g2d.setColor(
                        new Color(
                                255,
                                212,
                                68,
                                12
                        )
                );

                g2d.fillOval(
                        -110,
                        height - 170,
                        240,
                        240
                );

                // Decorative dots
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
                g2d.setColor(
                        CARD_BG
                );

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
                g2d.setColor(
                        CARD_BORDER
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
                new BorderLayout(
                        0,
                        16
                )
        );

        mainPanel.setPreferredSize(
                new Dimension(
                        580,
                        500
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

        JLabel iconLabel =
                new JLabel("⌕");

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
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
                        42,
                        42
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
                        "Search Parcel"
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
                        "Find parcel information using Parcel ID"
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
                Box.createVerticalStrut(
                        3
                )
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
                        new BorderLayout(
                                0,
                                14
                        )
                );

        centerPanel.setOpaque(false);

        // =====================================================
        // SEARCH SECTION
        // =====================================================

        JPanel searchContainer =
                new JPanel(
                        new BorderLayout(
                                0,
                                6
                        )
                );

        searchContainer.setOpaque(false);

        JLabel parcelIdLabel =
                new JLabel(
                        "PARCEL ID"
                );

        parcelIdLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        parcelIdLabel.setForeground(
                SKY_BLUE
        );

        searchContainer.add(
                parcelIdLabel,
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

        parcelIdField =
                new JTextField();

        styleInputField(
                parcelIdField
        );

        parcelIdField.setToolTipText(
                "Enter Parcel ID"
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
                parcelIdField,
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
        // RESULT SECTION
        // =====================================================

        JLabel resultLabel =
                new JLabel(
                        "PARCEL DETAILS"
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
        // BOTTOM
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
        // ADD ROOT
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
                e -> searchParcel()
        );

        parcelIdField.addActionListener(
                e -> searchParcel()
        );

        clearButton.addActionListener(
                e -> {
                    parcelIdField.setText("");
                    resultArea.setText("");
                    parcelIdField.requestFocus();
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
    // SEARCH PARCEL
    // =========================================================

    private void searchParcel() {

        String parcelID =
                parcelIdField
                        .getText()
                        .trim();

        if (parcelID.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Parcel ID!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            Parcel parcel =
                    AdminFile.searchParcel(parcelID);

            StringBuilder result =
                    new StringBuilder();

            result.append(
                    "========== PARCEL DETAILS ==========\n\n"
            );

            result.append("Parcel ID       : ")
                    .append(parcel.getParcelID())
                    .append("\n");

            result.append("Parcel Name     : ")
                    .append(parcel.getParcelName())
                    .append("\n");

            result.append("Receiver Address: ")
                    .append(parcel.getReciverAddress())
                    .append("\n");

            result.append("Receiver Phone  : ")
                    .append(parcel.getReciverPhone())
                    .append("\n");

            result.append("Weight          : ")
                    .append(parcel.getWeight())
                    .append(" kg\n");

            result.append("Sender Email    : ")
                    .append(parcel.getSenderEmail())
                    .append("\n");

            result.append("Sender ID       : ")
                    .append(parcel.getSenderId())
                    .append("\n");

            result.append("Status          : ")
                    .append(parcel.getParcelStatus())
                    .append("\n");

            result.append("Delivery Charge : ")
                    .append(parcel.getDeliveryCharge())
                    .append("\n");

            String riderId =
                    parcel.getRiderId();

            result.append("Rider ID        : ")
                    .append(
                            riderId == null
                                    ? "Not Assigned"
                                    : riderId
                    )
                    .append("\n\n");

            result.append(
                    "===================================="
            );

            resultArea.setText(
                    result.toString()
            );

        } catch (NotFoundException e) {

            resultArea.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Parcel Not Found",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (UnauthorizedAccessException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Unauthorized",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (InvalidAmountException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Invalid Parcel Data",
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
}