package co.com.sofka.application.handles;

import co.com.sofka.business.usecases.AddCourseUseCase;
import co.com.sofka.business.usecases.CreateProgramUseCase;
import co.com.sofka.domain.program.command.AddCourseCommand;
import co.com.sofka.domain.program.command.CreateProgramCommand;
import co.com.sofka.generic.EventStoreRepository;
import co.com.sofka.generic.StoredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AddCourseHandle {

    @Autowired
    private AddCourseUseCase addCourseUseCase;
    @Autowired
    private EventStoreRepository repository;
    @Autowired
    private StoredEvent.EventSerializer eventSerializer;

    @EventListener
    void handleAddCourse(AddCourseCommand command) {
        var events = addCourseUseCase.apply(command);
        events.forEach(domainEvent -> {
            var stored = StoredEvent.wrapEvent(domainEvent, eventSerializer);
            repository.saveEvent("program", domainEvent.getAggregateId(), stored);
        });
        //2. emitir el evento

    }

}
