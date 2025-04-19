package com.devstack.taskmanager.controller;

import com.devstack.taskmanager.dao.AppUserRepository;
import com.devstack.taskmanager.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private AppUserRepository appUserRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder){
        this.appUserRepository=appUserRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("appUser", new AppUser());
        return "register";
    }

    @PostMapping("/register/save")
    public String saveUser(@ModelAttribute AppUser appUser, Model model){

        try {
            if(appUserRepository.findByUsername(appUser.getUsername())!=null){
                model.addAttribute("error", "Username already exists!");
                return "register";
            }
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            appUserRepository.save(appUser);
            return "redirect:/showLoginPage";
        } catch (Exception e){
            model.addAttribute("error", "An error occurred while saving the user:  "+e.getMessage());
            return "register";
        }

    }
}
