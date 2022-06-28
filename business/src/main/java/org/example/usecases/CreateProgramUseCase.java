package org.example.usecases;


import org.example.domain.program.Program;
import org.example.domain.program.command.CreateProgramCommand;
import org.example.generic.DomainEvent;

import java.util.List;
import java.util.function.Function;

public class CreateProgramUseCase  implements Function<CreateProgramCommand, List<DomainEvent>> {
    @Override
    public List<DomainEvent> apply(CreateProgramCommand command) {
        var program = new Program(command.getProgramId(), command.getName());
        return program.getUncommittedChanges();
    }
}
