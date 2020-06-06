package org.spm.coworking.services.repo;

import lombok.Getter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.spm.coworking.entity.Feature;
import org.spm.coworking.entity.Image;
import org.springframework.stereotype.Service;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Getter
public class ImageService extends BaseRepoService<Image> {

    public Optional<Image> findByImageId(Long id){
        return repoHolderService.getImageRepository().findByImageId(id);
    }

    public void save(Image image){
        repoHolderService.getImageRepository().save(image);
    }

    public StreamedContent getImage() {
        if (FacesContext.getCurrentInstance().getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        }
        else {
            Map<String, String> params = FacesContext.getCurrentInstance()
                    .getExternalContext().getRequestParameterMap();

            String param = params.get("image_id");
            if (param.equals("")){
                return new DefaultStreamedContent();
            }
            Long imageId = Long.valueOf(param);
            Optional<Image> imageOpt = repoHolderService.getImageRepository().findByImageId(imageId);

            if (imageOpt.isPresent()  && imageOpt.get().getImage() != null){
                return new DefaultStreamedContent(
                        new ByteArrayInputStream(imageOpt.get().getImage()),
                        "image/jpeg");
            }
            return new DefaultStreamedContent();
        }
    }

    @Override
    public Set<Image> getSetOfEntitiesById(long id) {
        return null;
    }

    @Override
    public Set<Image> getSetOfAllEntities() {
        return null;
    }
}
