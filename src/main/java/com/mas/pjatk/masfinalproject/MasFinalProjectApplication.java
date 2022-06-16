package com.mas.pjatk.masfinalproject;

import com.github.javafaker.Faker;
import com.mas.pjatk.masfinalproject.model.Owner;
import com.mas.pjatk.masfinalproject.model.Patient;
import com.mas.pjatk.masfinalproject.model.Vet;
import com.mas.pjatk.masfinalproject.model.command.*;
import com.mas.pjatk.masfinalproject.service.EmployeeService;
import com.mas.pjatk.masfinalproject.service.OwnerService;
import com.mas.pjatk.masfinalproject.service.PatientService;
import com.mas.pjatk.masfinalproject.service.VetService;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MasFinalProjectApplication {
    //TODO
    //adding all entieties
    //n+1
    //controllers
    //exceptions

    public static void main(String[] args) {
        SpringApplication.run(MasFinalProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(Faker faker, EmployeeService employeeService, PatientService patientService, OwnerService ownerService) {
        return (args) -> {
            Owner owner = ownerService.savePatient(Owner.builder().build());
            ownerService.savePatient(Owner.builder().build());
            ownerService.savePatient(Owner.builder().build());
            ownerService.savePatient(Owner.builder().build());
            patientService.savePatient(Patient.builder().owner(owner).build());
            patientService.savePatient(Patient.builder().owner(owner).build());
            patientService.savePatient(Patient.builder().owner(owner).build());
            patientService.savePatient(Patient.builder().owner(owner).build());
            for (int i = 0; i < 20; i++) {
                String firstname = faker.name().firstName();
                String lastname = faker.name().lastName();
                Date birthday = faker.date().birthday();
                BigDecimal rate = BigDecimal.valueOf(faker.number().randomDouble(1, 80, 300));
                Date past = faker.date().past(2000, TimeUnit.DAYS);
                Date future = faker.date().future(2000, TimeUnit.DAYS);
                LocalDate localDateBirthdate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate localDateFuture = future.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate localDatePast = past.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int workTime = 160;
                //employeeService.addAdminEmployeeContract(new CreateContractAdminEmployeeCommand(firstname, lastname, localDateBirthdate, rate, rate, localDatePast, localDateFuture));
                //employeeService.addAdminEmployeeFullTime(new CreateFullTimeAdminEmployeeCommand(firstname, lastname, localDateBirthdate, rate, rate, workTime));
                //employeeService.addDirectorContract(new CreateContractDirectorCommand(firstname, lastname, localDateBirthdate, rate, rate, localDatePast, localDateFuture, localDatePast, localDateFuture));
                employeeService.addDirectorFullTime(new CreateFullTimeDirector(firstname, lastname, localDateBirthdate, rate, rate, localDatePast, localDateFuture, workTime));
                //employeeService.addVetFullTime(new CreateFullTimeVetCommand(firstname, lastname, localDateBirthdate, rate, rate, faker.random().hex(), faker.animal().name(), workTime));
                //employeeService.addVetContract(new CreateContractVetCommand(firstname, lastname, localDateBirthdate, rate, rate, faker.random().hex(), faker.animal().name(), localDatePast, localDateFuture));
            }
        };
    }
}
