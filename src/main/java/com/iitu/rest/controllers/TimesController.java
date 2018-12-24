package com.iitu.rest.controllers;

import com.iitu.entities.Times;
import com.iitu.services.implemented.TimesService;
import com.iitu.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Assylkhan
 * on 12.12.2018
 * @project qapp
 */
@RestController
@RequestMapping(path = "/api")
public class TimesController {

    private TimesService timesService;

    @GetMapping(path = "/times", produces = "application/json")
    @ResponseBody
    public ResponseEntity getAll(){
        List times  = timesService.getAll();
        if(times.isEmpty()){
            return new ResponseEntity<List<Times>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Times>>(times, HttpStatus.OK);
    }

    @GetMapping(path = "/times/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity get(@PathVariable Long id){
        Times time = timesService.getById(id);
        if (time== null) {
            System.out.println("Time with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        return new ResponseEntity<>(time, HttpStatus.OK);
    }


    @PostMapping(path = "/times")
    public ResponseEntity add(@RequestBody Times time){
        timesService.create(time);
        return ResponseEntity.status(HttpStatus.CREATED).body(time);
    }


    @DeleteMapping(path = "/times/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable Long id){
        Times time = timesService.getById(id);
        if(time != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        timesService.delete(time.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/times", produces = "application/json")
    public ResponseEntity update(
            @RequestParam(value = "id") Long id ,
            @RequestBody Times time){

        Times tempTime = timesService.getById(id);
        if(tempTime != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        time.setId(tempTime.getId());
        timesService.update(time);
        return new ResponseEntity(HttpStatus.OK);
    }

    public TimesService getTimesService() {
        return timesService;
    }

    @Autowired
    public void setTimesService(TimesService timesService) {
        this.timesService = timesService;
    }
}
