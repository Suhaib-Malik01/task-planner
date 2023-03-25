package com.masai.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.masai.Dto.AppUserDto;
import com.masai.Exception.UserException;
import com.masai.Module.AppUser;
import com.masai.Repository.AppUserRepository;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;



    @Override
    public AppUserDto addUser(AppUserDto appUserDto) {

        AppUser user = new AppUser();
        user.setEmail(appUserDto.getEmail());
        user.setName(appUserDto.getName());
        user.setPassword(appUserDto.getPassword());

        user = appUserRepository.save(user);

        appUserDto.setId(user.getId());

        return appUserDto;
    }

    @Override
    public AppUserDto deleteUser() throws UserException{

        String email = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            email = authentication.getPrincipal().toString();
        } else {
            throw new UserException("The User is not Logged in");
        }

        AppUser user = appUserRepository.findByEmail(email).orElseThrow(() -> new UserException("Login Expired"));

        appUserRepository.delete(user);

        return new AppUserDto(user.getId(), user.getName(), user.getEmail(), null);
    }
    
}
