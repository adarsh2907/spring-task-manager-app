package com.devstack.taskmanager.controller;

import com.devstack.taskmanager.dao.AppUserRepository;
import com.devstack.taskmanager.dao.TaskRepository;
import com.devstack.taskmanager.entity.AppUser;
import com.devstack.taskmanager.entity.Task;
import com.devstack.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private TaskService taskService;

    private TaskRepository taskRepository;

    private AppUserRepository appUserRepository;

    private AppUser getLoggedInUser(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        return appUserRepository.findByUsername(auth.getName());
    }

    @Autowired
    public HomeController(TaskService taskService, TaskRepository taskRepository, AppUserRepository appUserRepository){
        this.taskService=taskService;
        this.taskRepository=taskRepository;
        this.appUserRepository=appUserRepository;
    }

    @GetMapping("/")
    public String getTasks(Model model) {
        AppUser appUser=getLoggedInUser();
        List<Task> tasks = taskRepository.findByUserAndCompletedFalse(appUser);
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", new Task()); // for modal
        return "/index";
    }

    @PostMapping("/deletetask")
    public String deleteTask(@RequestParam ("taskId") int taskId){
        if(taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        }
        return "redirect:/";
    }

    @PostMapping("/completetask")
    public String completeTask(@RequestParam ("taskId") int taskId){
        taskService.completeTask(taskId);
        return "redirect:/";
    }

    @GetMapping("/completedTask")
    public String getCompletedTask(Model model){
        AppUser appUser=getLoggedInUser();
        List<Task> tasks=taskRepository.findByUserAndCompletedTrue(appUser);
        model.addAttribute("tasks", tasks);
        return "/completedTask";
    }

    @PostMapping("/addTask")
    public String addTask(@ModelAttribute ("task") Task task){
        AppUser user=getLoggedInUser();
        task.setUser(user);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        taskService.addTask(task);
        return  "redirect:/";
    }


    @GetMapping("/updateTask")
    public String showFormForUpdate(@RequestParam ("id") int id, Model model){
        Optional<Task> optionalTask=taskRepository.findById(id);
        if(optionalTask.isPresent()) {
            Task task=optionalTask.get();
            model.addAttribute("task", task);
        }
        return "/editTask";
    }

    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task){
        taskService.updateTask(task);
        return "redirect:/";
    }

}
