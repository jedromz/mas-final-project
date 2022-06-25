package com.mas.pjatk.masfinalproject.controller;

import com.mas.pjatk.masfinalproject.model.Visit;
import com.mas.pjatk.masfinalproject.model.command.CreateVisitCommand;
import com.mas.pjatk.masfinalproject.model.command.PossibleVisitsCommand;
import com.mas.pjatk.masfinalproject.model.dto.DateTimeDto;
import com.mas.pjatk.masfinalproject.model.dto.VisitDto;
import com.mas.pjatk.masfinalproject.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/visits")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VisitController {

    private final VisitService visitService;
    private final ModelMapper modelMapper;

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<VisitDto>> getVisits() {
        return ResponseEntity.ok(visitService.findAllVisits().stream().map(v -> modelMapper.map(v, VisitDto.class)).toList());
    }

    @SneakyThrows
    @PostMapping
    public ResponseEntity<VisitDto> addVisit(@RequestBody @Valid CreateVisitCommand command) {
        Visit visit = visitService.saveVisit(command);
        return ResponseEntity.ok(modelMapper.map(visit, VisitDto.class));
    }

    @PostMapping("/possible-visits")
    @SneakyThrows
    public ResponseEntity<Set<DateTimeDto>> getPossibleVisits(@RequestBody @Valid PossibleVisitsCommand command) {

        Set<DateTimeDto> possibleVisits = visitService.findPossibleVisits(command.getPatientId(), command.getVetId(), command.getDate(), command.getSize(), command.getDurationInMinutes());
        return ResponseEntity.ok(possibleVisits);
    }
}
