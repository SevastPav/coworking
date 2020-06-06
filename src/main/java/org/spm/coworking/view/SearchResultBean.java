package org.spm.coworking.view;

import lombok.Data;
import org.spm.coworking.entity.*;
import org.spm.coworking.services.ServiceHolder;
import org.spm.coworking.services.repo.RepoHolderService;
import org.spm.coworking.utils.SomeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Data
@SessionScope
public class SearchResultBean {

    private List<Long> cityIds = new ArrayList<>();
    private List<Long> nearestMetroIds = new ArrayList<>();
    private List<Long> typeOfRentIds = new ArrayList<>();
    private List<Long> durationTypeIds = new ArrayList<>();
    private List<Long> featureIds = new ArrayList<>();

    @Autowired
    private SearchFilterBean searchFilterBean;

    @Autowired
    private OfficeBean officeBean;

    private final ServiceHolder serviceHolder;

    public SearchResultBean(ServiceHolder serviceHolder) {
        this.serviceHolder = serviceHolder;
    }

    public List<Office> getOffices(){
        Set<Long> cityIds = serviceHolder.getCityService()
                .getOfficesIdsByEntityIds(this.cityIds);
        Set<Long> metroIds = serviceHolder.getMetroService()
                .getOfficesIdsByEntityIds(this.nearestMetroIds);
        Set<Long> rentTypeIds = serviceHolder.getRentTypeService()
                .getOfficesIdsByEntityIds(this.typeOfRentIds);
        Set<Long> durationTypeIds = serviceHolder.getDurationTypeService()
                .getOfficesIdsByEntityIds(this.durationTypeIds);
        Set<Long> featureIds = serviceHolder.getFeatureService()
                .getOfficesIdsByEntityIds(this.featureIds);

        if (SomeUtils.isAnyEmpty(cityIds, metroIds, rentTypeIds, durationTypeIds, featureIds)){
            return new ArrayList<>();
        }

        return serviceHolder.getOfficeService()
                .findOfficesByCityMetroRentDuration(cityIds, metroIds, rentTypeIds, durationTypeIds, featureIds);
    }

    public void redirectToSearchResult() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("search");
    }

    public void redirectToOfficePage() throws IOException {
        Office office = (Office) FacesContext.getCurrentInstance()
                .getExternalContext().getRequestMap().get("office");
        officeBean.setOffice(office);
        FacesContext.getCurrentInstance().getExternalContext().redirect("office");
    }

}
