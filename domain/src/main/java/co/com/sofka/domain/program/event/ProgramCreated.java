package co.com.sofka.domain.program.event;


import co.com.sofka.generic.DomainEvent;

public class ProgramCreated extends DomainEvent {

    private final String name;

    public ProgramCreated(String name) {
        super("sofkau.program.programcreated");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
