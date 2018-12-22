package com.iitu.services.implemented;

import com.iitu.entities.Interviews;
import com.iitu.repositories.InterviewsRepository;
import com.iitu.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 19.12.2018
 * @project qapp
 */
@Service
public class InterviewsService implements GenericService<Interviews>{

    private InterviewsRepository interviewsRepository;

    @Override
    public void create(Interviews interviews) {
        interviewsRepository.save(interviews);
    }

    @Override
    public boolean update(Interviews interviews) {
        if(interviews.getId()!=null){
            interviewsRepository.save(interviews);
            return true;
        }
        return false;
    }

    @Override
    public Interviews getById(Long id) {
        Optional<Interviews> interviewsOptional = interviewsRepository.findById(id);
        if(interviewsOptional.isPresent()){
            return  interviewsOptional.get();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Interviews> interviewsOptional = interviewsRepository.findById(id);
        if(interviewsOptional.isPresent()){
            interviewsRepository.delete(interviewsOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Interviews> getAll() {
        return interviewsRepository.findAll();
    }

    public InterviewsRepository getInterviewsRepository() {
        return interviewsRepository;
    }

    @Autowired
    public void setInterviewsRepository(InterviewsRepository interviewsRepository) {
        this.interviewsRepository = interviewsRepository;
    }
}
