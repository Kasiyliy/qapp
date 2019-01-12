package com.iitu.rest.controllers;

import com.iitu.entities.Interviewers;
import com.iitu.entities.Statuses;
import com.iitu.services.implemented.InterviewersService;
import com.iitu.services.implemented.StatusesService;
import com.iitu.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        return new ResponseEntity<>(interviewer, HttpStatus.OK);
    }


    @PostMapping(path = "/interviewers")
    public ResponseEntity add(@RequestBody  @Valid Interviewers interviewer){
        interviewersService.create(interviewer);
        return ResponseEntity.status(HttpStatus.CREATED).body(interviewer);
    }


    @DeleteMapping(path = "/interviewers/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable Long id){
        Interviewers interviewers= interviewersService.getById(id);
        if(interviewers != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        interviewersService.delete(interviewers.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/interviewers", produces = "application/json")
    public ResponseEntity update(
            @RequestParam(value = "id") Long id ,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName ,
            @RequestParam(value = "middleName", required = false) String middleName,
            @RequestParam(value="birthDate",required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date birthDate){

        Interviewers interviewer = interviewersService.getById(id);
        if(interviewer == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        interviewer.setFirstName(firstName != null ? firstName : interviewer.getFirstName());
        interviewer.setLastName(lastName != null ? lastName : interviewer.getLastName());
        interviewer.setMiddleName(middleName != null ? middleName : interviewer.getMiddleName());
        interviewer.setBirthDate(birthDate != null ? birthDate : interviewer.getBirthDate());
        interviewersService.update(interviewer);
        return new ResponseEntity<>(HttpStatus.OK);
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
