package com.crm.controller;

import com.crm.dto.LeadDto;
import com.crm.service.LeadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
public class LeadController {

    private final LeadService leadService;

    @PostMapping
    public LeadDto createLead(@Valid @RequestBody LeadDto dto){
        return leadService.createLead(dto);
    }

    @GetMapping("/{id}")
    public LeadDto getLead(@PathVariable Long id){
        return leadService.getLeadById(id);
    }

    @GetMapping
    public List<LeadDto> getAllLeads(){
        return leadService.getAllLeads();
    }

    @PutMapping("/{id}")
    public LeadDto updateLead(@RequestBody LeadDto dto, @PathVariable Long id){
        return leadService.updateLead(dto, id);
    }

    @DeleteMapping("/{id}")
    public String deleteLead(@PathVariable Long id){
        leadService.deleteLead(id);
        return "Lead deleted successfully";
    }

    @PostMapping("/{id}/convert")
    public LeadDto convertLead(@PathVariable Long id)
    {
        return leadService.convertLeadToCustomer(id);
    }
}
