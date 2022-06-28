package org.example.domain.program;


import org.example.generic.EventChange;
import org.example.domain.program.event.CourseAssigned;
import org.example.domain.program.event.ProgramCreated;
import org.example.domain.program.event.ScoreAssigned;

import java.util.HashMap;

public class ProgramEventChange implements EventChange {
    public ProgramEventChange(Program program){
        listener((ProgramCreated event)-> {
            program.name = event.getName();
            program.scores = new HashMap<>();
            program.courses =  new HashMap<>();
        });
        listener((CourseAssigned event) -> {
            var course =  new Course(event.getCourseId(), event.getName());
            event.getCategories().forEach(course::addCategory);
            program.courses.put(event.getCourseId(), course);
        });
        listener((ScoreAssigned event) -> {
            var scoreId = event.getCourseId()+event.getCategory()+event.getUser();
            program.scores.put(scoreId, new Score(
                    scoreId, event.getUser(), event.getValue(), event.getDate()
            ));
        });
    }
}
