package com.mas.pjatk.masfinalproject.mappings;

import com.mas.pjatk.masfinalproject.model.Patient;
import com.mas.pjatk.masfinalproject.model.dto.PatientDto;
import com.mas.pjatk.masfinalproject.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class PatientToPatientDto implements Converter<Patient, PatientDto> {

    @Override
    public PatientDto convert(MappingContext<Patient, PatientDto> mappingContext) {
        Patient patient = mappingContext.getSource();
        return PatientDto.builder()
                .id(patient.getId())
                .name(patient.getName())
                .birthdate(patient.getBirthDate())
                .age(patient.age())
                .race(patient.getRace())
                .type(patient.getType())
                .ownerEmail(patient.getOwner().getEmail())
                .ownerMobile(patient.getOwner().getMobile())
                .build();
    }
}
