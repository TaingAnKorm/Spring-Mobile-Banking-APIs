package kh.edu.istad.mobilebankingapi.controller;

import jakarta.validation.Valid;
import kh.edu.istad.mobilebankingapi.dto.CreateAccountRequest;
import kh.edu.istad.mobilebankingapi.dto.UpdateAccountRequest;
import kh.edu.istad.mobilebankingapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<?> findAllAccounts() {
        return new ResponseEntity<>(Map.of(
                "accounts", accountService.findAllAccounts()), HttpStatus.OK);
    }

    @GetMapping("/{actNo}")
    public ResponseEntity<?> findAccountByActNo(@PathVariable String actNo) {
        return new ResponseEntity<>(accountService.findAccountByActNo(actNo), HttpStatus.OK);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> findAccountsByCustomer(@PathVariable Integer customerId) {
        return new ResponseEntity<>(Map.of(
                "accounts", accountService.findAccountsByCustomerId(customerId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createNewAccount(@Valid @RequestBody CreateAccountRequest request) {
        return new ResponseEntity<>(accountService.createNewAccount(request), HttpStatus.CREATED);
    }

    @PutMapping("/{actNo}")
    public ResponseEntity<?> updateAccountByActNo(@PathVariable String actNo,
                                                  @Valid @RequestBody UpdateAccountRequest request) {
        return new ResponseEntity<>(accountService.updateAccountByActNo(actNo, request), HttpStatus.OK);
    }

    @PatchMapping("/{actNo}/disable")
    public ResponseEntity<?> disableAccountByActNo(@PathVariable String actNo) {
        return new ResponseEntity<>(accountService.disableAccountByActNo(actNo), HttpStatus.OK);
    }

    @DeleteMapping("/{actNo}")
    public ResponseEntity<?> deleteAccountByActNo(@PathVariable String actNo) {
        accountService.deleteAccountByActNo(actNo);
        return new ResponseEntity<>(Map.of(
                "message", "Account deleted successfully"), HttpStatus.OK);
    }
}
