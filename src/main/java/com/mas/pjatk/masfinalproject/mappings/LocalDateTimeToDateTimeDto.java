package com.mas.pjatk.masfinalproject.mappings;

import com.mas.pjatk.masfinalproject.model.dto.DateTimeDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class LocalDateTimeToDateTimeDto implements Converter<LocalDateTime, DateTimeDto> {
    @Override
    public DateTimeDto convert(MappingContext<LocalDateTime, DateTimeDto> mappingContext) {
        LocalDateTime localDateTime = mappingContext.getSource();
        return DateTimeDto.builder().date(localDateTime.toLocalDate()).startTime(localDateTime.toLocalTime()).endTime(LocalTime.from(localDateTime.plusMinutes(15))).build();
    }
}
