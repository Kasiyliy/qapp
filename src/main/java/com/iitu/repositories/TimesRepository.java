package com.iitu.repositories;

import com.iitu.entities.Times;
import com.iitu.entities.Weeks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Assylkhan
 * on 29.11.2018
 * @project qapp
 */

@Repository
public interface TimesRepository extends JpaRepository<Times, Long> {
    
}
