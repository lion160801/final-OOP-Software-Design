package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.BrandDto;
import com.gmail.merikbest2015.ecommerce.dto.mapper.Mapper;

import com.gmail.merikbest2015.ecommerce.dto.domaindto.PerfumeDto;
import com.gmail.merikbest2015.ecommerce.service.BrandService;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/rest")
public class MainRestController {

    private final PerfumeService perfumeService;

    private final BrandService brandService;

    private final Mapper mapper;

    @Autowired
    public MainRestController(PerfumeService perfumeService, BrandService brandService, Mapper mapper) {
        this.perfumeService = perfumeService;
        this.brandService = brandService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Perfume> perfumes = perfumeService.findAll();

        List<PerfumeDto> perfumeDtoList = perfumes.stream().map(p -> mapper.perfumeToPerFumeDto(p)).collect(Collectors.toList());
        return new ResponseEntity<>(perfumeDtoList, HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") Long perfumeId) {
//        Perfume perfume = perfumeService.getOne(perfumeId);
        PerfumeDto perfume = mapper.perfumeToPerFumeDto(perfumeService.getOne(perfumeId));

        return new ResponseEntity<>(perfume, HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<?> getBrands() {
        List<BrandDto> brands = brandService.findAll().stream().map(b -> mapper.brandToBrandDto(b)).collect(Collectors.toList());
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }
}
