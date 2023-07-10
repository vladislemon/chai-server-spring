package net.chai.chaiserverspring.common.model;

import java.util.UUID;

public interface CrudUpdateRequest<T extends Entity> {

    T toEntity(T oldEntity);

    UUID id();
}
