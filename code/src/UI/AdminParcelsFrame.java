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
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class AdminParcelsFrame extends JPanel {

    private final User user;
    private JTable parcelTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> tableSorter;
    private JComboBox<String> filterComboBox;
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

    private final Color GREEN = new Color(48, 148, 94);
    private final Color GREEN_BG = new Color(233, 248, 240);

    private final Color GOLD = new Color(205, 155, 20);
    private final Color RED = new Color(205, 62, 73);

    public AdminParcelsFrame(User user) {

        this.user = user;

        setLayout(new BorderLayout());
        setBackground(BG);

        buildUI();
        loadParcels();
    }

    private void buildUI() {

        JPanel page = new JPanel(new BorderLayout(0, 18));
        page.setOpaque(false);
        page.setBorder(new EmptyBorder(24, 28, 24, 28));

        JPanel pageHeader = new JPanel(new BorderLayout(14, 0));
        pageHeader.setOpaque(false);

        JPanel iconPanel = new RoundedPanel(BLUE, 16);
        iconPanel.setPreferredSize(new Dimension(52, 52));
        iconPanel.setLayout(new GridBagLayout());

        JLabel iconLabel = new JLabel("▣");
        iconLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
        iconLabel.setForeground(Color.WHITE);
        iconPanel.add(iconLabel);

        pageHeader.add(iconPanel, BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel sectionLabel = new JLabel("PARCEL MANAGEMENT");
        sectionLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        sectionLabel.setForeground(ORANGE);

        JLabel titleLabel = new JLabel("All Parcels Overview");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 26));
        titleLabel.setForeground(TEXT_DARK);

        JLabel subtitleLabel = new JLabel("Manage and inspect all system-wide delivery records");
        subtitleLabel.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitleLabel.setForeground(TEXT_MUTED);

        titlePanel.add(sectionLabel);
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(titleLabel);
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(subtitleLabel);

        pageHeader.add(titlePanel, BorderLayout.CENTER);

        countLabel = new JLabel("0 PARCELS");
        countLabel.setOpaque(true);
        countLabel.setBackground(LIGHT_BLUE);
        countLabel.setForeground(BLUE);
        countLabel.setFont(new Font("SansSerif", Font.BOLD, 10));
        countLabel.setBorder(new EmptyBorder(8, 12, 8, 12));

        pageHeader.add(countLabel, BorderLayout.EAST);
        page.add(pageHeader, BorderLayout.NORTH);

        JPanel mainCard = new RoundedPanel(WHITE, 22);
        mainCard.setLayout(new BorderLayout(0, 16));
        mainCard.setBorder(new EmptyBorder(20, 22, 20, 22));

        JPanel tableTop = new JPanel(new BorderLayout());
        tableTop.setOpaque(false);

        JPanel tableTitlePanel = new JPanel();
        tableTitlePanel.setOpaque(false);
        tableTitlePanel.setLayout(new BoxLayout(tableTitlePanel, BoxLayout.Y_AXIS));

        JLabel tableTitle = new JLabel("Parcel Directory");
        tableTitle.setFont(new Font("SansSerif", Font.BOLD, 17));
        tableTitle.setForeground(TEXT_DARK);

        JLabel tableSubtitle = new JLabel("All registered parcels, delivery status and rider assignments");
        tableSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tableSubtitle.setForeground(TEXT_MUTED);

        tableTitlePanel.add(tableTitle);
        tableTitlePanel.add(Box.createVerticalStrut(3));
        tableTitlePanel.add(tableSubtitle);

        tableTop.add(tableTitlePanel, BorderLayout.WEST);

        // Filter Dropdown Container with Exact Enum Options
        JPanel filterContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        filterContainer.setOpaque(false);

        JLabel filterLabel = new JLabel("Filter Status:");
        filterLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        filterLabel.setForeground(TEXT_DARK);

        String[] statuses = {
                "ALL",
                "PENDING",
                "ACCEPTED",
                "ON_TRANSIT",
                "REACHED_DESTINATION",
                "DELIVERED",
                "CANCELED"
        };

        filterComboBox = new JComboBox<>(statuses);
        filterComboBox.setFont(new Font("SansSerif", Font.PLAIN, 12));
        filterComboBox.setBackground(WHITE);
        filterComboBox.setFocusable(false);
        filterComboBox.addActionListener(e -> applyFilter());

        JLabel directoryBadge = new JLabel("SYSTEM RECORDS");
        directoryBadge.setOpaque(true);
        directoryBadge.setBackground(GREEN_BG);
        directoryBadge.setForeground(GREEN);
        directoryBadge.setFont(new Font("SansSerif", Font.BOLD, 10));
        directoryBadge.setBorder(new EmptyBorder(7, 10, 7, 10));

        filterContainer.add(filterLabel);
        filterContainer.add(filterComboBox);
        filterContainer.add(directoryBadge);

        tableTop.add(filterContainer, BorderLayout.EAST);

        mainCard.add(tableTop, BorderLayout.NORTH);

        String[] columns = {
                "Parcel ID",
                "Parcel Name",
                "Receiver Address",
                "Receiver Phone",
                "Weight (kg)",
                "Sender Email",
                "Sender ID",
                "Status",
                "Delivery Charge",
                "Rider ID"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        parcelTable = new JTable(tableModel);

        tableSorter = new TableRowSorter<>(tableModel);
        parcelTable.setRowSorter(tableSorter);

        parcelTable.setRowHeight(38);
        parcelTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
        parcelTable.setForeground(TEXT_DARK);
        parcelTable.setBackground(WHITE);
        parcelTable.setSelectionBackground(LIGHT_BLUE);
        parcelTable.setSelectionForeground(TEXT_DARK);
        parcelTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        parcelTable.setShowGrid(true);
        parcelTable.setGridColor(BORDER);
        parcelTable.setIntercellSpacing(new Dimension(1, 1));
        parcelTable.setFillsViewportHeight(true);
        parcelTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);

        JTableHeader tableHeader = parcelTable.getTableHeader();
        tableHeader.setFont(new Font("SansSerif", Font.BOLD, 11));
        tableHeader.setBackground(BLUE);
        tableHeader.setForeground(Color.WHITE);
        tableHeader.setReorderingAllowed(false);
        tableHeader.setPreferredSize(new Dimension(tableHeader.getPreferredSize().width, 40));

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setBackground(BLUE);
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setFont(new Font("SansSerif", Font.BOLD, 11));

        tableHeader.setDefaultRenderer(headerRenderer);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column
            ) {
                Component component = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column
                );

                if (isSelected) {
                    component.setBackground(LIGHT_BLUE);
                    component.setForeground(TEXT_DARK);
                } else {
                    component.setBackground(row % 2 == 0 ? WHITE : new Color(247, 250, 252));
                    component.setForeground(TEXT_DARK);
                }

                setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));

                if (column == 7) {
                    String status = value == null ? "" : value.toString().toUpperCase().trim();

                    if (!isSelected) {
                        if ("DELIVERED".equals(status)) {
                            component.setForeground(GREEN);
                        } else if ("CANCELED".equals(status)) {
                            component.setForeground(RED);
                        } else if ("PENDING".equals(status)) {
                            component.setForeground(GOLD);
                        } else if ("ON_TRANSIT".equals(status) || "ACCEPTED".equals(status) || "REACHED_DESTINATION".equals(status)) {
                            component.setForeground(BLUE);
                        }
                    }
                }

                return component;
            }
        };

        for (int i = 0; i < parcelTable.getColumnCount(); i++) {
            parcelTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        int[] widths = {90, 125, 220, 115, 90, 190, 100, 110, 110, 100};
        for (int i = 0; i < widths.length; i++) {
            parcelTable.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }

        JScrollPane scrollPane = new JScrollPane(parcelTable);
        scrollPane.setBackground(WHITE);
        scrollPane.getViewport().setBackground(WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(BORDER, 1, true));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(14);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(14);

        mainCard.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        JLabel infoLabel = new JLabel("System-wide parcel records and current delivery assignments.");
        infoLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        infoLabel.setForeground(TEXT_MUTED);

        bottomPanel.add(infoLabel, BorderLayout.WEST);

        JButton refreshButton = createPrimaryButton("↻  Refresh List");
        refreshButton.setPreferredSize(new Dimension(145, 40));
        refreshButton.addActionListener(e -> loadParcels());

        bottomPanel.add(refreshButton, BorderLayout.EAST);
        mainCard.add(bottomPanel, BorderLayout.SOUTH);

        page.add(mainCard, BorderLayout.CENTER);
        add(page, BorderLayout.CENTER);
    }

    private void applyFilter() {
        if (filterComboBox == null || tableSorter == null) return;

        String selected = (String) filterComboBox.getSelectedItem();

        if (selected == null || "ALL".equalsIgnoreCase(selected.trim())) {
            tableSorter.setRowFilter(null);
        } else {
            String cleanSelected = selected.trim();

            // Flexibility for status string values with spaces or underscores
            String regex = "(?i)^" + cleanSelected.replace("_", "[ _]?") + "$";
            tableSorter.setRowFilter(RowFilter.regexFilter(regex, 7));
        }

        updateCountLabel();
    }

    private void updateCountLabel() {
        int count = parcelTable.getRowCount();
        countLabel.setText(count + (count == 1 ? " PARCEL" : " PARCELS"));
    }

    private JButton createPrimaryButton(String text) {
        JButton button = new JButton(text) {
            private boolean hovered;

            {
                addMouseListener(new MouseAdapter() {
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
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(hovered ? ORANGE_HOVER : ORANGE);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 12, 12));
                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    public void loadParcels() {
        tableModel.setRowCount(0);

        try {
            ArrayList<Parcel> parcels = AdminFile.getAllParcels();

            for (Parcel parcel : parcels) {

                String statusStr = parcel.getParcelStatus() != null
                        ? parcel.getParcelStatus().toString()
                        : "";

                Object[] row = {
                        parcel.getParcelID(),
                        parcel.getParcelName(),
                        parcel.getReciverAddress(),
                        parcel.getReciverPhone(),
                        parcel.getWeight(),
                        parcel.getSenderEmail(),
                        parcel.getSenderId(),
                        statusStr,
                        parcel.getDeliveryCharge(),
                        parcel.getRiderId() == null ? "Not Assigned" : parcel.getRiderId()
                };

                tableModel.addRow(row);
            }

            applyFilter();

        } catch (NotFoundException e) {
            countLabel.setText("0 PARCELS");
            JOptionPane.showMessageDialog(this, e.getMessage(), "No Parcels", JOptionPane.INFORMATION_MESSAGE);
        } catch (UnauthorizedAccessException e) {
            countLabel.setText("0 PARCELS");
            JOptionPane.showMessageDialog(this, e.getMessage(), "Unauthorized", JOptionPane.ERROR_MESSAGE);
        } catch (InvalidAmountException e) {
            countLabel.setText("0 PARCELS");
            JOptionPane.showMessageDialog(this, e.getMessage(), "Invalid Parcel Data", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            countLabel.setText("0 PARCELS");
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static class RoundedPanel extends JPanel {
        private final Color background;
        private final int radius;

        public RoundedPanel(Color background, int radius) {
            this.background = background;
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(background);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();

            super.paintComponent(g);
        }
    }
}