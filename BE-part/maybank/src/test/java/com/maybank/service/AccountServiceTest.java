package com.maybank.service;

import com.maybank.entity.Account;
import com.maybank.entity.Customer;
import com.maybank.model.response.AccountResponse;

import com.maybank.repository.AccountRepository;
import com.maybank.repository.CustomerRepository;
import com.maybank.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;


    @InjectMocks
    private AccountService accountService;

    private Customer customer;
    private Account account;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");

        account = new Account();
        account.setAccountNumber(12345L);
        account.setAccountType("Savings");
        account.setBalance(1000.0);
        account.setStatus("Active");
        account.setCustomer(customer);

    }

    @Test
    void testDeposit_Success() {
        when(accountRepository.findByAccountNumber(12345L)).thenReturn(account);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountResponse response = accountService.deposit(12345L, 500.0);

        assertNotNull(response);
        assertEquals(1500.0, response.getBalance());

        verify(accountRepository, times(1)).findByAccountNumber(12345L);
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testDeposit_AccountNotFound() {
        when(accountRepository.findByAccountNumber(12345L)).thenReturn(null);

        AccountResponse response = accountService.deposit(12345L, 500.0);

        assertNull(response);

        verify(accountRepository, times(1)).findByAccountNumber(12345L);
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void testWithdraw_Success() {
        when(accountRepository.findByAccountNumber(12345L)).thenReturn(account);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountResponse response = accountService.withdraw(12345L, 500.0);

        assertNotNull(response);
        assertEquals(500.0, response.getBalance());

        verify(accountRepository, times(1)).findByAccountNumber(12345L);
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testWithdraw_InsufficientBalance() {
        when(accountRepository.findByAccountNumber(12345L)).thenReturn(account);

        AccountResponse response = accountService.withdraw(12345L, 1500.0);

        assertNull(response);

        verify(accountRepository, times(1)).findByAccountNumber(12345L);
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void testCloseAccount_Success() {
        when(accountRepository.findByAccountNumber(12345L)).thenReturn(account);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountResponse response = accountService.closeAccount(12345L);

        assertNotNull(response);
        assertEquals("Closed", response.getStatus());

        verify(accountRepository, times(1)).findByAccountNumber(12345L);
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void testCloseAccount_AccountNotFound() {
        when(accountRepository.findByAccountNumber(12345L)).thenReturn(null);

        AccountResponse response = accountService.closeAccount(12345L);

        assertNull(response);

        verify(accountRepository, times(1)).findByAccountNumber(12345L);
        verify(accountRepository, never()).save(any(Account.class));
    }
}
