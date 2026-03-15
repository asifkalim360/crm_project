package com.crm.dto;

import com.crm.entity.Lead;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "Lead id required")
    private Long leadId;
    @NotNull(message = "Followup date required")
    private LocalDate followupDate;
    @NotBlank(message = "Notes connot be empry")
    private String notes;
    @NotBlank(message = "Status is required")
    private String status;

    private LocalDate nextFollowupDate;
}

