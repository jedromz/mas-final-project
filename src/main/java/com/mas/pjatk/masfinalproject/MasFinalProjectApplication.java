package com.mas.pjatk.masfinalproject;

import com.github.javafaker.Faker;
import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.Owner;
import com.mas.pjatk.masfinalproject.model.Patient;
import com.mas.pjatk.masfinalproject.model.Vet;
import com.mas.pjatk.masfinalproject.model.command.*;
import com.mas.pjatk.masfinalproject.service.*;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class MasFinalProjectApplication {
    //TODO
    //adding all entieties
    //n+1
    //controllers
    //exceptions
    //METODY CONTROLLERA
    //- addPatient
    //- getVets
    //- getRooms
    //- get free vets (vacations/shifts)
    //- isPatient free(date)

    public static void main(String[] args) {
        SpringApplication.run(MasFinalProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(VetService vetService, Faker faker, EmployeeService employeeService, PatientService patientService, OwnerService ownerService, VisitService visitService) throws EntityNotFoundException {
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
                employeeService.addAdminEmployeeContract(new CreateContractAdminEmployeeCommand(faker.name().firstName(), faker.name().lastName(), localDateBirthdate, rate, rate, localDatePast, localDateFuture));
                employeeService.addAdminEmployeeFullTime(new CreateFullTimeAdminEmployeeCommand(faker.name().firstName(), faker.name().lastName(), localDateBirthdate, rate, rate, workTime));
                employeeService.addDirectorContract(new CreateContractDirectorCommand(faker.name().firstName(), faker.name().lastName(), localDateBirthdate, rate, rate, localDatePast, localDateFuture, localDatePast, localDateFuture));
                employeeService.addDirectorFullTime(new CreateFullTimeDirector(faker.name().firstName(), faker.name().lastName(), localDateBirthdate, rate, rate, localDatePast, localDateFuture, workTime));
                employeeService.addVetFullTime(new CreateFullTimeVetCommand(faker.name().firstName(), faker.name().lastName(), localDateBirthdate, rate, rate, faker.random().hex(), faker.animal().name(), workTime));
                employeeService.addVetContract(new CreateContractVetCommand(faker.name().firstName(), faker.name().lastName(), localDateBirthdate, rate, rate, faker.random().hex(), faker.animal().name(), localDatePast, localDateFuture));
            }
            visitService.saveVisit(CreateVisitCommand.builder().vetId(1l).patientId(1l).date(LocalDate.now()).endTime(LocalTime.now().plusMinutes(30)).startTime(LocalTime.now().minusMinutes(30)).build());
            visitService.saveVisit(CreateVisitCommand.builder().vetId(2l).patientId(1l).date(LocalDate.now()).endTime(LocalTime.now().plusMinutes(30)).startTime(LocalTime.now().minusMinutes(30)).build());
            visitService.saveVisit(CreateVisitCommand.builder().vetId(3l).patientId(1l).date(LocalDate.now()).endTime(LocalTime.now().plusMinutes(30)).startTime(LocalTime.now().minusMinutes(30)).build());
            visitService.saveVisit(CreateVisitCommand.builder().vetId(4l).patientId(1l).date(LocalDate.now()).endTime(LocalTime.now().plusMinutes(30)).startTime(LocalTime.now().minusMinutes(30)).build());
            visitService.saveVisit(CreateVisitCommand.builder().vetId(5l).patientId(1l).date(LocalDate.now()).endTime(LocalTime.now().plusMinutes(30)).startTime(LocalTime.now().minusMinutes(30)).build());
            vetService.findVetWithVisits(1l).getVisits().forEach(System.out::println);
            LocalDateTime from = LocalDateTime.now().minusMinutes(5);
            LocalDateTime to = LocalDateTime.now().plusMinutes(5);
            System.out.println(from.toLocalDate());
            System.out.println(to.toLocalDate());
            System.out.println(from.toLocalTime());
            System.out.println(to.toLocalTime());
            System.out.println("MOJA");
            employeeService.findAvailableVets(from, to).forEach(v-> System.out.println(v.getId()));
        };


    }
}
