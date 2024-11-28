package by.leha.entity.hotel;


import by.leha.entity.hotel.enums.RoomClass;
import by.leha.entity.hotel.enums.RoomStatus;
import jakarta.persistence.*;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Table(name = "room")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "floor")
    private String floor;
    @Column(name = "number")
    private String number;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "room_status")
    private RoomStatus status;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "room_class")
    private RoomClass roomClass;
}
