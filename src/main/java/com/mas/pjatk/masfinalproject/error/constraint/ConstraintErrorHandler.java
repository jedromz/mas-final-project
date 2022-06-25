package com.mas.pjatk.masfinalproject.error.constraint;

public interface ConstraintErrorHandler {
    String constraintName();

    String message();

    String field();
}
