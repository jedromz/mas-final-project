package com.mas.pjatk.masfinalproject.configuration;

import com.github.javafaker.Faker;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Set;

@Configuration

public class ApplicationConfiguration {
    @Bean
    public ModelMapper modelMapper(Set<Converter> converters) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        converters.forEach(modelMapper::addConverter);
        return modelMapper;
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
