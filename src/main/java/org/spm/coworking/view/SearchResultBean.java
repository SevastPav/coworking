package org.spm.coworking.view;

import lombok.Data;
import org.spm.coworking.entity.City;
import org.spm.coworking.entity.DurationType;
import org.spm.coworking.entity.Metro;
import org.spm.coworking.entity.RentType;
import org.spm.coworking.services.RepoHolderService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Data
@SessionScope
public class SearchResultBean {

    private Long cityId;
    private Long nearestMetroId;
    private Long typeOfRentId;
    private Long durationTypeId;

    private final RepoHolderService repoHolderService;

    public SearchResultBean(RepoHolderService repoHolderService) {
        this.repoHolderService = repoHolderService;
    }

    public void redirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("search");
    }

}
