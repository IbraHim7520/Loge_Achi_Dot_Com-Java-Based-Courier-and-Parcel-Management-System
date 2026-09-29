package UI;

import custom_exception.NotFoundException;
import file.AdminFile;
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

public class AdminUsersFrame extends JPanel {

    private final User user;
    private JTable userTable;
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

    public AdminUsersFrame(User user) {
        this.user = user;

        setLayout(new BorderLayout());
        setBackground(BG);

        buildUI();
        loadUsers();
    }

    private void buildUI() {

        JPanel page = new JPanel(new BorderLayout(0, 18));
        page.setOpaque(false);
        page.setBorder(new EmptyBorder(24, 28, 24, 28));

        JPanel headerPanel = new JPanel(
                new BorderLayout(14, 0)
        );
        headerPanel.setOpaque(false);

        JPanel iconPanel = new RoundedPanel(
                BLUE,
                16
        );

        iconPanel.setPreferredSize(
                new Dimension(52, 52)
        );

        iconPanel.setLayout(
                new GridBagLayout()
        );

        JLabel iconLabel = new JLabel("♙");
        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        26
                )
        );
        iconLabel.setForeground(Color.WHITE);

        iconPanel.add(iconLabel);

        headerPanel.add(
                iconPanel,
                BorderLayout.WEST
        );

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel sectionLabel = new JLabel(
                "USER MANAGEMENT"
        );

        sectionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        sectionLabel.setForeground(
                ORANGE
        );

        JLabel titleLabel = new JLabel(
                "All System Users"
        );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        26
                )
        );

        titleLabel.setForeground(
                TEXT_DARK
        );

        JLabel subtitleLabel = new JLabel(
                "View and inspect all registered accounts across roles"
        );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        subtitleLabel.setForeground(
                TEXT_MUTED
        );

        titlePanel.add(sectionLabel);
        titlePanel.add(
                Box.createVerticalStrut(3)
        );
        titlePanel.add(titleLabel);
        titlePanel.add(
                Box.createVerticalStrut(3)
        );
        titlePanel.add(subtitleLabel);

        headerPanel.add(
                titlePanel,
                BorderLayout.CENTER
        );

        countLabel = new JLabel(
                "0 USERS"
        );

        countLabel.setOpaque(true);
        countLabel.setBackground(
                LIGHT_BLUE
        );

        countLabel.setForeground(
                BLUE
        );

        countLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        countLabel.setBorder(
                new EmptyBorder(
                        8,
                        12,
                        8,
                        12
                )
        );

        headerPanel.add(
                countLabel,
                BorderLayout.EAST
        );

        page.add(
                headerPanel,
                BorderLayout.NORTH
        );

        JPanel mainCard = new RoundedPanel(
                WHITE,
                22
        );

        mainCard.setLayout(
                new BorderLayout(
                        0,
                        16
                )
        );

        mainCard.setBorder(
                new EmptyBorder(
                        20,
                        22,
                        20,
                        22
                )
        );

        JPanel tableHeaderPanel = new JPanel(
                new BorderLayout()
        );

        tableHeaderPanel.setOpaque(false);

        JPanel tableTitlePanel = new JPanel();

        tableTitlePanel.setOpaque(false);

        tableTitlePanel.setLayout(
                new BoxLayout(
                        tableTitlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel tableTitle = new JLabel(
                "User Directory"
        );

        tableTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        tableTitle.setForeground(
                TEXT_DARK
        );

        JLabel tableSubtitle = new JLabel(
                "All registered users and their assigned roles"
        );

        tableSubtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        tableSubtitle.setForeground(
                TEXT_MUTED
        );

        tableTitlePanel.add(
                tableTitle
        );

        tableTitlePanel.add(
                Box.createVerticalStrut(3)
        );

        tableTitlePanel.add(
                tableSubtitle
        );

        tableHeaderPanel.add(
                tableTitlePanel,
                BorderLayout.WEST
        );

        JLabel directoryBadge = new JLabel(
                "USER DIRECTORY"
        );

        directoryBadge.setOpaque(true);

        directoryBadge.setBackground(
                SUCCESS_BG
        );

        directoryBadge.setForeground(
                SUCCESS
        );

        directoryBadge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        directoryBadge.setBorder(
                new EmptyBorder(
                        7,
                        10,
                        7,
                        10
                )
        );

        tableHeaderPanel.add(
                directoryBadge,
                BorderLayout.EAST
        );

        mainCard.add(
                tableHeaderPanel,
                BorderLayout.NORTH
        );

        String[] columns = {
                "User ID",
                "Name",
                "Email",
                "Role"
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

        userTable = new JTable(tableModel);

        userTable.setRowHeight(40);

        userTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        userTable.setForeground(
                TEXT_DARK
        );

        userTable.setBackground(
                WHITE
        );

        userTable.setSelectionBackground(
                LIGHT_BLUE
        );

        userTable.setSelectionForeground(
                TEXT_DARK
        );

        userTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        userTable.setShowGrid(true);

        userTable.setGridColor(
                BORDER
        );

        userTable.setIntercellSpacing(
                new Dimension(
                        1,
                        1
                )
        );

        userTable.setFillsViewportHeight(true);

        userTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS
        );

        JTableHeader tableHeader =
                userTable.getTableHeader();

        tableHeader.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        tableHeader.setBackground(
                BLUE
        );

        tableHeader.setForeground(
                Color.WHITE
        );

        tableHeader.setReorderingAllowed(false);

        tableHeader.setPreferredSize(
                new Dimension(
                        tableHeader.getPreferredSize().width,
                        40
                )
        );

        DefaultTableCellRenderer headerRenderer =
                new DefaultTableCellRenderer();

        headerRenderer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        headerRenderer.setBackground(
                BLUE
        );

        headerRenderer.setForeground(
                Color.WHITE
        );

        headerRenderer.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        11
                )
        );

        tableHeader.setDefaultRenderer(
                headerRenderer
        );

        DefaultTableCellRenderer cellRenderer =
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

                        if (isSelected) {

                            component.setBackground(
                                    LIGHT_BLUE
                            );

                            component.setForeground(
                                    TEXT_DARK
                            );

                        } else {

                            component.setBackground(
                                    row % 2 == 0
                                            ? WHITE
                                            : new Color(
                                            247,
                                            250,
                                            252
                                    )
                            );

                            component.setForeground(
                                    TEXT_DARK
                            );
                        }

                        setBorder(
                                BorderFactory.createEmptyBorder(
                                        0,
                                        10,
                                        0,
                                        10
                                )
                        );

                        return component;
                    }
                };

        for (
                int i = 0;
                i < userTable.getColumnCount();
                i++
        ) {

            userTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(
                            cellRenderer
                    );
        }

        int[] widths = {
                120,
                180,
                300,
                130
        };

        for (
                int i = 0;
                i < widths.length;
                i++
        ) {

            userTable
                    .getColumnModel()
                    .getColumn(i)
                    .setPreferredWidth(
                            widths[i]
                    );
        }

        JScrollPane scrollPane =
                new JScrollPane(
                        userTable
                );

        scrollPane.setBackground(
                WHITE
        );

        scrollPane.getViewport()
                .setBackground(
                        WHITE
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER,
                        1,
                        true
                )
        );

        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );

        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(14);

        scrollPane.getHorizontalScrollBar()
                .setUnitIncrement(14);

        mainCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout()
                );

        bottomPanel.setOpaque(false);

        JLabel infoLabel = new JLabel(
                "Use the refresh button to load the latest user records."
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

        JButton refreshButton =
                createPrimaryButton(
                        "↻  Refresh List"
                );

        refreshButton.setPreferredSize(
                new Dimension(
                        140,
                        40
                )
        );

        refreshButton.addActionListener(
                e -> loadUsers()
        );

        bottomPanel.add(
                refreshButton,
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

    private JButton createPrimaryButton(
            String text
    ) {

        JButton button =
                new JButton(text) {

                    private boolean hovered;

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

        button.setForeground(
                Color.WHITE
        );

        button.setContentAreaFilled(
                false
        );

        button.setOpaque(false);

        button.setBorderPainted(
                false
        );

        button.setFocusPainted(
                false
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    public void loadUsers() {

        tableModel.setRowCount(0);

        try {

            ArrayList<User> users =
                    AdminFile.getAllUsers();

            for (User currentUser : users) {

                Object[] row = {

                        currentUser.getUser_id(),
                        currentUser.getUser_name(),
                        currentUser.getUser_email(),
                        currentUser.getUser_role()
                };

                tableModel.addRow(row);
            }

            int count =
                    tableModel.getRowCount();

            countLabel.setText(
                    count +
                            (count == 1
                                    ? " USER"
                                    : " USERS")
            );

        } catch (NotFoundException e) {

            countLabel.setText(
                    "0 USERS"
            );

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "No Users Found",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {

            countLabel.setText(
                    "0 USERS"
            );

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private static class RoundedPanel
            extends JPanel {

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
        protected void paintComponent(
                Graphics g
        ) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(
                    background
            );

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