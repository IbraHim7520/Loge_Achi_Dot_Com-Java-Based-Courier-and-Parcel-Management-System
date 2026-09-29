package UI;

import custom_exception.InvalidAmountException;
import custom_exception.NotFoundException;
import custom_exception.UnauthorizedAccessException;
import file.AdminFile;
import model.Parcel;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class SearchParcelFrame extends JPanel {

    private final User user;

    private JTextField parcelIdField;
    private JTextArea resultArea;

    private final Color BG = new Color(241, 248, 253);
    private final Color WHITE = Color.WHITE;
    private final Color BLACK = new Color(15, 18, 20);
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

    public SearchParcelFrame(User user) {
        this.user = user;

        setLayout(new BorderLayout());
        setBackground(BG);

        buildUI();
    }

    private void buildUI() {
        JPanel page = new JPanel(new BorderLayout(0, 18));
        page.setOpaque(false);
        page.setBorder(new EmptyBorder(24, 28, 24, 28));

        JPanel header = new JPanel(new BorderLayout(14, 0));
        header.setOpaque(false);

        JPanel icon = new RoundedPanel(BLUE, 16);
        icon.setPreferredSize(new Dimension(52, 52));
        icon.setLayout(new GridBagLayout());

        JLabel iconLabel = new JLabel("⌕");
        iconLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
        iconLabel.setForeground(Color.WHITE);
        icon.add(iconLabel);

        header.add(icon, BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel smallTitle = new JLabel("PARCEL MANAGEMENT");
        smallTitle.setFont(new Font("SansSerif", Font.BOLD, 11));
        smallTitle.setForeground(ORANGE);

        JLabel title = new JLabel("Search Parcel");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(TEXT_DARK);

        JLabel subtitle = new JLabel("Find parcel information using Parcel ID");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
        subtitle.setForeground(TEXT_MUTED);

        titlePanel.add(smallTitle);
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(title);
        titlePanel.add(Box.createVerticalStrut(3));
        titlePanel.add(subtitle);

        header.add(titlePanel, BorderLayout.CENTER);

        page.add(header, BorderLayout.NORTH);

        JPanel mainCard = new RoundedPanel(WHITE, 22);
        mainCard.setLayout(new BorderLayout(0, 18));
        mainCard.setBorder(new EmptyBorder(22, 24, 22, 24));

        JPanel searchCard = new RoundedPanel(LIGHT_BLUE, 16);
        searchCard.setLayout(new BorderLayout(14, 0));
        searchCard.setBorder(new EmptyBorder(16, 18, 16, 18));

        JPanel searchText = new JPanel();
        searchText.setOpaque(false);
        searchText.setLayout(new BoxLayout(searchText, BoxLayout.Y_AXIS));

        JLabel searchTitle = new JLabel("Search by Parcel ID");
        searchTitle.setFont(new Font("SansSerif", Font.BOLD, 15));
        searchTitle.setForeground(TEXT_DARK);

        JLabel searchHint = new JLabel("Enter the unique Parcel ID to view complete details.");
        searchHint.setFont(new Font("SansSerif", Font.PLAIN, 12));
        searchHint.setForeground(TEXT_MUTED);

        searchText.add(searchTitle);
        searchText.add(Box.createVerticalStrut(3));
        searchText.add(searchHint);

        JPanel fieldPanel = new JPanel(new BorderLayout(10, 0));
        fieldPanel.setOpaque(false);

        parcelIdField = new JTextField();
        styleInputField(parcelIdField);
        parcelIdField.setToolTipText("Enter Parcel ID");

        JButton searchButton = createPrimaryButton("Search");

        fieldPanel.add(parcelIdField, BorderLayout.CENTER);
        fieldPanel.add(searchButton, BorderLayout.EAST);

        searchCard.add(searchText, BorderLayout.CENTER);
        searchCard.add(fieldPanel, BorderLayout.SOUTH);

        mainCard.add(searchCard, BorderLayout.NORTH);

        JPanel resultCard = new RoundedPanel(new Color(251, 253, 254), 16);
        resultCard.setLayout(new BorderLayout(0, 12));
        resultCard.setBorder(new EmptyBorder(18, 18, 18, 18));

        JPanel resultHeader = new JPanel(new BorderLayout());
        resultHeader.setOpaque(false);

        JPanel resultTitlePanel = new JPanel();
        resultTitlePanel.setOpaque(false);
        resultTitlePanel.setLayout(new BoxLayout(resultTitlePanel, BoxLayout.Y_AXIS));

        JLabel resultTitle = new JLabel("Parcel Details");
        resultTitle.setFont(new Font("SansSerif", Font.BOLD, 17));
        resultTitle.setForeground(TEXT_DARK);

        JLabel resultSubtitle = new JLabel("Information retrieved from the parcel record");
        resultSubtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
        resultSubtitle.setForeground(TEXT_MUTED);

        resultTitlePanel.add(resultTitle);
        resultTitlePanel.add(Box.createVerticalStrut(3));
        resultTitlePanel.add(resultSubtitle);

        JLabel statusBadge = new JLabel("SEARCH");
        statusBadge.setOpaque(true);
        statusBadge.setBackground(SUCCESS_BG);
        statusBadge.setForeground(SUCCESS);
        statusBadge.setFont(new Font("SansSerif", Font.BOLD, 10));
        statusBadge.setBorder(new EmptyBorder(6, 10, 6, 10));

        resultHeader.add(resultTitlePanel, BorderLayout.CENTER);
        resultHeader.add(statusBadge, BorderLayout.EAST);

        resultCard.add(resultHeader, BorderLayout.NORTH);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFocusable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        resultArea.setForeground(TEXT_DARK);
        resultArea.setBackground(WHITE);
        resultArea.setLineWrap(false);
        resultArea.setWrapStyleWord(false);
        resultArea.setBorder(new EmptyBorder(18, 18, 18, 18));

        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultScrollPane.setBorder(
                BorderFactory.createLineBorder(BORDER, 1, true)
        );
        resultScrollPane.setBackground(WHITE);
        resultScrollPane.getViewport().setBackground(WHITE);
        resultScrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        );
        resultScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        );

        resultCard.add(resultScrollPane, BorderLayout.CENTER);

        mainCard.add(resultCard, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bottomPanel.setOpaque(false);

        JButton clearButton = createOutlineButton("Clear");
        clearButton.setPreferredSize(new Dimension(95, 38));

        bottomPanel.add(clearButton);

        mainCard.add(bottomPanel, BorderLayout.SOUTH);

        page.add(mainCard, BorderLayout.CENTER);

        add(page, BorderLayout.CENTER);

        searchButton.addActionListener(e -> searchParcel());

        parcelIdField.addActionListener(e -> searchParcel());

        clearButton.addActionListener(e -> {
            parcelIdField.setText("");
            resultArea.setText("");
            parcelIdField.requestFocus();
        });
    }

    private void styleInputField(JTextField field) {
        field.setOpaque(true);
        field.setBackground(WHITE);
        field.setForeground(TEXT_DARK);
        field.setCaretColor(BLUE);
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(INPUT_BORDER, 1, true),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)
                )
        );

        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(ORANGE, 2, true),
                                BorderFactory.createEmptyBorder(7, 11, 7, 11)
                        )
                );
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(INPUT_BORDER, 1, true),
                                BorderFactory.createEmptyBorder(8, 12, 8, 12)
                        )
                );
            }
        });
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

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(hovered ? ORANGE_HOVER : ORANGE);

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

        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(110, 40));
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private JButton createOutlineButton(String text) {
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
                g2.setStroke(new BasicStroke(1.2f));

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

        button.setFont(new Font("SansSerif", Font.BOLD, 13));
        button.setForeground(BLUE);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    private void searchParcel() {

        String parcelID = parcelIdField.getText().trim();

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

            Parcel parcel = AdminFile.searchParcel(parcelID);

            StringBuilder result = new StringBuilder();

            result.append("PARCEL INFORMATION\n\n");

            result.append("Parcel ID        : ")
                    .append(parcel.getParcelID())
                    .append("\n\n");

            result.append("Parcel Name      : ")
                    .append(parcel.getParcelName())
                    .append("\n\n");

            result.append("Receiver Address : ")
                    .append(parcel.getReciverAddress())
                    .append("\n\n");

            result.append("Receiver Phone   : ")
                    .append(parcel.getReciverPhone())
                    .append("\n\n");

            result.append("Weight           : ")
                    .append(parcel.getWeight())
                    .append(" kg\n\n");

            result.append("Sender Email     : ")
                    .append(parcel.getSenderEmail())
                    .append("\n\n");

            result.append("Sender ID        : ")
                    .append(parcel.getSenderId())
                    .append("\n\n");

            result.append("Status           : ")
                    .append(parcel.getParcelStatus())
                    .append("\n\n");

            result.append("Delivery Charge  : ")
                    .append(parcel.getDeliveryCharge())
                    .append(" BDT\n\n");

            String riderId = parcel.getRiderId();

            result.append("Rider ID         : ")
                    .append(riderId == null ? "Not Assigned" : riderId)
                    .append("\n");

            resultArea.setText(result.toString());
            resultArea.setCaretPosition(0);

        } catch (NotFoundException e) {

            resultArea.setText("");

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Parcel Not Found",
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