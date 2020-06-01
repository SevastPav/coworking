package org.spm.coworking.services.repo;

import lombok.Getter;
import org.spm.coworking.entity.Feature;
import org.spm.coworking.entity.Image;
import org.springframework.stereotype.Service;

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

    @Override
    public Set<Image> getSetOfEntitiesById(long id) {
        return null;
    }

    @Override
    public Set<Image> getSetOfAllEntities() {
        return null;
    }
}
