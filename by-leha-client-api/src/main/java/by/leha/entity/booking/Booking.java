package by.leha.entity.booking;


import by.leha.entity.booking.enums.BookingStatus;
import by.leha.entity.client.Client;
import by.leha.entity.room.Room;
import jakarta.persistence.*;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

@Entity
@Table(name = "booking")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ammount")
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id",nullable = false)
    @EqualsAndHashCode.Exclude
    private Room room;

    @Column(name = "date_in")
    private Date date_in;

    @Column(name = "date_out")
    private Date date_out;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private BookingStatus status;
    @JoinColumn(name ="client_id", referencedColumnName = "id")
    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Client client_id;



}
