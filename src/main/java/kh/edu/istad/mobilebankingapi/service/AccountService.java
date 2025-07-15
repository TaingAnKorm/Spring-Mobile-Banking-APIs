package kh.edu.istad.mobilebankingapi.service;
import kh.edu.istad.mobilebankingapi.dto.AccountResponse;
import kh.edu.istad.mobilebankingapi.dto.CreateAccountRequest;
import kh.edu.istad.mobilebankingapi.dto.UpdateAccountRequest;

import java.util.List;

public interface AccountService {
    AccountResponse createNewAccount(CreateAccountRequest createAccountRequest);
    List<AccountResponse> findAllAccounts();
    AccountResponse findAccountByActNo(String actNo);
    List<AccountResponse> findAccountsByCustomerId(Integer customerId);
    AccountResponse updateAccountByActNo(String actNo, UpdateAccountRequest updateAccountRequest);
    void deleteAccountByActNo(String actNo);
    AccountResponse disableAccountByActNo(String actNo);
}
