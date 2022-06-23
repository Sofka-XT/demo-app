package co.com.sofka.application;

import co.com.sofka.business.usecases.AddCourseUseCase;
import co.com.sofka.business.usecases.CreateProgramUseCase;
import co.com.sofka.generic.EventStoreRepository;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;

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

    public ReactiveMongoTemplate mongoTemplateQueries(@Value("${spring.queries.uri}") String uri)  {
        ConnectionString connectionString = new ConnectionString(uri);
        return new ReactiveMongoTemplate(new SimpleReactiveMongoDatabaseFactory(connectionString));
    }


}
