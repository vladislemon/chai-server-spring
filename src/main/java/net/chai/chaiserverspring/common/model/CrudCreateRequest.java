package net.chai.chaiserverspring.common.model;

public interface CrudCreateRequest<T extends Entity> {

    T toEntity();
}
