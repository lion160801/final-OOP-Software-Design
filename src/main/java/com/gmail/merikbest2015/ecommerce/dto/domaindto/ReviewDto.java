package com.gmail.merikbest2015.ecommerce.dto.domaindto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReviewDto {

    private Long id;

    private String author;

    private String message;

    private LocalDate date;
}
