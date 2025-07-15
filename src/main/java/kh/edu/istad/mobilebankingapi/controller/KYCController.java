package kh.edu.istad.mobilebankingapi.controller;

import kh.edu.istad.mobilebankingapi.domain.KYC;
import kh.edu.istad.mobilebankingapi.service.KYCService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kycs")
public class KYCController {
    private final KYCService kycService;

    @PutMapping("/{customerId}/verify")
    public ResponseEntity<KYC> verifyCustomerKyc(@PathVariable Integer customerId) {
        KYC kyc = kycService.verifyCustomerKyc(customerId);
        return new ResponseEntity<>(kyc, HttpStatus.OK);
    }
}
