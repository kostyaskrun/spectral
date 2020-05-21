package com.spectral.demoapplication.repository;


import com.spectral.demoapplication.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findCountryByNameIgnoreCase(String countryName);
}
