package com.rolandsalloum.TutorialWeb.Controller.CustomerController;

import com.rolandsalloum.TutorialWeb.Models.Customer;
import com.rolandsalloum.TutorialWeb.Service.CustomerService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/home")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity findAllCustomers(){
        try {
            List<Customer> customerList = customerService.findAllCustomers();
            List<CustomerApiResponse> responseList = buildResponseForCustomers(customerList);
            return  ResponseEntity.status(HttpStatus.OK).body(responseList);
        } catch (FailedToFindCustomer failedToFindCustomers) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(failedToFindCustomers.getLocalizedMessage());
        }
    }




    @GetMapping("/customers/{customerId}")
    public ResponseEntity findCustomerById(@PathVariable("customerId") int instructorId){
        try {
            Customer customer = customerService.findCustomerById(instructorId);
            CustomerApiResponse responseList = getCustomerApiResponse(customer);
            return  ResponseEntity.status(HttpStatus.OK).body(responseList);
        } catch (FailedToFindCustomer failedToFindCustomers) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(failedToFindCustomers.getLocalizedMessage());
        }

    }


    @PostMapping("/customers")
    public ResponseEntity createCustomer(@RequestBody CustomerApiRequest request) {
        try {
            Customer customer = customerService.createCustomer(getCustomerFromRequest(request));
            CustomerApiResponse customerApiResponse = getCustomerApiResponse(customer);
            return ResponseEntity.status(HttpStatus.OK).body(customerApiResponse);
        } catch (FailedToCreateCustomer failedToCreateCustomer) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedToCreateCustomer.getLocalizedMessage());
        }

    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity deleteCustomerById(@PathVariable("customerId") int customerId) {
        try {
            int customerIdDeleted = customerService.deleteCustomerById(customerId);
            return ResponseEntity.status(HttpStatus.OK).body(customerIdDeleted);
        } catch (FailedToDeleteCustomer failedToDeleteCustomer) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedToDeleteCustomer.getLocalizedMessage());
        }

    }


    @PutMapping("/customers/{customerId}")
    public ResponseEntity updateCustomerById(@PathVariable("customerId") int customerId, @RequestBody CustomerApiRequest request) {
        try {
            Customer customer = customerService.updateCustomer(getCustomerFromRequest(request), customerId);
            CustomerApiResponse response = getCustomerApiResponse(customer);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (FailedToUpdatedCustomer failedToUpdatedCustomer) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failedToUpdatedCustomer.getLocalizedMessage());
        }
    }

    private List<CustomerApiResponse> buildResponseForCustomers(List<Customer> customerList) {
        List<CustomerApiResponse> responseList = new ArrayList<>();
        for(Customer customer: customerList)
            responseList.add(getCustomerApiResponse(customer));
        return  responseList;
    }

    private CustomerApiResponse getCustomerApiResponse(Customer customer) {
        return new CustomerApiResponse().builder()
                .customerId(customer.getCustomerId())
                .CustomerFname(customer.getCustomerFname())
                .CustomerLname(customer.getCustomerLname())
                .customerUsername(customer.getCustomerUsername())
                .customerPassword(customer.getCustomerPassword())
                .customerAddress(customer.getCustomerAddress())
                .customerGender(customer.getCustomerGender())
                .build();
    }

    private Customer getCustomerFromRequest(CustomerApiRequest request) {
        return new Customer().builder()
                .CustomerFname(request.getCustomerFname())
                .CustomerLname(request.getCustomerLname())
                .customerUsername(request.getCustomerUsername())
                .customerPassword(request.getCustomerPassword())
                .customerAddress(request.getCustomerAddress())
                .customerGender(request.getCustomerGender())
                .build();
    }


}
