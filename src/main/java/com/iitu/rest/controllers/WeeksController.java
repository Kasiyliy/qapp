package com.iitu.rest.controllers;

import com.iitu.entities.Weeks;
import com.iitu.services.implemented.WeeksService;
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
public class WeeksController {

    private WeeksService weeksService;

    @GetMapping(path = "/weeks", produces = "application/json")
    @ResponseBody
    public ResponseEntity getAll(){
        List weeks  = weeksService.getAll();
        if(weeks.isEmpty()){
            return new ResponseEntity<List<Weeks>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Weeks>>(weeks, HttpStatus.OK);
    }

    @GetMapping(path = "/weeks/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity get(@PathVariable Long id){
        Weeks week = weeksService.getById(id);
        if (week== null) {
            System.out.println("Week with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        return new ResponseEntity<>(week, HttpStatus.OK);
    }


    @PostMapping(path = "/weeks")
    public ResponseEntity add(@RequestBody Weeks week){
        weeksService.create(week);
        return ResponseEntity.status(HttpStatus.CREATED).body(week);
    }


    @DeleteMapping(path = "/weeks/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable Long id){
        Weeks week = weeksService.getById(id);
        if(week != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        weeksService.delete(week.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/weeks", produces = "application/json")
    public ResponseEntity update(
            @RequestParam(value = "id") Long id ,
            @RequestBody Weeks week){

        Weeks tempWeek = weeksService.getById(id);
        if(tempWeek != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        week.setId(tempWeek.getId());
        weeksService.update(week);
        return new ResponseEntity(HttpStatus.OK);
    }

    public WeeksService getWeeksService() {
        return weeksService;
    }

    @Autowired
    public void setWeeksService(WeeksService weeksService) {
        this.weeksService = weeksService;
    }
}
