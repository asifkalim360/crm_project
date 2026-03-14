package com.crm.service;

import com.crm.dto.CustomerDto;

import java.util.List;

public interface CustomerService {
    public CustomerDto createCustomer(CustomerDto dto);

    public CustomerDto getCustomerById(Long id);

    public List<CustomerDto> getAllCustomers();

    public CustomerDto updateCustomer(Long id, CustomerDto dto);

    public void deleteCustomer(Long id);
}
