package UI;

import file.RiderFile;
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

public class AssignedParcelsFrame extends JPanel {

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
    private final Color CARD_BORDER = new Color(138, 207, 248, 90);

    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_MUTED = new Color(190, 220, 235);

    // Input / table colors
    private final Color TABLE_HEADER_BG = new Color(0, 97, 153);
    private final Color TABLE_ROW_EVEN = new Color(7, 63, 92);
    private final Color TABLE_ROW_ODD = new Color(5, 55, 82);
    private final Color TABLE_SELECTION_BG = new Color(255, 212, 68, 100);

    // Button colors
    private final Color BTN_TOP = new Color(255, 224, 90);
    private final Color BTN_BOTTOM = new Color(255, 212, 68);
    private final Color BTN_HOVER_TOP = new Color(255, 235, 135);
    private final Color BTN_HOVER_BOTTOM = new Color(255, 202, 45);
    private final Color BTN_TEXT = new Color(35, 55, 65);

    public AssignedParcelsFrame(User user) {
        this.user = user;

        setLayout(new BorderLayout());
        setOpaque(false);

        buildUI();
        loadAssignedParcels();
    }

    // =========================================================
    // BUILD UI
    // =========================================================

    private void buildUI() {

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Main card background
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

                g2d.setStroke(new BasicStroke(1f));

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
        mainPanel.setLayout(new BorderLayout(0, 18));
        mainPanel.setBorder(
                new EmptyBorder(24, 28, 24, 28)
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);

        // Left accent
        JPanel titleAccent = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();

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
        titleAccent.setPreferredSize(new Dimension(12, 52));

        // Header text
        JPanel headerTextGroup = new JPanel();
        headerTextGroup.setLayout(
                new BoxLayout(
                        headerTextGroup,
                        BoxLayout.Y_AXIS
                )
        );
        headerTextGroup.setOpaque(false);

        JLabel titleLabel =
                new JLabel("My Assigned Parcels");

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        22
                )
        );

        titleLabel.setForeground(TEXT_WHITE);

        JLabel subtitleLabel =
                new JLabel(
                        "Shipments assigned to you for delivery dispatch"
                );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        subtitleLabel.setForeground(TEXT_MUTED);

        headerTextGroup.add(titleLabel);
        headerTextGroup.add(
                Box.createVerticalStrut(5)
        );
        headerTextGroup.add(subtitleLabel);

        JPanel headerLeft = new JPanel(new BorderLayout(8, 0));
        headerLeft.setOpaque(false);

        headerLeft.add(
                titleAccent,
                BorderLayout.WEST
        );

        headerLeft.add(
                headerTextGroup,
                BorderLayout.CENTER
        );

        // Small information badge
        JLabel infoBadge =
                new JLabel("ASSIGNED");

        infoBadge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        infoBadge.setForeground(
                new Color(255, 245, 180)
        );

        infoBadge.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        infoBadge.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(255, 212, 68, 130),
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
                infoBadge,
                BorderLayout.EAST
        );

        mainPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE
        // =====================================================

        String[] columns = {
                "Parcel ID",
                "Parcel Name",
                "Receiver Address",
                "Receiver Phone",
                "Weight",
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
                new JTable(tableModel) {

                    @Override
                    public Component prepareRenderer(
                            javax.swing.table.TableCellRenderer renderer,
                            int row,
                            int column
                    ) {

                        Component c =
                                super.prepareRenderer(
                                        renderer,
                                        row,
                                        column
                                );

                        if (!isRowSelected(row)) {

                            c.setBackground(
                                    row % 2 == 0
                                            ? TABLE_ROW_EVEN
                                            : TABLE_ROW_ODD
                            );

                            c.setForeground(
                                    TEXT_WHITE
                            );

                        } else {

                            c.setBackground(
                                    TABLE_SELECTION_BG
                            );

                            c.setForeground(
                                    TEXT_WHITE
                            );
                        }

                        return c;
                    }
                };

        parcelTable.setRowHeight(38);

        parcelTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        parcelTable.setShowGrid(false);

        parcelTable.setIntercellSpacing(
                new Dimension(0, 0)
        );

        parcelTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        parcelTable.setFillsViewportHeight(true);

        parcelTable.setBackground(
                TABLE_ROW_ODD
        );

        parcelTable.setForeground(
                TEXT_WHITE
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

        header.setForeground(
                TEXT_WHITE
        );

        header.setBackground(
                TABLE_HEADER_BG
        );

        header.setPreferredSize(
                new Dimension(
                        header.getPreferredSize().width,
                        40
                )
        );

        header.setReorderingAllowed(false);

        header.setBorder(
                BorderFactory.createMatteBorder(
                        0,
                        0,
                        2,
                        0,
                        GOLD
                )
        );

        // =====================================================
        // CENTER ALIGN TABLE TEXT
        // =====================================================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                JLabel.CENTER
        );

        centerRenderer.setForeground(
                TEXT_WHITE
        );

        for (
                int i = 0;
                i < parcelTable.getColumnCount();
                i++
        ) {

            parcelTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            centerRenderer
                    );
        }

        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(parcelTable);

        scrollPane.setOpaque(false);

        scrollPane.getViewport()
                .setOpaque(false);

        scrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        138,
                                        207,
                                        248,
                                        80
                                ),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                1,
                                1,
                                1,
                                1
                        )
                )
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(14);

        scrollPane.getHorizontalScrollBar()
                .setUnitIncrement(14);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOTTOM BUTTON AREA
        // =====================================================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                12,
                                0
                        )
                );

        bottomPanel.setOpaque(false);

        JButton refreshButton =
                createGoldenButton(
                        "Refresh List"
                );

        refreshButton.setPreferredSize(
                new Dimension(
                        145,
                        38
                )
        );

        refreshButton.addActionListener(
                e -> loadAssignedParcels()
        );

        bottomPanel.add(
                refreshButton
        );

        mainPanel.add(
                bottomPanel,
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

                        g2d.setPaint(gradient);

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
    // LOAD ASSIGNED PARCELS
    // =========================================================

    public void loadAssignedParcels() {

        tableModel.setRowCount(0);

        try {

            ArrayList<String> parcels =
                    RiderFile.getMyAssignedParcels(
                            user.getUser_id()
                    );

            for (String parcel : parcels) {

                String[] data =
                        parcel.split(
                                "\\|",
                                -1
                        );

                if (data.length >= 10) {

                    Object[] row = {

                            data[3], // Parcel ID
                            data[0], // Parcel Name
                            data[1], // Receiver Address
                            data[2], // Receiver Phone
                            data[4], // Weight
                            data[5], // Sender Email
                            data[6], // Sender ID
                            data[7], // Status
                            data[8], // Charge
                            data[9]  // Rider ID
                    };

                    tableModel.addRow(row);
                }
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "No Assigned Parcels",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
}
