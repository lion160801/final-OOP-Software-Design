package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.Review;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.AuthenticationRequestDTO;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.OrderDto;
import com.gmail.merikbest2015.ecommerce.dto.mapper.Mapper;
import com.gmail.merikbest2015.ecommerce.service.OrderService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/rest")
public class UserRestController {

    private final UserService userService;

    private final OrderService orderService;

    Mapper mapper;

    @Autowired
    public UserRestController(UserService userService, OrderService orderService, Mapper mapper) {
        this.userService = userService;
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @GetMapping("/user/edit")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal User userSession) {
        User user = userService.findByEmail(userSession.getEmail());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/user/edit")
    public ResponseEntity<?> updateUserInfo(
            @AuthenticationPrincipal User userSession,
            @RequestBody AuthenticationRequestDTO request
    ) {
        userService.updateProfile(userSession, request.getPassword(), request.getEmail());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/orders")
    public ResponseEntity<?> getAllUserOrders(@AuthenticationPrincipal User userSession) {
        User user = userService.findByEmail(userSession.getEmail());
        List<OrderDto> orders = orderService.findOrderByUser(user).stream().map(o->mapper.orderToOrderDto(o)).collect(Collectors.toList());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/user/review")
    public ResponseEntity<?> addReviewToPerfume(
            @RequestParam(required = false, name = "perfumeId") Long perfumeId,
            @Valid Review review,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            userService.addReviewToPerfume(review, perfumeId);

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
