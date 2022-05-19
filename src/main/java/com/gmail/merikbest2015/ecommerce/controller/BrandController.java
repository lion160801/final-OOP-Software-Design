package com.gmail.merikbest2015.ecommerce.controller;


import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.BrandDto;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.PerfumeDto;
import com.gmail.merikbest2015.ecommerce.dto.mapper.Mapper;
import com.gmail.merikbest2015.ecommerce.service.BrandService;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rest")
public class BrandController {
    private final PerfumeService perfumeService;

    private final BrandService brandService;

    private final Mapper mapper;

    @Autowired
    public BrandController(PerfumeService perfumeService, BrandService brandService, Mapper mapper) {
        this.perfumeService = perfumeService;
        this.brandService = brandService;
        this.mapper = mapper;
    }

    @GetMapping("/brands")
    public ResponseEntity<?> getBrands() {
        List<BrandDto> brands = brandService.findAll().stream().map(b -> mapper.brandToBrandDto(b)).collect(Collectors.toList());
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

}
