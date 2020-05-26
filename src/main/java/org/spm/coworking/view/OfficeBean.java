package org.spm.coworking.view;

import lombok.Data;
import org.spm.coworking.entity.*;
import org.spm.coworking.services.ServiceHolder;
import org.spm.coworking.view.enityregistration.BaseRegistrationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Data
@SessionScope
public class OfficeBean extends BaseRegistrationBean {

    private Office office;
    private Reservation reservationDto;

    private Date date;
    private Date time;
    private Long typeOfRentId;
    private Long durationTypeId;

    public Map<String, Long> getRentTypesMap() {
        return office.getRentTypes().stream().collect(Collectors
                .toMap(RentType::getTitle, RentType::getRentTypeId));
    }

    public Map<String, Long> getDurationTypesMap() {
        return office.getDurationTypes().stream().collect(Collectors
                .toMap(DurationType::getTitle, DurationType::getDurationTypeId));
    }

    public List<Feature> getTop3Features() {
        return office.getFeatures().stream().limit(3).collect(Collectors.toList());
    }

    public List<Feature> getFirstHalfFeatures() {
        Set<Feature> features = office.getFeatures();
        return features.stream().limit(features.size() / 2).collect(Collectors.toList());
    }

    public List<Feature> getSecondHalfFeatures() {
        Set<Feature> features = office.getFeatures();
        return features.stream().skip(features.size() / 2).collect(Collectors.toList());
    }

    public void redirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("search");
    }

    @Override
    protected boolean validateDto() {
        errors = new ArrayList<>();
        if (date == null) {
            errors.add("Некорректная дата");
            error("Некорректная дата");
            return false;
        }
        if (time == null) {
            errors.add("Некорректное время");
            error("Некорректное время");
            return false;
        }
        if (typeOfRentId == 0) {
            errors.add("Некорректный тип аренды");
            error("Некорректный тип аренды");
            return false;
        }
        if (durationTypeId == 0) {
            errors.add("Некорректная продолжительность аренды");
            error("Некорректная продолжительность аренды");
            return false;
        }
        return errors.isEmpty();
    }

    @Override
    protected void saveDto() {
        UserProfile user = serviceHolder.getAuthService().getCurrentUserProfile();

        DurationType durationType = serviceHolder
                .getDurationTypeService().findByDurationTypeId(durationTypeId).get();
        RentType rentType = serviceHolder
                .getRentTypeService().findByRentTypeId(typeOfRentId).get();
        reservationDto.setDurationTypeId(durationType);
        reservationDto.setRentTypeId(rentType);
        reservationDto.setDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        reservationDto.setTime(time.toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
        reservationDto.setUserId(user);
    }

    @Override
    public void updateDto() {
        reservationDto = new Reservation();
        date = null;
        time = null;
        typeOfRentId = Long.valueOf(0);
        durationTypeId = Long.valueOf(0);
    }
}
