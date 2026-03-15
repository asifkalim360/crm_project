package com.crm.controller;

import com.crm.dto.FollowupDTO;
import com.crm.service.FollowupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/followups")
@RequiredArgsConstructor
public class FollowupController {

    private final FollowupService followupService;

    @PostMapping
    public FollowupDTO createFollowup(@RequestBody FollowupDTO dto)
    {
        return followupService.createFollowup(dto);
    }

    @GetMapping("/lead/{leadId}")
    public List<FollowupDTO> getFollowupsByLead(@PathVariable Long leadId)
    {
        return followupService.getFollowupsByLead(leadId);
    }

    @PutMapping("/{id}")
    public FollowupDTO updateFollowup(@RequestBody FollowupDTO dto, @PathVariable Long id)
    {
        return followupService.updateFollowup(dto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteFollowup(Long id)
    {
        followupService.deleteFollowup((id));
    }

}
