package co.com.sofka.application;

import co.com.sofka.domain.program.command.AddCourseCommand;
import co.com.sofka.domain.program.command.CreateProgramCommand;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommandController {

    private final ApplicationEventPublisher publisher;

    public CommandController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }


    @PostMapping("/createProgram")
    public void createProgram(@RequestBody CreateProgramCommand command) {
        publisher.publishEvent(command);
    }

    @PostMapping("/addCourse")
    public void createProgram(@RequestBody AddCourseCommand command) {
        publisher.publishEvent(command);
    }
}
