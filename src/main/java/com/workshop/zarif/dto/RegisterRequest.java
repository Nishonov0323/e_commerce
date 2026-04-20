package com.workshop.zarif.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Ism kiritilishi shart")
    private String firstname;

    @NotBlank(message = "Familiya kiritilishi shart")
    private String lastname;

    @NotBlank(message = "Email kiritilishi shart")
    @Email(message = "Email formati noto'g'ri")
    private String email;

    @NotBlank(message = "Parol kiritilishi shart")
    private String password;
}

