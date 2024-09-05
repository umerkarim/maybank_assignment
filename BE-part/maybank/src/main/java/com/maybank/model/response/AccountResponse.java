package com.maybank.model.response;

import lombok.Builder;

import java.util.List;


@Builder
public class AccountResponse {

    private Long accountNumber;
    private CustomerResponse customerResponse;
    private String accountType;
    private double balance;
    private String status;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public CustomerResponse getCustomer() {
        return customerResponse;
    }

    public void setCustomer(CustomerResponse customerResponse) {
        this.customerResponse = customerResponse;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
// Getters and setters
}
