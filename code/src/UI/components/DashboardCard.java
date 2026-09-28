package UI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardCard extends JPanel {

    private final Color WHITE = Color.WHITE;
    private final Color BORDER = Color.decode("#E2E8F0");
    private final Color TEXT = Color.decode("#0F172A");
    private final Color MUTED = Color.decode("#64748B");

    public DashboardCard(String icon, String title, String value, String description) {

        setLayout(new BorderLayout());
        setBackground(WHITE);
        setPreferredSize(new Dimension(220, 145));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));

        JPanel top = new JPanel(new BorderLayout());
        top.setBackground(WHITE);

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
        iconLabel.setForeground(Color.decode("#2563EB"));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        titleLabel.setForeground(MUTED);

        top.add(iconLabel, BorderLayout.WEST);
        top.add(titleLabel, BorderLayout.CENTER);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        valueLabel.setForeground(TEXT);

        JLabel descriptionLabel = new JLabel(description);
        descriptionLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
        descriptionLabel.setForeground(MUTED);

        JPanel bottom = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
        bottom.setBackground(WHITE);

        bottom.add(valueLabel);
        bottom.add(Box.createVerticalStrut(3));
        bottom.add(descriptionLabel);

        add(top, BorderLayout.NORTH);
        add(bottom, BorderLayout.SOUTH);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.decode("#93C5FD")
                        ),
                        BorderFactory.createEmptyBorder(
                                18, 18, 18, 18
                        )
                ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(BORDER),
                        BorderFactory.createEmptyBorder(
                                18, 18, 18, 18
                        )
                ));
            }
        });
    }
}