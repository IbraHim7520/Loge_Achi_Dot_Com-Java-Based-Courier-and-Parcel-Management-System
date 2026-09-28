package UI;

import model.Rider;
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

public class PendingParcelsFrame extends JPanel {

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

    public PendingParcelsFrame(User user) {

        this.user = user;

        setLayout(new BorderLayout());
        setOpaque(false);

        buildUI();
        loadPendingParcels();
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

                // Top-right decorative glow
                g2d.setColor(
                        new Color(
                                SKY_BLUE.getRed(),
                                SKY_BLUE.getGreen(),
                                SKY_BLUE.getBlue(),
                                22
                        )
                );

                g2d.fillOval(
                        width - 190,
                        -100,
                        300,
                        300
                );

                // Bottom-left decorative glow
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
                        270,
                        270
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
                new BorderLayout()
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

        // Icon
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
                        "RIDER MANAGEMENT"
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

        JLabel titleLabel =
                new JLabel(
                        "Pending Parcels"
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

        JLabel subtitleLabel =
                new JLabel(
                        "Review available parcels and accept a delivery"
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

        headerText.add(titleLabel);

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
        // TABLE
        // =====================================================

        String[] columns = {
                "Parcel ID",
                "Parcel Name",
                "Receiver Address",
                "Phone",
                "Weight (kg)",
                "Sender Email",
                "Sender ID",
                "Status",
                "Charge",
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

        parcelTable.setRowHeight(34);

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

        parcelTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        parcelTable.setShowGrid(true);

        parcelTable.setGridColor(
                new Color(
                        215,
                        230,
                        238
                )
        );

        parcelTable.setIntercellSpacing(
                new Dimension(
                        1,
                        1
                )
        );

        // =====================================================
        // TABLE HEADER
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
        // TABLE CELL RENDERER
        // =====================================================

        DefaultTableCellRenderer customRenderer =
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

            parcelTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            customRenderer
                    );
        }

        // Better column widths
        int[] widths = {
                90,
                120,
                190,
                100,
                90,
                170,
                100,
                120,
                90,
                100
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
        // BOTTOM AREA
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.setOpaque(false);

        JLabel infoLabel =
                new JLabel(
                        "Select a parcel from the table to accept it."
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

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        JButton acceptButton =
                createGoldenButton(
                        "✓  Accept Selected Parcel"
                );

        JButton refreshButton =
                createOutlineButton(
                        "↻  Refresh"
                );

        acceptButton.setPreferredSize(
                new Dimension(
                        200,
                        40
                )
        );

        refreshButton.setPreferredSize(
                new Dimension(
                        105,
                        40
                )
        );

        acceptButton.addActionListener(
                e -> acceptSelectedParcel()
        );

        refreshButton.addActionListener(
                e -> loadPendingParcels()
        );

        buttonPanel.add(
                acceptButton
        );

        buttonPanel.add(
                refreshButton
        );

        bottomPanel.add(
                buttonPanel,
                BorderLayout.EAST
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        rootPanel.add(
                mainPanel,
                BorderLayout.CENTER
        );

        add(
                rootPanel,
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
                                        180
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

    // =========================================================
    // LOAD PENDING PARCELS
    // =========================================================

    private void loadPendingParcels() {

        tableModel.setRowCount(0);

        try {

            ArrayList<String> parcels =
                    file.RiderFile.getPendingParcels();

            if (parcels == null ||
                    parcels.isEmpty()) {

                return;
            }

            for (String parcel : parcels) {

                addParcelToTable(parcel);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to load parcels: "
                            + e.getMessage(),
                    "Pending Parcels",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // ADD PARCEL TO TABLE
    // =========================================================

    private void addParcelToTable(
            String parcel
    ) {

        if (parcel == null ||
                parcel.trim().isEmpty()) {

            return;
        }

        String[] data =
                parcel.split(
                        "\\|",
                        -1
                );

        if (data.length < 10) {
            return;
        }

        tableModel.addRow(
                new Object[]{
                        data[3],
                        data[0],
                        data[1],
                        data[2],
                        data[4],
                        data[5],
                        data[6],
                        data[7],
                        data[8],
                        data[9]
                }
        );
    }

    // =========================================================
    // ACCEPT PARCEL
    // =========================================================

    private void acceptSelectedParcel() {

        int selectedRow =
                parcelTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a parcel first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String parcelId =
                tableModel
                        .getValueAt(
                                selectedRow,
                                0
                        )
                        .toString();

        int confirm =
                JOptionPane.showConfirmDialog(
                        this,
                        "Accept parcel "
                                + parcelId
                                + "?",
                        "Confirm Acceptance",
                        JOptionPane.YES_NO_OPTION
                );

        if (confirm !=
                JOptionPane.YES_OPTION) {

            return;
        }

        try {

            Rider rider =
                    new Rider(user);

            rider.acceptParcelsByRider(
                    parcelId
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Parcel accepted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadPendingParcels();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Failed to accept parcel: "
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
