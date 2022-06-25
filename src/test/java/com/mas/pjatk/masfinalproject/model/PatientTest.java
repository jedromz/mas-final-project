package com.mas.pjatk.masfinalproject.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void age() {
        int age = 10;
        Patient patient = Patient.builder().birthDate(LocalDate.now().minusYears(age)).build();
        assertEquals(age, patient.age());
    }
}
