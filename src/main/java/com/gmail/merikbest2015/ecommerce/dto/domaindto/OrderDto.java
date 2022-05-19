package com.gmail.merikbest2015.ecommerce.dto.domaindto;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
public class OrderDto {

    private Long id;

    private Double totalPrice;

    private LocalDate date;

    private String firstName;

    private String lastName;

    private String city;

    private String address;

    private String email;

    private String phoneNumber;

    private Integer postIndex;

    private List<PerfumeDto> perfumeList;

    private UserDto user;

}
