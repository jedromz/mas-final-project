package com.mas.pjatk.masfinalproject.model;

import lombok.*;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
@EqualsAndHashCode(exclude = "{id}")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private Hospital hospital;
    private boolean deleted;

    public Room(String number) {
        this.number = number;

    }
}
