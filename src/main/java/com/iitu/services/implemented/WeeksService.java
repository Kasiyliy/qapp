package com.iitu.services.implemented;

import com.iitu.entities.Weeks;
import com.iitu.repositories.WeeksRepository;
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
public class WeeksService implements GenericService<Weeks>{

    private WeeksRepository weeksRepository;

    @Override
    public void create(Weeks weeks) {
        weeksRepository.save(weeks);
    }

    @Override
    public boolean update(Weeks weeks) {
        if(weeks.getId()!=null){
            weeksRepository.save(weeks);
            return true;
        }
        return false;
    }

    @Override
    public Weeks getById(Long id) {
        Optional<Weeks> optionalWeeks = weeksRepository.findById(id);
        if(optionalWeeks.isPresent()){
            return optionalWeeks.get();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Weeks> optionalWeeks = weeksRepository.findById(id);
        if(optionalWeeks.isPresent()){
            weeksRepository.delete(optionalWeeks.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Weeks> getAll() {
        return weeksRepository.findAll();
    }

    public WeeksRepository getWeeksRepository() {
        return weeksRepository;
    }

    @Autowired
    public void setWeeksRepository(WeeksRepository weeksRepository) {
        this.weeksRepository = weeksRepository;
    }
}
