package com.iitu.rest.controllers;

import com.iitu.entities.Interviewers;
import com.iitu.repositories.InterviewersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

    @Autowired
    InterviewersRepository interviewersRepository;

    @GetMapping(path = "/interviewers", produces = "application/json")
    @ResponseBody
    public ResponseEntity getAll(){
        List interviewers  = interviewersRepository.findAll();
        if(interviewers.isEmpty()){
            return new ResponseEntity<List<Interviewers>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Interviewers>>(interviewers, HttpStatus.OK);
    }

    @GetMapping(path = "/interviewers/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity get(@PathVariable Long id){
        Interviewers interviewer = interviewersRepository.findById(id).get();
        if (interviewer== null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Interviewers>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Interviewers>(interviewer, HttpStatus.OK);
    }

    @PostMapping(path = "/interviewers", produces = "application/json")
    public @ResponseBody ResponseEntity add(
            @RequestParam(value = "firstName") String firstName,
            @RequestParam(value = "lastName") String lastName ,
            @RequestParam(value = "middleName") String middleName,
            @RequestParam(value="birthDate")     @DateTimeFormat(pattern="yyyy-MM-dd") Date birthDate){
        Interviewers interviewer = new Interviewers();
        interviewer.setFirstName(firstName);
        interviewer.setLastName(lastName);
        interviewer.setMiddleName(middleName);
        interviewer.setBirthDate(birthDate);
        interviewersRepository.saveAndFlush(interviewer);
        return new ResponseEntity(interviewer, HttpStatus.CREATED);
    }


    @DeleteMapping(path = "/interviewers/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable Long id){
        Optional <Interviewers>interviewersOptional = interviewersRepository.findById(id);
        if(!interviewersOptional.isPresent()){
            return new ResponseEntity<Interviewers>(HttpStatus.NOT_FOUND);
        }
        interviewersRepository.delete(interviewersOptional.get());
        interviewersRepository.flush();
        return new ResponseEntity<Interviewers>(HttpStatus.OK);
    }

    @PutMapping(path = "/interviewers", produces = "application/json")
    public ResponseEntity update(
            @RequestParam(value = "id") Long id ,
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName ,
            @RequestParam(value = "middleName", required = false) String middleName,
            @RequestParam(value="birthDate",required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date birthDate){

        Optional<Interviewers> interviewersOptional = interviewersRepository.findById(id);
        if(!interviewersOptional.isPresent()){
            return new ResponseEntity<Interviewers>(HttpStatus.NOT_FOUND);
        }
        Interviewers interviewer = interviewersOptional.get();
        interviewer.setFirstName(firstName != null ? firstName : interviewer.getFirstName());
        interviewer.setLastName(lastName != null ? lastName : interviewer.getLastName());
        interviewer.setMiddleName(middleName != null ? middleName : interviewer.getMiddleName());
        interviewer.setBirthDate(birthDate != null ? birthDate : interviewer.getBirthDate());

        interviewersRepository.save(interviewer);
        interviewersRepository.flush();
        return new ResponseEntity<Interviewers>(HttpStatus.OK);
    }


}
