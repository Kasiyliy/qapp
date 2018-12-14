package com.iitu.repositories;

import com.iitu.entities.Interviews;
import com.iitu.entities.Statuses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Assylkhan
 * on 29.11.2018
 * @project qapp
 */

@Repository
public interface StatusesRepository extends JpaRepository<Statuses, Long> {
    
}
