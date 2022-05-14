package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.PerfumeDto;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.UserDto;
import com.gmail.merikbest2015.ecommerce.dto.mapper.Mapper;

import com.gmail.merikbest2015.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rest")
public class CartRestController {

    private final UserService userService;

    private final Mapper mapper;

    @Autowired
    public CartRestController(UserService userService, Mapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/cart/{email}")
    public ResponseEntity<?> getCart(@PathVariable String email) {
        User user = userService.findByEmail(email);
        List<Perfume> perfumeList = user.getPerfumeList();
        List<PerfumeDto> perfumes = perfumeList.stream().map(p -> mapper.perfumeToPerFumeDto(p)).collect(Collectors.toList());

        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }

    @PostMapping("/cart/add")
    public ResponseEntity<?> addToCart(@RequestBody PerfumeDto perfume, @AuthenticationPrincipal User userSession) {
        UserDto userDto = mapper.userToUserDto(userSession);
        userDto.getPerfumeList().add(perfume);
        User user = mapper.userDtoToUser(userDto);
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/cart/remove")
    public ResponseEntity<?> removeFromCart(@RequestBody Perfume perfume, @AuthenticationPrincipal User userSession) {
        User user = userService.findByEmail(userSession.getEmail());
        user.getPerfumeList().remove(perfume);

        userService.save(user);

        List<Perfume> perfumeList = user.getPerfumeList();

        return new ResponseEntity<>(perfumeList, HttpStatus.OK);
    }
}
