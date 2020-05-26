package org.spm.coworking.services.repo;

import lombok.Getter;
import org.spm.coworking.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Getter
public class OfficeService extends BaseRepoService<Office> {

    public void save(Office office){
        repoHolderService.getOfficeRepository().save(office);
    }

    public Office findByOfficeId(Long officeID){
        Optional<Office> office = repoHolderService.getOfficeRepository().findByOfficeId(officeID);
        return office.orElse(null);
    }

    public Map<String, Long> getOfficesMap() {
        List<Office> offices = repoHolderService.getOfficeRepository().findAll();
        return offices.stream().collect(Collectors
                .toMap(Office::getTitle, Office::getOfficeId));
    }

    public List<Office> findAll(){
        return repoHolderService.getOfficeRepository().findAll();
    }

    //TODO
    public List<Office> findOfficesByCityMetroRentDuration(City city,
                                                           Set<Office> metrosOffices,
                                                           Set<Office> rentTypesOffices,
                                                           Set<Office> durationTypesOffices) {
/*        return repoHolderService.getOfficeRepository()
                .findAllByMetrosContainsAndDurationTypesContainsAndRentTypesContainsAndCityId(
                        metros,
                        durationTypes,
                        rentTypes,
                        city);*/
/*        return repoHolderService.getOfficeRepository()
                .getAllByMetrosDurationTypesRentTypesAndCity(
                        metrosOffices,
                        durationTypesOffices,
                        rentTypesOffices,
                        city);*/
/*        return repoHolderService.getOfficeRepository()
                .getAllByMetrosDurationTypesRentTypesAndCity(
                        metrosOffices.stream().map(Office::getOfficeId).collect(Collectors.toSet()),
                        durationTypesOffices.stream().map(Office::getOfficeId).collect(Collectors.toSet()),
                        rentTypesOffices.stream().map(Office::getOfficeId).collect(Collectors.toSet()),
                        city);*/
return repoHolderService.getOfficeRepository().findAll();
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
