package net.chai.chaiserverspring.user.service;

import lombok.extern.slf4j.Slf4j;
import net.chai.chaiserverspring.common.service.CrudService;
import net.chai.chaiserverspring.user.model.User;
import net.chai.chaiserverspring.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService extends CrudService<User> {

    @Autowired
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }
}
