package kh.edu.istad.mobilebankingapi.service.Impl;

import kh.edu.istad.mobilebankingapi.domain.KYC;
import kh.edu.istad.mobilebankingapi.repository.KYCRepository;
import kh.edu.istad.mobilebankingapi.service.KYCService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class KYCServiceImpl implements KYCService {
    private final KYCRepository kycRepository;

    @Override
    public KYC verifyCustomerKyc(Integer customerId) {
        KYC kyc = kycRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "KYC for customer id " + customerId + " does not exist"));
        kyc.setIsVerified(true);
        return kycRepository.save(kyc);
    }
}
