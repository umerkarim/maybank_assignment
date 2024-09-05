package com.maybank.services;


import com.maybank.entity.Customer;
import com.maybank.model.response.CustomerResponse;
import com.maybank.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerResponse createCustomer(String name) {
        log.info("createCustomer with name:{}",name);
        Customer customer = new Customer();
        customer.setName(name);
         customerRepository.save(customer);
         return  getCustomerResponse(customer);
    }

    public CustomerResponse getCustomer(Long id) {
        log.info("getCustomer with id:{}",id);
        Customer customer = customerRepository.findById(id).orElse(null);
        if(!ObjectUtils.isEmpty(customer)){
            return getCustomerResponse(customer);
        }
        return null;
    }

    private CustomerResponse getCustomerResponse(Customer customer) {
        log.info("getting CustomerResponse");
        return CustomerResponse.builder().id(customer.getId())
                .name(customer.getName()).build();
    }
}
