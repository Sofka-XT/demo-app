package org.example.domain.program.event;


import org.example.generic.DomainEvent;

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
