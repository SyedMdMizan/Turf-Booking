package com.technologies.application.service;

import com.technologies.application.dto.SendOtpResponseDto;
import com.technologies.application.entity.Otp;
import com.technologies.application.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private OtpRepository otpRepository;

    public SendOtpResponseDto generateOtp(String mobileNumber) {
        SendOtpResponseDto response = new SendOtpResponseDto();
        List<Otp> recentOtp = otpRepository.findByMobileNumberAndExpiryTimeAfter(mobileNumber, LocalDateTime.now().minusMinutes(10));
        if(recentOtp.size()>3){
            response.setMessage("Rate limit exceeded. Please try again after some time.");
            response.setSuccess(false);
            return response;
        }
        String generatedOtp = this.generateOtp();
        boolean sent = this.sendOtp(mobileNumber, generatedOtp);
        if(sent){
            Otp otp = new Otp();
            otp.setVerified(false);
            otp.setOtp(generatedOtp);
            otp.setMobileNumber(mobileNumber);
            otp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
            otp.setRateLimit(1);
            otp.setAttemptCount(0);

            otpRepository.save(otp);
            response.setMessage("OTP sent successfully");
            response.setSuccess(true);
            response.setOtp(generatedOtp);

        }
        else{
            response.setMessage("Failed to send OTP");
            response.setOtp(generatedOtp);
            response.setSuccess(false);
        }
        return response;
    }

    private String generateOtp(){
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }

    private boolean sendOtp(String mobileNumber, String otp){

        return true;
    }

//    public Map<String, String> verifyOtp(Map<String, String> payload) {
//
//    }
}
