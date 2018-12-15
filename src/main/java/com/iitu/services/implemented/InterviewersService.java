package com.iitu.services.implemented;

import com.iitu.entities.Interviewers;
import com.iitu.repositories.InterviewersRepository;
import com.iitu.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Assylkhan
 * on 14.12.2018
 * @project qapp
 */
@Service
public class InterviewersService implements GenericService<Interviewers> {

    @Autowired
    private InterviewersRepository interviewersRepository;

    public void create(Interviewers interviewer){
        interviewersRepository.save(interviewer);
    }

    public boolean update(Interviewers interviewer){
        if(interviewer.getId()!=null){
            interviewersRepository.save(interviewer);
            return true;
        }
        return false;
    }

    public Interviewers getById(Long id){
        Optional<Interviewers> interviewersOptional = interviewersRepository.findById(id);
        if(interviewersOptional.isPresent()){
            return interviewersOptional.get();
        }else{
            return null;
        }
    }

    public boolean delete(Long id){
        Interviewers interviewer = getById(id);
        if(interviewer!=null){
            interviewersRepository.delete(interviewer);
            return true;
        }
        return false;
    }
}
