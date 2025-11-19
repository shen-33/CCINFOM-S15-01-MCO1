package com.parkincinema.dao;

import com.parkincinema.dbconnection.DBConnection;
import com.parkincinema.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class CustomerDAO {

    // Insert a new customer and return ID (CREATE)
    public int addCustomer(Customer customer) {
        String sqlPrompt = "INSERT INTO Customer (last_name, first_name, phoneNo, car_plate) VALUES (?, ?, ?, ?)";
        int generatedId = -1; //  set default value as -1

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(sqlPrompt, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, customer.getLastName());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getPhoneNo());
            statement.setString(4, customer.getCarPlate());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // closing result
            try {
                if (generatedKeys != null)
                    generatedKeys.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // closing statement
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // closing connection
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return generatedId;
    }

    // Update customer details
    public boolean updateCustomer(Customer customer) {
        String sql = "UPDATE Customer SET last_name = ?, first_name = ?, phoneNo = ?, car_plate = ? WHERE customer_id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getLastName());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getPhoneNo());
            stmt.setString(4, customer.getCarPlate());
            stmt.setInt(5, customer.getCustomerId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete existing customers
    public boolean deleteCustomer(int id) {
        String sql = "DELETE FROM Customer WHERE customer_id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM Customer";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Customer c = new Customer();
                c.setCustomerId(rs.getInt("customer_id"));
                c.setLastName(rs.getString("last_name"));
                c.setFirstName(rs.getString("first_name"));
                c.setPhoneNo(rs.getString("phoneNo"));
                c.setCarPlate(rs.getString("car_plate"));
                list.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Find customer by ID (READ)
    public Customer getCustomerById(int id) {
        Customer customer = null;
        String sqlPrompt = "SELECT * FROM Customer WHERE customer_id = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(sqlPrompt);
            statement.setInt(1, id);

            result = statement.executeQuery();

            if (result.next()) {
                customer = new Customer();
                customer.setCustomerId(result.getInt("customer_id"));
                customer.setLastName(result.getString("last_name"));
                customer.setFirstName(result.getString("first_name"));
                customer.setPhoneNo(result.getString("phoneNo"));
                customer.setCarPlate(result.getString("car_plate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing resources
            try {
                if (result != null)
                    result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return customer;
    }
}