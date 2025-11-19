package com.parkincinema.view;

import com.parkincinema.controller.ReportController;
import javax.swing.*;
import java.awt.*;

public class ReportWindow extends JFrame {

    private JTextField monthField, yearField;
    private JTextArea output;

    public ReportWindow() {
        setTitle("Reports");
        setSize(600, 500);
        setLocationRelativeTo(null);

        JPanel top = new JPanel(new GridLayout(2, 2, 5, 5));
        monthField = new JTextField();
        yearField = new JTextField();

        top.add(new JLabel("Month:"));
        top.add(monthField);
        top.add(new JLabel("Year:"));
        top.add(yearField);

        JButton salesBtn = new JButton("Sales Report");
        JButton slotBtn = new JButton("Slot Occupancy Report");
        JButton patternsBtn = new JButton("Booking Patterns");
        JButton backBtn = new JButton("Back");

        JPanel buttons = new JPanel(new GridLayout(1, 4, 5, 5));
        buttons.add(salesBtn);
        buttons.add(slotBtn);
        buttons.add(patternsBtn);
        buttons.add(backBtn);

        output = new JTextArea();
        output.setEditable(false);
        JScrollPane scroll = new JScrollPane(output);

        salesBtn.addActionListener(e -> output.setText("Sales Report (placeholder)"));
        slotBtn.addActionListener(e -> output.setText("Slot Occupancy Report (placeholder)"));
        patternsBtn.addActionListener(e -> output.setText("Booking Patterns Report (placeholder)"));
        backBtn.addActionListener(e -> dispose());

        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(buttons, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        setVisible(true);
    }
}
