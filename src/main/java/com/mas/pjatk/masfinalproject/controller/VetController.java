package com.mas.pjatk.masfinalproject.controller;

import com.mas.pjatk.masfinalproject.model.Vet;
import com.mas.pjatk.masfinalproject.model.dto.VetDto;
import com.mas.pjatk.masfinalproject.service.VetService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vets")
@RequiredArgsConstructor
@CrossOrigin("*")
public class VetController {
    private final VetService vetService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<VetDto>> getAllVets() {
        return ResponseEntity.ok(vetService.findAll().stream()
                .map(v -> modelMapper.map(v, VetDto.class))
                .toList());
    }

    @GetMapping("/{id}")
    @SneakyThrows
    public ResponseEntity<VetDto> getVet(@PathVariable Long id) {
        Vet vet = vetService.findVetWithVisits(id);
        return ResponseEntity.ok(modelMapper.map(vet, VetDto.class));
    }
}
