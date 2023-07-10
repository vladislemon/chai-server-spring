package net.chai.chaiserverspring.user.model;

import net.chai.chaiserverspring.common.model.Entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.Instant;
import java.util.UUID;

public record User(
        @Id UUID id,
        @Version long version,
        String name,
        Instant createdAt,
        Instant lastModifiedAt
) implements Entity {
}
