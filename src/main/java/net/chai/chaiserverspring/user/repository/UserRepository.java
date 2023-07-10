package net.chai.chaiserverspring.user.repository;

import net.chai.chaiserverspring.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
}
