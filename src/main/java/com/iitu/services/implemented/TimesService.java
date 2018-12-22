package com.iitu.services.implemented;

import com.iitu.entities.Times;
import com.iitu.entities.Times;
import com.iitu.repositories.TimesRepository;
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
public class TimesService implements GenericService<Times>{

    private TimesRepository timesRepository;

    @Override
    public void create(Times Times) {
        timesRepository.save(Times);
    }

    @Override
    public boolean update(Times Times) {
        if(Times.getId()!=null){
            timesRepository.save(Times);
            return true;
        }
        return false;
    }

    @Override
    public Times getById(Long id) {
        Optional<Times> optionalTimes = timesRepository.findById(id);
        if(optionalTimes.isPresent()){
            return optionalTimes.get();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Times> optionalTimes = timesRepository.findById(id);
        if(optionalTimes.isPresent()){
            timesRepository.delete(optionalTimes.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Times> getAll() {
        return getTimesRepository().findAll();
    }

    public TimesRepository getTimesRepository() {
        return timesRepository;
    }

    @Autowired
    public void setTimesRepository(TimesRepository timesRepository) {
        this.timesRepository = timesRepository;
    }
}
