package com.masai.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.Dto.AppUserDto;
import com.masai.Exception.TaskException;
import com.masai.Exception.UserException;
import com.masai.Module.AppUser;
import com.masai.Module.Sprint;
import com.masai.Module.Task;
import com.masai.Repository.AppUserRepository;
import com.masai.Service.AppUserService;
import com.masai.Service.SprintService;
import com.masai.Service.TaskService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SprintService sprintService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AppUserDto> registerUser(@RequestBody AppUserDto user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return new ResponseEntity<>(appUserService.addUser(user), HttpStatus.ACCEPTED);
    }

    @GetMapping("/signIn")
    public ResponseEntity<AppUser> getLoggedInUserDetailsHandler(Authentication auth) {

        AppUser user = appUserRepository.findByEmail(auth.getName())
                .orElseThrow(() -> new BadCredentialsException("Invalid Username or password"));

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

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

    @PostMapping("/sprint")
    public ResponseEntity<Sprint> addSprint(@RequestBody Sprint sprint){

        return new ResponseEntity<>(sprintService.addSprint(sprint),HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/sprint/{id}")

    public ResponseEntity<Sprint> deleteSprint(@RequestBody Integer id){

        return new ResponseEntity<>(sprintService.deleteSprint(id),HttpStatus.ACCEPTED);
    }
}
