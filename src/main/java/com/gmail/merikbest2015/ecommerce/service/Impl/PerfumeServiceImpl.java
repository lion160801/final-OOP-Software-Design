package com.gmail.merikbest2015.ecommerce.service.Impl;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.repository.PerfumeRepository;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class PerfumeServiceImpl implements PerfumeService {

    private final PerfumeRepository perfumeRepository;

    @Autowired
    public PerfumeServiceImpl(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    @Override
    public Perfume getOne(Long id) {
        return perfumeRepository.getOne(id);
    }

    @Override
    public List<Perfume> findAll() {
        return perfumeRepository.findAll();
    }


    @Override
    public List<Perfume> filter(List<String> brands, List<String> genders, List<Integer> prices) {
        List<Perfume> perfumeList = new ArrayList<>();

        if (!prices.isEmpty()) {
            if (!brands.isEmpty() && !genders.isEmpty()){
                perfumeList = perfumeRepository.findByPriceBetweenAndBrand_NameInAndPerfumeGenderInOrderByPriceDesc( prices.get(0), prices.get(1), brands, genders);
            } else if (!brands.isEmpty() || !genders.isEmpty()) {
                perfumeList = perfumeRepository.findByPriceBetweenAndBrand_NameInOrPerfumeGenderInOrderByPriceDesc(prices.get(0), prices.get(1), brands, genders );
            } else {
                perfumeList = perfumeRepository.findByPriceBetweenOrderByPriceDesc(prices.get(0), prices.get(1));
            }
        } else if (!brands.isEmpty() && !genders.isEmpty()) {
            perfumeList = perfumeRepository.findByBrand_NameInAndPerfumeGenderInOrderByPriceDesc(brands, genders);
        } else if (!brands.isEmpty() || !genders.isEmpty()) {
            perfumeList = perfumeRepository.findByBrand_NameInOrPerfumeGenderInOrderByPriceDesc(brands, genders);
        } else {
            perfumeList = perfumeRepository.findAll();
        }
        return perfumeList;
    }

    @Override
    public List<Perfume> findByPerfumerOrderByPriceDesc(String perfumer) {
        return perfumeRepository.findByBrand_NameOrderByPriceDesc(perfumer);
    }

    @Override
    public List<Perfume> findByPerfumeGenderOrderByPriceDesc(String perfumeGender) {
        return perfumeRepository.findByPerfumeGenderOrderByPriceDesc(perfumeGender);
    }

    @Override
    public void saveProductInfoById(String perfumeTitle, String perfumer, Integer year, String country,
                                    String perfumeGender, String fragranceTopNotes, String fragranceMiddleNotes,
                                    String fragranceBaseNotes, String description, String filename,
                                    Integer price, String volume, String type, Long id
    ) {
        perfumeRepository.saveProductInfoById(perfumeTitle, perfumer, year, country, perfumeGender, fragranceTopNotes,
                fragranceMiddleNotes, fragranceBaseNotes, description, filename, price, volume, type, id);
    }

    @Override
    public Perfume save(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }
}
