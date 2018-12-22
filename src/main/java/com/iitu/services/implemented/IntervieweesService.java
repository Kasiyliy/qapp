package com.iitu.services.implemented;

import com.iitu.entities.Interviewees;
import com.iitu.repositories.IntervieweesRepository;
import com.iitu.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 15.12.2018
 * @project qapp
 */
@Service
public class IntervieweesService implements GenericService<Interviewees>{

    private IntervieweesRepository intervieweesRepository;

    @Override
    public void create(Interviewees interviewees) {
        intervieweesRepository.save(interviewees);
    }

    @Override
    public boolean update(Interviewees interviewees) {
        if(interviewees.getId()!=null){
            intervieweesRepository.save(interviewees);
            return true;
        }
        return false;
    }

    @Override
    public Interviewees getById(Long id) {
        Optional<Interviewees> interviewersOptional = intervieweesRepository.findById(id);
        if(interviewersOptional.isPresent()){
            return interviewersOptional.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        Interviewees interviewees = getById(id);
        if(interviewees!=null){
            intervieweesRepository.delete(interviewees);
            return true;
        }
        return false;
    }

    @Override
    public List<Interviewees> getAll() {
        return intervieweesRepository.findAll();
    }

    public IntervieweesRepository getIntervieweesRepository() {
        return intervieweesRepository;
    }

    @Autowired
    public void setIntervieweesRepository(IntervieweesRepository intervieweesRepository) {
        this.intervieweesRepository = intervieweesRepository;
    }
}
