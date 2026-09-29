package UI;

import custom_exception.NotFoundException;
import model.Admin;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class AdminUpdateParcelStatusFrame extends JPanel {

    private final User user;

    private JTextField parcelIdField;
    private JComboBox<String> statusComboBox;

    private final Color BG = new Color(241, 248, 253);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT_DARK = new Color(31, 38, 43);
    private final Color TEXT_MUTED = new Color(103, 117, 128);
    private final Color ORANGE = new Color(248, 116, 35);
    private final Color ORANGE_HOVER = new Color(235, 94, 20);
    private final Color BLUE = new Color(0, 97, 153);
    private final Color LIGHT_BLUE = new Color(225, 240, 249);
    private final Color BORDER = new Color(216, 227, 234);
    private final Color INPUT_BG = new Color(249, 251, 252);
    private final Color INPUT_BORDER = new Color(198, 211, 220);

    private final Color SUCCESS = new Color(48, 148, 94);
    private final Color SUCCESS_BG = new Color(233, 248, 240);

    public AdminUpdateParcelStatusFrame(User user) {

        this.user = user;

        setLayout(new BorderLayout());
        setBackground(BG);

        buildUI();
    }

    private void buildUI() {

        JPanel page = new JPanel(
                new BorderLayout(0, 18)
        );

        page.setOpaque(false);

        page.setBorder(
                new EmptyBorder(
                        24,
                        28,
                        24,
                        28
                )
        );

        JPanel headerPanel = new JPanel(
                new BorderLayout(14, 0)
        );

        headerPanel.setOpaque(false);

        JPanel iconPanel = new RoundedPanel(
                BLUE,
                16
        );

        iconPanel.setPreferredSize(
                new Dimension(
                        52,
                        52
                )
        );

        iconPanel.setLayout(
                new GridBagLayout()
        );

        JLabel iconLabel = new JLabel("↻");

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        27
                )
        );

        iconLabel.setForeground(
                Color.WHITE
        );

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
                "PARCEL MANAGEMENT"
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
                "Update Parcel Status"
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
                "Modify the current tracking status of a parcel"
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

        JLabel badge = new JLabel(
                "STATUS CONTROL"
        );

        badge.setOpaque(true);

        badge.setBackground(
                SUCCESS_BG
        );

        badge.setForeground(
                SUCCESS
        );

        badge.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        badge.setBorder(
                new EmptyBorder(
                        7,
                        11,
                        7,
                        11
                )
        );

        headerPanel.add(
                badge,
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
                        18,
                        0
                )
        );

        mainCard.setBorder(
                new EmptyBorder(
                        22,
                        24,
                        22,
                        24
                )
        );

        JPanel formCard = new RoundedPanel(
                new Color(251, 253, 254),
                18
        );

        formCard.setLayout(
                new GridBagLayout()
        );

        formCard.setBorder(
                new EmptyBorder(
                        22,
                        24,
                        22,
                        24
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JLabel formTitle = new JLabel(
                "Update Delivery Status"
        );

        formTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        formTitle.setForeground(
                TEXT_DARK
        );

        JLabel formSubtitle = new JLabel(
                "Enter the parcel ID and select its new status."
        );

        formSubtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        formSubtitle.setForeground(
                TEXT_MUTED
        );

        gbc.gridy = 0;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        4,
                        0
                );

        formCard.add(
                formTitle,
                gbc
        );

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        22,
                        0
                );

        formCard.add(
                formSubtitle,
                gbc
        );

        JLabel parcelIdLabel =
                createFormLabel(
                        "PARCEL ID"
                );

        gbc.gridy = 2;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        7,
                        0
                );

        formCard.add(
                parcelIdLabel,
                gbc
        );

        parcelIdField =
                new JTextField();

        styleInputField(
                parcelIdField
        );

        gbc.gridy = 3;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        18,
                        0
                );

        formCard.add(
                parcelIdField,
                gbc
        );

        JLabel statusLabel =
                createFormLabel(
                        "NEW STATUS"
                );

        gbc.gridy = 4;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        7,
                        0
                );

        formCard.add(
                statusLabel,
                gbc
        );

        String[] statuses = {
                "PENDING",
                "ACCEPTED",
                "ON_TRANSIT",
                "REACHED_DESTINATION",
                "DELIVERED",
                "CANCELED"
        };

        statusComboBox =
                new JComboBox<>(
                        statuses
                );

        styleComboBox(
                statusComboBox
        );

        gbc.gridy = 5;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        18,
                        0
                );

        formCard.add(
                statusComboBox,
                gbc
        );

        JPanel noteCard = new RoundedPanel(
                LIGHT_BLUE,
                14
        );

        noteCard.setLayout(
                new BorderLayout(
                        10,
                        0
                )
        );

        noteCard.setBorder(
                new EmptyBorder(
                        13,
                        14,
                        13,
                        14
                )
        );

        JPanel noteIcon = new RoundedPanel(
                BLUE,
                10
        );

        noteIcon.setPreferredSize(
                new Dimension(
                        32,
                        32
                )
        );

        noteIcon.setLayout(
                new GridBagLayout()
        );

        JLabel noteSymbol = new JLabel(
                "i"
        );

        noteSymbol.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        noteSymbol.setForeground(
                Color.WHITE
        );

        noteIcon.add(noteSymbol);

        noteCard.add(
                noteIcon,
                BorderLayout.WEST
        );

        JLabel noteLabel = new JLabel(
                "<html><b>Status update</b><br>" +
                        "<span style='color:#687580'>" +
                        "The selected status will be saved for this parcel." +
                        "</span></html>"
        );

        noteLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        noteCard.add(
                noteLabel,
                BorderLayout.CENTER
        );

        gbc.gridy = 6;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        0
                );

        formCard.add(
                noteCard,
                gbc
        );

        mainCard.add(
                formCard,
                BorderLayout.CENTER
        );

        JPanel guideCard = new RoundedPanel(
                LIGHT_BLUE,
                18
        );

        guideCard.setLayout(
                new BorderLayout(
                        0,
                        18
                )
        );

        guideCard.setBorder(
                new EmptyBorder(
                        22,
                        22,
                        22,
                        22
                )
        );

        guideCard.setPreferredSize(
                new Dimension(
                        285,
                        0
                )
        );

        JPanel guideHeader = new JPanel();

        guideHeader.setOpaque(false);

        guideHeader.setLayout(
                new BoxLayout(
                        guideHeader,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel guideTitle = new JLabel(
                "Status Flow"
        );

        guideTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        guideTitle.setForeground(
                TEXT_DARK
        );

        JLabel guideSubtitle = new JLabel(
                "Typical parcel delivery progress"
        );

        guideSubtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        guideSubtitle.setForeground(
                TEXT_MUTED
        );

        guideHeader.add(
                guideTitle
        );

        guideHeader.add(
                Box.createVerticalStrut(3)
        );

        guideHeader.add(
                guideSubtitle
        );

        guideCard.add(
                guideHeader,
                BorderLayout.NORTH
        );

        JPanel steps = new JPanel();

        steps.setOpaque(false);

        steps.setLayout(
                new BoxLayout(
                        steps,
                        BoxLayout.Y_AXIS
                )
        );

        steps.add(
                createStatusStep(
                        "01",
                        "PENDING",
                        "Parcel is waiting for processing."
                )
        );

        steps.add(
                Box.createVerticalStrut(13)
        );

        steps.add(
                createStatusStep(
                        "02",
                        "ACCEPTED",
                        "Parcel has been accepted."
                )
        );

        steps.add(
                Box.createVerticalStrut(13)
        );

        steps.add(
                createStatusStep(
                        "03",
                        "ON_TRANSIT",
                        "Parcel is moving to destination."
                )
        );

        steps.add(
                Box.createVerticalStrut(13)
        );

        steps.add(
                createStatusStep(
                        "04",
                        "DELIVERED",
                        "Parcel delivery is completed."
                )
        );

        guideCard.add(
                steps,
                BorderLayout.CENTER
        );

        JPanel accessCard = new RoundedPanel(
                WHITE,
                14
        );

        accessCard.setLayout(
                new BorderLayout(
                        10,
                        0
                )
        );

        accessCard.setBorder(
                new EmptyBorder(
                        13,
                        14,
                        13,
                        14
                )
        );

        JPanel dot = new RoundedPanel(
                ORANGE,
                10
        );

        dot.setPreferredSize(
                new Dimension(
                        10,
                        10
                )
        );

        accessCard.add(
                dot,
                BorderLayout.WEST
        );

        JLabel accessLabel = new JLabel(
                "<html><b>ADMIN CONTROL</b><br>" +
                        "<span style='color:#687580'>" +
                        "Status can be updated by an authorized administrator." +
                        "</span></html>"
        );

        accessLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        accessCard.add(
                accessLabel,
                BorderLayout.CENTER
        );

        guideCard.add(
                accessCard,
                BorderLayout.SOUTH
        );

        mainCard.add(
                guideCard,
                BorderLayout.EAST
        );

        page.add(
                mainCard,
                BorderLayout.CENTER
        );

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                0
                        )
                );

        bottomPanel.setOpaque(false);

        JButton clearButton =
                createOutlineButton(
                        "Clear"
                );

        JButton updateButton =
                createPrimaryButton(
                        "Update Status  →"
                );

        clearButton.setPreferredSize(
                new Dimension(
                        95,
                        40
                )
        );

        updateButton.setPreferredSize(
                new Dimension(
                        155,
                        40
                )
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        updateButton.addActionListener(
                e -> updateStatus()
        );

        bottomPanel.add(
                clearButton
        );

        bottomPanel.add(
                updateButton
        );

        page.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        add(
                page,
                BorderLayout.CENTER
        );
    }

    private JLabel createFormLabel(
            String text
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        label.setForeground(
                BLUE
        );

        return label;
    }

    private void styleInputField(
            JTextField field
    ) {

        field.setOpaque(true);

        field.setBackground(
                WHITE
        );

        field.setForeground(
                TEXT_DARK
        );

        field.setCaretColor(
                ORANGE
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
                                9,
                                12,
                                9,
                                12
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
                                                ORANGE,
                                                2,
                                                true
                                        ),
                                        BorderFactory.createEmptyBorder(
                                                8,
                                                11,
                                                8,
                                                11
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
                                                9,
                                                12,
                                                9,
                                                12
                                        )
                                )
                        );
                    }
                }
        );
    }

    private void styleComboBox(
            JComboBox<String> comboBox
    ) {

        comboBox.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        comboBox.setBackground(
                WHITE
        );

        comboBox.setForeground(
                TEXT_DARK
        );

        comboBox.setPreferredSize(
                new Dimension(
                        0,
                        44
                )
        );

        comboBox.setFocusable(false);

        comboBox.setRenderer(
                new DefaultListCellRenderer() {

                    @Override
                    public Component
                    getListCellRendererComponent(
                            JList<?> list,
                            Object value,
                            int index,
                            boolean selected,
                            boolean focus
                    ) {

                        JLabel label =
                                (JLabel) super
                                        .getListCellRendererComponent(
                                                list,
                                                value,
                                                index,
                                                selected,
                                                focus
                                        );

                        label.setFont(
                                new Font(
                                        "SansSerif",
                                        Font.PLAIN,
                                        13
                                )
                        );

                        label.setBorder(
                                new EmptyBorder(
                                        8,
                                        12,
                                        8,
                                        12
                                )
                        );

                        if (selected) {

                            label.setBackground(
                                    BLUE
                            );

                            label.setForeground(
                                    Color.WHITE
                            );

                        } else {

                            label.setBackground(
                                    WHITE
                            );

                            label.setForeground(
                                    TEXT_DARK
                            );
                        }

                        return label;
                    }
                }
        );

        comboBox.setUI(
                new BasicComboBoxUI() {

                    @Override
                    protected JButton
                    createArrowButton() {

                        JButton button =
                                super
                                        .createArrowButton();

                        button.setBackground(
                                WHITE
                        );

                        button.setBorder(
                                BorderFactory.createEmptyBorder()
                        );

                        button.setContentAreaFilled(
                                false
                        );

                        return button;
                    }
                }
        );
    }

    private JPanel createStatusStep(
            String number,
            String status,
            String description
    ) {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                12,
                                0
                        )
                );

        panel.setOpaque(false);

        JPanel numberPanel =
                new RoundedPanel(
                        ORANGE,
                        10
                );

        numberPanel.setPreferredSize(
                new Dimension(
                        36,
                        36
                )
        );

        numberPanel.setLayout(
                new GridBagLayout()
        );

        JLabel numberLabel =
                new JLabel(number);

        numberLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        10
                )
        );

        numberLabel.setForeground(
                Color.WHITE
        );

        numberPanel.add(
                numberLabel
        );

        panel.add(
                numberPanel,
                BorderLayout.WEST
        );

        JPanel textPanel =
                new JPanel();

        textPanel.setOpaque(false);

        textPanel.setLayout(
                new BoxLayout(
                        textPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel statusLabel =
                new JLabel(
                        status
                );

        statusLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        statusLabel.setForeground(
                TEXT_DARK
        );

        JLabel descriptionLabel =
                new JLabel(
                        "<html>"
                                + description
                                + "</html>"
                );

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        11
                )
        );

        descriptionLabel.setForeground(
                TEXT_MUTED
        );

        textPanel.add(
                statusLabel
        );

        textPanel.add(
                Box.createVerticalStrut(3)
        );

        textPanel.add(
                descriptionLabel
        );

        panel.add(
                textPanel,
                BorderLayout.CENTER
        );

        return panel;
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

    private JButton createOutlineButton(
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

                        if (hovered) {

                            g2.setColor(
                                    LIGHT_BLUE
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
                        }

                        g2.setColor(
                                BLUE
                        );

                        g2.setStroke(
                                new BasicStroke(
                                        1.2f
                                )
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

        button.setForeground(
                BLUE
        );

        button.setContentAreaFilled(
                false
        );

        button.setOpaque(false);

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

    private void updateStatus() {

        String parcelID =
                parcelIdField
                        .getText()
                        .trim();

        String newStatus =
                (String) statusComboBox
                        .getSelectedItem();

        if (parcelID.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter Parcel ID!",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            Admin admin =
                    new Admin(user);

            admin.updateParcelStatus(
                    parcelID,
                    newStatus
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Parcel status updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

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

    private void clearFields() {

        parcelIdField.setText("");

        statusComboBox.setSelectedIndex(0);
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