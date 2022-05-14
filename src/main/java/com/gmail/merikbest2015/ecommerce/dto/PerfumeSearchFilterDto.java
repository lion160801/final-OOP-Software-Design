package com.gmail.merikbest2015.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class PerfumeSearchFilterDto {
    List<Integer> prices;
    List<String> brands;
    List<String> genders;
    String perfumeGender;
    String perfumer;
}
