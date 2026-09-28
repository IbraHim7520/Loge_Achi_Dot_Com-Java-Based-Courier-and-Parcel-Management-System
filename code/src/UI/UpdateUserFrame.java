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
import java.awt.geom.RoundRectangle2D;

public class UpdateUserFrame extends JFrame {

    private User user;

    private JTextField userIdField;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;

    // =========================================================
    // THEME
    // =========================================================

    private final Color BLUE = new Color(0, 97, 153);
    private final Color BLUE_DARK = new Color(0, 61, 95);

    private final Color SKY_BLUE = new Color(138, 207, 248);
    private final Color SOFT_YELLOW = new Color(244, 235, 108);
    private final Color GOLD = new Color(255, 212, 68);

    private final Color BG_TOP = new Color(3, 39, 63);
    private final Color BG_BOTTOM = new Color(0, 72, 110);

    private final Color CARD_BG =
            new Color(0, 55, 88, 235);

    private final Color CARD_BORDER =
            new Color(138, 207, 248, 80);

    private final Color INPUT_BG =
            new Color(245, 250, 253);

    private final Color INPUT_BORDER =
            new Color(138, 207, 248, 100);

    private final Color INPUT_FOCUS =
            new Color(138, 207, 248);

    private final Color INPUT_TEXT =
            new Color(25, 45, 58);

    private final Color TEXT_WHITE =
            Color.WHITE;

    private final Color TEXT_LABEL =
            new Color(225, 240, 248);

    private final Color TEXT_HINT =
            new Color(175, 210, 225);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public UpdateUserFrame(User user) {

        this.user = user;

        setTitle(
                "Update User - Courier Management System"
        );

        setSize(
                520,
                570
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(false);

        // =====================================================
        // ROOT BACKGROUND
        // =====================================================

        JPanel rootPanel =
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

                        int width =
                                getWidth();

                        int height =
                                getHeight();

                        // Main blue gradient
                        GradientPaint gradient =
                                new GradientPaint(
                                        0,
                                        0,
                                        BG_TOP,
                                        width,
                                        height,
                                        BG_BOTTOM
                                );

                        g2d.setPaint(
                                gradient
                        );

                        g2d.fillRect(
                                0,
                                0,
                                width,
                                height
                        );

                        // =================================================
                        // DECORATIVE GLOW - TOP RIGHT
                        // =================================================

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
                                -110,
                                300,
                                300
                        );

                        // =================================================
                        // DECORATIVE GLOW - BOTTOM LEFT
                        // =================================================

                        g2d.setColor(
                                new Color(
                                        255,
                                        212,
                                        68,
                                        10
                                )
                        );

                        g2d.fillOval(
                                -130,
                                height - 150,
                                280,
                                280
                        );

                        // =================================================
                        // SMALL DECORATIVE DOTS
                        // =================================================

                        g2d.setColor(
                                new Color(
                                        138,
                                        207,
                                        248,
                                        70
                                )
                        );

                        g2d.fillOval(
                                30,
                                45,
                                5,
                                5
                        );

                        g2d.fillOval(
                                45,
                                65,
                                3,
                                3
                        );

                        g2d.fillOval(
                                width - 55,
                                height - 65,
                                5,
                                5
                        );

                        g2d.dispose();
                    }
                };

        rootPanel.setLayout(
                new GridBagLayout()
        );

        // =========================================================
        // MAIN CARD
        // =========================================================

        JPanel mainPanel =
                new JPanel() {

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

        mainPanel.setOpaque(
                false
        );

        mainPanel.setLayout(
                new BorderLayout()
        );

        mainPanel.setPreferredSize(
                new Dimension(
                        430,
                        490
                )
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        22,
                        28,
                        20,
                        28
                )
        );

        // =========================================================
        // TITLE HEADER
        // =========================================================

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(
                false
        );

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        // Icon
        JLabel iconLabel =
                new JLabel(
                        "♙"
                );

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25
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
                        "Update User Account"
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
                        "Modify user account information"
                );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        subtitleLabel.setForeground(
                TEXT_HINT
        );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        titlePanel.add(
                iconLabel
        );

        titlePanel.add(
                Box.createVerticalStrut(3)
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

        titlePanel.add(
                Box.createVerticalStrut(14)
        );

        mainPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // =========================================================
        // FORM
        // =========================================================

        JPanel formPanel =
                new JPanel();

        formPanel.setOpaque(
                false
        );

        formPanel.setLayout(
                new BoxLayout(
                        formPanel,
                        BoxLayout.Y_AXIS
                )
        );

        userIdField =
                new JTextField();

        styleInputField(
                userIdField
        );

        nameField =
                new JTextField();

        styleInputField(
                nameField
        );

        emailField =
                new JTextField();

        styleInputField(
                emailField
        );

        passwordField =
                new JPasswordField();

        styleInputField(
                passwordField
        );

        formPanel.add(
                createFormRow(
                        "User ID *",
                        userIdField
                )
        );

        formPanel.add(
                Box.createVerticalStrut(
                        9
                )
        );

        formPanel.add(
                createFormRow(
                        "New Name *",
                        nameField
                )
        );

        formPanel.add(
                Box.createVerticalStrut(
                        9
                )
        );

        formPanel.add(
                createFormRow(
                        "New Email *",
                        emailField
                )
        );

        formPanel.add(
                Box.createVerticalStrut(
                        9
                )
        );

        formPanel.add(
                createFormRow(
                        "New Password",
                        passwordField
                )
        );

        formPanel.add(
                Box.createVerticalStrut(
                        5
                )
        );

        JLabel passwordHint =
                new JLabel(
                        "Leave password empty to keep the old password."
                );

        passwordHint.setFont(
                new Font(
                        "SansSerif",
                        Font.ITALIC,
                        10
                )
        );

        passwordHint.setForeground(
                TEXT_HINT
        );

        passwordHint.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        formPanel.add(
                passwordHint
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =========================================================
        // BUTTONS
        // =========================================================

        JButton updateButton =
                createGoldenButton(
                        "Update User"
                );

        JButton clearButton =
                createOutlineButton(
                        "Clear"
                );

        JButton closeButton =
                createOutlineButton(
                        "Close"
                );

        updateButton.setPreferredSize(
                new Dimension(
                        130,
                        38
                )
        );

        clearButton.setPreferredSize(
                new Dimension(
                        85,
                        38
                )
        );

        closeButton.setPreferredSize(
                new Dimension(
                        85,
                        38
                )
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                9,
                                4
                        )
                );

        buttonPanel.setOpaque(
                false
        );

        buttonPanel.add(
                updateButton
        );

        buttonPanel.add(
                clearButton
        );

        buttonPanel.add(
                closeButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =========================================================
        // ADD CARD
        // =========================================================

        rootPanel.add(
                mainPanel
        );

        add(
                rootPanel
        );

        // =========================================================
        // ACTION LISTENERS
        // =========================================================

        updateButton.addActionListener(
                e -> updateUser()
        );

        clearButton.addActionListener(
                e -> {

                    userIdField.setText("");
                    nameField.setText("");
                    emailField.setText("");
                    passwordField.setText("");
                }
        );

        closeButton.addActionListener(
                e -> dispose()
        );
    }

    // =========================================================
    // FORM ROW
    // =========================================================

    private JPanel createFormRow(
            String labelText,
            JTextField field
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout(
                                0,
                                4
                        )
                );

        row.setOpaque(
                false
        );

        JLabel label =
                new JLabel(
                        labelText
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        label.setForeground(
                TEXT_LABEL
        );

        row.add(
                label,
                BorderLayout.NORTH
        );

        row.add(
                field,
                BorderLayout.CENTER
        );

        return row;
    }

    // =========================================================
    // INPUT FIELD STYLE
    // =========================================================

    private void styleInputField(
            JTextField field
    ) {

        field.setOpaque(
                true
        );

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
                                6,
                                10,
                                6,
                                10
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
                                                5,
                                                9,
                                                5,
                                                9
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
                                                6,
                                                10,
                                                6,
                                                10
                                        )
                                )
                        );
                    }
                }
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        34
                )
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
                                        225,
                                        95
                                )
                                        : SOFT_YELLOW;

                        Color bottom =
                                isHovered
                                        ? new Color(
                                        255,
                                        195,
                                        35
                                )
                                        : GOLD;

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
                BLUE_DARK
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
                                            35
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
                                        150
                                )
                        );

                        g2d.setStroke(
                                new BasicStroke(
                                        1.1f
                                )
                        );

                        g2d.draw(
                                new RoundRectangle2D.Float(
                                        1,
                                        1,
                                        getWidth() - 2,
                                        getHeight() - 2,
                                        10,
                                        10
                                )
                        );

                        g2d.dispose();

                        super.paintComponent(
                                g
                        );
                    }
                };

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
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
    // UPDATE USER
    // =========================================================

    private void updateUser() {

        String userID =
                userIdField.getText().trim();

        String name =
                nameField.getText().trim();

        String email =
                emailField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
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

            userIdField.setText("");
            nameField.setText("");
            emailField.setText("");
            passwordField.setText("");

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