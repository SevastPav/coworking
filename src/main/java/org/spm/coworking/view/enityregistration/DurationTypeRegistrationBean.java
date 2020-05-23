package org.spm.coworking.view.enityregistration;

/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/

import lombok.Data;
import org.primefaces.PrimeFaces;
import org.spm.coworking.entity.DurationType;
import org.spm.coworking.entity.RentType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;

@Component
@SessionScope
@Data
public class DurationTypeRegistrationBean extends BaseRegistrationBean {

    private DurationType durationTypeDto;

    @Override
    protected boolean validateDto() {
        errors = new ArrayList<>();
        if (StringUtils.isEmpty(durationTypeDto.getTitle())){
            errors.add("Некорректный заголовок продолжительности аренды");
            error("Некорректный заголовок продолжительности аренды");
            return false;
        }
        if (StringUtils.isEmpty(durationTypeDto.getDescription())){
            errors.add("Некорректное описание продолжительности аренды");
            error("Некорректное описание продолжительности аренды");
            return false;
        }
        return errors.isEmpty();
    }

    @Override
    protected void saveDto() {
        serviceHolder.getDurationTypeService().save(durationTypeDto);
    }

    @Override
    public void updateDto() {
        durationTypeDto = new DurationType();
    }

    @Override
    public void hideDialogAfterSavingDto() {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('durationAddDlg').hide();");
    }
}
