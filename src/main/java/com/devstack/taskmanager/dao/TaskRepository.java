package com.devstack.taskmanager.dao;

import com.devstack.taskmanager.entity.AppUser;
import com.devstack.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUserAndCompletedFalse(AppUser appUser);

    List<Task> findByUserAndCompletedTrue(AppUser appUser);

}
