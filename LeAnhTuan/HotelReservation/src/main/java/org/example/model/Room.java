package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "room_number", nullable = false, length = 10)
    private String roomNumber;

    @ColumnDefault("'Available'")
    @Column(name = "status", columnDefinition = "room_status")
    private Object status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id")
    private Roomtype roomType;


}