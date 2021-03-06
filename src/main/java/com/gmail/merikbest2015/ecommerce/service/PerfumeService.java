package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Brand;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.service.Impl.PerfumeServiceImpl;

import javax.transaction.Transactional;
import java.util.List;


public interface PerfumeService {

    Perfume getOne(Long id);

    List<Perfume> findAll();

    List<Perfume> filter(List<String> brands, List<String> genders, List<Integer> prices);

    List<Perfume> findByBrand_NameOrderByPriceDesc(String perfumer);

    List<Perfume> findByPerfumeGenderOrderByPriceDesc(String perfumeGender);

    void saveProductInfoById(String perfumeTitle, Brand brand, Integer year, String country, String perfumeGender,
                             String fragranceTopNotes, String fragranceMiddleNotes, String fragranceBaseNotes, String description,
                             String filename, Integer price, String volume, String type, Long id);

    Perfume save(Perfume perfume);
}