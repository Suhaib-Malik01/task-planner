package com.masai.Service;

import java.util.List;

import com.masai.Exception.SprintException;
import com.masai.Module.Sprint;
import com.masai.Module.Task;

public interface SprintService {
    
    public Sprint addSprint(Sprint sprint);

    public Sprint deleteSprint(Integer id) throws SprintException;

    public List<Task> getAllTask(Integer id) throws SprintException;
    
}
