package kh.edu.istad.mobilebankingapi.service.Impl;

import jakarta.transaction.Transactional;
import kh.edu.istad.mobilebankingapi.domain.Customer;
import kh.edu.istad.mobilebankingapi.domain.CustomerSegment;
import kh.edu.istad.mobilebankingapi.domain.KYC;
import kh.edu.istad.mobilebankingapi.dto.CreateCustomerRequest;
import kh.edu.istad.mobilebankingapi.dto.CustomerResponse;
import kh.edu.istad.mobilebankingapi.dto.UpdateCustomer;
import kh.edu.istad.mobilebankingapi.mapper.CustomerMapper;
import kh.edu.istad.mobilebankingapi.repository.CustomerRepository;
import kh.edu.istad.mobilebankingapi.repository.CustomerSegmentRepository;
import kh.edu.istad.mobilebankingapi.repository.KYCRepository;
import kh.edu.istad.mobilebankingapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerSegmentRepository customerSegmentRepository;
    private final KYCRepository kycRepository;

    @Transactional
    @Override
    public void disableByPhoneNumber(String phoneNumber) {
        if (!customerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer phone number not found");
        }
        customerRepository.disableByPhoneNumber(phoneNumber);
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customerMapper::mapFromCustomerToCustomerResponse)
                .toList();
    }

    @Override
    public CustomerResponse getCustomerByEmail(String email) {
        if(!customerRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Customer with email " + email + " does not exist");
        }
        Customer customer = customerRepository.findByEmail(email);
        return customerMapper.mapFromCustomerToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        if (customerRepository.existsByEmail(createCustomerRequest.email())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Customer's email already exists!"
            );
        }

        if (customerRepository.existsByPhoneNumber(createCustomerRequest.phoneNumber())){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Customer's phone number already exists!") ;
        }

        Customer customer = new Customer();
        customer.setFullName(createCustomerRequest.fullName());
        customer.setEmail(createCustomerRequest.email());
        customer.setGender(createCustomerRequest.gender());
        customer.setPhoneNumber(createCustomerRequest.phoneNumber());
        customer.setRemark(createCustomerRequest.remark());
        customer.setNationalCardId(createCustomerRequest.nationalCardId());

        CustomerSegment segment = null;
        if (createCustomerRequest.customerSegmentId() != null) {
            segment = customerSegmentRepository.findById(createCustomerRequest.customerSegmentId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Segment with id " + createCustomerRequest.customerSegmentId() + " does not exist"));
            customer.setCustomerSegment(segment);
        }

        customer.setIsDeleted(false);
        customer = customerRepository.save(customer);

        KYC kyc = new KYC();
        kyc.setCustomer(customer);
        kyc.setNationalCardId(customer.getNationalCardId());
        kyc.setIsVerified(false);
        kyc.setIsDeleted(false);
        kycRepository.save(kyc);

        return customerMapper.mapFromCustomerToCustomerResponse(customer);
    }

    @Override
    public CustomerResponse updateCustomerById(Integer id, UpdateCustomer updateCustomer) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer with id " + id + " does not exist"));
        customer.setFullName(updateCustomer.fullName());
        customer.setEmail(updateCustomer.email());
        customer.setPhoneNumber(updateCustomer.phoneNumber());
        customer.setRemark(updateCustomer.remark());
        customer.setNationalCardId(updateCustomer.nationalCardId());
        if (updateCustomer.customerSegmentId() != null) {
            CustomerSegment segment = customerSegmentRepository.findById(updateCustomer.customerSegmentId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Segment with id " + updateCustomer.customerSegmentId() + " does not exist"));
            customer.setCustomerSegment(segment);
        }
        customer = customerRepository.save(customer);
        return customerMapper.mapFromCustomerToCustomerResponse(customer);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        if(!customerRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Customer with id " + id + " does not exist");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerResponse fineCustomerByPhoneNumber(String phoneNumber) {
        return null;
    }
}