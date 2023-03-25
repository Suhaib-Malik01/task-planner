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
import com.masai.Module.AppUser;
import com.masai.Repository.AppUserRepository;
import com.masai.Service.AppUserService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserService appUserService;

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
}
