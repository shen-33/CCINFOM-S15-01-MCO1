package com.parkincinema.view;

import com.parkincinema.controller.MovieController;
import java.time.LocalDateTime;
import javax.swing.*;
import java.awt.*;

public class ManageMoviesWindow extends JFrame {

    private JTextField idField, titleField, priceField, genreField, ratingField, durationField, showDateField, timeSlotField;

    public ManageMoviesWindow() {
        setTitle("Manage Movies");
        setSize(500, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(12, 2, 8, 8));

        idField = new JTextField();
        titleField = new JTextField();
        priceField = new JTextField();
        genreField = new JTextField();
        ratingField = new JTextField();
        durationField = new JTextField();
        showDateField = new JTextField("YYYY-MM-DDTHH:MM");
        timeSlotField = new JTextField("YYYY-MM-DDTHH:MM");

        JButton addBtn = new JButton("Add Movie");
        JButton updateBtn = new JButton("Update Movie");
        JButton deleteBtn = new JButton("Delete Movie");
        JButton backBtn = new JButton("Back");

        panel.add(new JLabel("Movie ID:"));
        panel.add(idField);

        panel.add(new JLabel("Title:"));
        panel.add(titleField);

        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        panel.add(new JLabel("Genre:"));
        panel.add(genreField);

        panel.add(new JLabel("Rating:"));
        panel.add(ratingField);

        panel.add(new JLabel("Duration (min):"));
        panel.add(durationField);

        panel.add(new JLabel("Show Date:"));
        panel.add(showDateField);

        panel.add(new JLabel("Time Slot:"));
        panel.add(timeSlotField);

        panel.add(addBtn);
        panel.add(updateBtn);
        panel.add(deleteBtn);
        panel.add(backBtn);

        addBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Movie added"));
        updateBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Movie updated"));
        deleteBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Movie deleted"));
        backBtn.addActionListener(e -> dispose());

        add(panel);
        setVisible(true);
    }
}
