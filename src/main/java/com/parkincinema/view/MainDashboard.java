package com.parkincinema.view;

import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {

    public MainDashboard() {
        setTitle("Park-In Cinema - Main Dashboard");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton bookingBtn = new JButton("Book a Ticket");
        JButton moviesBtn = new JButton("Manage Movies");
        JButton reportsBtn = new JButton("Reports");
        JButton exitBtn = new JButton("Exit");

        bookingBtn.addActionListener(e -> new BookingWindow());
        moviesBtn.addActionListener(e -> new ManageMoviesWindow());
        reportsBtn.addActionListener(e -> new ReportWindow());
        exitBtn.addActionListener(e -> System.exit(0));

        panel.add(bookingBtn);
        panel.add(moviesBtn);
        panel.add(reportsBtn);
        panel.add(exitBtn);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainDashboard();
    }
}
