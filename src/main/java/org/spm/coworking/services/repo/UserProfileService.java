package org.spm.coworking.services.repo;

import lombok.Getter;
import org.spm.coworking.entity.Metro;
import org.spm.coworking.entity.Rle;
import org.spm.coworking.entity.UserProfile;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Getter
public class UserProfileService extends BaseRepoService<UserProfile> {

    public UserProfile findByUserId(Long id){
        Optional<UserProfile> userProfile = repoHolderService.getUserProfileRepository().findByUserId(id);
        return userProfile.orElse(null);
    }

    public Optional<UserProfile> findByLogin(String username){
        return  repoHolderService.getUserProfileRepository().findByLogin(username);
    }

    public Map<String, Long> getAdminUsersMap() {
        return getUsersMapByRole(Rle.ADMIN);
    }

    public Map<String, Long> getUsersMapByRole(Rle roles) {
        List<UserProfile> users = repoHolderService.getUserProfileRepository()
                .findAllByRoles(Collections.singleton(roles));
        return users.stream().collect(Collectors.toMap(UserProfile::getLogin, UserProfile::getUserId));
    }

    public Map<String, Long> getUsersMap() {
        List<UserProfile> users = repoHolderService.getUserProfileRepository().findAll();
        return users.stream().collect(Collectors.toMap(UserProfile::getLogin, UserProfile::getUserId));
    }

    public void save(UserProfile userProfile){
        repoHolderService.getUserProfileRepository().save(userProfile);
    }

    @Override
    public Set<UserProfile> getSetOfEntitiesById(long id) {
        Optional<UserProfile> userProfile = repoHolderService.getUserProfileRepository()
                .findByUserId(id);
        return userProfile.map(value -> Stream.of(value).collect(Collectors.toSet()))
                .orElse(null);
    }

    @Override
    public Set<UserProfile> getSetOfAllEntities() {
        List<UserProfile> allUsers = repoHolderService.getUserProfileRepository().findAll();
        return new HashSet<>(allUsers);
    }
}
