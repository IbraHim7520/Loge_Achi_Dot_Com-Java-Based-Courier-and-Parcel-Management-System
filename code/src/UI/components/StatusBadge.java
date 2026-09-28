package UI.components;

import javax.swing.*;
import java.awt.*;

public class StatusBadge extends JLabel {

    public StatusBadge(String status) {

        super(status);

        setFont(new Font(
                "SansSerif",
                Font.BOLD,
                11
        ));

        setHorizontalAlignment(
                SwingConstants.CENTER
        );

        setOpaque(true);

        setBorder(
                BorderFactory.createEmptyBorder(
                        5, 10, 5, 10
                )
        );

        setStatusColor(status);
    }

    private void setStatusColor(String status) {

        if (status == null) {
            setBackground(Color.decode("#E2E8F0"));
            setForeground(Color.decode("#475569"));
            return;
        }

        switch (status.toUpperCase()) {

            case "PENDING":
                setBackground(Color.decode("#FEF3C7"));
                setForeground(Color.decode("#92400E"));
                break;

            case "ACCEPTED":
                setBackground(Color.decode("#DBEAFE"));
                setForeground(Color.decode("#1D4ED8"));
                break;

            case "ON_TRANSIT":
                setBackground(Color.decode("#CFFAFE"));
                setForeground(Color.decode("#0E7490"));
                break;

            case "REACHED_DESTINATION":
                setBackground(Color.decode("#E0E7FF"));
                setForeground(Color.decode("#4338CA"));
                break;

            case "DELIVERED":
                setBackground(Color.decode("#DCFCE7"));
                setForeground(Color.decode("#166534"));
                break;

            case "CANCELED":
                setBackground(Color.decode("#FEE2E2"));
                setForeground(Color.decode("#991B1B"));
                break;

            default:
                setBackground(Color.decode("#E2E8F0"));
                setForeground(Color.decode("#475569"));
        }
    }
}