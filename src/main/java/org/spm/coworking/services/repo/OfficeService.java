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

    public void save(Office office) {
        repoHolderService.getOfficeRepository().save(office);
    }

    public Office findByOfficeId(Long officeID) {
        Optional<Office> office = repoHolderService.getOfficeRepository().findByOfficeId(officeID);
        return office.orElse(null);
    }

    public Map<String, Long> getOfficesMap() {
        List<Office> offices = repoHolderService.getOfficeRepository().findAll();
        return offices.stream().collect(Collectors
                .toMap(Office::getTitle, Office::getOfficeId));
    }

    public List<Office> findAll() {
        return repoHolderService.getOfficeRepository().findAll();
    }

    public List<Office> findOfficesByCityMetroRentDuration(Set<Long> cityIds,
                                                           Set<Long> metrosOfficesIds,
                                                           Set<Long> rentTypesOfficesIds,
                                                           Set<Long> durationTypesOfficesIds) {
        return repoHolderService.getOfficeRepository()
                .findAllByMetrosDurationTypesRentTypesAndCity(
                        metrosOfficesIds,
                        durationTypesOfficesIds,
                        rentTypesOfficesIds,
                        cityIds);
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
