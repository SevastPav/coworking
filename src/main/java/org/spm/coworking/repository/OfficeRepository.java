package org.spm.coworking.repository;

import org.spm.coworking.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface OfficeRepository extends JpaRepository<Office, Long>, JpaSpecificationExecutor<Office> {

    List<Office> findAll();

    List<Office> findAllByMetrosContainsAndDurationTypesContainsAndRentTypesContainsAndCityId(Set<Metro> metros,
                                                                                              Set<DurationType> durationTypes,
                                                                                              Set<RentType> rentTypes,
                                                                                              City cityId);

}
