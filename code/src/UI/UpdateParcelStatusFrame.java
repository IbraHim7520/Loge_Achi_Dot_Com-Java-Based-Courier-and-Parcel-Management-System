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
import java.awt.geom.RoundRectangle2D;

public class UpdateParcelStatusFrame extends JPanel {

    private final User user;

    private JTextField parcelIdField;
    private JComboBox<String> statusComboBox;

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

    private final Color INPUT_FOCUS_BORDER =
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

    public UpdateParcelStatusFrame(User user) {

        this.user = user;

        setLayout(new BorderLayout());

        buildUI();
    }

    // =====================================================
    // BUILD UI
    // =====================================================

    private void buildUI() {

        // =================================================
        // ROOT BACKGROUND
        // =================================================

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

                // Main deep blue gradient
                GradientPaint background =
                        new GradientPaint(
                                0,
                                0,
                                BG_TOP,
                                width,
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
                        width - 220,
                        -100,
                        300,
                        300
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
                        -120,
                        height - 180,
                        260,
                        260
                );

                // Small decorative dots
                g2d.setColor(
                        new Color(
                                138,
                                207,
                                248,
                                80
                        )
                );

                for (int i = 0; i < 5; i++) {

                    int x = 25 + (i * 22);

                    g2d.fillOval(
                            x,
                            25,
                            4,
                            4
                    );
                }

                g2d.dispose();
            }
        };

        rootPanel.setLayout(
                new GridBagLayout()
        );

        // =================================================
        // MAIN CARD
        // =================================================

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

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

                // Top accent line
                g2d.setColor(
                        GOLD
                );

                g2d.fillRoundRect(
                        28,
                        0,
                        getWidth() - 56,
                        4,
                        4,
                        4
                );

                g2d.dispose();
            }
        };

        mainPanel.setOpaque(false);

        mainPanel.setLayout(
                new BorderLayout()
        );

        mainPanel.setPreferredSize(
                new Dimension(
                        520,
                        360
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

        // Icon
        JLabel iconLabel =
                new JLabel(
                        "↻"
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

        // Title
        JLabel titleLabel =
                new JLabel(
                        "Update Parcel Status"
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        titleLabel.setForeground(
                TEXT_WHITE
        );

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        // Subtitle
        JLabel subtitleLabel =
                new JLabel(
                        "Change the delivery status of a parcel"
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
                Box.createVerticalStrut(2)
        );

        titlePanel.add(
                titleLabel
        );

        titlePanel.add(
                Box.createVerticalStrut(4)
        );

        titlePanel.add(
                subtitleLabel
        );

        titlePanel.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        20,
                        0
                )
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

        // =================================================
        // PARCEL ID
        // =================================================

        parcelIdField =
                new JTextField();

        styleInputField(
                parcelIdField
        );

        formPanel.add(
                createFormRow(
                        "📦  Parcel ID",
                        parcelIdField
                )
        );

        formPanel.add(
                Box.createVerticalStrut(
                        14
                )
        );

        // =================================================
        // STATUS
        // =================================================

        String[] statuses = {
                "ON_TRANSIT",
                "REACHED_DESTINATION",
                "DELIVERED",
                "CANCELED"
        };

        statusComboBox =
                new JComboBox<>(
                        statuses
                );

        styleComboBox(
                statusComboBox
        );

        formPanel.add(
                createFormRow(
                        "📌  New Status",
                        statusComboBox
                )
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // BUTTONS
        // =================================================

        JButton updateButton =
                createGoldenButton(
                        "✓  Update Status"
                );

        JButton clearButton =
                createOutlineButton(
                        "Clear"
                );

        updateButton.setPreferredSize(
                new Dimension(
                        145,
                        38
                )
        );

        clearButton.setPreferredSize(
                new Dimension(
                        90,
                        38
                )
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                5
                        )
                );

        buttonPanel.setOpaque(false);

        buttonPanel.add(
                updateButton
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

        rootPanel.add(
                mainPanel
        );

        add(
                rootPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // ACTION LISTENERS
        // =================================================

        updateButton.addActionListener(
                e -> updateStatus()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );
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
                                                INPUT_FOCUS_BORDER,
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
    // COMBO BOX STYLE
    // =====================================================

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
                TEXT_WHITE
        );

        comboBox.setFocusable(
                false
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
                    public Component getListCellRendererComponent(
                            JList<?> list,
                            Object value,
                            int index,
                            boolean isSelected,
                            boolean cellHasFocus
                    ) {

                        JLabel label =
                                (JLabel) super.getListCellRendererComponent(
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
                                        13
                                )
                        );

                        label.setBorder(
                                new EmptyBorder(
                                        6,
                                        8,
                                        6,
                                        8
                                )
                        );

                        if (isSelected) {

                            label.setBackground(
                                    BLUE
                            );

                            label.setForeground(
                                    Color.WHITE
                            );

                        } else {

                            label.setBackground(
                                    INPUT_BG
                            );

                            label.setForeground(
                                    Color.WHITE
                            );
                        }

                        return label;
                    }
                }
        );

        comboBox.setUI(
                new BasicComboBoxUI() {

                    @Override
                    protected JButton createArrowButton() {

                        JButton button =
                                super.createArrowButton();

                        button.setContentAreaFilled(
                                false
                        );

                        button.setBorder(
                                BorderFactory.createEmptyBorder()
                        );

                        button.setForeground(
                                GOLD
                        );

                        return button;
                    }
                }
        );

        comboBox.setMaximumSize(
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
                                        55
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

        button.setBorder(
                new EmptyBorder(
                        8,
                        14,
                        8,
                        14
                )
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
    // CLEAR FORM
    // =====================================================

    private void clearForm() {

        parcelIdField.setText("");

        statusComboBox.setSelectedIndex(
                0
        );
    }

    // =====================================================
    // UPDATE STATUS
    // =====================================================

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

            parcelIdField.setText("");

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
}