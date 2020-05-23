package org.spm.coworking.services;

import lombok.Getter;
import org.spm.coworking.services.repo.*;
import org.springframework.stereotype.Service;

@Service
@Getter
public class ServiceHolder {

    private final CityService cityService;

    private final DurationTypeService durationTypeService;

    private final MetroService metroService;

    private final OfficeService officeService;

    private final RentTypeService rentTypeService;

    private final UserProfileService userProfileService;

    public ServiceHolder(CityService cityService,
                         DurationTypeService durationTypeService,
                         MetroService metroService,
                         OfficeService officeService,
                         RentTypeService rentTypeService,
                         UserProfileService userProfileService){
        this.cityService = cityService;
        this.durationTypeService = durationTypeService;
        this.metroService = metroService;
        this.officeService = officeService;
        this.rentTypeService = rentTypeService;
        this.userProfileService = userProfileService;
    }

}
