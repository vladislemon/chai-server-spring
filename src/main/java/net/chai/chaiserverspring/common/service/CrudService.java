package net.chai.chaiserverspring.common.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chai.chaiserverspring.common.exception.NotFoundException;
import net.chai.chaiserverspring.common.model.CrudCreateRequest;
import net.chai.chaiserverspring.common.model.CrudDeleteRequest;
import net.chai.chaiserverspring.common.model.CrudUpdateRequest;
import net.chai.chaiserverspring.common.model.Entity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
public abstract class CrudService<T extends Entity> {
    private final CrudRepository<T, UUID> repository;

    public T create(CrudCreateRequest<T> request) {
        log.info("{}", request);
        return repository.save(request.toEntity());
    }

    public T get(UUID id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public T update(CrudUpdateRequest<T> request) {
        log.info("{}", request);
        T oldEntity = get(request.id());
        return repository.save(request.toEntity(oldEntity));
    }

    public void delete(CrudDeleteRequest<T> request) {
        log.info("{}", request);
        repository.delete(request.toEntity());
    }
}
