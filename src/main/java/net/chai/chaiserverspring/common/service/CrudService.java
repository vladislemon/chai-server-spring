package net.chai.chaiserverspring.common.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.chai.chaiserverspring.common.exception.NotFoundException;
import net.chai.chaiserverspring.common.model.CrudCreateRequest;
import net.chai.chaiserverspring.common.model.CrudDeleteRequest;
import net.chai.chaiserverspring.common.model.CrudUpdateRequest;
import net.chai.chaiserverspring.common.model.Entity;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.relational.core.conversion.DbActionExecutionException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@AllArgsConstructor
public abstract class CrudService<T extends Entity> {
    private static final Pattern DUPLICATE_KEY_PATTERN = Pattern.compile("Key \\((.*)\\)=\\((.*)\\) already exists");
    private final CrudRepository<T, UUID> repository;

    public T create(CrudCreateRequest<T> request) {
        log.info("{}", request);
        return save(request.toEntity());
    }

    public T get(UUID id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    public T update(CrudUpdateRequest<T> request) {
        log.info("{}", request);
        T oldEntity = get(request.id());
        return save(request.toEntity(oldEntity));
    }

    public void delete(CrudDeleteRequest<T> request) {
        log.info("{}", request);
        repository.delete(request.toEntity());
    }

    protected T save(T entity) {
        try {
            return repository.save(entity);
        } catch (DbActionExecutionException e) {
            if (e.getCause() instanceof DuplicateKeyException) {
                Matcher matcher = DUPLICATE_KEY_PATTERN.matcher(e.getCause().getMessage());
                if (matcher.find()) {
                    throw conflict(String.format("%s with %s '%s' already exists", entity.getClass().getSimpleName(), matcher.group(1), matcher.group(2)));
                }
                throw conflict(String.format("Similar %s already exists", entity.getClass().getSimpleName()));
            }
            throw e;
        } catch (OptimisticLockingFailureException e) {
            throw conflict(String.format("%s has been modified from another source", entity.getClass().getSimpleName()));
        }
    }

    protected static ErrorResponseException conflict(String detail) {
        ErrorResponseException exception = new ErrorResponseException(HttpStatus.CONFLICT);
        exception.setDetail(detail);
        return exception;
    }
}
