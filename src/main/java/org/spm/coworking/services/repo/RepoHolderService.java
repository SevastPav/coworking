package org.spm.coworking.services.repo;

import lombok.Getter;
import org.spm.coworking.repository.*;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
@Getter
public class RepoHolderService {

    private final CityRepository cityRepository;

    private final DurationTypeRepository durationTypeRepository;

    private final FeatureRepository featureRepository;

    private final ReservationRepository reservationRepository;

    private final PlaceRepository placeRepository;

    private final MetroRepository metroRepository;

    private final RentTypeRepository rentTypeRepository;

    private final OfficeRepository officeRepository;

    private final UserProfileRepository userProfileRepository;

    public RepoHolderService(CityRepository cityRepository,
                             DurationTypeRepository durationTypeRepository,
                             FeatureRepository featureRepository,
                             ReservationRepository reservationRepository,
                             PlaceRepository placeRepository,
                             MetroRepository metroRepository,
                             RentTypeRepository rentTypeRepository,
                             OfficeRepository officeRepository,
                             UserProfileRepository userProfileRepository){
        this.cityRepository = cityRepository;
        this.durationTypeRepository = durationTypeRepository;
        this.featureRepository = featureRepository;
        this.reservationRepository = reservationRepository;
        this.placeRepository = placeRepository;
        this.metroRepository = metroRepository;
        this.rentTypeRepository = rentTypeRepository;
        this.officeRepository = officeRepository;
        this.userProfileRepository = userProfileRepository;
    }

}