package org.spm.coworking.services.repo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Getter
public abstract class BaseRepoService<T> {

    @Autowired
    protected RepoHolderService repoHolderService;

    public Set<T> getEntitiesByIdIfNullReturnAll(long id){
        Set<T> enititys = getSetOfEntitiesById(id);
        if (enititys == null){
            return getSetOfAllEntities();
        }
        return enititys;
    }

    public abstract Set<T> getSetOfEntitiesById(long id);

    public abstract Set<T> getSetOfAllEntities();
}
