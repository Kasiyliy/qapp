package com.iitu.services.implemented;

import com.iitu.entities.IntervieweeDocuments;
import com.iitu.repositories.IntervieweeDocumentsRepository;
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
public class IntervieweeDocumentsService implements GenericService<IntervieweeDocuments>{

    private IntervieweeDocumentsRepository intervieweeDocumentsRepository;

    @Override
    public void create(IntervieweeDocuments intervieweeDocuments) {
        intervieweeDocumentsRepository.save(intervieweeDocuments);
    }

    @Override
    public boolean update(IntervieweeDocuments intervieweeDocuments) {
        if(intervieweeDocuments.getId()!=null){
            intervieweeDocumentsRepository.save(intervieweeDocuments);
            return true;
        }
        return false;
    }

    @Override
    public IntervieweeDocuments getById(Long id) {
        Optional<IntervieweeDocuments> interviewersOptional = intervieweeDocumentsRepository.findById(id);
        if(interviewersOptional.isPresent()){
            return interviewersOptional.get();
        }else{
            return null;
        }
    }

    @Override
    public boolean delete(Long id) {
        IntervieweeDocuments intervieweeDocuments = getById(id);
        if(intervieweeDocuments!=null){
            intervieweeDocumentsRepository.delete(intervieweeDocuments);
            return true;
        }
        return false;
    }

    @Override
    public List<IntervieweeDocuments> getAll() {
        return intervieweeDocumentsRepository.findAll();
    }

    public IntervieweeDocumentsRepository getIntervieweeDocumentsRepository() {
        return intervieweeDocumentsRepository;
    }

    @Autowired
    public void setIntervieweeDocumentsRepository(IntervieweeDocumentsRepository intervieweeDocumentsRepository) {
        this.intervieweeDocumentsRepository = intervieweeDocumentsRepository;
    }
}
