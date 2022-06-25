package com.mas.pjatk.masfinalproject.repository;

import com.mas.pjatk.masfinalproject.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
