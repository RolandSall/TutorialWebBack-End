package com.rolandsalloum.TutorialWeb.Service.CustomerService;

import com.rolandsalloum.TutorialWeb.Models.Customer;
import com.rolandsalloum.TutorialWeb.Repository.CustomerRepository.CustomerRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private CustomerRepositoryDAO customerRepositoryDAO;

    @Autowired
    public CustomerService(CustomerRepositoryDAO customerRepositoryDAO) {
        this.customerRepositoryDAO = customerRepositoryDAO;
    }


    @Override
    public Customer createCustomer(Customer customer) throws FailedToCreateCustomer {
        try {
            return customerRepositoryDAO.save(customer);
        } catch (Exception e) {
            throw new FailedToCreateCustomer("Failed To Create Customer ! ");

        }
    }

    @Override
    public int deleteCustomerById(int customerId) throws FailedToDeleteCustomer {
        try {
            customerRepositoryDAO.deleteById(customerId);
            return customerId;
        } catch (Exception e) {
            throw new FailedToDeleteCustomer("Failed To Delete Customer !");
        }
    }

    @Override
    public Customer updateCustomer(Customer customer, int customerId) throws FailedToUpdatedCustomer {
        try {
            Customer customerToBeUpdated = customerRepositoryDAO.getOne(customerId);
            customerToBeUpdated.setCustomerFname(customer.getCustomerFname());
            customerToBeUpdated.setCustomerLname(customer.getCustomerLname());
            customerToBeUpdated.setCustomerUsername(customer.getCustomerUsername());
            customerToBeUpdated.setCustomerPassword(customer.getCustomerPassword());
            customerToBeUpdated.setCustomerAddress(customer.getCustomerAddress());
            customerToBeUpdated.setCustomerGender(customer.getCustomerGender());
            customerToBeUpdated.setCustomerId(customerId);
            customerRepositoryDAO.save(customerToBeUpdated);
            return customerToBeUpdated;
        } catch (Exception e) {
            throw new FailedToUpdatedCustomer("Failed To Update Customer!");
        }


    }

    @Override
    public List<Customer> findAllCustomers() throws FailedToFindCustomer {
        try {
            List<Customer> customerList = customerRepositoryDAO.findAll();

            return customerList;
        } catch (Exception e) {
            throw new FailedToFindCustomer("Customer Not Found !");
        }
    }

    @Override
    public Customer findCustomerById(int instructorId) throws FailedToFindCustomer {
        try {
            Customer customer = customerRepositoryDAO.getOne(instructorId);

            if (customer.equals(null))
                throw new FailedToFindCustomer("Customer Not Found  !");
            return customer;
        } catch (Exception e) {
            throw new FailedToFindCustomer("Customer Not Found !");
        }
    }
}




