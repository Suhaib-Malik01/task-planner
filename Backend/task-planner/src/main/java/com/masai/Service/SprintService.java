package com.masai.Service;

import com.masai.Exception.SprintException;
import com.masai.Module.Sprint;

public interface SprintService {
    
    public Sprint addSprint(Sprint sprint);

    public Sprint deleteSprint(Integer id) throws SprintException;
}
