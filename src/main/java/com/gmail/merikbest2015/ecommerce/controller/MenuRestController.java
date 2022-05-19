package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.dto.PerfumeSearchFilterDto;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.PerfumeDto;
import com.gmail.merikbest2015.ecommerce.dto.mapper.Mapper;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rest")
public class MenuRestController {


    Mapper mapper;
    private final PerfumeService perfumeService;

    @Autowired
    public MenuRestController(PerfumeService perfumeService, Mapper mapper) {
        this.perfumeService = perfumeService;
        this.mapper = mapper;
    }

    @PostMapping("/menu/search")
    public ResponseEntity<?> findProductsByFilterParams(@RequestBody PerfumeSearchFilterDto filterDto) {

        filterDto.setGenders(filterDto.getGenders().stream().map(g->g.toLowerCase()).collect(Collectors.toList()));
        List<String> brands = filterDto.getBrands()!=null?filterDto.getBrands(): Collections.emptyList();
        List<String> genders = filterDto.getGenders()!=null?filterDto.getGenders(): Collections.emptyList();
        List<Integer> prices = filterDto.getPrices()!=null?filterDto.getPrices(): Collections.emptyList();

        List<PerfumeDto> perfumes = perfumeService.filter(brands, genders, prices).stream().map(p -> mapper.perfumeToPerFumeDto(p)).collect(Collectors.toList());
        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }

    @PostMapping("/menu/gender")
    public ResponseEntity<?> findByPerfumeGender(@RequestBody PerfumeSearchFilterDto filterDto) {
        String gender = filterDto.getPerfumeGender().toLowerCase();
        List<PerfumeDto> perfumes = perfumeService.findByPerfumeGenderOrderByPriceDesc(gender).stream().map(p -> mapper.perfumeToPerFumeDto(p)).collect(Collectors.toList());
        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }

    @PostMapping("/menu/brand")
    public ResponseEntity<?> findByPerfumer(@RequestBody PerfumeSearchFilterDto filterDto) {
        String brand = filterDto.getBrand();
        List<PerfumeDto> perfumes = perfumeService.findByBrand_NameOrderByPriceDesc(brand).stream().map(p -> mapper.perfumeToPerFumeDto(p)).collect(Collectors.toList());
        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }

}
