package org.spm.coworking.view;

import lombok.Data;
import lombok.SneakyThrows;
import org.primefaces.model.DefaultStreamedContent;
import org.spm.coworking.entity.*;
import org.spm.coworking.view.enityregistration.BaseRegistrationBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Data
@SessionScope
public class OrderBean extends BaseRegistrationBean {

    @Autowired
    private final PayBean payBean;

    public void redirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("search");
    }

    public List<Reservation> getActiveUserReservations(){
        if (!serviceHolder.getAuthService().isAuth())
            return new ArrayList<>();
        List<Reservation> reservations = serviceHolder.getAuthService().getCurrentUserProfile().getReservations();
        return reservations.stream().filter(Reservation::getActive).collect(Collectors.toList());
    }

    public void cancelReservation(Long reservationId) {
        Reservation reservation = serviceHolder
                .getReservationService().findByReservationId(reservationId).get();
        reservation.setActive(false);
        serviceHolder.getReservationService().save(reservation);
    }

    @SneakyThrows
    public void payReservation(Long reservationId) {
        Reservation reservation = serviceHolder
                .getReservationService().findByReservationId(reservationId).get();
        payBean.setReservationDto(reservation);
        redirectToPay();
    }

    public void redirectToPay() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("pay");
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
