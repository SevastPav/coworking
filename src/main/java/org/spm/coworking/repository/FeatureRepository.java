package org.spm.coworking.repository;

import org.spm.coworking.entity.DurationType;
import org.spm.coworking.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long>, JpaSpecificationExecutor<Feature> {
    List<Feature> findAll();

    Optional<Feature> findByFeatureId(Long featureId);

}
