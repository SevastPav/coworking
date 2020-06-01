package org.spm.coworking.view;

import lombok.Data;
import org.spm.coworking.entity.Office;
import org.spm.coworking.entity.Reservation;
import org.spm.coworking.entity.Rle;
import org.spm.coworking.view.enityregistration.BaseRegistrationBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Data
@SessionScope
public class PersonalAreaBean extends BaseRegistrationBean {

    private Long officeId;

    private Office officeDto;

    public void redirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("search");
    }

    public void onChange(){
        officeDto = serviceHolder.getOfficeService().findByOfficeId(officeId);
    }

    public List<Reservation> getTodayReservations(){
        return serviceHolder.getReservationService()
                .findAllByOfficeIdAndDate(officeDto, serviceHolder.getWeekService().getToday());
    }

    public Map<String, Long> getOfficesMap() {
        if (serviceHolder.getAuthService().isAuth() &&
            serviceHolder.getAuthService().hasRole(Rle.ADMIN.name())) {
            List<Office> managedOffices = serviceHolder.getAuthService()
                    .getCurrentUserProfile().getManagedOffices();
            return managedOffices.stream().collect(Collectors
                    .toMap(Office::getTitle, Office::getOfficeId));
        }
        return null;
    }

    @Override
    protected boolean validateDto() {
        return true;
    }

    @Override
    protected void saveDto() {
        return;
    }

    @Override
    public void updateDto() {
        return;
    }
}
