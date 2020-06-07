package org.spm.coworking.repository;

import org.spm.coworking.entity.Office;
import org.spm.coworking.entity.Place;
import org.spm.coworking.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {
    List<Reservation> findAll();

    Optional<Reservation> findByReservationId(Long reservationId);

    @Query("select reservation from Reservation reservation where " +
            "reservation.date = :date and " +
            "reservation.placeId.officeId = :office")
    List<Reservation> findAllByOfficeIdAndDate(@Param("office") Office office,
                                               @Param("date") LocalDate date);

    @Query("select reservation from Reservation reservation where " +
            "reservation.date >= :dateBefore and " +
            "reservation.date <= :dateAfter and " +
            "reservation.placeId.officeId.officeId = :officeId")
    List<Reservation> findAllByOfficeIdAndDateBeforeAndDateAfter(@Param("officeId") Long officeId,
                                                                 @Param("dateBefore") LocalDate dateBefore,
                                                                 @Param("dateAfter") LocalDate dateAfter);

    @Query("select reservation from Reservation reservation where " +
            "reservation.date >= :dateBefore and " +
            "reservation.date <= :dateAfter")
    List<Reservation> findAllByDateBeforeAndDateAfter(@Param("dateBefore") LocalDate dateBefore,
                                                      @Param("dateAfter") LocalDate dateAfter);

}
