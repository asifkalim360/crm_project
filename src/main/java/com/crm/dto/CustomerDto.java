package com.crm.dto;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String company;

    private String address;
}
// DTO ka use isliye karte hain taaki
// direct entity ko API me expose na karein.
// Isse security aur flexibility dono milti hai.

