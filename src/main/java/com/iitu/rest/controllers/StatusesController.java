package com.iitu.rest.controllers;

import com.iitu.entities.Interviewers;
import com.iitu.entities.Statuses;
import com.iitu.services.implemented.InterviewersService;
import com.iitu.services.implemented.StatusesService;
import com.iitu.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author Assylkhan
 * on 12.12.2018
 * @project qapp
 */
@RestController
@RequestMapping(path = "/api")
public class StatusesController {

    private StatusesService statusesService;

    @GetMapping(path = "/statuses", produces = "application/json")
    @ResponseBody
    public ResponseEntity getAll(){
        List statuses  = statusesService.getAll();
        if(statuses.isEmpty()){
            return new ResponseEntity<List<Statuses>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Statuses>>(statuses, HttpStatus.OK);
    }

    @GetMapping(path = "/statuses/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity get(@PathVariable Long id){
        Statuses status = statusesService.getById(id);
        if (status== null) {
            System.out.println("Status with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    @PostMapping(path = "/statuses")
    public ResponseEntity add(@RequestBody Statuses status){
        statusesService.create(status);
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }


    @DeleteMapping(path = "/statuses/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable Long id){
        Statuses status = statusesService.getById(id);
        if(status != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        statusesService.delete(status.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping(path = "/statuses", produces = "application/json")
    public ResponseEntity update(
            @RequestParam(value = "id") Long id ,
            @RequestParam(value = "name", required = true) String name){

        Statuses status = statusesService.getById(id);
        if(status != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        status.setName(name);
        statusesService.update(status);
        return new ResponseEntity(HttpStatus.OK);
    }

    public StatusesService getStatusesService() {
        return statusesService;
    }

    @Autowired
    public void setStatusesService(StatusesService statusesService) {
        this.statusesService = statusesService;
    }
}
