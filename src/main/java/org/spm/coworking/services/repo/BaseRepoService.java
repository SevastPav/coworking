package org.spm.coworking.services.repo;

import lombok.Getter;
import org.spm.coworking.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Getter
public abstract class BaseRepoService<T> {

    @Autowired
    protected RepoHolderService repoHolderService;

    public Set<T> getEntitiesByIdIfNullReturnAll(long id){
        Set<T> enitities = getSetOfEntitiesById(id);
        if (enitities == null){
            return getSetOfAllEntities();
        }
        return enitities;
    }

    public Set<Long> getAllOfficesIds(){
        List<Office> offices = repoHolderService.getOfficeRepository().findAll();
        return offices.stream()
                .map(Office::getOfficeId).collect(Collectors.toSet());
    }

    public Set<Long> getOfficesIdsByEntityIds(List<Long> ids){
        return new HashSet<>();
    }

    public abstract Set<T> getSetOfEntitiesById(long id);

    public abstract Set<T> getSetOfAllEntities();
}
