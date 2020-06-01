package org.spm.coworking.repository;

import org.spm.coworking.entity.DurationType;
import org.spm.coworking.entity.Metro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface MetroRepository extends JpaRepository<Metro, Long>, JpaSpecificationExecutor<Metro> {
    List<Metro> findAll();

    Optional<Metro> findByMetroId(Long metroId);

    @Query("select metro from Metro metro where metro.metroId in :metro")
    List<Metro> findAllByMetroIds(@Param("metro") Set<Long> metroIds);
}
