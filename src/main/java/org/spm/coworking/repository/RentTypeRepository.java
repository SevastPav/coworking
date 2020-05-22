package org.spm.coworking.repository;

import org.spm.coworking.entity.Metro;
import org.spm.coworking.entity.RentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RentTypeRepository extends JpaRepository<RentType, Long>, JpaSpecificationExecutor<RentType> {
    List<RentType> findAll();
}
