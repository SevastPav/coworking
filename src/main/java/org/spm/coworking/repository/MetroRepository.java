package org.spm.coworking.repository;

import org.spm.coworking.entity.DurationType;
import org.spm.coworking.entity.Metro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MetroRepository extends JpaRepository<Metro, Long>, JpaSpecificationExecutor<Metro> {
    List<Metro> findAll();
}
