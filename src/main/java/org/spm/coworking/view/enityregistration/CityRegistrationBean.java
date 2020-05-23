package org.spm.coworking.view.enityregistration;

/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/

import lombok.Data;
import org.primefaces.PrimeFaces;
import org.spm.coworking.entity.City;
import org.spm.coworking.entity.Rle;
import org.spm.coworking.entity.UserProfile;
import org.spm.coworking.repository.UserProfileRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@SessionScope
@Data
public class CityRegistrationBean extends BaseRegistrationBean {

    private City cityDto;

    @Override
    protected boolean validateDto() {
        errors = new ArrayList<>();
        if (StringUtils.isEmpty(cityDto.getName())){
            errors.add("Некорректное название города");
            error("Некорректное название города");
            return false;
        }
        return errors.isEmpty();
    }

    @Override
    protected void saveDto() {
        serviceHolder.getCityService().save(cityDto);
    }

    @Override
    public void updateDto() {
        cityDto = new City();
    }

    @Override
    public void hideDialogAfterSavingDto() {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('cityAddDlg').hide();");
    }
}
