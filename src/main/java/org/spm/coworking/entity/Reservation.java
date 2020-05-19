package org.spm.coworking.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A ClientSystem.
 */
@Data
@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGeneratorForReservation")
    @SequenceGenerator(name = "sequenceGeneratorForReservation")
    @Column(name = "reservation_id", nullable = false, unique = true)
    private Long reservationId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.TRUE;

    @Column(name = "paid", nullable = false)
    private Boolean paid = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserProfile userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="place_id")
    private Place placeId;

}
