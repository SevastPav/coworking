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
    @JoinColumn(name="duration_type_id")
    private DurationType durationTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="rent_type_id")
    private RentType rentTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private UserProfile userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="place_id")
    private Place placeId;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Reservation reservation = (Reservation) obj;
        return reservation.equals(reservation.reservationId);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((reservationId == null) ? 0 : reservationId.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

}
