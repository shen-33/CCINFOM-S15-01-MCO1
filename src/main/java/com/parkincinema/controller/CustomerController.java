package com.parkincinema.controller;

import com.parkincinema.dao.CustomerDAO;
import com.parkincinema.model.Customer;

import java.util.List;

public class CustomerController {

    private CustomerDAO customerDAO = new CustomerDAO();

    // Creating a new customer
    public int addCustomer(String lastName, String firstName, String phone, String carPlate) {
        Customer c = new Customer();
        c.setLastName(lastName);
        c.setFirstName(firstName);
        c.setPhoneNo(phone);
        c.setCarPlate(carPlate);

        return customerDAO.addCustomer(c);
    }

    public Customer getCustomer(int id) {
        return customerDAO.getCustomerById(id);
    }

    public boolean updateCustomer(int id, String lastName, String firstName, String phone, String carPlate) {
        Customer existing = customerDAO.getCustomerById(id);
        if (existing == null) return false;

        existing.setLastName(lastName);
        existing.setFirstName(firstName);
        existing.setPhoneNo(phone);
        existing.setCarPlate(carPlate);

        customerDAO.updateCustomer(existing);
        return true;
    }

    public boolean deleteCustomer(int id) {
        return customerDAO.deleteCustomer(id);
    }

    // LIST (requires DAO function)
    public List<Customer> listCustomers() {
        return customerDAO.getAllCustomers();
    }
}
