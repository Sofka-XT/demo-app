package co.com.sofka.application.handles;

import co.com.sofka.business.usecases.AddCourseUseCase;
import co.com.sofka.business.usecases.CreateProgramUseCase;
import co.com.sofka.domain.program.command.AddCourseCommand;
import co.com.sofka.domain.program.command.CreateProgramCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AddCourseHandle {

    @Autowired
    private AddCourseUseCase addCourseUseCase;

    @EventListener
    void handleAddCourse(AddCourseCommand command) {
        var events = addCourseUseCase.apply(command);
        //1. guardar el evento

    }

}
