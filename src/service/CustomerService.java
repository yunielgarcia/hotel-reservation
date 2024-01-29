package service;

import model.Customer;

import java.util.Collection;

public class CustomerService {
    private final static CustomerService reference = new CustomerService();


    public void addCustomer(String email, String first, String last) {

    }
    public Customer getCustomer(String email) {
        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return null;
    }

    public static CustomerService getInstance() {
        return CustomerService.reference;
    }
}
