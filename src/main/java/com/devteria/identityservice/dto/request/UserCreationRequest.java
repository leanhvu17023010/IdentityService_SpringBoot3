package com.devteria.identityservice.dto.request;

import com.devteria.identityservice.validator.DobConstraint;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 3, message = "USERNAME_INVALID")
    String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    String password;
    String firstName;
    String lastName;

    // 1 số annotation khác: @Email, @NotNull, @NotBlack, @NotEmpty

    @DobConstraint(min = 16, message = "INVALID_DOB" )
    LocalDate dob;

}