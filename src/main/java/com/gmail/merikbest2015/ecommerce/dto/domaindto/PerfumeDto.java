package com.gmail.merikbest2015.ecommerce.dto.domaindto;

import com.gmail.merikbest2015.ecommerce.domain.Brand;
import com.gmail.merikbest2015.ecommerce.domain.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PerfumeDto {

    private Long id;
    private String perfumeTitle;
    private String perfumer;
    private Integer year;
    private String country;
    private String perfumeGender;
    private String fragranceTopNotes;
    private String fragranceMiddleNotes;
    private String fragranceBaseNotes;
    private String description;
    private String filename;
    private Integer price;
    private String volume;
    private String type;
    private List<ReviewDto> reviews = new ArrayList<>();

    private String brandName;
    private Long brandId;
}
