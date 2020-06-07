package org.spm.coworking.view.enityregistration;

/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/

import lombok.Data;
import org.primefaces.PrimeFaces;
import org.spm.coworking.entity.RentType;
import org.spm.coworking.entity.Rle;
import org.spm.coworking.entity.UserProfile;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

@Component
@SessionScope
@Data
public class UserProfileRegistrationBean extends BaseRegistrationBean {

    UserProfile userProfileDto;

    @Override
    protected boolean validateDto() {
        errors = new ArrayList<>();
        if (userProfileDto.getLogin().isEmpty()) {
            errors.add("Некорректный логин");
            error("Некорректный логин");
            return false;
        }
        if (userProfileDto.getFio().isEmpty()) {
            errors.add("Введите ФИО");
            error("Введите ФИО");
            return false;
        }
        if (serviceHolder.getUserProfileService()
                .findByLogin(userProfileDto.getLogin()).isPresent()) {
            errors.add("Такой пользователь уже существует");
            error("Такой пользователь уже существует");
        }
        if (userProfileDto.getPassword().isEmpty()) {
            errors.add("Введите пароль");
            error("Введите пароль");
        }
        return errors.isEmpty();
    }

    public void saveAdmin() {
        if (!validateDto())
            return;
        saveDto(Rle.ADMIN);
        updateDto();
    }

    public void saveRoot() {
        if (!validateDto())
            return;
        saveDto(Rle.ROOT);
        updateDto();
    }

    public void saveUser() {
        if (!validateDto())
            return;
        saveDto(Rle.USER);
        updateDto();
    }

    private void saveDto(Rle role) {
        userProfileDto.setRoles(Collections.singleton(role));
        userProfileDto.setEntryDate(serviceHolder.getWeekService().getToday());
        serviceHolder.getUserProfileService().save(userProfileDto);
    }

    @Override
    protected void saveDto() {}

    @Override
    public void updateDto() {
        userProfileDto = new UserProfile();
    }

    @Override
    public void hideDialogAfterSavingDto() {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('rentAddDlg').hide();");
    }
}
