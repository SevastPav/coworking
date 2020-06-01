package org.spm.coworking.repository;

import org.spm.coworking.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface OfficeRepository extends JpaRepository<Office, Long>, JpaSpecificationExecutor<Office> {

    List<Office> findAll();

    Optional<Office> findByOfficeId(Long officeId);

    @Query("select office from Office office where office.officeId in :city and " +
            "office.officeId in :metros and " +
            "office.officeId in :duration and " +
            "office.officeId in :rent")
    List<Office> findAllByMetrosDurationTypesRentTypesAndCity(@Param("metros") Set<Long> metros,
                                                             @Param("duration") Set<Long> durationTypes,
                                                             @Param("rent") Set<Long> rentTypes,
                                                             @Param("city") Set<Long> cityIds);

}
