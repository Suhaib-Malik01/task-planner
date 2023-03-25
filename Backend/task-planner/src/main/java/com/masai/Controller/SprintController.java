package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.SprintException;
import com.masai.Module.Sprint;
import com.masai.Service.SprintService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class SprintController {

    
    @Autowired
    private SprintService sprintService;

    @PostMapping("/sprint")
    public ResponseEntity<Sprint> addSprint(@RequestBody Sprint sprint){

        return new ResponseEntity<>(sprintService.addSprint(sprint),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/sprint/{id}")
    public ResponseEntity<Sprint> deleteSprint(@RequestBody Integer id) throws SprintException{

        return new ResponseEntity<>(sprintService.deleteSprint(id),HttpStatus.ACCEPTED);
    }

    
    
}
