package com.iitu.rest.controllers;

import com.iitu.entities.IntervieweeDocuments;
import com.iitu.services.implemented.IntervieweeDocumentsService;
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
public class IntervieweeDocumentsController {

    private IntervieweeDocumentsService intervieweeDocumentsService;

    @GetMapping(path = "/interviewee/documents", produces = "application/json")
    @ResponseBody
    public ResponseEntity getAll(){
        List intervieweeDocuments = intervieweeDocumentsService.getAll();
        if(intervieweeDocuments.isEmpty()){
            return new ResponseEntity<List<IntervieweeDocuments>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<IntervieweeDocuments>>(intervieweeDocuments, HttpStatus.OK);
    }

    @GetMapping(path = "/interviewee/documents/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity get(@PathVariable Long id){
        IntervieweeDocuments intervieweeDocuments = intervieweeDocumentsService.getById(id);
        if (intervieweeDocuments== null) {
            System.out.println("User with id " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        return new ResponseEntity<>(intervieweeDocuments, HttpStatus.OK);
    }


    @PostMapping(path = "/interviewee/documents")
    public ResponseEntity add(@RequestBody  @Valid IntervieweeDocuments intervieweeDocuments){
        intervieweeDocumentsService.create(intervieweeDocuments);
        return ResponseEntity.status(HttpStatus.CREATED).body(intervieweeDocuments);
    }


    @DeleteMapping(path = "/interviewee/documents/{id}", produces = "application/json")
    public ResponseEntity delete(@PathVariable Long id){
        IntervieweeDocuments intervieweeDocument= intervieweeDocumentsService.getById(id);
        if(intervieweeDocument != null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        intervieweeDocumentsService.delete(intervieweeDocument.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/interviewee/documents", produces = "application/json")
    public ResponseEntity update(
            @RequestParam(value = "id") Long id ,@RequestBody @Valid IntervieweeDocuments intervieweeDocument){

        IntervieweeDocuments tempIntervieweeDocuments = intervieweeDocumentsService.getById(id);
        if(tempIntervieweeDocuments  == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(MessageUtils.getMessageJSON(HttpStatus.NOT_FOUND.getReasonPhrase()));
        }
        intervieweeDocument.setId(id);
        intervieweeDocumentsService.update(intervieweeDocument);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Autowired
    public void setIntervieweeDocumentsService(IntervieweeDocumentsService intervieweeDocumentsService) {
        this.intervieweeDocumentsService = intervieweeDocumentsService;
    }

    public IntervieweeDocumentsService getIntervieweeDocumentsService() {
        return intervieweeDocumentsService;
    }
}
