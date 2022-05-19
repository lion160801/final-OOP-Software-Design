package com.gmail.merikbest2015.ecommerce.repository;


import com.gmail.merikbest2015.ecommerce.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository  extends JpaRepository<Brand, Long> {

    List<Brand> findAllByOrderByNameAsc();

}
