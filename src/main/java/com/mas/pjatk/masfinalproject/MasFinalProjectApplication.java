package com.mas.pjatk.masfinalproject;

import com.github.javafaker.Faker;
import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.*;
import com.mas.pjatk.masfinalproject.model.command.CreateFullTimeDirector;
import com.mas.pjatk.masfinalproject.model.command.CreateFullTimeVetCommand;
import com.mas.pjatk.masfinalproject.model.command.CreatePatientCommand;
import com.mas.pjatk.masfinalproject.repository.*;
import com.mas.pjatk.masfinalproject.service.*;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@SpringBootApplication
public class MasFinalProjectApplication {


    public static void main(String[] args) {

        SpringApplication.run(MasFinalProjectApplication.class, args);

    }

    @Bean
    public CommandLineRunner loadData(HospitalRepository hospitalRepository, HospitalService hospitalService, OwnerRepository ownerRepository, PatientRepository patientRepository, ShiftRepository shiftRepository, EmployeeRepository employeeRepository, VetService vetService, Faker faker, EmployeeService employeeService, PatientService patientService, OwnerService ownerService, VisitService visitService) throws EntityNotFoundException {
        return (args) -> {

        };

    }

    private void saveVets(EmployeeService employeeService) throws FileNotFoundException, EntityNotFoundException {
        Scanner input = new Scanner(new File("vets.txt"));
        input.useDelimiter(",");
        while (input.hasNext()) {
            Long vetId = Long.valueOf(input.next());
            String firstname = input.next();
            String lastname = input.next();
            LocalDate birthDate = LocalDate.parse(input.next());
            BigDecimal rate = BigDecimal.valueOf(Double.parseDouble(input.next()));
            BigDecimal bonus = BigDecimal.valueOf(Double.parseDouble(input.next()));
            Integer workTime = Integer.valueOf(input.next());
            String vetLicense = input.next();
            String special = input.next();
            employeeService.addVetFullTime(CreateFullTimeVetCommand.builder()
                    .firstname(firstname)
                    .lastname(lastname)
                    .rate(rate)
                    .birthDate(birthDate)
                    .bonus(bonus)
                    .hospitalId(1l)
                    .workTime(workTime)
                    .specialization(special)
                    .vetLicense(vetLicense)
                    .build());
        }
    }

    private void savePatients(OwnerRepository ownerRepository, PatientRepository patientRepository) throws FileNotFoundException, EntityNotFoundException {
        Scanner input = new Scanner(new File("patients.txt"));
        input.useDelimiter(",");
        while (input.hasNext()) {
            Long patientId = Long.valueOf(input.next());
            String name = input.next();
            LocalDate birthDate = LocalDate.parse(input.next());
            String type = input.next();
            String race = input.next();
            Long ownerId = Long.valueOf(input.next());
            System.out.println(ownerId);
            Owner owner = ownerRepository.findById(ownerId)
                    .orElseThrow(() -> new EntityNotFoundException("OWNER", ownerId.toString()));
            Patient patient = Patient.builder()
                    .name(name)
                    .race(race)
                    .type(type)
                    .birthDate(birthDate)
                    .owner(owner)
                    .build();
            patientRepository.saveAndFlush(patient);
            System.out.println(owner);
        }
    }

    private void saaveOwners(OwnerService ownerService) throws FileNotFoundException {
        Scanner input = new Scanner(new File("owners.txt"));
        input.useDelimiter(",");
        while (input.hasNext()) {
            Long ownerId = Long.valueOf(input.next());
            String firstname = input.next();
            String lastname = input.next();
            LocalDate birthDate = LocalDate.parse(input.next());
            String email = input.next();
            String mobile = input.next();

            Owner owner = new Owner(firstname, lastname, birthDate, email, mobile);
            ownerService.saveOwner(owner);
            System.out.println(owner);
        }
    }
}
