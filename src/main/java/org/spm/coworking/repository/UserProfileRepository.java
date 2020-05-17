package org.spm.coworking.repository;

import org.spm.coworking.entity.Rle;
import org.spm.coworking.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


/**
 * Spring Data  repository for the ClientSystem entity.
 */
//@SuppressWarnings("unused")
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>, JpaSpecificationExecutor<UserProfile> {

    List<UserProfile> findAll();

    //List<UserProfile> findByRoleId(Role role);

    Optional<UserProfile> findByUserId(Long userId);

    Optional<UserProfile> findByLogin(String username);

    List<UserProfile> findAllByRoles(Set<Rle> roles);

}
