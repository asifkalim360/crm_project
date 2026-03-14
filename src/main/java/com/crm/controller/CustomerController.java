package com.crm.controller;

import com.crm.dto.CustomerDto;
import com.crm.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerDto createCustomer(@RequestBody CustomerDto dto)
    {
        return customerService.createCustomer(dto);
    }
    @GetMapping("/{id}")
    public CustomerDto getCustomerById(@PathVariable Long id)
    {
       return  customerService.getCustomerById(id);
    }
    @GetMapping
    public List<CustomerDto> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }
    @PutMapping("/{id}")
    public CustomerDto updateCustomer(@PathVariable Long id, @RequestBody CustomerDto dto)
    {
        return customerService.updateCustomer(id, dto);
    }
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id)
    {
        customerService.deleteCustomer(id);
        return"Customer delete successfully";
    }
}
