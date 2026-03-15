package com.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="followups")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Followup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Many followups for one lead.
    @ManyToOne  //Matlab ek lead par multiple followups ho sakte hain. isliye Followup entity me Lead ka reference rakha gaya hai
    @JoinColumn(name = "lead_id")
    private Lead lead;

    private LocalDate followupDate;

    private String notes;

    private String status;

    private LocalDate nextFollowupDate;
}
