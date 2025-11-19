package com.parkincinema.controller;

import com.parkincinema.dao.ReportDAO;
import java.util.List;
import java.util.Map;

public class ReportController {

    private ReportDAO reportDAO = new ReportDAO();

    // SALES REPORT
    public Map<String, Object> getSalesReport(int month, int year) {
        return reportDAO.generateSalesReport(month, year);
    }

    // SLOT OCCUPANCY REPORT
    public List<Map<String, Object>> getSlotOccupancyReport(int month, int year) {
        return reportDAO.generateSlotOccupancy(month, year);
    }

    // BOOKING PATTERN REPORT
    public List<Map<String, Object>> getBookingPatterns(int month, int year) {
        return reportDAO.generateBookingPattern(month, year);
    }
}