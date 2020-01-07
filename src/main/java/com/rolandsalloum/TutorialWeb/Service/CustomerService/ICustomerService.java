package com.rolandsalloum.TutorialWeb.Service.CustomerService;

import com.rolandsalloum.TutorialWeb.Models.Customer;

import java.util.List;

public interface ICustomerService {
    Customer createCustomer(Customer customerFromRequest) throws FailedToCreateCustomer;

    int deleteCustomerById(int customerId) throws FailedToDeleteCustomer;

    

    Customer updateCustomer(Customer customer, int customerId) throws FailedToUpdatedCustomer;

    List<Customer> findAllCustomers() throws FailedToFindCustomer;

    Customer findCustomerById(int instructorId) throws FailedToFindCustomer;
}
