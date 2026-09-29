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
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;

public class MyParcelsPanel extends JPanel {

    private final User user;

    private JTable parcelTable;
    private DefaultTableModel tableModel;
    private JLabel countLabel;

    private static final Color BG = new Color(241, 248, 253);
    private static final Color WHITE = Color.WHITE;
    private static final Color BLACK = new Color(15, 18, 20);
    private static final Color TEXT_DARK = new Color(31, 38, 43);
    private static final Color TEXT_MUTED = new Color(103, 117, 128);

    private static final Color ORANGE = new Color(248, 116, 35);
    private static final Color ORANGE_HOVER = new Color(235, 94, 20);

    private static final Color BLUE = new Color(0, 97, 153);
    private static final Color LIGHT_BLUE = new Color(225, 240, 249);
    private static final Color SKY_BLUE = new Color(181, 219, 241);

    private static final Color BORDER = new Color(216, 227, 234);

    public MyParcelsPanel(User user) {
        this.user = user;

        setLayout(new BorderLayout());
        setOpaque(false);

        buildUI();
        loadUserParcels();
    }

    private void buildUI() {

        JPanel mainCard = new JPanel(new BorderLayout(0, 16)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(WHITE);

                g2.fill(
                        new RoundRectangle2D.Float(
                                0,
                                0,
                                getWidth(),
                                getHeight(),
                                22,
                                22
                        )
                );

                g2.setColor(BORDER);

                g2.draw(
                        new RoundRectangle2D.Float(
                                0.5f,
                                0.5f,
                                getWidth() - 1,
                                getHeight() - 1,
                                22,
                                22
                        )
                );

                g2.setColor(ORANGE);

                g2.fillRoundRect(
                        28,
                        0,
                        105,
                        4,
                        4,
                        4
                );

                g2.dispose();
            }
        };

        mainCard.setOpaque(false);
        mainCard.setBorder(
                new EmptyBorder(
                        22,
                        24,
                        20,
                        24
                )
        );

        mainCard.add(
                createHeader(),
                BorderLayout.NORTH
        );

        mainCard.add(
                createTableSection(),
                BorderLayout.CENTER
        );

        add(
                mainCard,
                BorderLayout.CENTER
        );
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(BG);

        g2.fillRect(
                0,
                0,
                getWidth(),
                getHeight()
        );

        g2.setColor(
                new Color(
                        196,
                        226,
                        243,
                        90
                )
        );

        g2.fill(
                new Ellipse2D.Float(
                        -120,
                        -120,
                        360,
                        360
                )
        );

        g2.setColor(
                new Color(
                        179,
                        216,
                        237,
                        65
                )
        );

        g2.fill(
                new Ellipse2D.Float(
                        getWidth() - 280,
                        getHeight() - 260,
                        450,
                        450
                )
        );

        g2.setColor(
                new Color(
                        ORANGE.getRed(),
                        ORANGE.getGreen(),
                        ORANGE.getBlue(),
                        70
                )
        );

        g2.fillOval(
                55,
                getHeight() - 80,
                7,
                7
        );

        g2.dispose();
    }

    private JPanel createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setOpaque(false);

        JPanel titleGroup =
                new JPanel();

        titleGroup.setOpaque(false);

        titleGroup.setLayout(
                new BoxLayout(
                        titleGroup,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel section =
                new JLabel(
                        "PARCEL MANAGEMENT"
                );

        section.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        section.setForeground(
                ORANGE
        );

        JLabel title =
                new JLabel(
                        "My Parcels"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                BLACK
        );

        JLabel subtitle =
                new JLabel(
                        "View and manage all parcels sent from your account"
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        subtitle.setForeground(
                TEXT_MUTED
        );

        titleGroup.add(section);

        titleGroup.add(
                Box.createVerticalStrut(5)
        );

        titleGroup.add(title);

        titleGroup.add(
                Box.createVerticalStrut(5)
        );

        titleGroup.add(subtitle);

        header.add(
                titleGroup,
                BorderLayout.WEST
        );

        JPanel right =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                9,
                                0
                        )
                );

        right.setOpaque(false);

        countLabel =
                new JLabel(
                        "0 PARCELS"
                );

        countLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        9
                )
        );

        countLabel.setForeground(
                BLUE
        );

        countLabel.setOpaque(true);

        countLabel.setBackground(
                LIGHT_BLUE
        );

        countLabel.setBorder(
                new EmptyBorder(
                        8,
                        10,
                        8,
                        10
                )
        );

        right.add(
                countLabel
        );

        JButton refreshButton =
                createPrimaryButton(
                        "↻  Refresh"
                );

        refreshButton.setPreferredSize(
                new Dimension(
                        120,
                        38
                )
        );

        refreshButton.addActionListener(
                e -> loadUserParcels()
        );

        right.add(
                refreshButton
        );

        header.add(
                right,
                BorderLayout.EAST
        );

        return header;
    }

    private JPanel createTableSection() {

        JPanel section =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        section.setOpaque(false);

        JPanel tableCard =
                new JPanel(
                        new BorderLayout()
                );

        tableCard.setOpaque(true);

        tableCard.setBackground(WHITE);

        tableCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER,
                                1,
                                true
                        ),
                        new EmptyBorder(
                                8,
                                8,
                                8,
                                8
                        )
                )
        );

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
                new JTable(
                        tableModel
                ) {
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
                                            ? WHITE
                                            : new Color(
                                            244,
                                            249,
                                            252
                                    )
                            );

                            component.setForeground(
                                    TEXT_DARK
                            );
                        } else {

                            component.setBackground(
                                    new Color(
                                            255,
                                            239,
                                            229
                                    )
                            );

                            component.setForeground(
                                    TEXT_DARK
                            );
                        }

                        return component;
                    }
                };

        parcelTable.setRowHeight(42);

        parcelTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        parcelTable.setForeground(TEXT_DARK);

        parcelTable.setBackground(WHITE);

        parcelTable.setShowGrid(false);

        parcelTable.setIntercellSpacing(
                new Dimension(
                        0,
                        0
                )
        );

        parcelTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        parcelTable.setFillsViewportHeight(true);

        parcelTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS
        );

        parcelTable.setRowMargin(0);

        JTableHeader header =
                parcelTable.getTableHeader();

        header.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        header.setForeground(
                WHITE
        );

        header.setBackground(BLUE);

        header.setOpaque(true);

        header.setPreferredSize(
                new Dimension(
                        header.getPreferredSize().width,
                        42
                )
        );

        header.setReorderingAllowed(false);

        DefaultTableCellRenderer headerRenderer =
                new DefaultTableCellRenderer();

        headerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        headerRenderer.setVerticalAlignment(
                SwingConstants.CENTER
        );

        headerRenderer.setForeground(
                WHITE
        );

        headerRenderer.setBackground(BLUE);

        headerRenderer.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        headerRenderer.setBorder(
                new EmptyBorder(
                        0,
                        5,
                        0,
                        5
                )
        );

        header.setDefaultRenderer(
                headerRenderer
        );

        DefaultTableCellRenderer centerRenderer =
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

                        setHorizontalAlignment(
                                SwingConstants.CENTER
                        );

                        setVerticalAlignment(
                                SwingConstants.CENTER
                        );

                        setBorder(
                                new EmptyBorder(
                                        0,
                                        8,
                                        0,
                                        8
                                )
                        );

                        if (!isSelected) {

                            component.setBackground(
                                    row % 2 == 0
                                            ? WHITE
                                            : new Color(
                                            244,
                                            249,
                                            252
                                    )
                            );

                            component.setForeground(
                                    TEXT_DARK
                            );
                        }

                        return component;
                    }
                };

        for (
                int i = 0;
                i < parcelTable.getColumnCount();
                i++
        ) {

            if (i == 6) {

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

        int[] widths = {
                95,
                135,
                230,
                115,
                100,
                110,
                125,
                115
        };

        for (
                int i = 0;
                i < widths.length;
                i++
        ) {

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
                .setBackground(WHITE);

        scrollPane.setBorder(null);

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        scrollPane.getHorizontalScrollBar()
                .setUnitIncrement(16);

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        section.add(
                tableCard,
                BorderLayout.CENTER
        );

        JLabel footer =
                new JLabel(
                        "● Parcel history is loaded from your account records"
                );

        footer.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        9
                )
        );

        footer.setForeground(TEXT_MUTED);

        footer.setBorder(
                new EmptyBorder(
                        0,
                        4,
                        0,
                        4
                )
        );

        section.add(
                footer,
                BorderLayout.SOUTH
        );

        return section;
    }

    public void loadUserParcels() {

        if (tableModel == null) {
            return;
        }

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

                    String cleanData =
                            parcelData.trim();

                    if (
                            cleanData.endsWith("#")
                    ) {

                        cleanData =
                                cleanData.substring(
                                        0,
                                        cleanData.length() - 1
                                );
                    }

                    String[] data =
                            cleanData.split(
                                    "\\|",
                                    -1
                            );

                    if (
                            data.length >= 10
                    ) {

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
                    }
                }
            }

            updateCount();

        } catch (
                UnauthorizedAccessException
                | NotFoundException e
        ) {

            updateCount();

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            updateCount();

            JOptionPane.showMessageDialog(
                    this,
                    "An error occurred while loading parcels.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            e.printStackTrace();
        }
    }

    private void updateCount() {

        if (countLabel == null) {
            return;
        }

        int count =
                tableModel.getRowCount();

        countLabel.setText(
                count
                        + (
                        count == 1
                                ? " PARCEL"
                                : " PARCELS"
                )
        );
    }

    private JButton createPrimaryButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

                    @Override
                    protected void paintComponent(
                            Graphics g
                    ) {

                        Graphics2D g2 =
                                (Graphics2D)
                                        g.create();

                        g2.setRenderingHint(
                                RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        Color top =
                                getModel().isRollover()
                                        ? new Color(
                                        255,
                                        151,
                                        68
                                )
                                        : new Color(
                                        255,
                                        137,
                                        48
                                );

                        Color bottom =
                                getModel().isRollover()
                                        ? ORANGE_HOVER
                                        : ORANGE;

                        g2.setPaint(
                                new GradientPaint(
                                        0,
                                        0,
                                        top,
                                        getWidth(),
                                        0,
                                        bottom
                                )
                        );

                        g2.fill(
                                new RoundRectangle2D.Float(
                                        0,
                                        0,
                                        getWidth(),
                                        getHeight(),
                                        11,
                                        11
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
                        11
                )
        );

        button.setForeground(WHITE);

        button.setContentAreaFilled(false);

        button.setOpaque(false);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    private class StatusCellRenderer
            extends DefaultTableCellRenderer {

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

            setHorizontalAlignment(
                    SwingConstants.CENTER
            );

            setVerticalAlignment(
                    SwingConstants.CENTER
            );

            setBorder(
                    new EmptyBorder(
                            0,
                            8,
                            0,
                            8
                    )
            );

            if (!isSelected) {

                component.setBackground(
                        row % 2 == 0
                                ? WHITE
                                : new Color(
                                244,
                                249,
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
                                    170,
                                    112,
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
                            BLUE
                    );

                    setFont(
                            getFont().deriveFont(
                                    Font.BOLD
                            )
                    );
                }
            }

            return component;
        }
    }
}