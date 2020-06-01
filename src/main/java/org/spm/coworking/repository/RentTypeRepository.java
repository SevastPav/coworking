package org.spm.coworking.repository;

import org.spm.coworking.entity.Metro;
import org.spm.coworking.entity.RentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface RentTypeRepository extends JpaRepository<RentType, Long>, JpaSpecificationExecutor<RentType> {
    List<RentType> findAll();

    Optional<RentType> findByRentTypeId(Long rentTypeId);

    @Query("select rent from RentType rent where rent.rentTypeId in :rent")
    List<RentType> findAllByRentTypeIds(@Param("rent") Set<Long> rentTypeIds);
}
