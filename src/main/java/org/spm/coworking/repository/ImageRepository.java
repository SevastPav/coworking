package org.spm.coworking.repository;

import org.spm.coworking.entity.Feature;
import org.spm.coworking.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long>, JpaSpecificationExecutor<Image> {
    List<Image> findAll();

    Optional<Image> findByImageId(Long imageId);

}
