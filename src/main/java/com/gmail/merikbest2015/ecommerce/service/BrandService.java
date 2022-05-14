package com.gmail.merikbest2015.ecommerce.service;

import com.gmail.merikbest2015.ecommerce.domain.Brand;

import java.util.List;

public interface BrandService {
    Brand findById(Long id);

    List<Brand> findAll();
}
