package com.iitu.rest.controllers;

import com.iitu.entities.Interviewees;
import com.iitu.services.implemented.IntervieweesService;
import com.iitu.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Assylkhan
 * on 12.12.2018
 * @project qapp
 */
@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "*")
public class IntervieweesController {

    private IntervieweesService intervieweesService;

    @GetMapping(path = "/interviewees", produces = "application/json")
    @ResponseBody
    public ResponseEntity getAll(){
        List interviewees = intervieweesService.getAll();
        if(interviewees.isEmpty()){
            return new ResponseEntity<List<Interviewees>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Interviewees>>(interviewees, HttpStatus.OK);
    }

    @GetMapping(path = "/interviewees/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity get(@PathVariable Long id){
        Interviewees interviewee = intervieweesService.getById(id);
        if (interviewee== null) {
            System.out.println("User with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        return new ResponseEntity<>(interviewee, HttpStatus.OK);
    }


    @PostMapping(path = "/interviewees")
    public ResponseEntity add(@RequestBody  @Valid Interviewees interviewee){
        intervieweesService.create(interviewee);
        return ResponseEntity.status(HttpStatus.CREATED).body(interviewee);
    }


    @DeleteMapping(path = "/interviewees/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable Long id){
        Interviewees interviewee= intervieweesService.getById(id);
        if(interviewee != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        intervieweesService.delete(interviewee.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/interviewees", produces = "application/json")
    public ResponseEntity update(
            @RequestBody @Valid Interviewees interviewee){

        Interviewees tempInterviewee = intervieweesService.getById(interviewee.getId());
        if(tempInterviewee == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        intervieweesService.update(interviewee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    public void setIntervieweesService(IntervieweesService intervieweesService) {
        this.intervieweesService = intervieweesService;
    }

    public IntervieweesService getIntervieweesService() {
        return intervieweesService;
    }
}
