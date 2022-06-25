package com.mas.pjatk.masfinalproject.service;

import com.mas.pjatk.masfinalproject.error.EntityNotFoundException;
import com.mas.pjatk.masfinalproject.model.Hospital;
import com.mas.pjatk.masfinalproject.model.Room;
import com.mas.pjatk.masfinalproject.repository.HospitalRepository;
import com.mas.pjatk.masfinalproject.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final RoomRepository roomRepository;

    /**
     * composition - rooms cannot be shared, cannot exist without a hospital
     * @param hospitalId
     * @param room
     * @return added and saved Room
     * @throws EntityNotFoundException
     */
    @Transactional
    public Room addRoom(Long hospitalId, Room room) throws EntityNotFoundException {
        Hospital hospital = hospitalRepository.findHospitalByIdWithRooms(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException("HOSPITAL_ID", hospitalId.toString()));
        if (roomRepository.existsById(room.getId()) || hospital.getRooms().contains(room)) {
            throw new IllegalArgumentException("ROOM_ALREADY_OWNED");
        }
        if (roomRepository.existsById(room.getId())) {
            throw new IllegalArgumentException("ROOM_ALREADY_OWNED");
        }
        hospital.getRooms().add(room);
        return room;
    }

    /**
     * sets hospital and its rooms to deleted
     * @param hospitalId
     * @throws EntityNotFoundException
     */
    @Transactional
    public void deleteHospital(Long hospitalId) throws EntityNotFoundException {
        Hospital hospital = hospitalRepository.findHospitalByIdWithRooms(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException("HOSPITAL_ID", hospitalId.toString()));
        hospital.getRooms().forEach(r -> r.setDeleted(true));
        hospital.setDeleted(true);
    }

    public Hospital findById(Long hospitalId) throws EntityNotFoundException {
        return hospitalRepository.findHospitalByIdWithRooms(hospitalId)
                .orElseThrow(() -> new EntityNotFoundException("HOSPITAL_ID", hospitalId.toString()));
    }
}
