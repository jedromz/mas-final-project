package com.mas.pjatk.masfinalproject.controller;

import com.mas.pjatk.masfinalproject.model.Owner;
import com.mas.pjatk.masfinalproject.model.dto.OwnerDto;
import com.mas.pjatk.masfinalproject.service.OwnerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OwnerController {
    private final OwnerService ownerService;
    private final ModelMapper modelMapper;

    @GetMapping("/{email}")
    @SneakyThrows
    public ResponseEntity<OwnerDto> getOwnerByEmail(@PathVariable String email) {
        Owner owner = ownerService.findByEmail(email);
        OwnerDto ownerDto = modelMapper.map(owner, OwnerDto.class);
        return ResponseEntity.ok(ownerDto);
    }
}
