package com.masai.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Dto.AppUserDto;
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
        user.setFirstName(appUserDto.getFirstName());
        user.setLastName(appUserDto.getLastName());
        user.setPassword(appUserDto.getPassword());

        user = appUserRepository.save(user);

        appUserDto.setId(user.getId());

        return appUserDto;
    }
    
}
