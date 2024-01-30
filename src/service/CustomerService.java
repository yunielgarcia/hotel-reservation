package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    Map<String, Customer> customersList = new HashMap<>();

    private final static CustomerService reference = new CustomerService();

    private CustomerService() {}

    public void addCustomer(String email, String first, String last) {
        Customer c = new Customer(first, last, email);
        this.customersList.put(email, c);
    }

    public Customer getCustomer(String email) {
        return this.customersList.get(email);
    }

    public Collection<Customer> getAllCustomers() {
        return null;
    }

    public static CustomerService getInstance() {
        return CustomerService.reference;
    }
}
