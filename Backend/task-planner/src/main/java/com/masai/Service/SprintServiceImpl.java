package com.masai.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.Exception.SprintException;
import com.masai.Module.Sprint;
import com.masai.Repository.SprintRepository;

public class SprintServiceImpl implements SprintService {

    @Autowired
    private SprintRepository sprintRepository;
    
    @Override
    public Sprint addSprint(Sprint sprint) {
        
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint deleteSprint(Integer id) throws SprintException {

        Sprint sprint = sprintRepository.findById(id).orElseThrow(() -> new SprintException("Sprint Not Found with id: "+ id));
        
        sprintRepository.delete(sprint);

        return sprint;
    }
}
