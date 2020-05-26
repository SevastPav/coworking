package org.spm.coworking.view.enityregistration;

/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/

import lombok.Data;
import org.primefaces.PrimeFaces;
import org.spm.coworking.entity.DurationType;
import org.spm.coworking.entity.Feature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;

@Component
@SessionScope
@Data
public class FeatureRegistrationBean extends BaseRegistrationBean {

    private Feature featureDto;

    @Override
    protected boolean validateDto() {
        errors = new ArrayList<>();
        if (StringUtils.isEmpty(featureDto.getTitle())){
            errors.add("Некорректное название функции");
            error("Некорректное название функции");
            return false;
        }
        if (StringUtils.isEmpty(featureDto.getDescription())){
            errors.add("Некорректное описание функции");
            error("Некорректное описание функции");
            return false;
        }
        return errors.isEmpty();
    }

    @Override
    protected void saveDto() {
        serviceHolder.getFeatureService().save(featureDto);
    }

    @Override
    public void updateDto() {
        featureDto = new Feature();
    }

    @Override
    public void hideDialogAfterSavingDto() {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('featureAddDlg').hide();");
    }
}
