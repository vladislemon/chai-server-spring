package net.chai.chaiserverspring.user.model;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import net.chai.chaiserverspring.common.model.CrudCreateRequest;

import java.time.Instant;

public record UserCreateRequest(
        @NotNull @Pattern(regexp = "\\A[a-zA-Z0-9_-]{4,32}\\z") String name
) implements CrudCreateRequest<User> {

    @Override
    public User toEntity() {
        Instant now = Instant.now();
        return new User(UuidCreator.getTimeOrderedEpoch(), 0, name, now, now);
    }
}
