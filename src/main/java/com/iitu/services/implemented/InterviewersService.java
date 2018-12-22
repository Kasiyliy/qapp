package com.iitu.services.implemented;

import com.iitu.entities.Interviewers;
import com.iitu.repositories.InterviewersRepository;
import com.iitu.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 14.12.2018
 * @project qapp
 */
@Service
public class InterviewersService implements GenericService<Interviewers> {


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
        }
        return null;
    }

    public boolean delete(Long id){
        Interviewers interviewer = getById(id);
        if(interviewer!=null){
            interviewersRepository.delete(interviewer);
            return true;
        }
        return false;
    }

    @Override
    public List<Interviewers> getAll() {
        return interviewersRepository.findAll();
    }

    public InterviewersRepository getInterviewersRepository() {
        return interviewersRepository;
    }

    @Autowired
    public void setInterviewersRepository(InterviewersRepository interviewersRepository) {
        this.interviewersRepository = interviewersRepository;
    }
}
