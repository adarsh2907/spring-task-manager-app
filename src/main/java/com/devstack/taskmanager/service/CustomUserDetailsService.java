package com.devstack.taskmanager.service;

import com.devstack.taskmanager.dao.AppUserRepository;
import com.devstack.taskmanager.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    public CustomUserDetailsService (AppUserRepository appUserRepository){
        this.appUserRepository=appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=appUserRepository.findByUsername(username);
        if(appUser==null){
            throw  new UsernameNotFoundException("User not found");
        }

        return new User(
                appUser.getUsername(),
                appUser.getPassword(),
                Collections.emptyList()
        );
    }
}
