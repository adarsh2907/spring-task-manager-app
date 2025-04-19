package com.devstack.taskmanager.service;

import com.devstack.taskmanager.dao.TaskRepository;
import com.devstack.taskmanager.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository=taskRepository;
    }

    @Transactional
    public boolean completeTask(int taskId){
        Optional<Task> taskOptional=taskRepository.findById(taskId);
        if(taskOptional.isPresent()){
            Task task=taskOptional.get();
            task.setCompletedAt(LocalDateTime.now());
            task.setCompleted(true);
            taskRepository.save(task);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean addTask(Task task){
        try {
            taskRepository.save(task);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Transactional
    public boolean updateTask(Task updatedTask){
        Optional<Task> optionalTask=taskRepository.findById(updatedTask.getId());
        if (optionalTask.isPresent()){
            Task existingTask=optionalTask.get();

            // Preserve field not in the form
            updatedTask.setCreatedAt(existingTask.getCreatedAt());
            updatedTask.setUser(existingTask.getUser());

            updatedTask.setUpdatedAt(LocalDateTime.now());

            taskRepository.save(updatedTask);
            return true;
        }
        return false;
    }
}
