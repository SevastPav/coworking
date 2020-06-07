package org.spm.coworking.view;

import lombok.Data;
import org.spm.coworking.entity.Office;
import org.spm.coworking.entity.Reservation;
import org.spm.coworking.entity.Rle;
import org.spm.coworking.entity.UserProfile;
import org.spm.coworking.view.enityregistration.BaseRegistrationBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Component
@Data
@SessionScope
public class PersonalAreaBean extends BaseRegistrationBean {

    private Long officeId;

    private Office officeDto;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<Reservation> reservations = new ArrayList<>();
    private List<Reservation> paidReservations = new ArrayList<>();
    private ConcurrentMap<Long, Integer> placesFreq;

    private List<Reservation> rootReservations = new ArrayList<>();
    private List<UserProfile> newUsers = new ArrayList<>();

    public void redirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("search");
    }

    public void onChange(){
        officeDto = serviceHolder.getOfficeService().findByOfficeId(officeId);
        reservations = new ArrayList<>();
        paidReservations = new ArrayList<>();

        rootReservations = new ArrayList<>();
    }

    public List<Reservation> getTodayReservations(){
        return serviceHolder.getReservationService()
                .findAllByOfficeIdAndDate(officeDto, serviceHolder.getWeekService().getToday());
    }

    public Map<String, Long> getOfficesMap() {
        if (serviceHolder.getAuthService().isAuth() &&
            serviceHolder.getAuthService().hasRole(Rle.ADMIN.name())) {
            List<Office> managedOffices = serviceHolder.getAuthService()
                    .getCurrentUserProfile().getManagedOffices();
            officeDto = managedOffices.get(0);
            officeId = officeDto.getOfficeId();
            return managedOffices.stream().collect(Collectors
                    .toMap(Office::getTitle, Office::getOfficeId));
        }
        return null;
    }

    public boolean validateDates(){
        if (startDate == null ||
            endDate == null ||
            startDate.isAfter(endDate)){
            error("Некорректные даты");
            return false;
        }
        return true;
    }

    public void showReservations() {
        if(validateDates()){
            reservations = serviceHolder.getReservationService()
                    .findAllByOfficeIdAndDateBeforeAndDateAfter(officeId, startDate, endDate);
            paidReservations = reservations.stream().filter(Reservation::getPaid).collect(Collectors.toList());
            placesFreq = reservations.stream().collect(Collectors.toConcurrentMap(
                    p -> p.getPlaceId().getInternalPlaceId(), p -> 1, Integer::sum));
        }
    }

    public void showStatistic() {
        if(validateDates()){
            rootReservations = serviceHolder.getReservationService()
                    .findAllByDateBeforeAndDateAfter(startDate, endDate);
            newUsers = serviceHolder.getUserProfileService()
                    .findAllByDateBeforeAndDateAfter(startDate, endDate);
        }
    }

    @Override
    protected boolean validateDto() {
        return true;
    }

    @Override
    protected void saveDto() {
        return;
    }

    @Override
    public void updateDto() {
        return;
    }
}
