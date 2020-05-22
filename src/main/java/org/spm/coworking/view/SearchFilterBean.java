package org.spm.coworking.view;

import lombok.Data;
import org.spm.coworking.entity.*;
import org.spm.coworking.services.RepoHolderService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Data
@SessionScope
public class SearchFilterBean {

    private Long cityId;
    private Long nearestMetroId;
    private Long typeOfRentId;
    private Long durationTypeId;

    private final RepoHolderService repoHolderService;

    public SearchFilterBean(RepoHolderService repoHolderService) {
        this.repoHolderService = repoHolderService;
    }

    public Map<String, Long> getCities() {
        List<City> cities = repoHolderService.getCityRepository().findAll();
        return cities.stream().collect(Collectors.toMap(City::getName, City::getCityId));
    }

    public Map<String, Long> getMetros() {
        List<Metro> metros = repoHolderService.getMetroRepository().findAll();
        return metros.stream().collect(Collectors.toMap(Metro::getName, Metro::getMetroId));
    }

    public Map<String, Long> getRentTypes() {
        List<RentType> rentTypes = repoHolderService.getRentTypeRepository().findAll();
        return rentTypes.stream().collect(Collectors.toMap(RentType::getTitle, RentType::getRentTypeId));
    }

    public Map<String, Long> getDurationTypes() {
        List<DurationType> durationTypes = repoHolderService.getDurationTypeRepository().findAll();
        return durationTypes.stream().collect(Collectors.toMap(DurationType::getTitle, DurationType::getDurationTypeId));
    }

    public void redirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("search");
    }

}
