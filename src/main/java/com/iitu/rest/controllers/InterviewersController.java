package com.iitu.rest.controllers;

import com.iitu.entities.Interviewers;
import com.iitu.entities.Statuses;
import com.iitu.services.implemented.InterviewersService;
import com.iitu.services.implemented.StatusesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Assylkhan
 * on 12.12.2018
 * @project qapp
 */
@RestController
@RequestMapping(path = "/api")
public class InterviewersController {

    private InterviewersService interviewersService;
    private StatusesService statusesService;

    @GetMapping(path = "/interviewers", produces = "application/json")
    @ResponseBody
    public ResponseEntity getAll(){
        List interviewers  = interviewersService.getAll();
        if(interviewers.isEmpty()){
            return new ResponseEntity<List<Interviewers>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Interviewers>>(interviewers, HttpStatus.OK);
    }

    @GetMapping(path = "/interviewers/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity get(@PathVariable Long id){
        Interviewers interviewer = interviewersService.getById(id);
        if (interviewer== null) {
            System.out.println("User with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("interviewer not found");
        }
        return new ResponseEntity<Interviewers>(interviewer, HttpStatus.OK);
    }

    @PostMapping(path = "/interviewers", produces = "application/json")
    public @ResponseBody ResponseEntity add(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName ,
            @RequestParam(value = "middleName") String middleName,
            @RequestParam(value = "birthDate")     @DateTimeFormat(pattern="yyyy-MM-dd") Date birthDate,
            @RequestParam(value = "statusId", required = false) Long statusId
    ){
        Interviewers interviewer = new Interviewers();
        interviewer.setFirstName(firstName);
        interviewer.setLastName(lastName);
        interviewer.setMiddleName(middleName);
        interviewer.setBirthDate(birthDate);
        if(statusId!=null){
            Statuses statuses = statusesService.getById(statusId);
            if(statuses!=null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Status not found");
            }
            interviewer.setStatus(statuses);
        }
        interviewersService.create(interviewer);
        return ResponseEntity.status(HttpStatus.CREATED).body(interviewer);
    }

    @PostMapping(path = "/interviewers/test",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public @ResponseBody ResponseEntity addTest(@RequestBody Interviewers interviewer){
//        interviewersService.saveAndFlush(interviewer);
        System.out.println(interviewer);
        return ResponseEntity.status(HttpStatus.CREATED).body(interviewer);
    }


    @DeleteMapping(path = "/interviewers/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable Long id){
        Interviewers interviewers= interviewersService.getById(id);
        if(interviewers != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("interviewer not found");
        }
        interviewersService.delete(interviewers.getId());
        return new ResponseEntity<Interviewers>(HttpStatus.OK);
    }

    @PutMapping(path = "/interviewers", produces = "application/json")
    public ResponseEntity update(
            @RequestParam(value = "id") Long id ,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName ,
            @RequestParam(value = "middleName", required = false) String middleName,
            @RequestParam(value="birthDate",required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date birthDate){

        Interviewers interviewer = interviewersService.getById(id);
        if(interviewer != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("interviewer not found");
        }
        interviewer.setFirstName(firstName != null ? firstName : interviewer.getFirstName());
        interviewer.setLastName(lastName != null ? lastName : interviewer.getLastName());
        interviewer.setMiddleName(middleName != null ? middleName : interviewer.getMiddleName());
        interviewer.setBirthDate(birthDate != null ? birthDate : interviewer.getBirthDate());
        interviewersService.update(interviewer);
        return new ResponseEntity<Interviewers>(HttpStatus.OK);
    }

    public InterviewersService getInterviewersService() {
        return interviewersService;
    }

    @Autowired
    public void setInterviewersService(InterviewersService interviewersService) {
        this.interviewersService = interviewersService;
    }

    public StatusesService getStatusesService() {
        return statusesService;
    }

    @Autowired
    public void setStatusesService(StatusesService statusesService) {
        this.statusesService = statusesService;
    }
}
