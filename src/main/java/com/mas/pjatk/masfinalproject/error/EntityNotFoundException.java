package com.mas.pjatk.masfinalproject.error;

import lombok.Value;

@Value
public class EntityNotFoundException extends Exception {
    private String key;
    private String value;
}
