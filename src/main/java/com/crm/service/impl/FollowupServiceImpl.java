package com.crm.service.impl;
// Ye sab classes ko is file me use karne ke liye import kiya gaya hai.
// Example:
//FollowupDTO → data transfer
//Followup → database entity
//Lead → lead entity
//FollowupRepository → DB access
//LeadRepository → DB access
//FollowupService → interface
import com.crm.dto.FollowupDTO;
import com.crm.entity.Followup;
import com.crm.entity.Lead;
import com.crm.repository.FollowupRepository;
import com.crm.repository.LeadRepository;
import com.crm.service.FollowupService;
import lombok.RequiredArgsConstructor;

// ye Spring framework ke annotations.
// @Service → Spring ko batata hai ki ye service layer class hai.
import org.springframework.stereotype.Service;

// List → multiple objects store karne ke liye
// Collectors → stream ko list me convert karne ke liye
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// Ye class FollowupService interface ko implement kar rahi hai.
// Kyunki Industry me pattern hota hai: Controller -> Service (Interface) -> ServiceImpl -> Repository ->Database | Isse code loosely coupled rehta hai.
public class FollowupServiceImpl implements FollowupService {
    // Spring automatically FollowupRepository object create karke inject karega.
    // Kyunki Repository database operations karta hai.
    // Example: save(), findById(), findAll(), deleteById()
    private final FollowupRepository followupRepository;

    // Kyunki Followup Lead se linked hota hai.
    // Relation: Lead -> Followup | Ek lead ke multiple followups ho sakte hain.
    private final LeadRepository leadRepository;

    @Override
    public FollowupDTO createFollowup(FollowupDTO dto) {    // Ye method new followup create karta hai. DTO aata hai controller se.
        if(dto.getLeadId() == null){
            throw new RuntimeException("Lead ID cannot be null");
        }
        // yahan pe DTO me jo leadId aayi hai usse Lead entity fetch kar rahe hain.
        // Example: leadId = 5
        // Database query: select * from lead where id=5
        // orElseThrow : Agar lead nahi mili to error throw karega.
        Lead lead = leadRepository.findById(dto.getLeadId())
                .orElseThrow(() -> new RuntimeException("Lead not found"));
        Followup followup = new Followup(); // Entity object create -> New followup object banaya.
        followup.setLead(lead);     // yahan pe Followup ko lead se connect kar diya | uske baad Database me ye banega: followup table lead_id = 5
        followup.setFollowupDate(dto.getFollowupDate());    // yahan pe DTO se value lekar entity me set kiya gaya hai.
        followup.setNotes(dto.getNotes());
        followup.setStatus(dto.getStatus());
        followup.setNextFollowupDate(dto.getNextFollowupDate());
        followup = followupRepository.save(followup);   // Ye database me INSERT query run karega.
        dto.setId(followup.getId());    // ye Database me auto generated id DTO me set kar di | Example: followup id = 10
        return dto;     // return Dto se Controller ko response mil jayega.
    }

    @Override
    public List<FollowupDTO> getFollowupsByLead(Long leadId) {  // yeh method Ek lead ke saare followups fetch karta hai.
        List<Followup> allList = followupRepository.findAll();  // yahan pe Database se saare followups fetch honge.
        return allList.stream()     // yahan pe List ko stream pipeline me convert kiya hai.
                .filter(f -> f.getLead().getId().equals(leadId))    // yahan pe Sirf wo followups select hoga jinke  -> lead_id = leadId
                .map(f -> {     // yahan pe Har followup entity ko DTO me convert kar rahe hain.
                    FollowupDTO dto = new FollowupDTO();    // DTO create
                    dto.setId(f.getId());   // Data copy hoga yahan se
                    dto.setLeadId(f.getLead().getId());
                    dto.setFollowupDate(f.getFollowupDate());
                    dto.setNotes(f.getNotes());
                    dto.setStatus(f.getStatus());
                    dto.setNextFollowupDate(f.getNextFollowupDate());
                    return dto;     // yahan pe Return DTO hoga
                }).collect(Collectors.toList());    // Stream ko List me convert kar dega.
    }

    @Override
    public FollowupDTO updateFollowup(FollowupDTO dto, Long id) {   // ye method hamare Existing followup ko update karta hai.
        Followup followup =  followupRepository.findById(id).orElseThrow(()-> new RuntimeException("Followup not found")); // Database se followup fetch | Agar record nahi mila to exception.
        followup.setNotes(dto.getNotes());
        followup.setStatus(dto.getStatus());
        followup.setNextFollowupDate(dto.getNextFollowupDate());

        followupRepository.save(followup);  // yahan pe UPDATE query run karega.
        return dto;     // Controller ko updated data return karega.
    }

    @Override
    public void deleteFollowup(Long id) {   // Followup delete karne ka method.
        followupRepository.deleteById(id);  // Database query: delete from followup where id=?
    }
}
