package com.mas.pjatk.masfinalproject.mappings;

import com.mas.pjatk.masfinalproject.model.Vet;
import com.mas.pjatk.masfinalproject.model.dto.VetDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class VetToVetDto implements Converter<Vet, VetDto> {
    @Override
    public VetDto convert(MappingContext<Vet, VetDto> mappingContext) {
        Vet vet = mappingContext.getSource();
        return VetDto.builder()
                .id(vet.getId())
                .employeeId(vet.getEmployee().getId())
                .firstname(vet.getEmployee().getFirstname())
                .lastname(vet.getEmployee().getLastname())
                .birthDate(vet.getEmployee().getBirthDate())
                .rate(vet.getEmployee().getRate())
                .bonus(vet.getEmployee().getBonus())
                .vetLicense(vet.getVetLicense())
                .specialization(vet.getSpecialization())
                .email(vet.getEmployee().getEmails().get(0))
                .mobile(vet.getEmployee().getMobileNumbers().get(0))
                .build();
    }
}
