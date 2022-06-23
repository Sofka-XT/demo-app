package co.com.sofka.application.handles;

import co.com.sofka.business.usecases.CreateProgramUseCase;
import co.com.sofka.domain.program.command.CreateProgramCommand;
import co.com.sofka.generic.DomainEvent;
import co.com.sofka.generic.EventStoreRepository;
import co.com.sofka.generic.StoredEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class CreateProgramHandle {

    @Autowired
    private CreateProgramUseCase createProgramUseCase;
    @Autowired
    private EventStoreRepository repository;
    @Autowired
    private StoredEvent.EventSerializer eventSerializer;

    @EventListener
    void handleCreateProgram(CreateProgramCommand command) {
        var events = createProgramUseCase.apply(command);
        events.forEach(domainEvent -> {
            var stored = StoredEvent.wrapEvent(domainEvent, eventSerializer);
            repository.saveEvent("program", domainEvent.getAggregateId(), stored);
        });
        //2. emitir el evento
    }

}
