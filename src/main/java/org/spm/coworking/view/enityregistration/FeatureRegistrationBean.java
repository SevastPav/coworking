package org.spm.coworking.view.enityregistration;

import lombok.Data;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.spm.coworking.entity.Feature;
import org.spm.coworking.entity.Image;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;
import java.util.ArrayList;

@Component
@SessionScope
@Data
public class FeatureRegistrationBean extends BaseRegistrationBean {

    private UploadedFile icon;

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
        if (icon == null || icon.getContents().length == 0){
            errors.add("Выберите иконку");
            error("Выберите иконку");
            return false;
        }
        return errors.isEmpty();
    }

    public void uploadPhoto(FileUploadEvent event) throws IOException{
        icon = event.getFile();
    }

    public void fileUploadListener(FileUploadEvent event) throws IOException {
        icon = event.getFile();
    }

    @Override
    protected void saveDto() {
        serviceHolder.getFeatureService().save(featureDto);
        Image icon = new Image();
        icon.setImage(this.icon.getContents());
        icon.setFeature(featureDto);
        serviceHolder.getImageService().save(icon);
    }

    @Override
    public void updateDto() {
        icon = null;
        featureDto = new Feature();
    }

    @Override
    public void hideDialogAfterSavingDto() {
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('featureAddDlg').hide();");
    }
}
