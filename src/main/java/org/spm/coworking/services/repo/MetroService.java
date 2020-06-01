package org.spm.coworking.services.repo;

import lombok.Getter;
import org.primefaces.component.collector.Collector;
import org.spm.coworking.entity.*;
import org.spm.coworking.repository.*;
import org.springframework.beans.Mergeable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Getter
public class MetroService extends BaseRepoService<Metro> {

    public Optional<Metro> findByMetroId(Long id){
        return repoHolderService.getMetroRepository().findByMetroId(id);
    }

    public Map<String, Long> getMetrosMap() {
        List<Metro> metros = repoHolderService.getMetroRepository().findAll();
        return metros.stream().collect(Collectors.toMap(Metro::getName, Metro::getMetroId));
    }

    public void save(Metro metro){
        repoHolderService.getMetroRepository().save(metro);
    }

    @Override
    public Set<Long> getOfficesIdsByEntityIds(List<Long> ids) {
        if (ids.isEmpty()){
            return getAllOfficesIds();
        }
        Set<Office> offices = new HashSet<>();
        List<Metro> metros = repoHolderService
                .getMetroRepository().findAllByMetroIds(new HashSet<>(ids));
        metros.forEach(c -> {
            offices.addAll(c.getOffices());
        });
        return offices.stream()
                .map(Office::getOfficeId).collect(Collectors.toSet());
    }

    @Override
    public Set<Metro> getSetOfEntitiesById(long id) {
        Optional<Metro> metro = repoHolderService.getMetroRepository()
                .findByMetroId(id);
        return metro.map(value -> Stream.of(value).collect(Collectors.toSet()))
                .orElse(null);
    }

    @Override
    public Set<Metro> getSetOfAllEntities() {
        List<Metro> allMetros = repoHolderService.getMetroRepository().findAll();
        return new HashSet<>(allMetros);
    }
}
