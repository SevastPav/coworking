package org.spm.coworking.services;

import lombok.Getter;
import org.spm.coworking.services.repo.*;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ServiceHolder {

    private final WeekService weekService;

    private final AuthService authService;

    private final CityService cityService;

    private final DurationTypeService durationTypeService;

    private final ImageService imageService;

    private final FeatureService featureService;

    private final PlaceService placeService;

    private final MetroService metroService;

    private final OfficeService officeService;

    private final RentTypeService rentTypeService;

    private final UserProfileService userProfileService;

    private final ReservationService reservationService;

    public ServiceHolder(WeekService weekService,
                         AuthService authService,
                         CityService cityService,
                         DurationTypeService durationTypeService,
                         ImageService imageService,
                         FeatureService featureService,
                         PlaceService placeService,
                         MetroService metroService,
                         OfficeService officeService,
                         RentTypeService rentTypeService,
                         UserProfileService userProfileService,
                         ReservationService reservationService) {
        this.weekService = weekService;
        this.authService = authService;
        this.cityService = cityService;
        this.durationTypeService = durationTypeService;
        this.imageService = imageService;
        this.featureService = featureService;
        this.placeService = placeService;
        this.metroService = metroService;
        this.officeService = officeService;
        this.rentTypeService = rentTypeService;
        this.userProfileService = userProfileService;
        this.reservationService = reservationService;
    }

}
