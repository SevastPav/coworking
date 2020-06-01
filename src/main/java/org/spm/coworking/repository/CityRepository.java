package org.spm.coworking.repository;

import org.spm.coworking.entity.City;
import org.spm.coworking.entity.Rle;
import org.spm.coworking.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
    List<City> findAll();

    Optional<City> findByCityId(Long cityId);

    @Query("select city from City city where city.cityId in :city")
    List<City> findAllByCityIds(@Param("city") Set<Long> cityIds);
}
