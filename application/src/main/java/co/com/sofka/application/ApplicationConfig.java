package co.com.sofka.application;

import co.com.sofka.application.repo.GsonEventSerializer;
import co.com.sofka.business.usecases.AddCourseUseCase;
import co.com.sofka.business.usecases.CreateProgramUseCase;
import co.com.sofka.generic.EventStoreRepository;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;

import java.net.URI;

@Configuration
public class ApplicationConfig {
    public static final String EXCHANGE = "scoreextraction";

    @Bean
    public CreateProgramUseCase createProgramUseCase(){
        return new CreateProgramUseCase();
    }

    @Bean
    public AddCourseUseCase addCourseUseCase(EventStoreRepository repository){
        return new AddCourseUseCase(repository);
    }

    @Bean("queries")
    public MongoTemplate mongoTemplateQueries(@Value("${spring.queries.uri}") String uri)  {
        ConnectionString connectionString = new ConnectionString(uri);
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(connectionString));
    }

    @Bean("commands")
    public MongoTemplate mongoTemplateCommand(@Value("${spring.commands.uri}") String uri)  {
        ConnectionString connectionString = new ConnectionString(uri);
        return new MongoTemplate(new SimpleMongoClientDatabaseFactory(connectionString));
    }

    @Bean
    public RabbitTemplate rabbitTemplate(@Value("${spring.bus.uri}") String uri) {
        return new RabbitTemplate(new CachingConnectionFactory(URI.create(uri)));
    }

    @Bean
    public RabbitAdmin rabbitAdmin(RabbitTemplate rabbitTemplate) {
        var admin =  new RabbitAdmin(rabbitTemplate);
        admin.declareExchange(new TopicExchange(EXCHANGE));
        return admin;
    }


}
