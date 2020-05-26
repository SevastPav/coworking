package org.spm.coworking.services.repo;

import lombok.Getter;
import org.spm.coworking.entity.DurationType;
import org.spm.coworking.entity.Feature;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Getter
public class FeatureService extends BaseRepoService<Feature> {

    public Optional<Feature> findByFeatureId(Long id){
        return repoHolderService.getFeatureRepository().findByFeatureId(id);
    }

    public void save(Feature feature){
        repoHolderService.getFeatureRepository().save(feature);
    }

    public Map<String, Long> getFeaturesTypesMap() {
        List<Feature> features = repoHolderService.getFeatureRepository().findAll();
        return features.stream().collect(Collectors.toMap(Feature::getTitle, Feature::getFeatureId));
    }

    @Override
    public Set<Feature> getSetOfEntitiesById(long id) {
        Optional<Feature> features = repoHolderService.getFeatureRepository()
                .findByFeatureId(id);
        return features.map(value -> Stream.of(value).collect(Collectors.toSet()))
                .orElse(null);
    }

    @Override
    public Set<Feature> getSetOfAllEntities() {
        List<Feature> allFeatures = repoHolderService.getFeatureRepository().findAll();
        return new HashSet<>(allFeatures);
    }
}
