package com.iitu.services.implemented;

import com.iitu.entities.BusyTimes;
import com.iitu.repositories.BusyTimesRepository;
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
public class BusyTimesService implements GenericService<BusyTimes>{

    private BusyTimesRepository busyTimesRepository;

    @Override
    public void create(BusyTimes BusyTimes) {
        busyTimesRepository.save(BusyTimes);
    }

    @Override
    public boolean update(BusyTimes BusyTimes) {
        if(BusyTimes.getId()!=null){
            busyTimesRepository.save(BusyTimes);
            return true;
        }
        return false;
    }

    @Override
    public BusyTimes getById(Long id) {
        Optional<BusyTimes> optionalBusyTimes = busyTimesRepository.findById(id);
        if(optionalBusyTimes.isPresent()){
            return optionalBusyTimes.get();
        }
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<BusyTimes> optionalBusyTimes = busyTimesRepository.findById(id);
        if(optionalBusyTimes.isPresent()){
            busyTimesRepository.delete(optionalBusyTimes.get());
            return true;
        }
        return false;
    }

    @Override
    public List<BusyTimes> getAll() {
        return busyTimesRepository.findAll();
    }

    public BusyTimesRepository getBusyTimesRepository() {
        return busyTimesRepository;
    }

    @Autowired
    public void setBusyTimesRepository(BusyTimesRepository busyTimesRepository) {
        this.busyTimesRepository= busyTimesRepository;
    }
}
