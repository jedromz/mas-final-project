package com.mas.pjatk.masfinalproject.configuration;

import com.github.javafaker.Faker;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public Main() throws FileNotFoundException {

    }


    public static void main(String[] args) throws FileNotFoundException {
        Faker faker = new Faker();
        File file = new File("owners.txt");
        //addOwners(faker, file);
        //addPatients();
        //addVETS();
    }

    private static void addVETS() {
        Faker faker = new Faker();
        List<String> specs = new ArrayList<>();
        specs.add("KARDIOLOG");
        specs.add("LARYNGOLOG");
        specs.add("PSYCHOLOG");
        specs.add("CHIRURG");
        specs.add("FIZJOTERAPUTA");
        System.out.println(specs.size());
        File file = new File("vets.txt");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 1; i < 11; i++) {
                LocalDate bd = LocalDateTime.ofInstant(faker.date().birthday().toInstant(), ZoneId.of("UTC")).toLocalDate();
                bw.write(i + "," + faker.name().firstName() + "," + faker.name().lastName() + "," + bd + "," + faker.number().numberBetween(100, 250) + "," + faker.number().numberBetween(100, 250) + "," + 160 + "," + UUID.randomUUID() + "," + specs.get((int) (Math.random() * 4)) + ",");

            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addPatients() {
        Faker faker = new Faker();
        List<String> specialization = new ArrayList<>();
        List<String> type = new ArrayList<>();
        type.addAll(List.of("DOG", "PARROT", "CAT", "FISH", "HORSE"));

        File file = new File("patients.txt");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 1; i < 11; i++) {
                LocalDate bd = LocalDateTime.ofInstant(faker.date().birthday().toInstant(), ZoneId.of("UTC")).toLocalDate();
                bw.write(i + "," + faker.name().firstName() + "," + bd + "," + type.get((int) (Math.random() * 5)) + "," + "MIXED" + "," + (1 + (int) (Math.random() * 5)) + ",");

            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addOwners(Faker faker, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 1; i < 11; i++) {
                LocalDate bd = LocalDateTime.ofInstant(faker.date().birthday().toInstant(), ZoneId.of("UTC")).toLocalDate();
                bw.write(i + "," + faker.name().firstName() + "," + faker.name().lastName() + "," + bd + "," + faker.internet().emailAddress() + "," + faker.phoneNumber().phoneNumber() + ",");

            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
