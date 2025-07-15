package kh.edu.istad.mobilebankingapi.controller;


import jakarta.validation.Valid;
import kh.edu.istad.mobilebankingapi.dto.CreateCustomerRequest;
import kh.edu.istad.mobilebankingapi.dto.UpdateCustomer;
import kh.edu.istad.mobilebankingapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{phoneNumber}")
    public void disableByPhoneNumber(@PathVariable String phoneNumber) {
        customerService.disableByPhoneNumber(phoneNumber);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{phoneNumber}")
    public void deleteByPhoneNumber(
            @PathVariable String phoneNumber
    ){

    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        return new ResponseEntity<>(Map.of(
                "customers", customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("{email}")
    public ResponseEntity<?> getCustomerByEmail(@PathVariable String email) {
        return new ResponseEntity<>(customerService.getCustomerByEmail(email), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer (@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        return new ResponseEntity<>(customerService.createCustomer(createCustomerRequest)
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCustomerById (@PathVariable Integer id, @RequestBody UpdateCustomer updateCustomerRequest) {
        return new ResponseEntity<>(customerService.updateCustomerById(id, updateCustomerRequest), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCustomerById (@PathVariable Integer id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(
                Map.of(
                        "message", "Customer deleted successfully"
                ),
                HttpStatus.OK);
    }
}