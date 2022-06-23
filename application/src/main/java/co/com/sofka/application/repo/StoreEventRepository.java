package co.com.sofka.application.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreEventRepository extends MongoRepository<DocumentStoredEvent, String> {
    List<DocumentStoredEvent> findByAggregateRootId(String aggregateRootId);
}
