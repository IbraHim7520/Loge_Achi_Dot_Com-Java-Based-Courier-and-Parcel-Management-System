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
import java.awt.geom.RoundRectangle2D;

public class SendParcelPanel extends JPanel {

    private final User user;

    private JTextField parcelNameField;
    private JTextField addressField;
    private JTextField phoneField;
    private JTextField weightField;

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

    private final Color INPUT_FOCUS =
            new Color(244, 235, 108);

    private final Color TEXT_WHITE =
            Color.WHITE;

    private final Color TEXT_MUTED =
            new Color(190, 220, 235);

    private final Color BTN_TEXT =
            new Color(20, 55, 70);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public SendParcelPanel(User user) {

        this.user = user;

        setLayout(
                new GridBagLayout()
        );

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
                        560,
                        550
                )
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        24,
                        32,
                        24,
                        32
                )
        );

        // =================================================
        // TITLE HEADER
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

        JLabel iconLabel =
                new JLabel(
                        "▣"
                );

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30
                )
        );

        iconLabel.setForeground(
                GOLD
        );

        iconLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel titleLabel =
                new JLabel(
                        "Send New Parcel"
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
                Component.CENTER_ALIGNMENT
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Enter the receiver and parcel details"
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
                Component.CENTER_ALIGNMENT
        );

        titlePanel.add(
                iconLabel
        );

        titlePanel.add(
                Box.createVerticalStrut(
                        2
                )
        );

        titlePanel.add(
                titleLabel
        );

        titlePanel.add(
                Box.createVerticalStrut(
                        4
                )
        );

        titlePanel.add(
                subtitleLabel
        );

        mainPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // =================================================
        // FORM
        // =================================================

        JPanel formPanel =
                new JPanel();

        formPanel.setOpaque(false);

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );

        // Parcel Name
        parcelNameField =
                new JTextField();

        // Address
        addressField =
                new JTextField();

        // Phone
        phoneField =
                new JTextField();

        // Weight
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

        formPanel.add(
                createFormRow(
                        "📦  Parcel Name",
                        parcelNameField
                )
        );

        formPanel.add(
                Box.createVerticalStrut(
                        12
                )
        );

        formPanel.add(
                createFormRow(
                        "📍  Receiver Address",
                        addressField
                )
        );

        formPanel.add(
                Box.createVerticalStrut(
                        12
                )
        );

        formPanel.add(
                createFormRow(
                        "📞  Receiver Phone",
                        phoneField
                )
        );

        formPanel.add(
                Box.createVerticalStrut(
                        12
                )
        );

        formPanel.add(
                createFormRow(
                        "⚖️  Weight (kg)",
                        weightField
                )
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // BUTTONS
        // =================================================

        JButton sendButton =
                createGoldenButton(
                        "✓  Send Parcel"
                );

        JButton clearButton =
                createOutlineButton(
                        "Clear"
                );

        sendButton.setPreferredSize(
                new Dimension(
                        145,
                        40
                )
        );

        clearButton.setPreferredSize(
                new Dimension(
                        90,
                        40
                )
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                5
                        )
                );

        buttonPanel.setOpaque(false);

        buttonPanel.add(
                sendButton
        );

        buttonPanel.add(
                clearButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =================================================
        // ADD TO ROOT
        // =================================================

        add(
                mainPanel
        );

        // =================================================
        // ACTIONS
        // =================================================

        sendButton.addActionListener(
                e -> sendParcel()
        );

        clearButton.addActionListener(
                e -> clearFields()
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

        // Main blue gradient
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
    // FORM ROW
    // =====================================================

    private JPanel createFormRow(
            String labelText,
            JComponent inputComponent
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout(
                                0,
                                5
                        )
                );

        row.setOpaque(false);

        JLabel label =
                new JLabel(
                        labelText
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        label.setForeground(
                SKY_BLUE
        );

        row.add(
                label,
                BorderLayout.NORTH
        );

        row.add(
                inputComponent,
                BorderLayout.CENTER
        );

        return row;
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
                                                INPUT_FOCUS,
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

                        // Subtle highlight
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
    // OUTLINE BUTTON
    // =====================================================

    private JButton createOutlineButton(
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
                                            12,
                                            12
                                    )
                            );
                        }

                        g2d.setColor(
                                new Color(
                                        138,
                                        207,
                                        248,
                                        150
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
                                        12,
                                        12
                                )
                        );

                        g2d.dispose();

                        super.paintComponent(g);
                    }
                };

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
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

    // =====================================================
    // SEND PARCEL
    // =====================================================

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

        } catch (NumberFormatException e) {

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
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        result,
                        "Failed",
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

    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        parcelNameField.setText("");

        addressField.setText("");

        phoneField.setText("");

        weightField.setText("");
    }
}