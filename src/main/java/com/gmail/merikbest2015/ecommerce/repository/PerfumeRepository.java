package com.gmail.merikbest2015.ecommerce.repository;

import com.gmail.merikbest2015.ecommerce.domain.Brand;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface PerfumeRepository extends JpaRepository<Perfume, Long> {


    List<Perfume> findByPriceBetweenAndBrand_NameInAndPerfumeGenderInOrderByPriceDesc(Integer startingPrice, Integer endingPrice, List<String> brands, List<String> genders);

    @Query ("select p from Perfume p where p.price between :startingPrice and :endingPrice and (p.brand.name in :brands or p.perfumeGender in :genders)")
    List<Perfume> findByPriceBetweenAndBrand_NameInOrPerfumeGenderInOrderByPriceDesc(Integer startingPrice, Integer endingPrice, List<String> brands, List<String> genders);

    //Price
    List<Perfume> findByPriceBetweenOrderByPriceDesc(Integer startingPrice, Integer endingPrice);

    //Brand and Gender
    List<Perfume> findByBrand_NameInAndPerfumeGenderInOrderByPriceDesc(List<String> brands, List<String> genders);

    //Brand or Gender
    List<Perfume> findByBrand_NameInOrPerfumeGenderInOrderByPriceDesc(List<String> brands, List<String> genders);

    List<Perfume> findByBrand_NameOrderByPriceDesc(String brand);

    List<Perfume> findByPerfumeGenderOrderByPriceDesc(String perfumeGender);

    @Modifying
    @Transactional
    @Query("update Perfume p set p.perfumeTitle = ?1, p.brand = ?2, p.year = ?3, p.country = ?4, " +
            "p.perfumeGender = ?5, p.fragranceTopNotes = ?6, p.fragranceMiddleNotes = ?7, p.fragranceBaseNotes = ?8," +
            "p.description = ?9, p.filename = ?10, p.price = ?11, p.volume = ?12, p.type = ?13  where p.id = ?14")
    void saveProductInfoById(String perfumeTitle, Brand brand, Integer year, String country, String perfumeGender,
                             String fragranceTopNotes, String fragranceMiddleNotes, String fragranceBaseNotes, String description,
                             String filename, Integer price, String volume, String type, Long id);
}