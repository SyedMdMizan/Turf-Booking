package com.technologies.application.repository;

import com.technologies.application.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

    List<Otp> findByMobileNumberAndExpiryTimeAfter(String mobileNumber, LocalDateTime localDateTime);
}
