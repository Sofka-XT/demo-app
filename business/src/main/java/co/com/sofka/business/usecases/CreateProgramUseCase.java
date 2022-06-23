package co.com.sofka.business.usecases;


import co.com.sofka.domain.program.Program;
import co.com.sofka.domain.program.command.CreateProgramCommand;
import co.com.sofka.generic.DomainEvent;

import java.util.List;
import java.util.function.Function;

public class CreateProgramUseCase  implements Function<CreateProgramCommand, List<DomainEvent>> {
    @Override
    public List<DomainEvent> apply(CreateProgramCommand command) {
        var program = new Program(command.getProgramId(), command.getName());
        return program.getUncommittedChanges();
    }
}
