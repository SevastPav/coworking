package org.spm.coworking.services.repo;

import lombok.Getter;
import org.spm.coworking.entity.DurationType;
import org.spm.coworking.entity.Feature;
import org.spm.coworking.entity.Office;
import org.spm.coworking.entity.RentType;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Getter
public class RentTypeService extends BaseRepoService<RentType> {

    public Optional<RentType> findByRentTypeId(Long id){
        return repoHolderService.getRentTypeRepository().findByRentTypeId(id);
    }

    public void save(RentType rentType){
        repoHolderService.getRentTypeRepository().save(rentType);
    }

    public Map<String, Long> getRentTypesMap() {
        List<RentType> rentTypes = repoHolderService.getRentTypeRepository().findAll();
        return rentTypes.stream().collect(Collectors.toMap(RentType::getTitle, RentType::getRentTypeId));
    }

    @Override
    public Set<Long> getOfficesIdsByEntityIds(List<Long> ids) {
        if (ids.isEmpty()){
            return getAllOfficesIds();
        }
        Set<Office> offices = new HashSet<>();
        List<RentType> rentTypes = repoHolderService
                .getRentTypeRepository().findAllByRentTypeIds(new HashSet<>(ids));
        rentTypes.forEach(c -> {
            offices.addAll(c.getOffices());
        });
        return offices.stream()
                .map(Office::getOfficeId).collect(Collectors.toSet());
    }

    @Override
    public Set<RentType> getSetOfEntitiesById(long id) {
        Optional<RentType> rentTypes = repoHolderService.getRentTypeRepository()
                .findByRentTypeId(id);
        return rentTypes.map(value -> Stream.of(value).collect(Collectors.toSet()))
                .orElse(null);
    }

    @Override
    public Set<RentType> getSetOfAllEntities() {
        List<RentType> allRentTypes = repoHolderService.getRentTypeRepository().findAll();
        return new HashSet<>(allRentTypes);
    }
}
