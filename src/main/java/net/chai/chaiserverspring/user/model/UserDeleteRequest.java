package net.chai.chaiserverspring.user.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import net.chai.chaiserverspring.common.model.CrudDeleteRequest;

import java.util.UUID;

public record UserDeleteRequest(
        @NotNull UUID id,
        @Min(1) long version
) implements CrudDeleteRequest<User> {

    @Override
    public User toEntity() {
        return new User(id, version, null, null, null);
    }
}
