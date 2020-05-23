package org.spm.coworking.services.repo;

import lombok.Getter;
import org.spm.coworking.entity.DurationType;
import org.spm.coworking.entity.Metro;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Getter
public class DurationTypeService extends BaseRepoService<DurationType> {

    public void save(DurationType durationType){
        repoHolderService.getDurationTypeRepository().save(durationType);
    }

    public Map<String, Long> getDurationTypesMap() {
        List<DurationType> durationTypes = repoHolderService.getDurationTypeRepository().findAll();
        return durationTypes.stream().collect(Collectors.toMap(DurationType::getTitle, DurationType::getDurationTypeId));
    }

    @Override
    public Set<DurationType> getSetOfEntitiesById(long id) {
        Optional<DurationType> durationTypes = repoHolderService.getDurationTypeRepository()
                .findByDurationTypeId(id);
        return durationTypes.map(value -> Stream.of(value).collect(Collectors.toSet()))
                .orElse(null);
    }

    @Override
    public Set<DurationType> getSetOfAllEntities() {
        List<DurationType> allDurationTypes = repoHolderService.getDurationTypeRepository().findAll();
        return new HashSet<>(allDurationTypes);
    }
}
