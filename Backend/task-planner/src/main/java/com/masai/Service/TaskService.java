package com.masai.Service;

import com.masai.Exception.TaskException;
import com.masai.Exception.UserException;
import com.masai.Module.Task;

public interface TaskService {

    public Task addTask(Task task);
    
    public Task deleteTask(Integer id) throws TaskException, UserException;

    public Task changeAssignee(Integer taskId,Integer userId) throws UserException, TaskException;

    public Task changeStatus(Integer taskId,Integer statusId) throws TaskException, UserException;
}
