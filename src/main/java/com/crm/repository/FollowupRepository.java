package com.crm.repository;

import com.crm.entity.Followup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowupRepository extends JpaRepository<Followup, Long> {

    List<Followup> findByLeadId(Long leadId);

}
