package org.spm.coworking.view;

import lombok.Data;
import org.spm.coworking.entity.*;
import org.spm.coworking.services.ServiceHolder;
import org.spm.coworking.services.repo.RepoHolderService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.context.FacesContext;
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

    private final ServiceHolder serviceHolder;

    public SearchFilterBean(ServiceHolder serviceHolder) {
        this.serviceHolder = serviceHolder;
    }

    public Map<String, Long> getCities() {
        return serviceHolder.getCityService().getCitiesMap();
    }

    public Map<String, Long> getMetros() {
        return serviceHolder.getMetroService().getMetrosMap();
    }

    public Map<String, Long> getRentTypes() {
        return serviceHolder.getRentTypeService().getRentTypesMap();
    }

    public Map<String, Long> getDurationTypes() {
        return serviceHolder.getDurationTypeService().getDurationTypesMap();
    }

    public void redirectToSearchResult() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("search");
    }

}
