package net.chai.chaiserverspring.common.model;

import java.util.UUID;

public interface Entity {

    UUID id();

    long version();
}
