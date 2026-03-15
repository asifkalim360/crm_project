package com.crm.service;

import com.crm.dto.FollowupDTO;

import java.util.List;

public interface FollowupService {

    public FollowupDTO createFollowup(FollowupDTO dto);

    public List<FollowupDTO> getFollowupsByLead(Long leadId);

    public FollowupDTO updateFollowup(FollowupDTO dto, Long id);

    public void deleteFollowup(Long id);

}
