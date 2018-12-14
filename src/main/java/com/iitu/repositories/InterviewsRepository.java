package com.iitu.repositories;

import com.iitu.entities.Interviewees;
import com.iitu.entities.Interviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Assylkhan
 * on 29.11.2018
 * @project qapp
 */

@Repository
public interface InterviewsRepository extends JpaRepository<Interviews, Long> {
    
}
