package org.spm.coworking.services.repo;

import lombok.Getter;
import org.spm.coworking.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Getter
public class OfficeService extends BaseRepoService<Office> {

    public void save(Office office){
        repoHolderService.getOfficeRepository().save(office);
    }

    public List<Office> findAll(){
        return repoHolderService.getOfficeRepository().findAll();
    }

    public List<Office> findOfficesByCityMetroRentDuration(City city,
                                                           Set<Metro> metros,
                                                           Set<RentType> rentTypes,
                                                           Set<DurationType> durationTypes) {
        return repoHolderService.getOfficeRepository()
                .findAllByMetrosContainsAndDurationTypesContainsAndRentTypesContainsAndCityId(
                        metros,
                        durationTypes,
                        rentTypes,
                        city);
    }

    @Override
    public Set<Office> getSetOfEntitiesById(long id) {
        return null;
    }

    @Override
    public Set<Office> getSetOfAllEntities() {
        return null;
    }
}
