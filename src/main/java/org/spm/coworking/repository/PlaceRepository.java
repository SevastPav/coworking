package org.spm.coworking.repository;

import org.spm.coworking.entity.Feature;
import org.spm.coworking.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PlaceRepository extends JpaRepository<Place, Long>, JpaSpecificationExecutor<Place> {
    List<Place> findAll();

    Optional<Place> findByPlaceId(Long placeId);

}
