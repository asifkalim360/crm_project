package com.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity     // ye class database table banegi
@Table(name = "customers")      // table ka naam explicitly define kiya
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {
    @Id     // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // auto increment id
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String company;

    private String address;
}
