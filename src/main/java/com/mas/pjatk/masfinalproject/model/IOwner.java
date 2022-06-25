package com.mas.pjatk.masfinalproject.model;

import java.util.List;
import java.util.Set;

public interface IOwner {
    String getFirstname();
    String getLastname();
    String getEmail();
    String getMobile();
    Set<Patient> getPatients();
}
