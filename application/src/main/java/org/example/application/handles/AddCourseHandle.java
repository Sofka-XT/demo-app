package org.example.application.handles;

import co.com.sofka.business.usecases.AddCourseUseCase;
import co.com.sofka.domain.program.command.AddCourseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AddCourseHandle extends HandleBase{

    @Autowired
    private AddCourseUseCase addCourseUseCase;

    @EventListener
    public void handleAddCourse(AddCourseCommand command) {
        var events = addCourseUseCase.apply(command);
        emit(events);
    }

}
