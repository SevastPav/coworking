package org.spm.coworking.repository;

import org.spm.coworking.entity.City;
import org.spm.coworking.entity.DurationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DurationTypeRepository extends JpaRepository<DurationType, Long>, JpaSpecificationExecutor<DurationType> {
    List<DurationType> findAll();

    Optional<DurationType> findByDurationTypeId(Long durationTypeId);
}
