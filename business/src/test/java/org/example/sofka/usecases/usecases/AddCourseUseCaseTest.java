package org.example.sofka.usecases.usecases;

import org.example.domain.program.command.AddCourseCommand;
import org.example.domain.program.event.CourseAssigned;
import org.example.domain.program.event.ProgramCreated;
import org.example.generic.DomainEvent;
import org.example.generic.EventStoreRepository;
import org.example.usecases.AddCourseUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class AddCourseUseCaseTest {

    @Mock
    EventStoreRepository repository;

    @InjectMocks
    AddCourseUseCase useCase;


    @Test
    void agregarCurso(){
        var command = new AddCourseCommand();
        command.setCategories(new ArrayList<>());
        command.getCategories().add("JAVA");
        command.getCategories().add("C#");
        command.setName("Programación Orientada a Objetos");
        command.setProgramId("COL-C2-2022");
        command.setCourseId("FFF");

        when(repository.getEventsBy("program","COL-C2-2022"));

        var result = useCase.apply(command);
        var event = (CourseAssigned)result.get(0);

        Assertions.assertEquals("Programación Orientada a Objetos", event.getName());
        Assertions.assertEquals("[JAVA, C#]", event.getCategories().toString());
        Assertions.assertEquals("FFF", event.getCourseId());

    }

    private List<DomainEvent> history() {
        return List.of(new ProgramCreated("Colombia 2022"));
    }
}