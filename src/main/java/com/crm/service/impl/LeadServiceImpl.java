package com.crm.service.impl;

import com.crm.dto.LeadDto;
import com.crm.entity.Customer;
import com.crm.entity.Lead;
import com.crm.repository.CustomerRepository;
import com.crm.repository.LeadRepository;
import com.crm.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;
    private final CustomerRepository customerRepository;

    //============Helper Mapping: Entity to DTO================
    // Iska purpose : Database se jo Entity object aata hai usko DTO me convert karna.
    // Flow: Database -> Entity (Lead) -> mapToDto() -> DTO (LeadDto) -> Client / API Response
    // EXAMPLE:
    // | id | name | email                                   | phone | source  | status |
    // | -- | ---- | --------------------------------------- | ----- | ------- | ------ |
    // | 1  | Asif | [asif@gmail.com](mailto:asif@gmail.com) | 99999 | Website | NEW    |
    //
    // Entity: Lead lead = leadRepository.findById(1);
    // mapToDto() convert karega: LeadDto dto = mapToDto(lead);

    // DTO response:
    // { "id":1, "name":"Asif", "email":"asif@gmail.com","phone":"99999","source":"Website","status":"NEW"}
    //yahan se DTO fhir client ko send ho jata hai.
    private LeadDto entityToDto(Lead lead)
    {
        return LeadDto.builder()
                .id(lead.getId())
                .name(lead.getName())
                .email(lead.getEmail())
                .phone(lead.getPhone())
                .source(lead.getSource())
                .status(lead.getStatus())
                .build();
    }

    //============Helper Mapping: DTO to Entity=================
    // Purpose : Client se jo data aata hai usko Entity me convert karna taaki database me save ho sake.
    // Flow: Client Request -> DTO -> mapToEntity() -> Entity -> Database Save
    // Example request:
    // { "name":"Rahul", "email":"rahul@gmail.com", "phone":"88888", "source":"Facebook", "status":"NEW" }
    private Lead dtoTOEntity(LeadDto dto)
    {
        return Lead.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .source(dto.getSource())
                .status(dto.getStatus())
                .createData(LocalDateTime.now())
                .build();
    }

    @Override
    public LeadDto createLead(LeadDto dto) {
        Lead lead = dtoTOEntity(dto);
        Lead saved = leadRepository.save(lead);
        return entityToDto(saved);
    }

    @Override
    public LeadDto getLeadById(Long id) {
        Lead lead = leadRepository.findById(id).orElseThrow(() -> new RuntimeException("Lead not found"));
        return entityToDto(lead);
    }

    @Override
    public List<LeadDto> getAllLeads() {
        List<LeadDto> allLeads = leadRepository.findAll()   // findAll() database se entity list laata hai.
                .stream()   // stream() list ko stream pipeline me convert karta hai.
                .map(this::entityToDto)  // map() har entity ko DTO me convert karta hai.
                .collect(Collectors.toList());  // collect() stream ko wapas List me convert karta hai.
        return allLeads;

        // Important tip (interview ke liye):
        // map() performs transformation, collect() converts stream to collection
    }

    @Override
    public LeadDto updateLead(LeadDto dto, Long id) {
        Lead lead = leadRepository.findById(id).orElseThrow(() -> new RuntimeException("Lead not found"));
        lead.setName(dto.getName());
        lead.setEmail(dto.getEmail());
        lead.setPhone(dto.getPhone());
        lead.setSource(dto.getSource());
        lead.setStatus(dto.getStatus());
        Lead updated = leadRepository.save(lead);
        return entityToDto(updated);
    }

    @Override
    public void deleteLead(Long id) {
        leadRepository.deleteById(id);
    }

    @Override
    public LeadDto convertLeadToCustomer(Long leadId) {
        // Lead find karo.
        Lead lead = leadRepository.findById(leadId).orElseThrow(() -> new RuntimeException("Lead not found!"));
        // Customer entity create karo.
        Customer customer = Customer.builder()
                .name(lead.getName())
                .email(lead.getEmail())
                .phone(lead.getPhone())
                .company("N/A")
                .address("N/A")
                .build();
        // Customer save karo
        customerRepository.save(customer);

        // Lead status update karo.
        lead.setStatus("CONVERTED");
        Lead updatedLead = leadRepository.save(lead);
        return entityToDto(updatedLead);

        // Ye CRM ka real business logic hai.
        //1️⃣ Pehle Lead database se fetch kiya
        //2️⃣ Us lead ke data se Customer object banaya
        //3️⃣ Customer table me save kiya
        //4️⃣ Lead ka status CONVERTED set kiya
        //5️⃣ Updated lead return ki

    }
}

// Explanation:
// mapToDto & mapToEntity → Entity aur DTO ke beech conversion
// CRUD methods → business logic aur DB call
// orElseThrow → Simple exception handling
