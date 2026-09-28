 package UI;

import custom_exception.NotFoundException;
import model.Admin;
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

public class AdminUpdateParcelStatusFrame extends JPanel {

    private final User user;

    private JTextField parcelIdField;
    private JComboBox<String> statusComboBox;

    // =========================================================
    // THEME COLORS
    // =========================================================

    private final Color BLUE = new Color(0, 97, 153);
    private final Color SKY_BLUE = new Color(138, 207, 248);
    private final Color SOFT_YELLOW = new Color(244, 235, 108);
    private final Color GOLD = new Color(255, 212, 68);

    private final Color CARD_BG = new Color(0, 55, 88, 238);
    private final Color CARD_BORDER = new Color(138, 207, 248, 90);

    private final Color INPUT_BG = new Color(248, 252, 255);
    private final Color INPUT_BORDER = new Color(138, 207, 248, 130);
    private final Color INPUT_FOCUS_BORDER = new Color(244, 235, 108);

    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_MUTED = new Color(190, 220, 235);
    private final Color INPUT_TEXT = new Color(20, 55, 75);

    // Golden button colors
    private final Color BTN_TOP = new Color(255, 224, 90);
    private final Color BTN_BOTTOM = new Color(255, 212, 68);
    private final Color BTN_HOVER_TOP = new Color(255, 235, 135);
    private final Color BTN_HOVER_BOTTOM = new Color(255, 202, 45);
    private final Color BTN_TEXT = new Color(35, 55, 65);

    public AdminUpdateParcelStatusFrame(User user) {

        this.user = user;

        setLayout(new BorderLayout());
        setOpaque(false);

        buildUI();
    }

    // =========================================================
    // BUILD UI
    // =========================================================

    private void buildUI() {

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
                g2d.setColor(CARD_BG);

                g2d.fill(
                        new RoundRectangle2D.Float(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                18,
                                18
                        )
                );

                // Border
                g2d.setColor(CARD_BORDER);

                g2d.setStroke(
                        new BasicStroke(1f)
                );

                g2d.draw(
                        new RoundRectangle2D.Float(
                                0.5f,
                                0.5f,
                                getWidth() - 1,
                                getHeight() - 1,
                                18,
                                18
                        )
                );

                g2d.dispose();
            }
        };

        mainPanel.setOpaque(false);

        mainPanel.setLayout(
                new BorderLayout(0, 20)
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        24,
                        28,
                        24,
                        28
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel titlePanel =
                new JPanel(new BorderLayout());

        titlePanel.setOpaque(false);

        // Gold accent
        JPanel titleAccent = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 =
                        (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(GOLD);

                g2.fillRoundRect(
                        0,
                        2,
                        5,
                        48,
                        4,
                        4
                );

                g2.dispose();
            }
        };

        titleAccent.setOpaque(false);

        titleAccent.setPreferredSize(
                new Dimension(12, 52)
        );

        // Header text
        JPanel headerTextGroup =
                new JPanel();

        headerTextGroup.setLayout(
                new BoxLayout(
                        headerTextGroup,
                        BoxLayout.Y_AXIS
                )
        );

        headerTextGroup.setOpaque(false);

        JLabel titleLabel =
                new JLabel("Update Parcel Status");

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

        JLabel subtitleLabel =
                new JLabel(
                        "Modify tracking status for active shipments"
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

        headerTextGroup.add(
                titleLabel
        );

        headerTextGroup.add(
                Box.createVerticalStrut(5)
        );

        headerTextGroup.add(
                subtitleLabel
        );

        JPanel headerLeft =
                new JPanel(
                        new BorderLayout(8, 0)
                );

        headerLeft.setOpaque(false);

        headerLeft.add(
                titleAccent,
                BorderLayout.WEST
        );

        headerLeft.add(
                headerTextGroup,
                BorderLayout.CENTER
        );

        // Header badge
        JLabel badge =
                new JLabel("STATUS CONTROL");

        badge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        badge.setForeground(
                new Color(255, 245, 180)
        );

        badge.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        badge.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        255,
                                        212,
                                        68,
                                        130
                                ),
                                1
                        ),
                        new EmptyBorder(
                                6,
                                12,
                                6,
                                12
                        )
                )
        );

        titlePanel.add(
                headerLeft,
                BorderLayout.WEST
        );

        titlePanel.add(
                badge,
                BorderLayout.EAST
        );

        mainPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // FORM
        // =====================================================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        10,
                        10,
                        10,
                        10
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // =====================================================
        // PARCEL ID LABEL
        // =====================================================

        JLabel parcelIdLabel =
                new JLabel("Parcel ID");

        parcelIdLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        parcelIdLabel.setForeground(
                TEXT_WHITE
        );

        // =====================================================
        // PARCEL ID FIELD
        // =====================================================

        parcelIdField =
                new JTextField() {

                    private boolean isFocused = false;

                    {
                        addFocusListener(
                                new FocusAdapter() {

                                    @Override
                                    public void focusGained(
                                            FocusEvent e
                                    ) {

                                        isFocused = true;
                                        repaint();
                                    }

                                    @Override
                                    public void focusLost(
                                            FocusEvent e
                                    ) {

                                        isFocused = false;
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

                        // Input background
                        g2d.setColor(
                                INPUT_BG
                        );

                        g2d.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        9,
                                        9
                                )
                        );

                        // Input border
                        g2d.setColor(
                                isFocused
                                        ? INPUT_FOCUS_BORDER
                                        : INPUT_BORDER
                        );

                        g2d.setStroke(
                                new BasicStroke(
                                        isFocused
                                                ? 1.6f
                                                : 1.0f
                                )
                        );

                        g2d.draw(
                                new RoundRectangle2D.Float(
                                        0.5f,
                                        0.5f,
                                        getWidth() - 1,
                                        getHeight() - 1,
                                        9,
                                        9
                                )
                        );

                        g2d.dispose();

                        super.paintComponent(g);
                    }
                };

        parcelIdField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        parcelIdField.setForeground(
                INPUT_TEXT
        );

        parcelIdField.setCaretColor(
                BLUE
        );

        parcelIdField.setOpaque(false);

        parcelIdField.setBorder(
                BorderFactory.createEmptyBorder(
                        6,
                        12,
                        6,
                        12
                )
        );

        parcelIdField.setPreferredSize(
                new Dimension(
                        260,
                        40
                )
        );

        // =====================================================
        // STATUS LABEL
        // =====================================================

        JLabel statusLabel =
                new JLabel("New Status");

        statusLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        statusLabel.setForeground(
                TEXT_WHITE
        );

        // =====================================================
        // STATUS COMBO BOX
        // =====================================================

        String[] statuses = {
                "PENDING",
                "ACCEPTED",
                "ON_TRANSIT",
                "REACHED_DESTINATION",
                "DELIVERED",
                "CANCELED"
        };

        statusComboBox =
                new JComboBox<>(statuses);

        statusComboBox.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        statusComboBox.setBackground(
                INPUT_BG
        );

        statusComboBox.setForeground(
                INPUT_TEXT
        );

        statusComboBox.setPreferredSize(
                new Dimension(
                        260,
                        40
                )
        );

        statusComboBox.setFocusable(false);

        // =====================================================
        // COMBO BOX RENDERER
        // =====================================================

        statusComboBox.setRenderer(
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

                        label.setBorder(
                                new EmptyBorder(
                                        8,
                                        12,
                                        8,
                                        12
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
                                    INPUT_TEXT
                            );
                        }

                        return label;
                    }
                }
        );

        // =====================================================
        // COMBO BOX UI
        // =====================================================

        statusComboBox.setUI(
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

                        return button;
                    }
                }
        );

        // =====================================================
        // ADD FORM COMPONENTS
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;

        formPanel.add(
                parcelIdLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;

        formPanel.add(
                parcelIdField,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;

        formPanel.add(
                statusLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;

        formPanel.add(
                statusComboBox,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        JButton clearButton =
                createOutlineButton(
                        "Clear"
                );

        JButton updateButton =
                createGoldenButton(
                        "Update Status"
                );

        clearButton.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );

        updateButton.setPreferredSize(
                new Dimension(
                        140,
                        38
                )
        );

        updateButton.addActionListener(
                e -> updateStatus()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        buttonPanel.add(
                clearButton
        );

        buttonPanel.add(
                updateButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // GOLDEN BUTTON
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
                                        ? BTN_HOVER_TOP
                                        : BTN_TOP;

                        Color bottom =
                                isHovered
                                        ? BTN_HOVER_BOTTOM
                                        : BTN_BOTTOM;

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
                                        10,
                                        10
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
                BTN_TEXT
        );

        button.setContentAreaFilled(false);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

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
                                            10,
                                            10
                                    )
                            );
                        }

                        g2d.setColor(
                                new Color(
                                        138,
                                        207,
                                        248,
                                        130
                                )
                        );

                        g2d.setStroke(
                                new BasicStroke(
                                        1.2f
                                )
                        );

                        g2d.draw(
                                new RoundRectangle2D.Float(
                                        0.5f,
                                        0.5f,
                                        getWidth() - 1,
                                        getHeight() - 1,
                                        10,
                                        10
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

        button.setContentAreaFilled(false);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // UPDATE STATUS
    // =========================================================

    private void updateStatus() {

        String parcelID =
                parcelIdField
                        .getText()
                        .trim();

        String newStatus =
                (String) statusComboBox
                        .getSelectedItem();

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

            Admin admin =
                    new Admin(user);

            admin.updateParcelStatus(
                    parcelID,
                    newStatus
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Parcel status updated successfully!",
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

    // =========================================================
    // CLEAR FIELDS
    // =========================================================

    private void clearFields() {

        parcelIdField.setText("");

        statusComboBox.setSelectedIndex(0);
    }
}

