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
    private JLabel countLabel;

    private final Color BG = new Color(241, 248, 253);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT_DARK = new Color(31, 38, 43);
    private final Color TEXT_MUTED = new Color(103, 117, 128);
    private final Color ORANGE = new Color(248, 116, 35);
    private final Color ORANGE_HOVER = new Color(235, 94, 20);
    private final Color BLUE = new Color(0, 97, 153);
    private final Color LIGHT_BLUE = new Color(225, 240, 249);
    private final Color BORDER = new Color(216, 227, 234);
    private final Color SUCCESS = new Color(48, 148, 94);
    private final Color SUCCESS_BG = new Color(233, 248, 240);

    public PendingParcelsFrame(User user) {
        this.user = user;

        setLayout(new BorderLayout());
        setBackground(BG);

        buildUI();
        loadPendingParcels();
    }

    private void buildUI() {

        JPanel page = new JPanel(new BorderLayout(0, 18));
        page.setOpaque(false);
        page.setBorder(new EmptyBorder(24, 28, 24, 28));

        JPanel header = new JPanel(new BorderLayout(14, 0));
        header.setOpaque(false);

        JPanel iconPanel = new RoundedPanel(BLUE, 16);
        iconPanel.setPreferredSize(new Dimension(52, 52));
        iconPanel.setLayout(new GridBagLayout());

        JLabel iconLabel = new JLabel("▣");
        iconLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        iconLabel.setForeground(Color.WHITE);

        iconPanel.add(iconLabel);

        header.add(iconPanel, BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel sectionLabel = new JLabel("RIDER MANAGEMENT");
        sectionLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        sectionLabel.setForeground(ORANGE);

        JLabel titleLabel = new JLabel("Pending Parcels");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(TEXT_DARK);

        JLabel subtitleLabel = new JLabel(
                "Review available parcels and accept a delivery"
        );
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitleLabel.setForeground(TEXT_MUTED);

        titlePanel.add(sectionLabel);
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(subtitleLabel);

        header.add(titlePanel, BorderLayout.CENTER);

        countLabel = new JLabel("0 PARCELS");
        countLabel.setOpaque(true);
        countLabel.setBackground(LIGHT_BLUE);
        countLabel.setForeground(BLUE);
        countLabel.setFont(new Font("SansSerif", Font.BOLD, 10));
        countLabel.setBorder(new EmptyBorder(8, 12, 8, 12));

        header.add(countLabel, BorderLayout.EAST);

        page.add(header, BorderLayout.NORTH);

        JPanel mainCard = new RoundedPanel(WHITE, 22);
        mainCard.setLayout(new BorderLayout(0, 16));
        mainCard.setBorder(new EmptyBorder(20, 22, 20, 22));

        JPanel tableHeaderPanel = new JPanel(new BorderLayout());
        tableHeaderPanel.setOpaque(false);

        JPanel tableTitlePanel = new JPanel();
        tableTitlePanel.setOpaque(false);
        tableTitlePanel.setLayout(new BoxLayout(
                tableTitlePanel,
                BoxLayout.Y_AXIS
        ));

        JLabel tableTitle = new JLabel("Available Parcels");
        tableTitle.setFont(new Font("SansSerif", Font.BOLD, 17));
        tableTitle.setForeground(TEXT_DARK);

        JLabel tableSubtitle = new JLabel(
                "Select a parcel to accept it for delivery"
        );
        tableSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tableSubtitle.setForeground(TEXT_MUTED);

        tableTitlePanel.add(tableTitle);
        tableTitlePanel.add(Box.createVerticalStrut(3));
        tableTitlePanel.add(tableSubtitle);

        tableHeaderPanel.add(tableTitlePanel, BorderLayout.WEST);

        JLabel pendingBadge = new JLabel("PENDING");
        pendingBadge.setOpaque(true);
        pendingBadge.setBackground(new Color(255, 245, 220));
        pendingBadge.setForeground(new Color(170, 115, 15));
        pendingBadge.setFont(new Font("SansSerif", Font.BOLD, 10));
        pendingBadge.setBorder(new EmptyBorder(7, 10, 7, 10));

        tableHeaderPanel.add(pendingBadge, BorderLayout.EAST);

        mainCard.add(tableHeaderPanel, BorderLayout.NORTH);

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

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        parcelTable = new JTable(tableModel);

        parcelTable.setRowHeight(38);
        parcelTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
        parcelTable.setForeground(TEXT_DARK);
        parcelTable.setBackground(WHITE);
        parcelTable.setSelectionBackground(LIGHT_BLUE);
        parcelTable.setSelectionForeground(TEXT_DARK);
        parcelTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );
        parcelTable.setShowGrid(true);
        parcelTable.setGridColor(BORDER);
        parcelTable.setIntercellSpacing(new Dimension(1, 1));
        parcelTable.setFillsViewportHeight(true);
        parcelTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS
        );

        JTableHeader theader = parcelTable.getTableHeader();

        theader.setFont(new Font("SansSerif", Font.BOLD, 11));
        theader.setBackground(BLUE);
        theader.setForeground(Color.WHITE);
        theader.setReorderingAllowed(false);
        theader.setPreferredSize(new Dimension(
                theader.getPreferredSize().width,
                40
        ));

        DefaultTableCellRenderer headerRenderer =
                new DefaultTableCellRenderer();

        headerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setBackground(BLUE);
        headerRenderer.setFont(
                new Font("SansSerif", Font.BOLD, 11)
        );

        theader.setDefaultRenderer(headerRenderer);

        DefaultTableCellRenderer cellRenderer =
                new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
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

                        if (isSelected) {
                            component.setBackground(LIGHT_BLUE);
                            component.setForeground(TEXT_DARK);
                        } else {
                            component.setBackground(
                                    row % 2 == 0
                                            ? WHITE
                                            : new Color(247, 250, 252)
                            );

                            component.setForeground(TEXT_DARK);
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

        for (int i = 0; i < parcelTable.getColumnCount(); i++) {
            parcelTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(cellRenderer);
        }

        int[] widths = {
                90,
                120,
                210,
                105,
                90,
                180,
                100,
                100,
                90,
                100
        };

        for (int i = 0; i < widths.length; i++) {
            parcelTable
                    .getColumnModel()
                    .getColumn(i)
                    .setPreferredWidth(widths[i]);
        }

        JScrollPane scrollPane = new JScrollPane(parcelTable);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER,
                        1,
                        true
                )
        );

        scrollPane.setBackground(WHITE);
        scrollPane.getViewport().setBackground(WHITE);

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        mainCard.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        JLabel infoLabel = new JLabel(
                "Select a parcel from the table to accept it."
        );
        infoLabel.setFont(
                new Font("SansSerif", Font.PLAIN, 11)
        );
        infoLabel.setForeground(TEXT_MUTED);

        bottomPanel.add(infoLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        10,
                        0
                )
        );
        buttonPanel.setOpaque(false);

        JButton acceptButton =
                createPrimaryButton(
                        "✓  Accept Selected Parcel"
                );

        JButton refreshButton =
                createOutlineButton(
                        "↻  Refresh"
                );

        acceptButton.setPreferredSize(
                new Dimension(205, 40)
        );

        refreshButton.setPreferredSize(
                new Dimension(105, 40)
        );

        acceptButton.addActionListener(
                e -> acceptSelectedParcel()
        );

        refreshButton.addActionListener(
                e -> loadPendingParcels()
        );

        buttonPanel.add(acceptButton);
        buttonPanel.add(refreshButton);

        bottomPanel.add(
                buttonPanel,
                BorderLayout.EAST
        );

        mainCard.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        page.add(
                mainCard,
                BorderLayout.CENTER
        );

        add(
                page,
                BorderLayout.CENTER
        );
    }

    private JButton createPrimaryButton(String text) {

        JButton button = new JButton(text) {

            private boolean hovered;

            {
                addMouseListener(
                        new MouseAdapter() {

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                hovered = true;
                                repaint();
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                hovered = false;
                                repaint();
                            }
                        }
                );
            }

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 =
                        (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(
                        hovered
                                ? ORANGE_HOVER
                                : ORANGE
                );

                g2.fill(
                        new RoundRectangle2D.Float(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                12,
                                12
                        )
                );

                g2.dispose();

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

        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    private JButton createOutlineButton(String text) {

        JButton button = new JButton(text) {

            private boolean hovered;

            {
                addMouseListener(
                        new MouseAdapter() {

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                hovered = true;
                                repaint();
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {
                                hovered = false;
                                repaint();
                            }
                        }
                );
            }

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 =
                        (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                if (hovered) {

                    g2.setColor(LIGHT_BLUE);

                    g2.fill(
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

                g2.setColor(BLUE);
                g2.setStroke(
                        new BasicStroke(1.2f)
                );

                g2.draw(
                        new RoundRectangle2D.Float(
                                1,
                                1,
                                getWidth() - 2,
                                getHeight() - 2,
                                12,
                                12
                        )
                );

                g2.dispose();

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

        button.setForeground(BLUE);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        return button;
    }

    private void loadPendingParcels() {

        tableModel.setRowCount(0);

        try {

            ArrayList<String> parcels =
                    file.RiderFile.getPendingParcels();

            if (parcels == null ||
                    parcels.isEmpty()) {

                countLabel.setText("0 PARCELS");
                return;
            }

            for (String parcel : parcels) {
                addParcelToTable(parcel);
            }

            int count = tableModel.getRowCount();

            countLabel.setText(
                    count + (count == 1
                            ? " PARCEL"
                            : " PARCELS")
            );

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

    private void addParcelToTable(String parcel) {

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

    private static class RoundedPanel extends JPanel {

        private final Color background;
        private final int radius;

        public RoundedPanel(
                Color background,
                int radius
        ) {
            this.background = background;
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(background);

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    radius,
                    radius
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }
}