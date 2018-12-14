package com.iitu.repositories;

import com.iitu.entities.Interviewees;
import com.iitu.entities.Weeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Assylkhan
 * on 29.11.2018
 * @project qapp
 */

@Repository
public interface WeeksRepository extends JpaRepository<Weeks, Long> {
    
}
