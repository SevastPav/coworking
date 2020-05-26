package org.spm.coworking.view.enityregistration;

/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/

import lombok.Data;
import org.primefaces.PrimeFaces;
import org.spm.coworking.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@SessionScope
@Data
public class PlaceRegistrationBean extends BaseRegistrationBean {

    private Place placeDto;

    private Long officeId;

    public Map<String, Long> getPlacesMap() {
        if (!serviceHolder.getAuthService().isAuth()) {
            return null;
        }
        if (serviceHolder.getAuthService().hasRole(Rle.ROOT.name())) {
            return serviceHolder.getOfficeService().getOfficesMap();
        }
        if (serviceHolder.getAuthService().hasRole(Rle.ADMIN.name())) {
            List<Office> offices = serviceHolder.getAuthService().getCurrentUserProfile().getManagedOffices();
            return offices.stream().collect(Collectors
                    .toMap(Office::getTitle, Office::getOfficeId));
        }
        return null;
    }

    @Override
    protected boolean validateDto() {
        errors = new ArrayList<>();
        if (placeDto.getInternalPlaceId() == 0) {
            errors.add("Некорректный номер места");
            error("Некорректный номер места");
            return false;
        }
        Office office = serviceHolder.getOfficeService().findByOfficeId(officeId);
        boolean internalIdMatch = office.getPlaces().stream()
                .anyMatch(p -> p.getInternalPlaceId().equals(placeDto.getInternalPlaceId()));
        if (internalIdMatch) {
            errors.add("Место с таким номер уже существует");
            error("Место с таким номер уже существует");
            return false;
        }
        return errors.isEmpty();
    }

    @Override
    protected void saveDto() {
        UserProfile admin = serviceHolder.getAuthService().getCurrentUserProfile();
        Office office = serviceHolder.getOfficeService().findByOfficeId(officeId);
        placeDto.setOfficeId(office);
        serviceHolder.getPlaceService().save(placeDto);
    }

    @Override
    public void updateDto() {
        placeDto = new Place();
        officeId = Long.valueOf(0);
    }

    @Override
    public void hideDialogAfterSavingDto() {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('placeAddDlg').hide();");
    }
}
