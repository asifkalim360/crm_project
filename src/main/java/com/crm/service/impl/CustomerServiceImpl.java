package com.crm.service.impl;

import com.crm.dto.CustomerDto;
import com.crm.entity.Customer;
import com.crm.repository.CustomerRepository;
import com.crm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    //==================== Entity to Dto =========================
    private CustomerDto entityToDto(Customer customer)
    {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .company(customer.getCompany())
                .address(customer.getAddress())
                .build();
    }
    //==================== Dto to Entity =========================
    private Customer dtoToEntity(CustomerDto dto)
    {
        return Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .company(dto.getCompany())
                .address(dto.getAddress())
                .build();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto dto) {
        Customer customer = dtoToEntity(dto);
        Customer saved = customerRepository.save(customer);
        return entityToDto(saved);
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        return entityToDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> allCustomers = customerRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
        return allCustomers;
    }

    @Override
    public CustomerDto updateCustomer(Long id, CustomerDto dto) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Lead not found!"));
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());
        customer.setCompany(dto.getCompany());
        customer.setAddress(dto.getAddress());
        Customer updatedData = customerRepository.save(customer);
        return entityToDto(updatedData);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
