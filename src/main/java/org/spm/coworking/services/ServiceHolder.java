package org.spm.coworking.services;

import lombok.Getter;
import org.spm.coworking.services.repo.*;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ServiceHolder {

    private final AuthService authService;

    private final CityService cityService;

    private final DurationTypeService durationTypeService;

    private final FeatureService featureService;

    private final PlaceService placeService;

    private final MetroService metroService;

    private final OfficeService officeService;

    private final RentTypeService rentTypeService;

    private final UserProfileService userProfileService;

    public ServiceHolder(AuthService authService,
                         CityService cityService,
                         DurationTypeService durationTypeService,
                         FeatureService featureService,
                         PlaceService placeService,
                         MetroService metroService,
                         OfficeService officeService,
                         RentTypeService rentTypeService,
                         UserProfileService userProfileService) {
        this.authService = authService;
        this.cityService = cityService;
        this.durationTypeService = durationTypeService;
        this.featureService = featureService;
        this.placeService = placeService;
        this.metroService = metroService;
        this.officeService = officeService;
        this.rentTypeService = rentTypeService;
        this.userProfileService = userProfileService;
    }

}
