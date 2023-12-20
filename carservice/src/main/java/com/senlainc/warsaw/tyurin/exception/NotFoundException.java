package com.senlainc.warsaw.tyurin.exception;

public class NotFoundException extends Exception {

    private long entityId;

    public NotFoundException(long entityId) {
        this.entityId = entityId;
    }

    public long getEntityId() {
        return entityId;
    }
}
