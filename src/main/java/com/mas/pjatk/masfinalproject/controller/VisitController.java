package com.mas.pjatk.masfinalproject.controller;

import com.mas.pjatk.masfinalproject.model.Visit;
import com.mas.pjatk.masfinalproject.model.command.CreatePatientCommand;
import com.mas.pjatk.masfinalproject.model.command.CreateVisitCommand;
import com.mas.pjatk.masfinalproject.model.dto.EmployeeDto;
import com.mas.pjatk.masfinalproject.model.dto.VisitDto;
import com.mas.pjatk.masfinalproject.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;
    private final ModelMapper modelMapper;

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<VisitDto>> getVisits() {
        return ResponseEntity.ok(visitService.findAllVisits().stream()
                .map(v -> modelMapper.map(v, VisitDto.class))
                .toList());
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<VisitDto> addVisit(CreateVisitCommand command) {
        Visit visit = visitService.saveVisit(command);
        return ResponseEntity.ok(modelMapper.map(visit,VisitDto.class));
    }
}
