package com.masai.Service;

import com.masai.Dto.AppUserDto;
import com.masai.Exception.UserException;
import com.masai.Module.Sprint;


public interface AppUserService {
    
    public AppUserDto addUser(AppUserDto appUserDto);

    public AppUserDto deleteUser() throws UserException;
}
