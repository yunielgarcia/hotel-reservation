package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    private final Map<String, Customer> customersList = new HashMap<>();

    private final static CustomerService reference = new CustomerService();

    private CustomerService() {}

    public void addCustomer(String email, String first, String last) {
        Customer c = new Customer(first, last, email);
        this.customersList.put(email, c);
    }

    public Customer getCustomer(String email) {
        return this.customersList.getOrDefault(email, null);
    }

    public Collection<Customer> getAllCustomers() {
        return customersList.values();
    }

    public static CustomerService getInstance() {
        return CustomerService.reference;
    }
}
