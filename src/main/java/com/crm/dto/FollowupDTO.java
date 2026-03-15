package com.crm.dto;

import com.crm.entity.Lead;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowupDTO {

    private Long id;

    private Long leadId;

    private LocalDate followupDate;

    private String notes;

    private String status;

    private LocalDate nextFollowupDate;
}

