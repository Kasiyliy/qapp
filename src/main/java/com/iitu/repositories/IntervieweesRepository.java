package com.iitu.repositories;

import com.iitu.entities.Interviewees;
import com.iitu.entities.Interviewers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Assylkhan
 * on 29.11.2018
 * @project qapp
 */

@Repository
public interface IntervieweesRepository extends JpaRepository<Interviewees, Long> {

}
