package com.technologies.application.controller;

import com.technologies.application.dto.SendOtpResponseDto;
import com.technologies.application.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/sendOTP")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> payload){
        SendOtpResponseDto response = authService.generateOtp(payload.get("mobile"));
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response);
        }
    }

//    @PostMapping("verifyOTP")
//    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> payload){
//        Map<String, String> response = authService.verifyOtp(payload);
////        return ResponseEntity<?>(response);
//    }
}
