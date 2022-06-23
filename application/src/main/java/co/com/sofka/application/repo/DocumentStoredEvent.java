package co.com.sofka.application.repo;

import co.com.sofka.generic.StoredEvent;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Document
public class DocumentStoredEvent extends StoredEvent {
    @Id
    private String id;

    public DocumentStoredEvent(){ }

    public DocumentStoredEvent(String typeName, Date occurredOn, String eventBody) {
        super(typeName, occurredOn, eventBody);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
