package kh.edu.istad.mobilebankingapi.service;

import kh.edu.istad.mobilebankingapi.domain.KYC;

public interface KYCService {
    KYC verifyCustomerKyc(Integer customerId);
}