package com.maybank.controllers;


import com.maybank.model.request.AccountRequest;
import com.maybank.model.response.AccountResponse;
import com.maybank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public AccountResponse createAccount(@RequestBody AccountRequest request) {
        return accountService.createAccount(request.getCustomerId(), request.getAccountType());
    }


    @PostMapping("/deposit")
    public AccountResponse deposit(@RequestBody AccountRequest request) {
        return accountService.deposit(request.getAccountNumber(), request.getAmount());
    }

    @PostMapping("/withdraw")
    public AccountResponse withdraw(@RequestBody AccountRequest request) {
        return accountService.withdraw(request.getAccountNumber(), request.getAmount());
    }

    @PostMapping("/close")
    public AccountResponse closeAccount(@RequestBody AccountRequest request) {
        return accountService.closeAccount(request.getAccountNumber());
    }

    @PostMapping("/get-account-detail")
    public AccountResponse getAccount(@RequestBody AccountRequest request) {
        return accountService.getAccountDetails(request.getAccountNumber());
    }
}
