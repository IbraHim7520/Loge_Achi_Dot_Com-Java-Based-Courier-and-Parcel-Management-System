package UI;

import custom_exception.InvalidAmountException;
import custom_exception.NotFoundException;
import custom_exception.UnauthorizedAccessException;
import file.AdminFile;
import model.Parcel;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class AdminParcelsFrame extends JPanel {

    private User user;
    private JTable parcelTable;
    private DefaultTableModel tableModel;

    // =========================
    // THEME COLORS
    // =========================

    private final Color BLUE = new Color(0, 97, 153);
    private final Color SKY_BLUE = new Color(138, 207, 248);
    private final Color SOFT_YELLOW = new Color(244, 235, 108);
    private final Color GOLD = new Color(255, 212, 68);

    private final Color BG_TOP = new Color(3, 39, 63);
    private final Color BG_BOTTOM = new Color(0, 72, 110);

    private final Color CARD_BG = new Color(0, 55, 88, 238);
    private final Color CARD_BORDER = new Color(138, 207, 248, 85);

    private final Color TABLE_HEADER_BG = new Color(0, 97, 153);
    private final Color TABLE_ROW_EVEN = new Color(4, 66, 99);
    private final Color TABLE_ROW_ODD = new Color(3, 57, 87);
    private final Color TABLE_SELECTION_BG = new Color(244, 235, 108, 150);

    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_MUTED = new Color(190, 220, 235);

    private final Color INPUT_BORDER = new Color(138, 207, 248, 90);

    public AdminParcelsFrame(User user) {
        this.user = user;

        setLayout(new BorderLayout());
        setOpaque(false);

        buildUI();
        loadParcels();
    }

    private void buildUI() {

        // =========================
        // MAIN GLASS CARD
        // =========================

        JPanel mainPanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g.create();

                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                GradientPaint gradient = new GradientPaint(
                        0,
                        0,
                        CARD_BG,
                        0,
                        getHeight(),
                        new Color(0, 48, 77, 238)
                );

                g2d.setPaint(gradient);

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

                g2d.setColor(CARD_BORDER);

                g2d.draw(
                        new RoundRectangle2D.Float(
                                0,
                                0,
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
        mainPanel.setLayout(new BorderLayout(0, 16));
        mainPanel.setBorder(
                new EmptyBorder(
                        22,
                        24,
                        20,
                        24
                )
        );

        // =========================
        // HEADER
        // =========================

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false);

        JPanel headerTextGroup = new JPanel();
        headerTextGroup.setLayout(
                new BoxLayout(
                        headerTextGroup,
                        BoxLayout.Y_AXIS
                )
        );
        headerTextGroup.setOpaque(false);

        JLabel smallBadge = new JLabel("  PARCEL DIRECTORY  ");
        smallBadge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );
        smallBadge.setForeground(GOLD);
        smallBadge.setOpaque(true);
        smallBadge.setBackground(
                new Color(
                        255,
                        212,
                        68,
                        25
                )
        );
        smallBadge.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        255,
                                        212,
                                        68,
                                        90
                                ),
                                1
                        ),
                        new EmptyBorder(
                                4,
                                7,
                                4,
                                7
                        )
                )
        );

        JLabel titleLabel = new JLabel("All Parcels Overview");

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        23
                )
        );

        titleLabel.setForeground(TEXT_WHITE);

        JLabel subtitleLabel = new JLabel(
                "Manage and inspect all system-wide delivery records"
        );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        subtitleLabel.setForeground(TEXT_MUTED);

        headerTextGroup.add(smallBadge);
        headerTextGroup.add(
                Box.createVerticalStrut(8)
        );
        headerTextGroup.add(titleLabel);
        headerTextGroup.add(
                Box.createVerticalStrut(4)
        );
        headerTextGroup.add(subtitleLabel);

        titlePanel.add(
                headerTextGroup,
                BorderLayout.WEST
        );

        mainPanel.add(
                titlePanel,
                BorderLayout.NORTH
        );

        // =========================
        // TABLE
        // =========================

        String[] columns = {
                "Parcel ID",
                "Parcel Name",
                "Receiver Address",
                "Receiver Phone",
                "Weight",
                "Sender Email",
                "Sender ID",
                "Status",
                "Delivery Charge",
                "Rider ID"
        };

        tableModel = new DefaultTableModel(
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

        parcelTable = new JTable(tableModel) {

            @Override
            public Component prepareRenderer(
                    javax.swing.table.TableCellRenderer renderer,
                    int row,
                    int column
            ) {

                Component component =
                        super.prepareRenderer(
                                renderer,
                                row,
                                column
                        );

                if (!isRowSelected(row)) {

                    component.setBackground(
                            row % 2 == 0
                                    ? TABLE_ROW_EVEN
                                    : TABLE_ROW_ODD
                    );

                    component.setForeground(
                            TEXT_WHITE
                    );

                } else {

                    component.setBackground(
                            TABLE_SELECTION_BG
                    );

                    component.setForeground(
                            new Color(
                                    0,
                                    55,
                                    88
                            )
                    );
                }

                return component;
            }
        };

        parcelTable.setRowHeight(34);

        parcelTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        parcelTable.setShowGrid(false);

        parcelTable.setIntercellSpacing(
                new Dimension(
                        0,
                        0
                )
        );

        parcelTable.setFillsViewportHeight(true);

        parcelTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        parcelTable.setBackground(
                TABLE_ROW_ODD
        );

        parcelTable.setForeground(
                TEXT_WHITE
        );

        // =========================
        // TABLE HEADER
        // =========================

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
                Color.WHITE
        );

        header.setBackground(
                TABLE_HEADER_BG
        );

        header.setPreferredSize(
                new Dimension(
                        header.getPreferredSize().width,
                        38
                )
        );

        header.setReorderingAllowed(false);

        header.setOpaque(true);

        // =========================
        // CENTER ALIGNMENT
        // =========================

        DefaultTableCellRenderer centerRenderer =
                new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(
                JLabel.CENTER
        );

        centerRenderer.setBackground(
                TABLE_ROW_ODD
        );

        centerRenderer.setForeground(
                TEXT_WHITE
        );

        for (int i = 0;
             i < parcelTable.getColumnCount();
             i++) {

            parcelTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            centerRenderer
                    );
        }

        // =========================
        // SCROLL PANE
        // =========================

        JScrollPane scrollPane =
                new JScrollPane(parcelTable);

        scrollPane.setOpaque(false);

        scrollPane.getViewport()
                .setOpaque(false);

        scrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                INPUT_BORDER,
                                1
                        ),
                        new EmptyBorder(
                                1,
                                1,
                                1,
                                1
                        )
                )
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        scrollPane.getHorizontalScrollBar()
                .setUnitIncrement(16);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =========================
        // BOTTOM ACTION AREA
        // =========================

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.setOpaque(false);

        JLabel infoLabel =
                new JLabel(
                        "System-wide parcel records"
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
                                0,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        JButton refreshButton =
                createGoldenButton(
                        "↻  Refresh"
                );

        refreshButton.setPreferredSize(
                new Dimension(
                        125,
                        38
                )
        );

        refreshButton.addActionListener(
                e -> loadParcels()
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

        add(
                mainPanel,
                BorderLayout.CENTER
        );
    }

    // =========================
    // GOLD GRADIENT BUTTON
    // =========================

    private JButton createGoldenButton(
            String text
    ) {

        JButton button = new JButton(text) {

            private boolean isHovered = false;

            {
                addMouseListener(
                        new java.awt.event.MouseAdapter() {

                            @Override
                            public void mouseEntered(
                                    java.awt.event.MouseEvent e
                            ) {

                                isHovered = true;
                                repaint();
                            }

                            @Override
                            public void mouseExited(
                                    java.awt.event.MouseEvent e
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
                                245,
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
                new Color(
                        0,
                        55,
                        88
                )
        );

        button.setContentAreaFilled(false);

        button.setOpaque(false);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setBorder(
                new EmptyBorder(
                        8,
                        16,
                        8,
                        16
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================
    // LOAD PARCELS
    // =========================

    public void loadParcels() {

        tableModel.setRowCount(0);

        try {

            ArrayList<Parcel> parcels =
                    AdminFile.getAllParcels();

            for (Parcel parcel : parcels) {

                Object[] row = {

                        parcel.getParcelID(),

                        parcel.getParcelName(),

                        parcel.getReciverAddress(),

                        parcel.getReciverPhone(),

                        parcel.getWeight(),

                        parcel.getSenderEmail(),

                        parcel.getSenderId(),

                        parcel.getParcelStatus(),

                        parcel.getDeliveryCharge(),

                        parcel.getRiderId()
                };

                tableModel.addRow(row);
            }

        } catch (NotFoundException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "No Parcels",
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
