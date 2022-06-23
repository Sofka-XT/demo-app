package co.com.sofka.application;

import co.com.sofka.business.usecases.AddCourseUseCase;
import co.com.sofka.business.usecases.CreateProgramUseCase;
import co.com.sofka.generic.EventStoreRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CreateProgramUseCase createProgramUseCase(){
        return new CreateProgramUseCase();
    }

    @Bean
    public AddCourseUseCase addCourseUseCase(EventStoreRepository repository){
        return new AddCourseUseCase(repository);
    }


}
