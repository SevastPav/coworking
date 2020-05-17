package org.spm.coworking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.spm.coworking.entity.TrainingDescription;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the ClientSystem entity.
 */
//@SuppressWarnings("unused")
@Repository
public interface TrainingDescRepository extends JpaRepository<TrainingDescription, Long>, JpaSpecificationExecutor<TrainingDescription> {

    List<TrainingDescription> findAll();

    Optional<TrainingDescription> findByTrainingDescId(Long trainingDescId);

}
