package com.devstack.taskmanager.service;

import com.devstack.taskmanager.dao.AppUserRepository;
import com.devstack.taskmanager.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private AppUserRepository appUserRepository;

    @Autowired
    public CustomOAuth2UserService(AppUserRepository appUserRepository){
        this.appUserRepository=appUserRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user=super.loadUser(userRequest);
        String email=user.getAttribute("email");

        AppUser existingUser=appUserRepository.findByUsername(email);
        if(existingUser==null){
            AppUser newUser=new AppUser();
            newUser.setUsername(email);
            newUser.setPassword(""); // or null
            appUserRepository.save(newUser);
        }
        return user;
    }
}
