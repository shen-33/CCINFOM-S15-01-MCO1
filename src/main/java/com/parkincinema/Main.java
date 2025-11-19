package com.parkincinema;

import com.parkincinema.view.*;
import com.parkincinema.view.MainDashboard;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainDashboard dashboard = new MainDashboard();
            dashboard.setVisible(true);
        });
    }
}