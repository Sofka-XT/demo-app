package co.com.sofka.application.handles;

import co.com.sofka.business.usecases.CreateProgramUseCase;
import co.com.sofka.domain.program.command.CreateProgramCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CreateProgramHandle {

    @Autowired
    private CreateProgramUseCase createProgramUseCase;

    @EventListener
    void handleCreateProgram(CreateProgramCommand command) {
        var events = createProgramUseCase.apply(command);
        //1. guardar el evento

    }

}
