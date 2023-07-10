package net.chai.chaiserverspring.common.model;

public interface CrudDeleteRequest<T extends Entity> {

    T toEntity();
}
