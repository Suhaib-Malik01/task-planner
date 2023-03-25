package com.masai.Service;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.masai.Enum.taskStatus;
import com.masai.Exception.TaskException;
import com.masai.Exception.UserException;
import com.masai.Module.AppUser;
import com.masai.Module.Task;
import com.masai.Repository.AppUserRepository;
import com.masai.Repository.TaskRepository;

public class TaskServiceImpl implements TaskService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task addTask(Task task) {

        task.getAssignee().getAssignedtasks().add(task);

        return taskRepository.save(task);
    }

    @Override
    public Task deleteTask(Integer id) throws TaskException, UserException {
        String email = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            email = authentication.getPrincipal().toString();
        } else {
            throw new UserException("The User is not Logged in");
        }

        AppUser user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("Login Expired"));

        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskException("Task Not found with id: " + id));

        if (user.getId() != task.getCreatedBy().getId()) {
            throw new TaskException("You are Not Authorized To delete This Task");
        }

        taskRepository.delete(task);

        return task;
    }

    @Override
    public Task changeAssignee(Integer taskId, Integer userId) throws UserException, TaskException {

        String email = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            email = authentication.getPrincipal().toString();
        } else {
            throw new UserException("The User is not Logged in");
        }

        AppUser user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("Login Expired"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException("Task Not found with id: " + taskId));

        if (user.getId() != task.getCreatedBy().getId()) {
            throw new TaskException("You are Not Authorized To delete This Task");
        }

        AppUser preUser = task.getAssignee();

        task.setAssignee(userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User Not Found With Id: " + userId)));

        preUser.setAssignedtasks(
                preUser.getAssignedtasks().stream().filter(t -> t.getId() != taskId).collect(Collectors.toList()));

        return task;
    }

    @Override
    public Task changeStatus(Integer taskId, Integer statusId) throws TaskException, UserException {

        String email = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            email = authentication.getPrincipal().toString();
        } else {
            throw new UserException("The User is not Logged in");
        }

        AppUser user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("Login Expired"));

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException("Task Not found with id: " + taskId));

        if (statusId == 1) {
            task.setStatus(taskStatus.ToDo);
        } else if (statusId == 2) {
            task.setStatus(taskStatus.inProgress);
        } else {
            task.setStatus(taskStatus.Completed);
        }

        return task;
    }

}
