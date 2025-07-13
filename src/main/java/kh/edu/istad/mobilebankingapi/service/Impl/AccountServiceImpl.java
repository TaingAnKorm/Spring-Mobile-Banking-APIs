package kh.edu.istad.mobilebankingapi.service.Impl;

import kh.edu.istad.mobilebankingapi.domain.Account;
import kh.edu.istad.mobilebankingapi.domain.AccountType;
import kh.edu.istad.mobilebankingapi.domain.Customer;
import kh.edu.istad.mobilebankingapi.dto.AccountResponse;
import kh.edu.istad.mobilebankingapi.dto.CreateAccountRequest;
import kh.edu.istad.mobilebankingapi.dto.UpdateAccountRequest;
import kh.edu.istad.mobilebankingapi.mapper.AccountMapper;
import kh.edu.istad.mobilebankingapi.repository.AccountRepository;
import kh.edu.istad.mobilebankingapi.repository.AccountTypeRepository;
import kh.edu.istad.mobilebankingapi.repository.CustomerRepository;
import kh.edu.istad.mobilebankingapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse createNewAccount(CreateAccountRequest createAccountRequest) {
        if (accountRepository.existsByActNo(createAccountRequest.actNo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Account number already exists");
        }
        Customer customer = customerRepository.findById(createAccountRequest.customerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Customer with id " + createAccountRequest.customerId() + " does not exist"));
        AccountType accountType = accountTypeRepository.findById(createAccountRequest.accountTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account type with id " + createAccountRequest.accountTypeId() + " does not exist"));
        Account account = new Account();
        account.setActNo(createAccountRequest.actNo());
        account.setActCurrency(createAccountRequest.actCurrency());
        account.setBalance(createAccountRequest.balance());
        account.setCustomer(customer);
        account.setAccountType(accountType);
        account.setIsDeleted(false);
        account = accountRepository.save(account);
        return accountMapper.mapFromAccountToAccountResponse(account);
    }

    @Override
    public List<AccountResponse> findAllAccounts() {
        return accountRepository.findAll()
                .stream()
                .map(accountMapper::mapFromAccountToAccountResponse)
                .toList();
    }

    @Override
    public AccountResponse findAccountByActNo(String actNo) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account with number " + actNo + " does not exist"));
        return accountMapper.mapFromAccountToAccountResponse(account);
    }

    @Override
    public List<AccountResponse> findAccountsByCustomerId(Integer customerId) {
        if (!customerRepository.existsById(customerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Customer with id " + customerId + " does not exist");
        }
        return accountRepository.findAllByCustomer_Id(customerId)
                .stream()
                .map(accountMapper::mapFromAccountToAccountResponse)
                .toList();
    }

    @Override
    public AccountResponse updateAccountByActNo(String actNo, UpdateAccountRequest updateAccountRequest) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account with number " + actNo + " does not exist"));
        AccountType accountType = accountTypeRepository.findById(updateAccountRequest.accountTypeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account type with id " + updateAccountRequest.accountTypeId() + " does not exist"));
        account.setActCurrency(updateAccountRequest.actCurrency());
        account.setBalance(updateAccountRequest.balance());
        account.setAccountType(accountType);
        account = accountRepository.save(account);
        return accountMapper.mapFromAccountToAccountResponse(account);
    }

    @Override
    public void deleteAccountByActNo(String actNo) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account with number " + actNo + " does not exist"));
        accountRepository.delete(account);
    }

    @Override
    public AccountResponse disableAccountByActNo(String actNo) {
        Account account = accountRepository.findByActNo(actNo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Account with number " + actNo + " does not exist"));
        account.setIsDeleted(true);
        account = accountRepository.save(account);
        return accountMapper.mapFromAccountToAccountResponse(account);
    }
}
