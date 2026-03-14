// Purpose: Service → Business logic handle karta hai (Controller se alag).
package com.crm.service;

import com.crm.dto.LeadDto;

import java.util.List;

public interface LeadService {

    public LeadDto createLead(LeadDto dto);

    public LeadDto getLeadById(Long id);

    public List<LeadDto> getAllLeads();

    public LeadDto updateLead(LeadDto dto, Long id);

    public void deleteLead(Long id);


    // CRM ka sabse important feature yehi hota hai.
    // Flow: Lead created, Sales team contact karti hai, Deal close hoti hai, Lead convert → Customer
    // Is conversion me: Lead table → status = CONVERTED, Customer table → new customer create
    public LeadDto convertLeadToCustomer(Long leadId);



}
