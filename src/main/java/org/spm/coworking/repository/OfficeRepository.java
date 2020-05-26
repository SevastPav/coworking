package org.spm.coworking.repository;

import org.spm.coworking.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface OfficeRepository extends JpaRepository<Office, Long>, JpaSpecificationExecutor<Office> {

    List<Office> findAll();

    Optional<Office> findByOfficeId(Long officeId);

    List<Office> findAllByMetrosContainsAndDurationTypesContainsAndRentTypesContainsAndCityId(Set<Metro> metros,
                                                                                              Set<DurationType> durationTypes,
                                                                                              Set<RentType> rentTypes,
                                                                                              City cityId);


    /*    @Query("select office from Office office where office.cityId = :city and " +
                ":metros member of office.metros and " +
                "office member of ?1"+
                ":duration member of office.durationTypes and " +
                ":rent member of office.rentTypes")*/

/*    @Query("select office from Office office where (office.cityId = :city) and " +
            "(:metros having office) and " +
            "(:duration having office) and " +
            "(:rent having office)")*/

/*    @Query("select office from Office office JOIN office.roles r where office.cityId = :city and " +
            "office.officeId in :metros and " +
            "office.officeId in :duration and " +
            "office.officeId in :rent")
    List<Office> getAllByMetrosDurationTypesRentTypesAndCity(@Param("metros") Set<Long> metros,
                                                             @Param("duration") Set<Long> durationTypes,
                                                             @Param("rent") Set<Long> rentTypes,
                                                             @Param("city") City cityId);*/

}
