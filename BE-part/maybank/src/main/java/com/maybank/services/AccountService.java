package com.maybank.services;


import com.maybank.entity.Account;
import com.maybank.entity.Customer;
import com.maybank.model.response.AccountResponse;
import com.maybank.model.response.CustomerResponse;
import com.maybank.repository.AccountRepository;
import com.maybank.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public AccountResponse createAccount(Long customerId, String accountType) {
        log.info("request received for create account with customer id:{} and account type:{}", customerId,accountType);
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if (customer == null) return null;

        Account accountDetail = new Account();
        accountDetail.setCustomer(customer);
        accountDetail.setAccountType(accountType);
        accountDetail.setBalance(0.0);
        accountDetail.setStatus("Active");
        accountRepository.save(accountDetail);
        return  buildAccountDetailResponse(accountDetail);
    }

    public AccountResponse deposit(Long accountNumber, double amount) {
        log.info("deposit account: {}",accountNumber);
        Account account = getAccount(accountNumber);
        if (account == null) return null;
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return buildAccountDetailResponse(account);
    }

    public AccountResponse withdraw(Long accountNumber, double amount) {
        log.info("withdraw account: {}",accountNumber);
        Account account = getAccount(accountNumber);
        if (account == null || account.getBalance() < amount) return null;
        account.setBalance(account.getBalance() - amount);
         accountRepository.save(account);
        return buildAccountDetailResponse(account);
    }

    public AccountResponse closeAccount(Long accountNumber) {
        log.info("closeAccount account: {}",accountNumber);
        Account account = getAccount(accountNumber);
        if (account == null) return null;
        account.setStatus("Closed");
         accountRepository.save(account);
         return buildAccountDetailResponse(account);
    }

    private AccountResponse buildAccountDetailResponse (Account accountDetail){
        log.info("building account detail response");
        return AccountResponse.builder().customerResponse(CustomerResponse.builder()
                        .name(accountDetail.getCustomer().getName())
                        .id(accountDetail.getCustomer().getId()).build())
                .accountNumber(accountDetail.getAccountNumber())
                .accountType(accountDetail.getAccountType())
                .status(accountDetail.getStatus()).balance(accountDetail.getBalance()).build();

    }
    private Account getAccount(Long accountNumber) {
        log.info("getAccount account detail: {}",accountNumber);
        Account accountDetail = accountRepository.findByAccountNumber(accountNumber);
        return accountDetail;
    }
    public  AccountResponse getAccountDetails(Long accountNumber) {
        log.info("getAccountDetails account detail: {}",accountNumber);
        Account accountDetail = getAccount(accountNumber);
        return buildAccountDetailResponse(accountDetail);
    }
}
