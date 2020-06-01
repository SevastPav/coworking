package org.spm.coworking.repository;

import org.spm.coworking.entity.City;
import org.spm.coworking.entity.DurationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface DurationTypeRepository extends JpaRepository<DurationType, Long>, JpaSpecificationExecutor<DurationType> {
    List<DurationType> findAll();

    Optional<DurationType> findByDurationTypeId(Long durationTypeId);

    @Query("select duration from DurationType duration where duration.durationTypeId in :duration")
    List<DurationType> findAllByDurationTypeIds(@Param("duration") Set<Long> durationIds);
}
