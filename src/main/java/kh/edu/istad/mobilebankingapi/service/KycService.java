package kh.edu.istad.mobilebankingapi.service;

import kh.edu.istad.mobilebankingapi.dto.KycResponse;

public interface KycService {
    KycResponse verifyKyc(Integer customerId);
}

