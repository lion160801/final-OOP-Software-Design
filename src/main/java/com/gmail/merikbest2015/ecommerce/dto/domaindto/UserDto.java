package com.gmail.merikbest2015.ecommerce.dto.domaindto;

import com.gmail.merikbest2015.ecommerce.domain.Perfume;
import com.gmail.merikbest2015.ecommerce.domain.Role;
import com.gmail.merikbest2015.ecommerce.domain.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserDto {

    private Long id;

    private String email;

    private Set<Role> roles;

    private String username;

    private String password;

    private boolean active;

    private List<PerfumeDto> perfumeList;

    private String activationCode;

    private String passwordResetCode;


}
