package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.OrderDto;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.PerfumeDto;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/rest")
public class OrderRestController {

    private final UserService userService;

    private final OrderService orderService;

    Mapper mapper;

    @Autowired
    public OrderRestController(UserService userService, OrderService orderService, Mapper mapper) {
        this.userService = userService;
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @GetMapping("/order")
    public ResponseEntity<?> getOrder(@AuthenticationPrincipal User userSession) {
        List<Perfume> perfumeList = userSession.getPerfumeList();
        List<PerfumeDto> perfumes = perfumeList.stream().map(p -> mapper.perfumeToPerFumeDto(p)).collect(Collectors.toList());

        return new ResponseEntity<>(perfumes, HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<?> postOrder(
            @AuthenticationPrincipal User userSession,
            @Valid @RequestBody Order validOrder,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {

            OrderDto order = mapper.orderToOrderDto(orderService.postOrder(validOrder, userSession));

            return new ResponseEntity<>(order, HttpStatus.CREATED);
        }
    }

    @GetMapping("/order/finalize")
    public ResponseEntity<?> finalizeOrder() {
        List<Order> orderList = orderService.findAll();
        Order orderIndex = orderList.get(orderList.size() - 1);

        return new ResponseEntity<>(orderIndex.getId(), HttpStatus.OK);
    }

    @GetMapping("/order/list")
    public ResponseEntity<?> getUserOrdersList(@AuthenticationPrincipal User userSession) {
        User user = userService.findByEmail(userSession.getEmail());
        List<Order> orders = orderService.findOrderByUser(user);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
