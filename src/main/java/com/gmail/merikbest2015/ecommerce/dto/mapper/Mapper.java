package com.gmail.merikbest2015.ecommerce.dto.mapper;

import com.gmail.merikbest2015.ecommerce.domain.*;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.*;
import com.gmail.merikbest2015.ecommerce.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {

    @Autowired
    BrandService brandService;
    
    public Perfume perfumeDtoToEntity(PerfumeDto dto) {
        Perfume entity = new Perfume();
        entity.setId(dto.getId());
        entity.setPerfumer(dto.getPerfumer());
        entity.setFilename(dto.getFilename());
        entity.setPerfumeTitle(dto.getPerfumeTitle());
        entity.setPerfumeGender(dto.getPerfumeGender());
        entity.setBrand(brandService.findById(dto.getBrandId()));
        entity.setCountry(dto.getCountry());
        entity.setDescription(dto.getDescription());
        entity.setYear(dto.getYear());
        entity.setVolume(dto.getVolume());
        entity.setReviews(dto.getReviews().stream().map(r->reviewDtoToReview(r)).collect(Collectors.toList()));
        entity.setFragranceBaseNotes(dto.getFragranceBaseNotes());
        entity.setFragranceMiddleNotes(dto.getFragranceMiddleNotes());
        entity.setFragranceTopNotes(dto.getFragranceTopNotes());
        entity.setPrice(dto.getPrice());
        entity.setType(dto.getType());
        return entity;
    }

    public PerfumeDto perfumeToPerFumeDto(Perfume entity) {
        PerfumeDto dto = new PerfumeDto();
        dto.setId(entity.getId());
        dto.setPerfumer(entity.getPerfumer());
        dto.setFilename(entity.getFilename());
        dto.setPerfumeTitle(entity.getPerfumeTitle());
        dto.setPerfumeGender(entity.getPerfumeGender());
        dto.setBrandName(entity.getBrand().getName());
        dto.setBrandId(entity.getBrand().getId());
        dto.setCountry(entity.getCountry());
        dto.setDescription(entity.getDescription());
        dto.setYear(entity.getYear());
        dto.setVolume(entity.getVolume());
        dto.setReviews(entity.getReviews().stream().map(r-> reviewToReviewDto(r)).collect(Collectors.toList()));
        dto.setFragranceBaseNotes(entity.getFragranceBaseNotes());
        dto.setFragranceMiddleNotes(entity.getFragranceMiddleNotes());
        dto.setFragranceTopNotes(entity.getFragranceTopNotes());
        dto.setPrice(entity.getPrice());
        dto.setType(entity.getType());
        return dto;
    }

    public Brand brandDtoToBrand(BrandDto dto){
        Brand entity = new Brand();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    public BrandDto brandToBrandDto(Brand entity) {
        BrandDto dto = new BrandDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public UserDto userToUserDto(User entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setEmail(entity.getEmail());
        dto.setRoles(entity.getRoles());
        dto.setPassword(entity.getPassword());
        dto.setActivationCode(entity.getActivationCode());
        dto.setPasswordResetCode(entity.getPasswordResetCode());
        dto.setActive(entity.isActive());
        dto.setUsername(entity.getUsername());
        dto.setPerfumeList(entity.getPerfumeList().stream().map(p->perfumeToPerFumeDto(p)).collect(Collectors.toList()));
        return dto;
    }

    public User userDtoToUser(UserDto dto) {
        User entity = new User();
        entity.setId(dto.getId());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRoles(dto.getRoles());
        entity.setActivationCode(dto.getActivationCode());
        entity.setPasswordResetCode(dto.getPasswordResetCode());
        entity.setActive(dto.isActive());
        entity.setUsername(dto.getUsername());
        entity.setPerfumeList(dto.getPerfumeList().stream().map(p -> perfumeDtoToEntity(p)).collect(Collectors.toList()));
        return entity;
    }

    public Order orderDtoToOrder(OrderDto dto) {
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setCity(dto.getCity());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setPostIndex(dto.getPostIndex());
        entity.setDate(dto.getDate());
        entity.setPerfumeList(dto.getPerfumeList().stream().map(p -> perfumeDtoToEntity(p)).collect(Collectors.toList()));
        entity.setUser(userDtoToUser(dto.getUser()));
        entity.setTotalPrice(dto.getTotalPrice());
        return entity;
    }

    public OrderDto orderToOrderDto(Order entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setPostIndex(entity.getPostIndex());
        dto.setDate(entity.getDate());
        dto.setPerfumeList(entity.getPerfumeList().stream().map(p -> perfumeToPerFumeDto(p)).collect(Collectors.toList()));
        dto.setUser(userToUserDto(entity.getUser()));
        dto.setTotalPrice(entity.getTotalPrice());
        return dto;
    }


    public Review reviewDtoToReview(ReviewDto dto) {
        Review entity = new Review();
        entity.setId(dto.getId());
        entity.setAuthor(dto.getAuthor());
        entity.setDate(dto.getDate());
        entity.setMessage(dto.getMessage());
        return entity;
    }

    public ReviewDto reviewToReviewDto(Review entity) {
        ReviewDto dto = new ReviewDto();
        dto.setId(entity.getId());
        dto.setAuthor(entity.getAuthor());
        dto.setDate(entity.getDate());
        dto.setMessage(entity.getMessage());
        return dto;
    }

}
