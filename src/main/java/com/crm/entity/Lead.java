// Purpose: Entity represents table in database. Lead ka data store hoga yahan.
package com.crm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity     // Ye batata hai ye class DB table banegi
@Table(name="leads")    // Table ka exact name define kar rahe
@Data   // Lombok annotation, getter, setter, toString, equals, hashCode auto-generate karega
// ye 3 Constructor aur builder pattern ko support karti hai
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lead {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String source;  // Website, Facebook, Referral, etc.

    private String status;  // NEW, CONTACTED, QUALIFIED, CONVERTED, lOST

    private LocalDateTime createData;

}
