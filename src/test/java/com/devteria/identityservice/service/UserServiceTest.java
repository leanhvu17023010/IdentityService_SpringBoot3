package com.devteria.identityservice.service;

import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.User;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.servlet.support.WebContentGenerator;

import java.text.ParseException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    private UserCreationRequest request;
    private UserResponse userResponse;
    private LocalDate dob;
    private User user;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private WebContentGenerator webContentGenerator;

    @BeforeEach
        // chạy trước test
    void initData(){

        dob = LocalDate.of(1990,1,1);

        request = UserCreationRequest.builder()
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .password("12345678")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("6d22364f56ee")
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .dob(dob)
                .build();

        user  = User.builder()
                .id("6d22364f56ee")
                .username("john")
                .firstName("John")
                .lastName("Doe")
                .password("12345678")
                .dob(dob)
                .build();

    }

    @Test
    void createUser_validRequest_success()  {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(user);

        // WHEN
        var response = userService.createUser(request);
        // THEN

        Assertions.assertThat(response.getId()).isEqualTo("6d22364f56ee");
        Assertions.assertThat(response.getUsername()).isEqualTo("john");

    }

    @Test
    void createUser_userExisted_fail() {
        // GIVEN
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        // WHEN
        var exception = assertThrows(AppException.class, () -> userService.createUser(request));

        // THEN
        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1002);
    }

//    @Test
//    @WithMockUser(username = "john")
//
//    void getMyInfo_valid_success() {
//        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
//
//        var response = userService.getMyInfo();
//
//        Assertions.assertThat(response.getUsername()).isEqualTo("john");
//        Assertions.assertThat(response.getId()).isEqualTo("6d22364f56ee");
//    }
//
//    @Test
//    @WithMockUser(username = "john")
//    void getMyInfo_userNotFound_error() {
//        when(userRepository.findByUsername(anyString())).thenReturn(Optional.ofNullable(null));
//
//        // WHEN
//        var exception = assertThrows(AppException.class, () -> userService.getMyInfo());
//
//        Assertions.assertThat(exception.getErrorCode().getCode()).isEqualTo(1005);
//    }


}
