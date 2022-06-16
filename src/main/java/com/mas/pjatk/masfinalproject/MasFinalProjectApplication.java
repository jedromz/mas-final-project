package com.mas.pjatk.masfinalproject;

import com.mas.pjatk.masfinalproject.model.Owner;
import com.mas.pjatk.masfinalproject.model.Patient;
import com.mas.pjatk.masfinalproject.model.Vet;
import com.mas.pjatk.masfinalproject.service.OwnerService;
import com.mas.pjatk.masfinalproject.service.PatientService;
import com.mas.pjatk.masfinalproject.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MasFinalProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasFinalProjectApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(VetService vetService, PatientService patientService, OwnerService ownerService) {
        return (args) -> {
            Owner owner = ownerService.savePatient(Owner.builder().build());
            ownerService.savePatient(Owner.builder().build());
            ownerService.savePatient(Owner.builder().build());
            ownerService.savePatient(Owner.builder().build());
            patientService.savePatient(Patient.builder().owner(owner).build());
            patientService.savePatient(Patient.builder().owner(owner).build());
            patientService.savePatient(Patient.builder().owner(owner).build());
            patientService.savePatient(Patient.builder().owner(owner).build());
            vetService.saveVet(Vet.builder().build());
            vetService.saveVet(Vet.builder().build());
            vetService.saveVet(Vet.builder().build());
            vetService.saveVet(Vet.builder().build());
        };
    }

}
