package com.masai.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.Module.AppUser;
import com.masai.Repository.AppUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        AppUser user = appUserRepository.findByEmail(username).orElseThrow(() -> new BadCredentialsException("User Not Found With Email: "+ username));

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new User(user.getEmail(), user.getPassword(), authorities);
    }
    
}
