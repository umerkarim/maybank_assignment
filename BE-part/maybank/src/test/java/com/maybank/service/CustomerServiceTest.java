package com.maybank.service;

import com.maybank.entity.Customer;
import com.maybank.model.response.CustomerResponse;
import com.maybank.repository.CustomerRepository;
import com.maybank.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
    }

    @Test
    void testCreateCustomer_Success() {
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerResponse response = customerService.createCustomer("John Doe");

        assertNotNull(response);
        assertEquals("John Doe", response.getName());

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

}
