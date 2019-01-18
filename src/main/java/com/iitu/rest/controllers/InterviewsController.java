package com.iitu.rest.controllers;

import com.iitu.entities.Interviews;
import com.iitu.services.implemented.InterviewsService;
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
public class InterviewsController {

    private InterviewsService interviewsService;

    @GetMapping(path = "/interviews", produces = "application/json")
    @ResponseBody
    public ResponseEntity getAll(){
        List interviews = interviewsService.getAll();
        if(interviews.isEmpty()){
            return new ResponseEntity<List<Interviews>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Interviews>>(interviews, HttpStatus.OK);
    }

    @GetMapping(path = "/interviews/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity get(@PathVariable Long id){
        Interviews interview = interviewsService.getById(id);
        if (interview== null) {
            System.out.println("Interview with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        return new ResponseEntity<>(interview, HttpStatus.OK);
    }


    @PostMapping(path = "/interviews")
    public ResponseEntity add(@RequestBody  @Valid Interviews interview){
        interviewsService.create(interview);
        return ResponseEntity.status(HttpStatus.CREATED).body(interview);
    }


    @DeleteMapping(path = "/interviews/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable Long id){
        Interviews interview= interviewsService.getById(id);
        if(interview == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        interviewsService.delete(interview.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/interviews", produces = "application/json")
    public ResponseEntity update(
            @RequestBody @Valid Interviews interview){

        Interviews tempInterview = interviewsService.getById(interview.getId());
        if(tempInterview == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        interviewsService.update(interview);
        return ResponseEntity.status(HttpStatus.CREATED).body(interview);
    }
    
    @Autowired
    public void setInterviewsService(InterviewsService interviewsService) {
        this.interviewsService = interviewsService;
    }

    public InterviewsService getInterviewsService() {
        return interviewsService;
    }
}
