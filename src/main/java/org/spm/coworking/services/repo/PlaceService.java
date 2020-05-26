package org.spm.coworking.services.repo;

import lombok.Getter;
import org.spm.coworking.entity.Feature;
import org.spm.coworking.entity.Place;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Getter
public class PlaceService extends BaseRepoService<Place> {

    public Optional<Place> findByPlaceId(Long id){
        return repoHolderService.getPlaceRepository().findByPlaceId(id);
    }

    public void save(Place place){
        repoHolderService.getPlaceRepository().save(place);
    }

    public Map<String, Long> getPlacesMap() {
        List<Place> places = repoHolderService.getPlaceRepository().findAll();
        return places.stream().collect(Collectors
                .toMap(p -> String.valueOf(p.getInternalPlaceId()), Place::getPlaceId));
    }

    @Override
    public Set<Place> getSetOfEntitiesById(long id) {
        Optional<Place> places = repoHolderService.getPlaceRepository()
                .findByPlaceId(id);
        return places.map(value -> Stream.of(value).collect(Collectors.toSet()))
                .orElse(null);
    }

    @Override
    public Set<Place> getSetOfAllEntities() {
        List<Place> allPlaces = repoHolderService.getPlaceRepository().findAll();
        return new HashSet<>(allPlaces);
    }
}
