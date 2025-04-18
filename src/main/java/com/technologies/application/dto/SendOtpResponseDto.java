package com.technologies.application.dto;

import lombok.Data;

@Data
public class SendOtpResponseDto {
    private String message;
    private boolean success;

//    For testing purpose only need to remove afterwards
    private String otp;
}
