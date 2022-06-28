package org.example.usecases;


import org.example.domain.program.Program;
import org.example.domain.program.command.AddCourseCommand;
import org.example.generic.DomainEvent;
import org.example.generic.EventStoreRepository;

import java.util.List;
import java.util.function.Function;

public class AddCourseUseCase implements Function<AddCourseCommand, List<DomainEvent>> {

    private final EventStoreRepository repository;

    public AddCourseUseCase(EventStoreRepository repository){
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(AddCourseCommand command) {
        var events = repository.getEventsBy("program", command.getProgramId());
        var program = Program.from(command.getProgramId(), events);

        program.addCourse(command.getCourseId(), command.getName(), command.getCategories());
        return program.getUncommittedChanges();
    }
}
