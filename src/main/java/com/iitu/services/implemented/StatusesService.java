package com.iitu.services.implemented;

import com.iitu.entities.Statuses;
import com.iitu.repositories.StatusesRepository;
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
public class StatusesService implements GenericService<Statuses>{

    private StatusesRepository statusesRepository;

    @Override
    public void create(Statuses statuses) {
        statusesRepository.save(statuses);
    }

    @Override
    public boolean update(Statuses statuses) {
        if(statuses.getId()!=null){
            statusesRepository.save(statuses);
            return true;
        }
        return false;
    }

    @Override
    public Statuses getById(Long id) {
        Optional<Statuses> statusesOptional = statusesRepository.findById(id);
        if(statusesOptional.isPresent()){
            return statusesOptional.get();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Statuses> statusesOptional = statusesRepository.findById(id);
        if(statusesOptional.isPresent()){
            statusesRepository.delete(statusesOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Statuses> getAll() {
        return statusesRepository.findAll();
    }

    public StatusesRepository getStatusesRepository() {
        return statusesRepository;
    }

    @Autowired
    public void setStatusesRepository(StatusesRepository statusesRepository) {
        this.statusesRepository = statusesRepository;
    }
}
