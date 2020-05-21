package com.spectral.demoapplication.service;

import com.spectral.demoapplication.model.entity.Country;
import com.spectral.demoapplication.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CountryService {
    final CountryRepository repository;

    @Transactional(readOnly = true)
    public Country getByName(String countryName) {
        return repository.findCountryByNameIgnoreCase(countryName).orElseThrow(() ->
                new EmptyResultDataAccessException(
                        String.format("Service %s, could not found entity by country name: %s", this, countryName), 1));
    }
}
