// Purpose:DTO (Data Transfer Object) → Controller aur Service ke beech safe data transfer.
//DTO DataBase structure ko directly expose nahi karte.
// Interview Tip: DTO use karne se security, validation, aur clean code maintain hota hai.
package com.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String source;

    private String status;

}
