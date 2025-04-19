package com.technologies.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VerifyOtpResponseDto {

    private String token;
    private boolean success;
    private String message;

}
