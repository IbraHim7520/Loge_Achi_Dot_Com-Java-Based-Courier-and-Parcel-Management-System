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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class TrackParcelPanel extends JPanel {

    private final User user;

    private JTextField parcelIdField;
    private JTextArea resultArea;

    // =====================================================
    // THEME COLORS
    // =====================================================

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
            new Color(8, 67, 99);

    private final Color INPUT_BORDER =
            new Color(138, 207, 248, 100);

    private final Color TEXT_WHITE =
            Color.WHITE;

    private final Color TEXT_MUTED =
            new Color(190, 220, 235);

    private final Color BTN_TEXT =
            new Color(20, 55, 70);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public TrackParcelPanel(User user) {

        this.user = user;

        setLayout(
                new GridBagLayout()
        );
    }

    // =====================================================
    // INITIALIZE UI
    // =====================================================

    {
        buildUI();
    }

    // =====================================================
    // BUILD UI
    // =====================================================

    private void buildUI() {

        // =================================================
        // MAIN CARD
        // =================================================

        JPanel mainPanel =
                new JPanel() {

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
                                CARD_BG
                        );

                        g2d.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        24,
                                        24
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
                                        24,
                                        24
                                )
                        );

                        // Gold accent
                        g2d.setColor(
                                GOLD
                        );

                        g2d.fillRoundRect(
                                30,
                                0,
                                getWidth() - 60,
                                4,
                                4,
                                4
                        );

                        g2d.dispose();
                    }
                };

        mainPanel.setOpaque(false);

        mainPanel.setLayout(
                new BorderLayout(
                        0,
                        18
                )
        );

        mainPanel.setPreferredSize(
                new Dimension(
                        620,
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

        // =================================================
        // TITLE
        // =================================================

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel icon =
                new JLabel(
                        "⌕"
                );

        icon.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30
                )
        );

        icon.setForeground(
                GOLD
        );

        icon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel title =
                new JLabel(
                        "Track Your Parcel"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        23
                )
        );

        title.setForeground(
                TEXT_WHITE
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitle =
                new JLabel(
                        "Enter your parcel ID to view delivery information"
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        subtitle.setForeground(
                TEXT_MUTED
        );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        titlePanel.add(
                icon
        );

        titlePanel.add(
                Box.createVerticalStrut(
                        2
                )
        );

        titlePanel.add(
                title
        );

        titlePanel.add(
                Box.createVerticalStrut(
                        4
                )
        );

        titlePanel.add(
                subtitle
        );

        mainPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // =================================================
        // CENTER AREA
        // =================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        centerPanel.setOpaque(false);

        // =================================================
        // SEARCH PANEL
        // =================================================

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        searchPanel.setOpaque(false);

        JLabel searchLabel =
                new JLabel(
                        "Parcel ID"
                );

        searchLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        searchLabel.setForeground(
                SKY_BLUE
        );

        parcelIdField =
                new JTextField();

        styleInputField(
                parcelIdField
        );

        parcelIdField.setToolTipText(
                "Enter Parcel ID"
        );

        JButton trackButton =
                createGoldenButton(
                        "⌕  Track"
                );

        trackButton.setPreferredSize(
                new Dimension(
                        105,
                        38
                )
        );

        searchPanel.add(
                searchLabel,
                BorderLayout.WEST
        );

        searchPanel.add(
                parcelIdField,
                BorderLayout.CENTER
        );

        searchPanel.add(
                trackButton,
                BorderLayout.EAST
        );

        centerPanel.add(
                searchPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // RESULT AREA
        // =================================================

        resultArea =
                new JTextArea();

        resultArea.setEditable(
                false
        );

        resultArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        resultArea.setForeground(
                TEXT_WHITE
        );

        resultArea.setBackground(
                new Color(
                        4,
                        48,
                        75
                )
        );

        resultArea.setCaretColor(
                TEXT_WHITE
        );

        resultArea.setLineWrap(
                true
        );

        resultArea.setWrapStyleWord(
                true
        );

        resultArea.setBorder(
                new EmptyBorder(
                        15,
                        17,
                        15,
                        17
                )
        );

        resultArea.setText(
                "Parcel information will appear here..."
        );

        resultArea.setForeground(
                TEXT_MUTED
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        resultArea
                );

        scrollPane.setOpaque(
                false
        );

        scrollPane.getViewport().setOpaque(
                true
        );

        scrollPane.getViewport().setBackground(
                new Color(
                        4,
                        48,
                        75
                )
        );

        scrollPane.setBorder(
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

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // ADD CARD
        // =================================================

        add(
                mainPanel
        );

        // =================================================
        // ACTION LISTENERS
        // =================================================

        trackButton.addActionListener(
                e -> trackParcel()
        );

        parcelIdField.addActionListener(
                e -> trackParcel()
        );
    }

    // =====================================================
    // ROOT BACKGROUND
    // =====================================================

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

        int width =
                getWidth();

        int height =
                getHeight();

        // Deep blue gradient
        GradientPaint background =
                new GradientPaint(
                        0,
                        0,
                        BG_TOP,
                        width,
                        height,
                        BG_BOTTOM
                );

        g2d.setPaint(
                background
        );

        g2d.fillRect(
                0,
                0,
                width,
                height
        );

        // Top-right glow
        g2d.setColor(
                new Color(
                        138,
                        207,
                        248,
                        16
                )
        );

        g2d.fillOval(
                width - 230,
                -110,
                310,
                310
        );

        // Bottom-left glow
        g2d.setColor(
                new Color(
                        255,
                        212,
                        68,
                        12
                )
        );

        g2d.fillOval(
                -130,
                height - 180,
                280,
                280
        );

        // Decorative dots
        g2d.setColor(
                new Color(
                        138,
                        207,
                        248,
                        70
                )
        );

        for (int i = 0; i < 5; i++) {

            g2d.fillOval(
                    25 + (i * 20),
                    25,
                    4,
                    4
            );
        }

        g2d.dispose();
    }

    // =====================================================
    // INPUT FIELD STYLE
    // =====================================================

    private void styleInputField(
            JTextField field
    ) {

        field.setOpaque(true);

        field.setBackground(
                INPUT_BG
        );

        field.setForeground(
                TEXT_WHITE
        );

        field.setCaretColor(
                SOFT_YELLOW
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
                                                SOFT_YELLOW,
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
                        38
                )
        );
    }

    // =====================================================
    // GOLDEN BUTTON
    // =====================================================

    private JButton createGoldenButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

                    private boolean isHovered =
                            false;

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
                                        ? new Color(
                                        255,
                                        230,
                                        100
                                )
                                        : GOLD;

                        Color bottom =
                                isHovered
                                        ? new Color(
                                        255,
                                        190,
                                        45
                                )
                                        : new Color(
                                        255,
                                        195,
                                        35
                                );

                        GradientPaint gp =
                                new GradientPaint(
                                        0,
                                        0,
                                        top,
                                        0,
                                        getHeight(),
                                        bottom
                                );

                        g2d.setPaint(
                                gp
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

                        // Highlight
                        g2d.setColor(
                                new Color(
                                        255,
                                        255,
                                        255,
                                        45
                                )
                        );

                        g2d.fillRoundRect(
                                1,
                                1,
                                getWidth() - 2,
                                getHeight() / 2,
                                11,
                                11
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

    // =====================================================
    // TRACK PARCEL
    // =====================================================

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
                    "========== PARCEL DETAILS ==========\n\n"
            );

            result.append(
                    "Parcel ID       : "
            ).append(
                    tracked.getParcelID()
            ).append(
                    "\n"
            );

            result.append(
                    "Parcel Name     : "
            ).append(
                    tracked.getParcelName()
            ).append(
                    "\n"
            );

            result.append(
                    "Receiver Address: "
            ).append(
                    tracked.getReciverAddress()
            ).append(
                    "\n"
            );

            result.append(
                    "Receiver Phone  : "
            ).append(
                    tracked.getReciverPhone()
            ).append(
                    "\n"
            );

            result.append(
                    "Weight          : "
            ).append(
                    tracked.getWeight()
            ).append(
                    " kg\n"
            );

            result.append(
                    "Delivery Charge : "
            ).append(
                    tracked.getDeliveryCharge()
            ).append(
                    "\n"
            );

            result.append(
                    "Status          : "
            ).append(
                    tracked.getParcelStatus()
            ).append(
                    "\n"
            );

            String riderId =
                    tracked.getRiderId();

            result.append(
                    "Rider ID        : "
            ).append(
                    riderId == null
                            ? "Not Assigned"
                            : riderId
            ).append(
                    "\n"
            );

            result.append(
                    "\n===================================="
            );

            resultArea.setForeground(
                    TEXT_WHITE
            );

            resultArea.setText(
                    result.toString()
            );

            resultArea.setCaretPosition(
                    0
            );

        } catch (NotFoundException e) {

            resultArea.setText("");

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

            resultArea.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}