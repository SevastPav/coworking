package org.spm.coworking.view.enityregistration;

/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/

import lombok.Data;
import org.primefaces.PrimeFaces;
import org.spm.coworking.entity.City;
import org.spm.coworking.entity.Metro;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;

@Component
@SessionScope
@Data
public class MetroRegistrationBean extends BaseRegistrationBean {

    private Metro metroDto;

    @Override
    protected boolean validateDto() {
        errors = new ArrayList<>();
        if (StringUtils.isEmpty(metroDto.getName())){
            errors.add("Некорректное название метро");
            error("Некорректное название метро");
            return false;
        }
        return errors.isEmpty();
    }

    @Override
    protected void saveDto() {
        serviceHolder.getMetroService().save(metroDto);
    }

    @Override
    public void updateDto() {
        metroDto = new Metro();
    }

    @Override
    public void hideDialogAfterSavingDto() {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('metroAddDlg').hide();");
    }
}
