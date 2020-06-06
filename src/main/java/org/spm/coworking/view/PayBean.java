package org.spm.coworking.view;

import lombok.Data;
import lombok.SneakyThrows;
import org.primefaces.PrimeFaces;
import org.spm.coworking.entity.*;
import org.spm.coworking.view.enityregistration.BaseRegistrationBean;
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
public class PayBean extends BaseRegistrationBean {

    private Reservation reservationDto;

    private String cardNumber;
    private String fio;
    private String date;
    private String cvc;

    @Override
    protected boolean validateDto() {
        errors = new ArrayList<>();
        if (cardNumber.equals("")) {
            errors.add("Некорректный номер карты");
            error("Некорректный номер карты");
            return false;
        }
        if (fio.equals("")) {
            errors.add("Некорректное ФИО");
            error("Некорректное ФИО");
            return false;
        }
        if (date.equals("")) {
            errors.add("Некорректная дата");
            error("Некорректная дата");
            return false;
        }
        if (cvc.equals("")) {
            errors.add("Некорректный cvc");
            error("Некорректный cvc");
            return false;
        }
        return errors.isEmpty();
    }

    @SneakyThrows
    @Override
    protected void saveDto() {
        reservationDto.setPaid(true);
        serviceHolder.getReservationService().save(reservationDto);
        redirectToOders();
    }

    @Override
    public void updateDto() {
        reservationDto = new Reservation();
        cardNumber = "";
        fio = "";
        date = "";
        cvc = "";
    }

    public void redirectToOders() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("orders");
    }

}
