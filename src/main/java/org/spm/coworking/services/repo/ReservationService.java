package org.spm.coworking.services.repo;

import lombok.Getter;
import org.spm.coworking.entity.Feature;
import org.spm.coworking.entity.Reservation;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Getter
public class ReservationService extends BaseRepoService<Reservation> {

    public Optional<Reservation> findByReservationId(Long id){
        return repoHolderService.getReservationRepository().findByReservationId(id);
    }

    public void save(Reservation reservation){
        repoHolderService.getReservationRepository().save(reservation);
    }

    public Map<String, Long> getReservationTypesMap() {
        List<Reservation> reservations = repoHolderService.getReservationRepository().findAll();
        return reservations.stream()
                .collect(Collectors.toMap(r -> r.getPlaceId().getOfficeId().getTitle(),
                        Reservation::getReservationId));
    }

    @Override
    public Set<Reservation> getSetOfEntitiesById(long id) {
        Optional<Reservation> reservations = repoHolderService.getReservationRepository()
                .findByReservationId(id);
        return reservations.map(value -> Stream.of(value).collect(Collectors.toSet()))
                .orElse(null);
    }

    @Override
    public Set<Reservation> getSetOfAllEntities() {
        List<Reservation> allReservations = repoHolderService.getReservationRepository().findAll();
        return new HashSet<>(allReservations);
    }
}