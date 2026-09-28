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

public class DeleteParcelFrame extends JPanel {

    private final User user;
    private JTextField parcelIdField;

    // =========================================================
    // Theme
    // =========================================================

    private final Color BLUE = new Color(0, 97, 153);
    private final Color SKY_BLUE = new Color(138, 207, 248);
    private final Color SOFT_YELLOW = new Color(244, 235, 108);
    private final Color GOLD = new Color(255, 212, 68);

    private final Color BG_TOP = new Color(3, 39, 63);
    private final Color BG_BOTTOM = new Color(0, 72, 110);

    private final Color CARD_BG = new Color(0, 55, 88, 238);
    private final Color CARD_BORDER = new Color(138, 207, 248, 90);

    private final Color INPUT_BG = new Color(248, 252, 255);
    private final Color INPUT_BORDER = new Color(138, 207, 248, 130);
    private final Color INPUT_FOCUS = new Color(244, 235, 108);

    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_MUTED = new Color(190, 220, 235);

    // Delete button
    private final Color DELETE_TOP = new Color(210, 65, 75);
    private final Color DELETE_BOTTOM = new Color(155, 35, 50);
    private final Color DELETE_HOVER_TOP = new Color(235, 80, 90);
    private final Color DELETE_HOVER_BOTTOM = new Color(180, 45, 60);

    public DeleteParcelFrame(User user) {
        this.user = user;

        setOpaque(false);
        setLayout(new BorderLayout());

        buildUI();
    }

    // =========================================================
    // UI
    // =========================================================

    private void buildUI() {

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

                // Main blue gradient
                GradientPaint gradient =
                        new GradientPaint(
                                0,
                                0,
                                BG_TOP,
                                0,
                                height,
                                BG_BOTTOM
                        );

                g2d.setPaint(gradient);

                g2d.fillRect(
                        0,
                        0,
                        width,
                        height
                );

                // Top-right sky blue glow
                g2d.setColor(
                        new Color(
                                SKY_BLUE.getRed(),
                                SKY_BLUE.getGreen(),
                                SKY_BLUE.getBlue(),
                                28
                        )
                );

                g2d.fillOval(
                        width - 180,
                        -80,
                        250,
                        250
                );

                // Bottom-left gold glow
                g2d.setColor(
                        new Color(
                                GOLD.getRed(),
                                GOLD.getGreen(),
                                GOLD.getBlue(),
                                18
                        )
                );

                g2d.fillOval(
                        -100,
                        height - 150,
                        230,
                        230
                );

                // Decorative dots
                g2d.setColor(
                        new Color(
                                SKY_BLUE.getRed(),
                                SKY_BLUE.getGreen(),
                                SKY_BLUE.getBlue(),
                                90
                        )
                );

                for (int i = 0; i < 5; i++) {

                    g2d.fillOval(
                            30 + i * 18,
                            30,
                            4,
                            4
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
        // Main Card
        // =====================================================

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

                // Glass card
                g2d.setColor(CARD_BG);

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
                g2d.setColor(CARD_BORDER);

                g2d.setStroke(
                        new BasicStroke(1f)
                );

                g2d.draw(
                        new RoundRectangle2D.Float(
                                0,
                                0,
                                getWidth() - 1,
                                getHeight() - 1,
                                24,
                                24
                        )
                );

                // Gold top accent
                g2d.setColor(GOLD);

                g2d.fillRoundRect(
                        25,
                        0,
                        getWidth() - 50,
                        4,
                        4,
                        4
                );

                g2d.dispose();
            }
        };

        mainPanel.setOpaque(false);

        mainPanel.setLayout(
                new BorderLayout(0, 20)
        );

        mainPanel.setPreferredSize(
                new Dimension(470, 310)
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        25,
                        30,
                        25,
                        30
                )
        );

        // =====================================================
        // Header
        // =====================================================

        JPanel headerPanel =
                new JPanel();

        headerPanel.setOpaque(false);

        headerPanel.setLayout(
                new BoxLayout(
                        headerPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel iconLabel =
                new JLabel("▣");

        iconLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        iconLabel.setForeground(GOLD);

        JLabel titleLabel =
                new JLabel("Delete Parcel");

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
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

        JLabel subtitleLabel =
                new JLabel(
                        "Remove a parcel from the system"
                );

        subtitleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
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

        headerPanel.add(iconLabel);

        headerPanel.add(
                Box.createVerticalStrut(4)
        );

        headerPanel.add(titleLabel);

        headerPanel.add(
                Box.createVerticalStrut(5)
        );

        headerPanel.add(subtitleLabel);

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // Form
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
                        8,
                        5,
                        8,
                        5
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // Parcel ID label
        JLabel parcelIdLabel =
                new JLabel("PARCEL ID");

        parcelIdLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        parcelIdLabel.setForeground(
                SKY_BLUE
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.25;

        formPanel.add(
                parcelIdLabel,
                gbc
        );

        // Parcel ID input
        parcelIdField = new JTextField() {

            private boolean focused = false;

            {
                addFocusListener(
                        new FocusAdapter() {

                            @Override
                            public void focusGained(
                                    FocusEvent e
                            ) {
                                focused = true;
                                repaint();
                            }

                            @Override
                            public void focusLost(
                                    FocusEvent e
                            ) {
                                focused = false;
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
                g2d.setColor(INPUT_BG);

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

                // Input border
                g2d.setColor(
                        focused
                                ? INPUT_FOCUS
                                : INPUT_BORDER
                );

                g2d.setStroke(
                        new BasicStroke(
                                focused
                                        ? 1.8f
                                        : 1f
                        )
                );

                g2d.draw(
                        new RoundRectangle2D.Float(
                                0,
                                0,
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

        parcelIdField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        parcelIdField.setForeground(
                new Color(25, 45, 60)
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
                        250,
                        40
                )
        );

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.75;

        formPanel.add(
                parcelIdField,
                gbc
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // Buttons
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        JButton deleteButton =
                createDangerButton(
                        "Delete Parcel"
                );

        JButton clearButton =
                createOutlineButton(
                        "Clear"
                );

        deleteButton.setPreferredSize(
                new Dimension(
                        140,
                        40
                )
        );

        clearButton.setPreferredSize(
                new Dimension(
                        90,
                        40
                )
        );

        deleteButton.addActionListener(
                e -> deleteParcel()
        );

        clearButton.addActionListener(
                e -> parcelIdField.setText("")
        );

        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        rootPanel.add(mainPanel);

        add(
                rootPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // Delete Button
    // =========================================================

    private JButton createDangerButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

                    private boolean hovered = false;

                    {
                        addMouseListener(
                                new MouseAdapter() {

                                    @Override
                                    public void mouseEntered(
                                            MouseEvent e
                                    ) {
                                        hovered = true;
                                        repaint();
                                    }

                                    @Override
                                    public void mouseExited(
                                            MouseEvent e
                                    ) {
                                        hovered = false;
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
                                hovered
                                        ? DELETE_HOVER_TOP
                                        : DELETE_TOP;

                        Color bottom =
                                hovered
                                        ? DELETE_HOVER_BOTTOM
                                        : DELETE_BOTTOM;

                        GradientPaint gradient =
                                new GradientPaint(
                                        0,
                                        0,
                                        top,
                                        0,
                                        getHeight(),
                                        bottom
                                );

                        g2d.setPaint(gradient);

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
                Color.WHITE
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
    // Outline Button
    // =========================================================

    private JButton createOutlineButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

                    private boolean hovered = false;

                    {
                        addMouseListener(
                                new MouseAdapter() {

                                    @Override
                                    public void mouseEntered(
                                            MouseEvent e
                                    ) {
                                        hovered = true;
                                        repaint();
                                    }

                                    @Override
                                    public void mouseExited(
                                            MouseEvent e
                                    ) {
                                        hovered = false;
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

                        if (hovered) {

                            g2d.setColor(
                                    new Color(
                                            SKY_BLUE.getRed(),
                                            SKY_BLUE.getGreen(),
                                            SKY_BLUE.getBlue(),
                                            35
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
                                        SKY_BLUE.getRed(),
                                        SKY_BLUE.getGreen(),
                                        SKY_BLUE.getBlue(),
                                        170
                                )
                        );

                        g2d.setStroke(
                                new BasicStroke(1.2f)
                        );

                        g2d.draw(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth() - 1,
                                        getHeight() - 1,
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
                SKY_BLUE
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
    // Delete Parcel
    // =========================================================

    private void deleteParcel() {

        String parcelID =
                parcelIdField.getText().trim();

        if (parcelID.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a Parcel ID!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete Parcel ID: "
                                + parcelID
                                + "?",
                        "Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            Admin admin =
                    new Admin(user);

            // Backend logic remains unchanged
            admin.deleteParcel(parcelID);

            JOptionPane.showMessageDialog(
                    this,
                    "Parcel deleted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            parcelIdField.setText("");

        } catch (NotFoundException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Delete Failed",
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
