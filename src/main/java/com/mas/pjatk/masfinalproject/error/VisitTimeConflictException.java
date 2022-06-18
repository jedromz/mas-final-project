package com.mas.pjatk.masfinalproject.error;

import lombok.Value;

@Value
public class VisitTimeConflictException extends Exception {
    private String message;
}
