package org.spm.coworking.services.repo;

import lombok.Getter;
import org.spm.coworking.entity.City;
import org.spm.coworking.entity.Metro;
import org.spm.coworking.entity.Office;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Getter
public class CityService extends BaseRepoService<City> {

    public City findCityById(long id) {
        Optional<City> city = repoHolderService.getCityRepository()
                .findByCityId(id);
        return city.orElse(null);
    }

    public Map<String, Long> getCitiesMap() {
        List<City> cities = repoHolderService.getCityRepository().findAll();
        return cities.stream().collect(Collectors.toMap(City::getName, City::getCityId));
    }

    public void save(City city){
        repoHolderService.getCityRepository().save(city);
    }

    @Override
    public Set<Long> getOfficesIdsByEntityIds(List<Long> ids) {
        if (ids.isEmpty()){
            return getAllOfficesIds();
        }
        Set<Office> offices = new HashSet<>();
        List<City> cities = repoHolderService
                .getCityRepository().findAllByCityIds(new HashSet<>(ids));
        cities.forEach(c -> {
            offices.addAll(c.getOffices());
        });
        return offices.stream()
                .map(Office::getOfficeId).collect(Collectors.toSet());
    }

    @Override
    public Set<City> getSetOfEntitiesById(long id) {
        Optional<City> cities = repoHolderService.getCityRepository()
                .findByCityId(id);
        return cities.map(value -> Stream.of(value).collect(Collectors.toSet()))
                .orElse(null);
    }

    @Override
    public Set<City> getSetOfAllEntities() {
        List<City> allCities = repoHolderService.getCityRepository().findAll();
        return new HashSet<>(allCities);
    }
}
