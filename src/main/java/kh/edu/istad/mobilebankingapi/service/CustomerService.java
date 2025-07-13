package kh.edu.istad.mobilebankingapi.service;

import kh.edu.istad.mobilebankingapi.dto.CreateCustomerRequest;
import kh.edu.istad.mobilebankingapi.dto.CustomerResponse;
import kh.edu.istad.mobilebankingapi.dto.UpdateCustomer;

import java.util.List;

public interface CustomerService {
    List<CustomerResponse> getAllCustomers();
    CustomerResponse getCustomerByEmail(String email);
    CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest);
    CustomerResponse updateCustomerById(Integer id, UpdateCustomer updateCustomer);
    void deleteCustomerById(Integer id);
    CustomerResponse fineCustomerByPhoneNumber(String phoneNumber);
}
