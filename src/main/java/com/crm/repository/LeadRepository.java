// Purpose: Repository → Database ke saath CRUD operations handle karta hai.
package com.crm.repository;

import com.crm.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    // JpaRepository provides: save, findById, findAll, deleteById, etc.

}

// Interview Tip: Spring Data JPA me custom queries ke liye method naming conventions use karte hain.
