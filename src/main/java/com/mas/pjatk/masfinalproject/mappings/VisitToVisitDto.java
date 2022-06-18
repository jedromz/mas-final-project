package com.mas.pjatk.masfinalproject.mappings;


import com.mas.pjatk.masfinalproject.model.Visit;
import com.mas.pjatk.masfinalproject.model.dto.VisitDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

@Service
public class VisitToVisitDto implements Converter<Visit, VisitDto> {
    @Override
    public VisitDto convert(MappingContext<Visit, VisitDto> mappingContext) {
        Visit visit = mappingContext.getSource();
        VisitDto visitDto = VisitDto.builder()
                .id(visit.getId())
                .patientId(visit.getPatient().getId())
                .vetId(visit.getVet().getId())
                .charge(visit.getCharge())
                .startTime(visit.getStartTime())
                .endTime(visit.getEndTime())
                .cancelled(visit.isCancelled())
                .confirmed(visit.isConfirmed())
                .deleted(visit.isDeleted())
                .build();
        return visitDto;
    }
}
