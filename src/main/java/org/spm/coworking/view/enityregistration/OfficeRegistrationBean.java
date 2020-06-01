package org.spm.coworking.view.enityregistration;

/*import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/

import lombok.Data;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.spm.coworking.entity.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@SessionScope
@Data
public class OfficeRegistrationBean extends BaseRegistrationBean {

    private boolean edit;

    private List<Long> durationIds;
    private List<Long> featuresIds;
    private List<Long> rentIds;
    private List<Long> metroIds;

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
        if (rentIds.size() == 0){
            errors.add("Выберите поддерживаемые типы аренды");
            error("Выберите поддерживаемые типы аренды");
            return false;
        }
        if (durationIds.size() == 0){
            errors.add("Выберите поддерживаемые продолжительности аренды");
            error("Выберите поддерживаемые продолжительности аренды");
            return false;
        }
        return errors.isEmpty();
    }

    private void addDurationTypes(){
        for (Long durationId:durationIds) {
            DurationType durationType = serviceHolder
                    .getDurationTypeService().findByDurationTypeId(durationId).get();
            officeDto.getDurationTypes().add(durationType);
        }
    }

    private void addFeatures(){
        for (Long featureId:featuresIds) {
            Feature feature = serviceHolder
                    .getFeatureService().findByFeatureId(featureId).get();
            officeDto.getFeatures().add(feature);
        }
    }

    private void addRentTypes(){
        for (Long rentId:rentIds) {
            RentType rentType = serviceHolder
                    .getRentTypeService().findByRentTypeId(rentId).get();
            officeDto.getRentTypes().add(rentType);
        }
    }

    private void addMetros(){
        for (Long metroId:metroIds) {
            Metro metro = serviceHolder
                    .getMetroService().findByMetroId(metroId).get();
            officeDto.getMetros().add(metro);
        }
    }

    @Override
    protected void saveDto() {
        City city = serviceHolder.getCityService().findCityById(cityId);
        UserProfile admin = serviceHolder.getUserProfileService().findByUserId(adminId);
        officeDto.setCityId(city);
        officeDto.setAdminId(admin);
        addDurationTypes();
        addFeatures();
        addRentTypes();
        addMetros();
        serviceHolder.getOfficeService().save(officeDto);
    }

    @Override
    protected void editDto() {
        officeDto.setDurationTypes(new HashSet<>());
        officeDto.setFeatures(new HashSet<>());
        officeDto.setRentTypes(new HashSet<>());
        officeDto.setMetros(new HashSet<>());
        addDurationTypes();
        addFeatures();
        addRentTypes();
        addMetros();
        serviceHolder.getOfficeService().save(officeDto);
    }

    public void updateForEditDto(Office office) {
        edit = true;
        officeDto = office;
        adminId = office.getAdminId().getUserId();
        cityId = office.getCityId().getCityId();
        durationIds = officeDto.getDurationTypes().stream().map(DurationType::getDurationTypeId)
                .collect(Collectors.toList());
        featuresIds = officeDto.getFeatures().stream().map(Feature::getFeatureId)
                .collect(Collectors.toList());
        rentIds = officeDto.getRentTypes().stream().map(RentType::getRentTypeId)
                .collect(Collectors.toList());
        metroIds = officeDto.getMetros().stream().map(Metro::getMetroId)
                .collect(Collectors.toList());
    }

    @Override
    public void updateDto() {
        edit = false;
        durationIds = new ArrayList<>();
        featuresIds = new ArrayList<>();
        rentIds = new ArrayList<>();
        metroIds = new ArrayList<>();
        adminId = Long.valueOf(0);
        cityId = Long.valueOf(0);
        officeDto = new Office();
        officeDto.setDurationTypes(new HashSet<>());
        officeDto.setFeatures(new HashSet<>());
        officeDto.setRentTypes(new HashSet<>());
        officeDto.setMetros(new HashSet<>());
    }

    @Override
    public void hideDialogAfterSavingDto() {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('officeAddDlg').hide();");
    }
}
