package com.mas.pjatk.masfinalproject.controller;

import com.mas.pjatk.masfinalproject.model.command.CreateVisitCommand;
import com.mas.pjatk.masfinalproject.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping
    public String addVisit(CreateVisitCommand command) {
        visitService.saveVisit(command);
        return "OK";
    }
}
