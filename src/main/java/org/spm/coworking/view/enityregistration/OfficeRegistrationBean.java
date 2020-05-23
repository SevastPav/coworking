package org.spm.coworking.view.enityregistration;

/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/

import lombok.Data;
import org.primefaces.PrimeFaces;
import org.spm.coworking.entity.City;
import org.spm.coworking.entity.Office;
import org.spm.coworking.entity.RentType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;

@Component
@SessionScope
@Data
public class OfficeRegistrationBean extends BaseRegistrationBean {

    private Long adminId;

    private Long cityId;

    private Office officeDto;

    @Override
    protected boolean validateDto() {
        errors = new ArrayList<>();
        if (StringUtils.isEmpty(officeDto.getTitle())){
            errors.add("Некорректное название офиса");
            error("Некорректное название офиса");
            return false;
        }
        if (StringUtils.isEmpty(officeDto.getDescription())){
            errors.add("Некорректное описание офиса");
            error("Некорректное описание офиса");
            return false;
        }
        if (StringUtils.isEmpty(officeDto.getAddress())){
            errors.add("Некорректный адрес офиса");
            error("Некорректный адрес офиса");
            return false;
        }
        if (adminId.equals(Long.valueOf(0))){
            errors.add("Администратор не выбран");
            error("Администратор не выбран");
            return false;
        }
        if (cityId.equals(Long.valueOf(0))){
            errors.add("Город не выбран");
            error("Город не выбран");
            return false;
        }
        return errors.isEmpty();
    }

    @Override
    protected void saveDto() {
        City city = serviceHolder.getCityService().findCityById(cityId);
        officeDto.setCityId(city);
        serviceHolder.getOfficeService().save(officeDto);
    }

    @Override
    public void updateDto() {
        adminId = Long.valueOf(0);
        cityId = Long.valueOf(0);
        officeDto = new Office();
    }

    @Override
    public void hideDialogAfterSavingDto() {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('officeAddDlg').hide();");
    }
}
