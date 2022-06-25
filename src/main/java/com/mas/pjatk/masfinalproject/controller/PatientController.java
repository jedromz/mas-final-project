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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PatientController {
    private final PatientService patientService;
    private final ModelMapper modelMapper;

    @PostMapping()
    @SneakyThrows
    public ResponseEntity addPatient(@RequestBody @Valid CreatePatientCommand command) {
        Patient patient = patientService.savePatient(command);
        return new ResponseEntity(modelMapper.map(patient, PatientDto.class), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        return ResponseEntity.ok(patientService.findAll()
                .stream()
                .map(p -> modelMapper.map(p, PatientDto.class))
                .toList());
    }

    @GetMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<PatientDto> getPatient(@PathVariable Long id) {
        Patient patient = patientService.findById(id);
        return ResponseEntity.ok(modelMapper.map(patient, PatientDto.class));
    }
}
