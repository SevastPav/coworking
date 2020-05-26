package org.spm.coworking.repository;

import org.spm.coworking.entity.Place;
import org.spm.coworking.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>, JpaSpecificationExecutor<Reservation> {
    List<Reservation> findAll();

    Optional<Reservation> findByReservationId(Long reservationId);

}
