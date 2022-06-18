package com.mas.pjatk.masfinalproject.controller;

import com.mas.pjatk.masfinalproject.model.Patient;
import com.mas.pjatk.masfinalproject.model.command.CreatePatientCommand;
import com.mas.pjatk.masfinalproject.model.dto.PatientDto;
import com.mas.pjatk.masfinalproject.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    private final ModelMapper modelMapper;

    @PostMapping()
    @SneakyThrows
    public ResponseEntity addPatient(@RequestBody CreatePatientCommand command) {
        Patient patient = patientService.savePatient(command);
        return new ResponseEntity(modelMapper.map(patient, PatientDto.class), HttpStatus.CREATED);
    }
}
