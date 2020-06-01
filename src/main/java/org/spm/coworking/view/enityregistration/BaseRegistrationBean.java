package org.spm.coworking.view.enityregistration;

/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/

import lombok.Data;
import org.spm.coworking.entity.Rle;
import org.spm.coworking.entity.UserProfile;
import org.spm.coworking.repository.UserProfileRepository;
import org.spm.coworking.services.ServiceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.annotation.MultipartConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
@SessionScope
@Data
abstract public class BaseRegistrationBean {

    protected List<String> errors = new ArrayList<>();

    @Autowired
    protected ServiceHolder serviceHolder;

    public BaseRegistrationBean(){
        updateDto();
    }

    protected void error(String details) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ошибка!", details));
    }

    public void createEntity(){
        if (!validateDto())
            return;
        saveDto();
        updateDto();
        hideDialogAfterSavingDto();
    };

    public void editEntity(){
        if (!validateDto())
            return;
        editDto();
        updateDto();
        hideDialogAfterSavingDto();
    };

    protected abstract boolean validateDto();

    protected abstract void saveDto();

    protected void editDto(){};

    public abstract void updateDto();

    public void hideDialogAfterSavingDto(){};

}
