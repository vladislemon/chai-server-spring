package net.chai.chaiserverspring.user.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import net.chai.chaiserverspring.common.model.CrudUpdateRequest;

import java.time.Instant;
import java.util.UUID;

public record UserUpdateRequest(
        @NotNull UUID id,
        @Min(1) long version,
        @NotNull @Pattern(regexp = "[a-zA-Z0-9_-]{4,32}+") String name
) implements CrudUpdateRequest<User> {

    @Override
    public User toEntity(User oldEntity) {
        return new User(oldEntity.id(), version, name, oldEntity.createdAt(), Instant.now());
    }
}
