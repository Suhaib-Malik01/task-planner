package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Exception.TaskException;
import com.masai.Exception.UserException;
import com.masai.Module.Task;
import com.masai.Service.TaskService;

@RestController
@RequestMapping("/user")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @PostMapping("/task")
    public ResponseEntity<Task> addTask(@RequestBody Task task){

        return new ResponseEntity<>(taskService.addTask(task),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Integer id) throws TaskException, UserException{

        return new ResponseEntity<>(taskService.deleteTask(id),HttpStatus.OK);
    }

    @PutMapping("/task/{id}/{userId}")
    public ResponseEntity<Task> changeAssignee(@PathVariable Integer id,@PathVariable Integer userId) throws UserException, TaskException{

        return new ResponseEntity<>(taskService.changeAssignee(id, userId),HttpStatus.OK);
    }

    @PutMapping("/task/status/{id}/{statusId}")
    public ResponseEntity<Task> changeStatus(@PathVariable Integer id,@PathVariable Integer statusId) throws UserException, TaskException{

        return new ResponseEntity<>(taskService.changeStatus(id, statusId),HttpStatus.OK);
    }
    
}
