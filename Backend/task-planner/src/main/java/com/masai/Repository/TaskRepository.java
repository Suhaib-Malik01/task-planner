package com.masai.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.Module.Task;

public interface TaskRepository extends JpaRepository<Task,Integer>{
    
}
