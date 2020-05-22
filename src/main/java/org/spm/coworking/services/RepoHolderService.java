package org.spm.coworking.services;

import lombok.Getter;
import org.spm.coworking.repository.CityRepository;
import org.spm.coworking.repository.DurationTypeRepository;
import org.spm.coworking.repository.MetroRepository;
import org.spm.coworking.repository.RentTypeRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
@Getter
public class RepoHolderService {

    private final CityRepository cityRepository;

    private final DurationTypeRepository durationTypeRepository;

    private final MetroRepository metroRepository;

    private final RentTypeRepository rentTypeRepository;

    public RepoHolderService(CityRepository cityRepository,
                             DurationTypeRepository durationTypeRepository,
                             MetroRepository metroRepository,
                             RentTypeRepository rentTypeRepository){
        this.cityRepository = cityRepository;
        this.durationTypeRepository = durationTypeRepository;
        this.metroRepository = metroRepository;
        this.rentTypeRepository = rentTypeRepository;
    }

}
