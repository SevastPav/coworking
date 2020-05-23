package org.spm.coworking.view.enityregistration;

/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/

import lombok.Data;
import org.primefaces.PrimeFaces;
import org.spm.coworking.entity.Metro;
import org.spm.coworking.entity.RentType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;

@Component
@SessionScope
@Data
public class RentTypeRegistrationBean extends BaseRegistrationBean {

    private RentType rentTypeDto;

    @Override
    protected boolean validateDto() {
        errors = new ArrayList<>();
        if (StringUtils.isEmpty(rentTypeDto.getTitle())){
            errors.add("Некорректный заголовок типа аренды");
            error("Некорректный заголовок типа аренды");
            return false;
        }
        if (StringUtils.isEmpty(rentTypeDto.getDescription())){
            errors.add("Некорректное описание типа аренды");
            error("Некорректное описание типа аренды");
            return false;
        }
        return errors.isEmpty();
    }

    @Override
    protected void saveDto() {
        serviceHolder.getRentTypeService().save(rentTypeDto);
    }

    @Override
    public void updateDto() {
        rentTypeDto = new RentType();
    }

    @Override
    public void hideDialogAfterSavingDto() {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('rentAddDlg').hide();");
    }
}
