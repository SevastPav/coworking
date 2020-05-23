package org.spm.coworking.view;

import lombok.Data;
import org.spm.coworking.entity.*;
import org.spm.coworking.services.ServiceHolder;
import org.spm.coworking.services.repo.RepoHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Data
@SessionScope
public class SearchResultBean {

    @Autowired
    private SearchFilterBean searchFilterBean;

    private final ServiceHolder serviceHolder;

    public SearchResultBean(ServiceHolder serviceHolder) {
        this.serviceHolder = serviceHolder;
    }

    public List<Office> getOffices(){
        City city = serviceHolder.getCityService().findCityById(searchFilterBean.getCityId());

        Set<Metro> metros = serviceHolder.getMetroService()
                .getEntitiesByIdIfNullReturnAll(searchFilterBean.getNearestMetroId());
        Set<RentType> rentTypes = serviceHolder.getRentTypeService()
                .getEntitiesByIdIfNullReturnAll(searchFilterBean.getTypeOfRentId());
        Set<DurationType> durationTypes = serviceHolder.getDurationTypeService()
                .getEntitiesByIdIfNullReturnAll(searchFilterBean.getDurationTypeId());

        if (city == null){
            return serviceHolder.getOfficeService().findAll();
        }

        return serviceHolder.getOfficeService()
                .findOfficesByCityMetroRentDuration(city, metros, rentTypes, durationTypes);
    }

    public void redirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("search");
    }

}
