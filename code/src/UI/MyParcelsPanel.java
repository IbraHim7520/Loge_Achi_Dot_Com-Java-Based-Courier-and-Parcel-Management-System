 package UI;

import custom_exception.NotFoundException;
import custom_exception.UnauthorizedAccessException;
import model.Parcel;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class MyParcelsPanel extends JPanel {

    private final User user;

    private JTable parcelTable;
    private DefaultTableModel tableModel;

    // =========================================================
    // THEME COLORS
    // =========================================================

    private final Color BLUE = new Color(0, 97, 153);
    private final Color SKY_BLUE = new Color(138, 207, 248);
    private final Color SOFT_YELLOW = new Color(244, 235, 108);
    private final Color GOLD = new Color(255, 212, 68);

    private final Color BG_TOP = new Color(3, 39, 63);
    private final Color BG_BOTTOM = new Color(0, 72, 110);

    private final Color CARD_BG = new Color(0, 55, 88, 238);
    private final Color CARD_BORDER = new Color(138, 207, 248, 85);

    private final Color BTN_TEXT = new Color(20, 55, 70);

    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_MUTED = new Color(190, 220, 235);

    public MyParcelsPanel(User user) {

        this.user = user;

        setLayout(new BorderLayout());
        setOpaque(false);

        buildUI();

        // Initial Data Load
        loadUserParcels();
    }

    // =========================================================
    // BUILD UI
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

                // Main background
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

                // Top-right glow
                g2d.setColor(
                        new Color(
                                SKY_BLUE.getRed(),
                                SKY_BLUE.getGreen(),
                                SKY_BLUE.getBlue(),
                                22
                        )
                );

                g2d.fillOval(
                        width - 200,
                        -110,
                        310,
                        310
                );

                // Bottom-left glow
                g2d.setColor(
                        new Color(
                                GOLD.getRed(),
                                GOLD.getGreen(),
                                GOLD.getBlue(),
                                14
                        )
                );

                g2d.fillOval(
                        -120,
                        height - 170,
                        280,
                        280
                );

                // Decorative dots
                g2d.setColor(
                        new Color(
                                255,
                                255,
                                255,
                                35
                        )
                );

                for (int i = 0; i < 9; i++) {

                    int x = 25 + (i * 90);
                    int y = 25 + ((i % 3) * 35);

                    g2d.fillOval(
                            x,
                            y,
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

                // Card
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

                // Top accent
                GradientPaint accent =
                        new GradientPaint(
                                0,
                                0,
                                GOLD,
                                getWidth(),
                                0,
                                SOFT_YELLOW
                        );

                g2d.setPaint(accent);

                g2d.fillRoundRect(
                        0,
                        0,
                        getWidth(),
                        5,
                        24,
                        24
                );

                // Border
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
                                24,
                                24
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
                        15
                )
        );

        mainPanel.setBorder(
                new EmptyBorder(
                        24,
                        26,
                        22,
                        26
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setOpaque(false);

        JLabel iconLabel =
                new JLabel("▣");

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
                        50,
                        50
                )
        );

        JPanel iconPanel =
                new JPanel(
                        new GridBagLayout()
                );

        iconPanel.setOpaque(false);

        iconPanel.add(iconLabel);

        headerPanel.add(
                iconPanel,
                BorderLayout.WEST
        );

        // Header text
        JPanel headerText =
                new JPanel();

        headerText.setOpaque(false);

        headerText.setLayout(
                new BoxLayout(
                        headerText,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel brandLabel =
                new JLabel(
                        "PARCEL MANAGEMENT"
                );

        brandLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        brandLabel.setForeground(
                SKY_BLUE
        );

        JLabel title =
                new JLabel(
                        "My Parcels"
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

        JLabel subtitleLabel =
                new JLabel(
                        "View and track all parcels sent from your account"
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

        headerText.add(brandLabel);

        headerText.add(
                Box.createVerticalStrut(2)
        );

        headerText.add(title);

        headerText.add(
                Box.createVerticalStrut(3)
        );

        headerText.add(subtitleLabel);

        headerPanel.add(
                headerText,
                BorderLayout.CENTER
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE SETUP
        // =====================================================

        String[] columns = {
                "Parcel ID",
                "Parcel Name",
                "Receiver Address",
                "Phone",
                "Weight (kg)",
                "Charge (BDT)",
                "Status",
                "Rider ID"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {
                        return false;
                    }
                };

        parcelTable =
                new JTable(tableModel);

        parcelTable.setRowHeight(35);

        parcelTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        parcelTable.setForeground(
                new Color(
                        25,
                        45,
                        58
                )
        );

        parcelTable.setBackground(
                Color.WHITE
        );

        parcelTable.setSelectionBackground(
                new Color(
                        138,
                        207,
                        248
                )
        );

        parcelTable.setSelectionForeground(
                new Color(
                        20,
                        55,
                        70
                )
        );

        parcelTable.setGridColor(
                new Color(
                        215,
                        230,
                        238
                )
        );

        parcelTable.setShowGrid(true);

        parcelTable.setIntercellSpacing(
                new Dimension(
                        1,
                        1
                )
        );

        parcelTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // =====================================================
        // HEADER STYLING
        // =====================================================

        JTableHeader header =
                parcelTable.getTableHeader();

        header.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        header.setBackground(
                BLUE
        );

        header.setForeground(
                Color.WHITE
        );

        header.setReorderingAllowed(false);

        header.setPreferredSize(
                new Dimension(
                        header.getPreferredSize().width,
                        38
                )
        );

        DefaultTableCellRenderer headerRenderer =
                new DefaultTableCellRenderer();

        headerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        headerRenderer.setForeground(
                Color.WHITE
        );

        headerRenderer.setBackground(
                BLUE
        );

        headerRenderer.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        header.setDefaultRenderer(
                headerRenderer
        );

        // =====================================================
        // TABLE CELL RENDERERS
        // =====================================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer() {

                    @Override
                    public Component
                    getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column
                    ) {

                        Component component =
                                super.getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column
                                );

                        setHorizontalAlignment(
                                SwingConstants.CENTER
                        );

                        if (!isSelected) {

                            component.setBackground(
                                    row % 2 == 0
                                            ? Color.WHITE
                                            : new Color(
                                            242,
                                            248,
                                            252
                                    )
                            );

                            component.setForeground(
                                    new Color(
                                            30,
                                            55,
                                            70
                                    )
                            );
                        }

                        setBorder(
                                BorderFactory.createEmptyBorder(
                                        0,
                                        8,
                                        0,
                                        8
                                )
                        );

                        return component;
                    }
                };

        for (int i = 0;
             i < parcelTable.getColumnCount();
             i++) {

            if (i == 6) {

                // Status column
                parcelTable
                        .getColumnModel()
                        .getColumn(i)
                        .setCellRenderer(
                                new StatusCellRenderer()
                        );

            } else {

                parcelTable
                        .getColumnModel()
                        .getColumn(i)
                        .setCellRenderer(
                                centerRenderer
                        );
            }
        }

        // =====================================================
        // COLUMN WIDTHS
        // =====================================================

        int[] widths = {
                90,
                120,
                190,
                100,
                90,
                100,
                120,
                110
        };

        for (int i = 0;
             i < widths.length;
             i++) {

            parcelTable
                    .getColumnModel()
                    .getColumn(i)
                    .setPreferredWidth(
                            widths[i]
                    );
        }

        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        parcelTable
                );

        scrollPane.setOpaque(false);

        scrollPane.getViewport()
                .setOpaque(true);

        scrollPane.getViewport()
                .setBackground(
                        Color.WHITE
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                138,
                                207,
                                248,
                                130
                        ),
                        1,
                        true
                )
        );

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM PANEL
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.setOpaque(false);

        JLabel infoLabel =
                new JLabel(
                        "Your parcel history is shown here."
                );

        infoLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        infoLabel.setForeground(
                TEXT_MUTED
        );

        bottomPanel.add(
                infoLabel,
                BorderLayout.WEST
        );

        JButton refreshBtn =
                createGoldenButton(
                        "↻  Refresh List"
                );

        refreshBtn.setPreferredSize(
                new Dimension(
                        145,
                        40
                )
        );

        refreshBtn.addActionListener(
                e -> loadUserParcels()
        );

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                0,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        buttonPanel.add(
                refreshBtn
        );

        bottomPanel.add(
                buttonPanel,
                BorderLayout.EAST
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // ADD CARD
        // =====================================================

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.fill =
                GridBagConstraints.BOTH;

        gbc.insets =
                new Insets(
                        18,
                        22,
                        18,
                        22
                );

        rootPanel.add(
                mainPanel,
                gbc
        );

        add(
                rootPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // LOAD USER PARCELS
    // =========================================================

    public void loadUserParcels() {

        tableModel.setRowCount(0);

        try {

            Parcel parcelHelper =
                    new Parcel(user);

            ArrayList<String> parcelList =
                    parcelHelper.getMyAllParcels();

            if (
                    parcelList != null
                            && !parcelList.isEmpty()
            ) {

                for (
                        String parcelData :
                        parcelList
                ) {

                    if (
                            parcelData == null
                                    || parcelData.trim().isEmpty()
                    ) {
                        continue;
                    }

                    // Backend stores parcel data using "|"
                    String cleanData =
                            parcelData.trim();

                    // Remove record ending "#"
                    if (
                            cleanData.endsWith("#")
                    ) {

                        cleanData =
                                cleanData.substring(
                                        0,
                                        cleanData.length() - 1
                                );
                    }

                    // Split using backend separator
                    String[] data =
                            cleanData.split(
                                    "\\|",
                                    -1
                            );

                    // Expected:
                    // 0 = Parcel Name
                    // 1 = Receiver Address
                    // 2 = Receiver Phone
                    // 3 = Parcel ID
                    // 4 = Weight
                    // 5 = Sender Email
                    // 6 = Sender ID
                    // 7 = Status
                    // 8 = Delivery Charge
                    // 9 = Rider ID

                    if (data.length >= 10) {

                        String parcelName =
                                data[0].trim();

                        String receiverAddress =
                                data[1].trim();

                        String phone =
                                data[2].trim();

                        String parcelId =
                                data[3].trim();

                        String weight =
                                data[4].trim();

                        String charge =
                                data[8].trim();

                        String status =
                                data[7].trim();

                        String riderId =
                                data[9].trim();

                        // Show "Not Assigned" instead of "null"
                        if (
                                riderId.equalsIgnoreCase(
                                        "null"
                                )
                                        || riderId.isEmpty()
                        ) {

                            riderId =
                                    "Not Assigned";
                        }

                        tableModel.addRow(
                                new Object[]{
                                        parcelId,
                                        parcelName,
                                        receiverAddress,
                                        phone,
                                        weight,
                                        charge,
                                        status,
                                        riderId
                                }
                        );

                    } else {

                        System.out.println(
                                "Invalid parcel data: "
                                        + parcelData
                        );
                    }
                }
            }

        } catch (
                UnauthorizedAccessException
                | NotFoundException e
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "An error occurred while loading parcels.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
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
                                        ? SOFT_YELLOW
                                        : GOLD;

                        Color bottom =
                                isHovered
                                        ? GOLD
                                        : new Color(
                                        255,
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

    // =========================================================
    // STATUS CELL RENDERER
    // =========================================================

    private class StatusCellRenderer
            extends DefaultTableCellRenderer {

        public StatusCellRenderer() {

            setHorizontalAlignment(
                    SwingConstants.CENTER
            );
        }

        @Override
        public Component
        getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column
        ) {

            Component component =
                    super.getTableCellRendererComponent(
                            table,
                            value,
                            isSelected,
                            hasFocus,
                            row,
                            column
                    );

            setHorizontalAlignment(
                    SwingConstants.CENTER
            );

            setBorder(
                    BorderFactory.createEmptyBorder(
                            0,
                            8,
                            0,
                            8
                    )
            );

            if (!isSelected) {

                component.setBackground(
                        row % 2 == 0
                                ? Color.WHITE
                                : new Color(
                                242,
                                248,
                                252
                        )
                );
            }

            if (value != null) {

                String status =
                        value.toString()
                                .toUpperCase();

                if (
                        status.contains(
                                "DELIVERED"
                        )
                ) {

                    component.setForeground(
                            new Color(
                                    0,
                                    125,
                                    65
                            )
                    );

                    setFont(
                            getFont().deriveFont(
                                    Font.BOLD
                            )
                    );

                } else if (
                        status.contains(
                                "PENDING"
                        )
                ) {

                    component.setForeground(
                            new Color(
                                    175,
                                    115,
                                    0
                            )
                    );

                    setFont(
                            getFont().deriveFont(
                                    Font.BOLD
                            )
                    );

                } else if (
                        status.contains(
                                "CANCEL"
                        )
                ) {

                    component.setForeground(
                            new Color(
                                    190,
                                    55,
                                    55
                            )
                    );

                    setFont(
                            getFont().deriveFont(
                                    Font.BOLD
                            )
                    );

                } else {

                    component.setForeground(
                            new Color(
                                    30,
                                    55,
                                    70
                            )
                    );

                    setFont(
                            getFont().deriveFont(
                                    Font.PLAIN
                            )
                    );
                }
            }

            return component;
        }
    }
}
