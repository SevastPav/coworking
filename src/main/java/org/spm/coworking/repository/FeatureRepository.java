package org.spm.coworking.repository;

import org.spm.coworking.entity.DurationType;
import org.spm.coworking.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long>, JpaSpecificationExecutor<Feature> {
    List<Feature> findAll();

    Optional<Feature> findByFeatureId(Long featureId);

    Optional<Feature> findByTitle(String title);

    @Query("select feature from Feature feature where feature.featureId in :feature")
    List<Feature> findAllByFeatureIds(@Param("feature") Set<Long> featureIds);

}
