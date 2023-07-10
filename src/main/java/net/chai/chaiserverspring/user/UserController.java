package net.chai.chaiserverspring.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import net.chai.chaiserverspring.user.model.User;
import net.chai.chaiserverspring.user.model.UserCreateRequest;
import net.chai.chaiserverspring.user.model.UserDeleteRequest;
import net.chai.chaiserverspring.user.model.UserUpdateRequest;
import net.chai.chaiserverspring.user.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public User create(@RequestBody @Valid UserCreateRequest request) {
        return userService.create(request);
    }

    @GetMapping("{id}")
    public User read(@PathVariable @NotNull UUID id) {
        return userService.get(id);
    }

    @PatchMapping
    public User update(@RequestBody @Valid UserUpdateRequest request) {
        return userService.update(request);
    }

    @DeleteMapping
    public void delete(@RequestBody @Valid UserDeleteRequest request) {
        userService.delete(request);
    }
}
