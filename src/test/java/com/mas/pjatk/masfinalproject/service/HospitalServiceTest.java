package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.model.Address;
import com.mas.pjatk.masfinalproject.model.Hospital;
import com.mas.pjatk.masfinalproject.model.Room;
import com.mas.pjatk.masfinalproject.repository.HospitalRepository;
import com.mas.pjatk.masfinalproject.repository.RoomRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class HospitalServiceTest {
    private HospitalService hospitalService;
    @Mock
    private HospitalRepository hospitalRepository;
    @Mock
    private RoomRepository roomRepository;
    Hospital HOSPITAL_1;
    Room ROOM_1;
    Room ROOM_2;
    Room ROOM_3;
    Set<Room> ROOMS;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        hospitalService = new HospitalService(hospitalRepository, roomRepository);
        HOSPITAL_1 = new Hospital("TEST_HOSPITAL", new Address());
        ROOM_1 = new Room("1A");
        ROOM_2 = new Room("2A");
        ROOM_3 = new Room("3A");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @SneakyThrows
    void shouldAddRoom() {
        Long hospitalId = 1L;
        when(hospitalRepository.findHospitalByIdWithRooms(anyLong())).thenReturn(Optional.ofNullable(HOSPITAL_1));
        when(roomRepository.existsById(any())).thenReturn(false);
        hospitalService.addRoom(hospitalId, ROOM_1);
        assertTrue(HOSPITAL_1.getRooms().contains(ROOM_1));
    }

    @Test
    @SneakyThrows
    void shouldNotAddOwnedRoom() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Long hospitalId = 1L;
            when(hospitalRepository.findHospitalByIdWithRooms(anyLong())).thenReturn(Optional.ofNullable(HOSPITAL_1));
            when(roomRepository.existsById(any())).thenReturn(false);
            hospitalService.addRoom(hospitalId, ROOM_1);
            hospitalService.addRoom(hospitalId, ROOM_1);
            assertFalse(HOSPITAL_1.getRooms().contains(ROOM_1));
        });
    }

    @Test
    @SneakyThrows
    void shouldNotAddExistingRoom() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Long hospitalId = 1L;
            when(hospitalRepository.findHospitalByIdWithRooms(anyLong())).thenReturn(Optional.ofNullable(HOSPITAL_1));
            when(roomRepository.existsById(any())).thenReturn(true);
            hospitalService.addRoom(hospitalId, ROOM_1);
            assertFalse(HOSPITAL_1.getRooms().contains(ROOM_1));
        });
    }

    @Test
    @SneakyThrows
    void deleteHospital() {
        Long hospitalId = 1L;
        when(hospitalRepository.findHospitalByIdWithRooms(any())).thenReturn(Optional.ofNullable(HOSPITAL_1));
        hospitalService.addRoom(hospitalId, ROOM_1);
        hospitalService.addRoom(hospitalId, ROOM_2);
        hospitalService.addRoom(hospitalId, ROOM_3);
        hospitalService.deleteHospital(hospitalId);
        assertTrue(HOSPITAL_1.getRooms().stream().allMatch(Room::isDeleted));
    }
}
