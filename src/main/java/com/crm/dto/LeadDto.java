// Purpose:DTO (Data Transfer Object) → Controller aur Service ke beech safe data transfer.
//DTO DataBase structure ko directly expose nahi karte.
// Interview Tip: DTO use karne se security, validation, aur clean code maintain hota hai.
package com.crm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number required")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank(message = "Source is required")
    private String source;

    private String status;
}
// @NotBlank → field empty nahi ho sakta
//@Email → valid email format check karega
//@Size → string ki length check karega
