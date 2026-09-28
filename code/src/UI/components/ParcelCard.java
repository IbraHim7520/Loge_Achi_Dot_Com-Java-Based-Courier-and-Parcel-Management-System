package UI.components;

import javax.swing.*;
import java.awt.*;

public class ParcelCard extends JPanel {

    private final Color WHITE = Color.WHITE;
    private final Color BORDER = Color.decode("#E2E8F0");
    private final Color TEXT = Color.decode("#0F172A");
    private final Color MUTED = Color.decode("#64748B");

    public ParcelCard(
            String parcelName,
            String parcelId,
            String receiver,
            String weight,
            String charge,
            String status
    ) {

        setLayout(new BorderLayout(15, 0));
        setBackground(WHITE);

        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(
                        16, 18, 16, 18
                )
        ));

        JPanel left = new JPanel();
        left.setLayout(new BoxLayout(
                left,
                BoxLayout.Y_AXIS
        ));
        left.setBackground(WHITE);

        JLabel nameLabel = new JLabel(parcelName);

        nameLabel.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                16
        ));

        nameLabel.setForeground(TEXT);

        JLabel idLabel = new JLabel(
                "Parcel ID: " + parcelId
        );

        idLabel.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                11
        ));

        idLabel.setForeground(MUTED);

        left.add(nameLabel);
        left.add(Box.createVerticalStrut(5));
        left.add(idLabel);

        JPanel center = new JPanel();

        center.setLayout(new GridLayout(
                2, 2, 20, 5
        ));

        center.setBackground(WHITE);

        center.add(createInfo(
                "Receiver",
                receiver
        ));

        center.add(createInfo(
                "Weight",
                weight + " kg"
        ));

        center.add(createInfo(
                "Charge",
                "৳" + charge
        ));

        center.add(createInfo(
                "Delivery",
                "Parcel"
        ));

        StatusBadge statusBadge = new StatusBadge(
                status
        );

        JPanel right = new JPanel(
                new GridBagLayout()
        );

        right.setBackground(WHITE);
        right.add(statusBadge);

        add(left, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);
        add(right, BorderLayout.EAST);
    }

    private JPanel createInfo(
            String title,
            String value
    ) {

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(
                panel,
                BoxLayout.Y_AXIS
        ));

        panel.setBackground(WHITE);

        JLabel titleLabel = new JLabel(title);

        titleLabel.setFont(new Font(
                "SansSerif",
                Font.PLAIN,
                10
        ));

        titleLabel.setForeground(MUTED);

        JLabel valueLabel = new JLabel(value);

        valueLabel.setFont(new Font(
                "SansSerif",
                Font.BOLD,
                12
        ));

        valueLabel.setForeground(TEXT);

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(2));
        panel.add(valueLabel);

        return panel;
    }
}