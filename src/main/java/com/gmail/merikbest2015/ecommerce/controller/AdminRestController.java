package com.gmail.merikbest2015.ecommerce.controller;

import com.gmail.merikbest2015.ecommerce.domain.Order;
import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.User;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.OrderDto;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.PerfumeDto;
import com.gmail.merikbest2015.ecommerce.dto.domaindto.UserDto;
import com.gmail.merikbest2015.ecommerce.dto.mapper.Mapper;
import com.gmail.merikbest2015.ecommerce.service.OrderService;
import com.gmail.merikbest2015.ecommerce.service.PerfumeService;
import com.gmail.merikbest2015.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/rest")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminRestController {

    @Value("${upload.path}")
    private String uploadPath;

    private final UserService userService;

    private final PerfumeService perfumeService;

    private final OrderService orderService;

    private final Mapper mapper;

    @Autowired
    public AdminRestController(UserService userService, PerfumeService perfumeService, OrderService orderService, Mapper mapper) {
        this.userService = userService;
        this.perfumeService = perfumeService;
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @PostMapping("/admin/add")
    public ResponseEntity<?> addPerfume(
            @Valid PerfumeDto perfumeDto,
            BindingResult bindingResult,
            @RequestPart(name = "file", required = false) MultipartFile file
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            Perfume perfume = mapper.perfumeDtoToEntity(perfumeDto);
            saveFile(perfume, file);

            Perfume savedPerfume = perfumeService.save(perfume);

            PerfumeDto addedPerfume = mapper.perfumeToPerFumeDto(savedPerfume);
            return new ResponseEntity<>(addedPerfume, HttpStatus.CREATED);
        }
    }

    @PutMapping("/admin/edit")
    public ResponseEntity<?> updatePerfume(
            @Valid PerfumeDto perfumeDto,
            BindingResult bindingResult,
            @RequestPart(name = "file", required = false) MultipartFile file
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            Perfume perfume = mapper.perfumeDtoToEntity(perfumeDto);
            if (file != null) {
                saveFile(perfume, file);
            }
            perfumeService.saveProductInfoById(perfume.getPerfumeTitle(), perfume.getBrand(), perfume.getYear(),
                    perfume.getCountry(), perfume.getPerfumeGender(), perfume.getFragranceTopNotes(),
                    perfume.getFragranceMiddleNotes(), perfume.getFragranceBaseNotes(), perfume.getDescription(),
                    perfume.getFilename(), perfume.getPrice(), perfume.getVolume(), perfume.getType(), perfume.getId());

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/admin/orders")
    public ResponseEntity<?> getAllOrders() {
        List<OrderDto> orders = orderService.findAll().stream().map(o->mapper.orderToOrderDto(o)).collect(Collectors.toList());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/admin/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long userId) {
        UserDto user = mapper.userToUserDto(userService.getOne(userId));

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/admin/user/all")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDto> userDtoList = users.stream().map(p -> mapper.userToUserDto(p)).collect(Collectors.toList());

        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @PutMapping("/admin/user/edit")
    public ResponseEntity<?> updateUser(
           @Valid UserDto userDto,
           BindingResult bindingResult
//            @RequestParam String username,
//            @RequestParam Map<String, String> form,
//            @RequestParam("userId") User user
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            User user = userService.getOne(userDto.getId());
            user.setRoles(userDto.getRoles());
            user.setUsername(userDto.getUsername());
            userService.save(user);

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    private void saveFile(Perfume perfume, @RequestParam("file") MultipartFile file) throws IOException {
        if (file == null) {
            perfume.setFilename("empty.jpg");
        } else {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            perfume.setFilename(resultFilename);
        }
    }
}
